package com.graduate.touslestemp.validator;


import com.graduate.touslestemp.domain.dto.SignUpRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @File: PasswordMatchesValidator.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:52 AM
 * @Update: 21/6/2023
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpRequest> {

    @Override
    public boolean isValid(final SignUpRequest user, final ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
