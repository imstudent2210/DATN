package com.graduate.touslestemp.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class ForeignKeyConstraintException extends DataIntegrityViolationException {
    public ForeignKeyConstraintException(String msg) {
        super(msg);
    }
}
