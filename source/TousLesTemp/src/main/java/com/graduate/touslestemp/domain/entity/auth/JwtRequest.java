package com.graduate.touslestemp.domain.entity.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    @NotEmpty(message = "Enter username !")
    String username;
    @NotEmpty(message = "Enter password !")
    String password;
}
