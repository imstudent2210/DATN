package com.graduate.touslestemp.config.authenticate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRespone {
    String token;
//    String name;
    Account account;
}
