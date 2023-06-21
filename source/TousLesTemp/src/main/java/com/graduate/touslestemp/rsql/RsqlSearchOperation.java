package com.graduate.touslestemp.rsql;

import com.graduate.touslestemp.exception.RequestException;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

import java.util.Arrays;

/**
 * @File: RsqlSearchOperation.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:25 AM
 * @Update: 21/6/2023
 */
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

    /**
     * Retrieves the RsqlSearchOperation enum value based on the provided ComparisonOperator.
     *
     * @param operator the ComparisonOperator to match
     * @return the corresponding RsqlSearchOperation value
     * @throws RequestException if no matching RsqlSearchOperation is found
     */
    public static RsqlSearchOperation getSimpleOperator(final ComparisonOperator operator) {
        return Arrays.stream(values())
                .filter(operation -> operation.getOperator() == operator)
                .findAny().orElseThrow(() -> new RequestException("No data!"));
    }
}