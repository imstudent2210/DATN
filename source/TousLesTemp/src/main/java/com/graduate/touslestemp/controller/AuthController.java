package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.exception.AdminAlreadyExistsException;
import com.graduate.touslestemp.exception.NotFoundAdminException;
import com.graduate.touslestemp.model.Admin;
import com.graduate.touslestemp.model.JwtRequest;
import com.graduate.touslestemp.model.JwtRespone;
import com.graduate.touslestemp.config.JwtAuthencationFilter;
import com.graduate.touslestemp.config.JwtAuthenticationEntryPoint;
import com.graduate.touslestemp.config.JwtUtil;
import com.graduate.touslestemp.service.impl.AdminDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AdminDetailServiceImpl adminDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticaticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new NotFoundAdminException(jwtRequest.getUsername());
        }

        UserDetails userDetails = this.adminDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtRespone(token,(Admin) userDetails));
    }
    private void authenticaticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (AdminAlreadyExistsException e){
            throw new Exception("User disabled " +e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials "+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public Admin getCurrentuser(Principal principal ){
        return ((Admin)this.adminDetailService.loadUserByUsername(principal.getName()));
    }
}
