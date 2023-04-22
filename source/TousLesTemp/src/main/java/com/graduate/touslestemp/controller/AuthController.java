package com.graduate.touslestemp.controller;

import com.graduate.touslestemp.config.CurrentUser;
import com.graduate.touslestemp.domain.dto.*;
import com.graduate.touslestemp.domain.entity.User;
import com.graduate.touslestemp.exception.UserAlreadyExistAuthenticationException;
import com.graduate.touslestemp.security.jwt.TokenProvider;
import com.graduate.touslestemp.service.UserService;
import com.graduate.touslestemp.util.GeneralUtils;
import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.samstevens.totp.code.HashingAlgorithm.SHA256;
import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Slf4j
@RestController
@Service
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    private QrDataFactory qrDataFactory = new QrDataFactory(SHA256, 6, 30);

    private QrGenerator qrGenerator = new ZxingPngQrGenerator();

    private TimeProvider timeProvider = new SystemTimeProvider();

    private CodeGenerator codeGenerator = new DefaultCodeGenerator();

    private CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        LocalUser localUser = (LocalUser) authentication.getPrincipal();
        boolean authenticated = !localUser.getUser().isUsing2FA();
        String jwt = tokenProvider.createToken(localUser, authenticated);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, authenticated, authenticated ? GeneralUtils.buildUserInfo(localUser) : null));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            User user = userService.registerNewAdmin(signUpRequest);
            if (signUpRequest.isUsing2FA()) {
                QrData data = qrDataFactory.newBuilder()
                        .label(user.getEmail())
                        .secret(user.getSecret())
                        .issuer("TamNLT2").build();
                // Generate the QR code image data as a base64 string which can be used in an <img> tag
                String qrCodeImage = getDataUriForImage(qrGenerator.generate(data), qrGenerator.getImageMimeType());
                return ResponseEntity.ok().body(new SignUpResponse(true, qrCodeImage));
            }
        } catch (UserAlreadyExistAuthenticationException e) {
            log.error("Exception Ocurred", e);
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        } catch (QrGenerationException e) {
            log.error("QR Generation Exception Ocurred", e);
            return new ResponseEntity<>(new ApiResponse(false, "Unable to generate QR code!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/verify")
    @PreAuthorize("hasRole('PRE_VERIFICATION_USER')")
    public ResponseEntity<?> verifyCode(@NotEmpty @RequestBody String code, @CurrentUser LocalUser user) {
        if (!verifier.isValidCode(user.getUser().getSecret(), code)) {
            return new ResponseEntity<>(new ApiResponse(false, "Invalid Code!"), HttpStatus.BAD_REQUEST);
        }
        String jwt = tokenProvider.createToken(user, true);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, true, GeneralUtils.buildUserInfo(user)));
    }
}