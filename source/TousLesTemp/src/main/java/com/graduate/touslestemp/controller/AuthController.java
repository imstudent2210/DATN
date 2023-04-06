package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.config.authenticate.JwtUtil;
import com.graduate.touslestemp.domain.dto.EmailMessage;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.config.authenticate.Account;
import com.graduate.touslestemp.config.authenticate.JwtRequest;
import com.graduate.touslestemp.config.authenticate.JwtRespone;
import com.graduate.touslestemp.service.EmailSendersService;
import com.graduate.touslestemp.service.impl.AccountDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AccountDetailServiceImpl adminDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody @Valid JwtRequest jwtRequest) throws Exception{
        try {
            authenticaticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new RequestException("Not found this account: "+ jwtRequest.getUsername());
        }

        UserDetails userDetails = this.adminDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtRespone(token,(Account) userDetails));
    }
    private void authenticaticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (RequestException e){
            throw new RequestException("User disable");
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials "+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public Account getCurrentUser(Principal principal ){
        return ((Account)this.adminDetailService.loadUserByUsername(principal.getName()));
    }

    @Autowired
    private EmailSendersService emailSendersService;

    @PostMapping("/sendmail")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        this.emailSendersService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok("Success");
    }
}
