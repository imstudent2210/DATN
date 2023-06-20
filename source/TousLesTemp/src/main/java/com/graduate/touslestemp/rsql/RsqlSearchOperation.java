package com.graduate.touslestemp.rsql;

import com.graduate.touslestemp.exception.RequestException;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

import java.util.Arrays;
/*
* @File:  RsqlSearchOperation.java com.graduate.touslestemp.rsql
*
* @Author: TamNLT
* @Since: 20/6/2023 11:28 PM
* @Last update: 20/6/2023
*
* */
public enum RsqlSearchOperation {
    EQUAL(RSQLOperators.EQUAL),
    NOT_EQUAL(RSQLOperators.NOT_EQUAL),
    GREATER_THAN(RSQLOperators.GREATER_THAN),
    GREATER_THAN_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL),
    LESS_THAN(RSQLOperators.LESS_THAN),
    LESS_THAN_OR_EQUAL(RSQLOperators.LESS_THAN_OR_EQUAL),
    IN(RSQLOperators.IN),
    NOT_IN(RSQLOperators.NOT_IN);

    private ComparisonOperator operator;

    public ComparisonOperator getOperator() {
        return operator;
    }

    private RsqlSearchOperation(ComparisonOperator operator) {
        this.operator = operator;
    }


    public static RsqlSearchOperation getSimpleOperator(final ComparisonOperator operator) {
        return Arrays.stream(values())
                .filter(operation -> operation.getOperator() == operator)
                .findAny().orElseThrow(() -> new RequestException("No data!"));
    }
    //    public static RsqlSearchOperation getSimpleOperator(ComparisonOperator operator) {
//        for (RsqlSearchOperation operation : values()) {
//            if (operation.getOperator() == operator) {
//                return operation;
//            }
//        }
//        return null;
//    }

}