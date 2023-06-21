package com.graduate.touslestemp.exception;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * @File: ForeignKeyConstraintException.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:24 AM
 * @Update: 21/6/2023
 */
public class ForeignKeyConstraintException extends DataIntegrityViolationException {
    public ForeignKeyConstraintException(String msg) {
        super(msg);
    }
}
