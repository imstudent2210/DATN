package com.graduate.touslestemp.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;
/*
* @File:  CustomRsqlVisitor.java com.graduate.touslestemp.rsql
*
* @Author: TamNLT
* @Since: 20/6/2023 11:28 PM
* @Last update: 20/6/2023
*
* */
public class CustomRsqlVisitor<T> implements RSQLVisitor<Specification<T>,Void> {
    private GenericRsqlSpecBuilder<T> builder;

    public CustomRsqlVisitor() {
        builder = new GenericRsqlSpecBuilder<T>();
    }

    @Override
    public Specification<T> visit(AndNode andNode, Void unused) {
        return builder.createSpecification(andNode);
    }

    @Override
    public Specification<T> visit(OrNode orNode, Void unused) {
        return builder.createSpecification(orNode);
    }

    @Override
    public Specification<T> visit(ComparisonNode comparisonNode, Void unused) {
        return builder.createSpecification(comparisonNode);
    }
}
