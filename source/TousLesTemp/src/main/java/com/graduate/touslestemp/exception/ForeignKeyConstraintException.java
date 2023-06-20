package com.graduate.touslestemp.exception;

import org.springframework.dao.DataIntegrityViolationException;
/*
* @File:  ForeignKeyConstraintException.java com.graduate.touslestemp.exception
*
* @Author: TamNLT
* @Since: 20/6/2023 11:26 PM
* @Last update: 20/6/2023
*
* */
public class ForeignKeyConstraintException extends DataIntegrityViolationException {
    public ForeignKeyConstraintException(String msg) {
        super(msg);
    }
}
