package com.graduate.touslestemp.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @File: GenericRsqlSpecBuilder.java
 * @Author: TamNLT
 * @Since: 21/6/2023 9:25 AM
 * @Update: 21/6/2023
 */
public class GenericRsqlSpecBuilder<T> {
    /**
     * Creates a Specification based on the given Node.
     *
     * @param node the Node to create the Specification from
     * @return the created Specification
     */
    public Specification<T> createSpecification(Node node) {
        if (node instanceof LogicalNode) {
            return createSpecification((LogicalNode) node);
        }
        if (node instanceof ComparisonNode) {
            return createSpecification((ComparisonNode) node);
        }
        return null;
    }

    /**
     * Creates a Specification based on the given LogicalNode.
     *
     * @param logicalNode the LogicalNode to create the Specification from
     * @return the created Specification
     */
    public Specification<T> createSpecification(LogicalNode logicalNode) {
        List<Specification> specs = logicalNode.getChildren()
                .stream()
                .map(node -> createSpecification(node))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Specification<T> result = specs.get(0);
        if (logicalNode.getOperator() == LogicalOperator.AND) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specification.where(result).and(specs.get(i));
            }
        } else if (logicalNode.getOperator() == LogicalOperator.OR) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specification.where(result).or(specs.get(i));
            }
        }

        return result;
    }

    /**
     * Creates a Specification based on the given ComparisonNode.
     *
     * @param comparisonNode the ComparisonNode to create the Specification from
     * @return the created Specification
     */
    public Specification<T> createSpecification(ComparisonNode comparisonNode) {
        Specification<T> result = Specification.where(
                new GenericRsqlSpecification<T>(
                        comparisonNode.getSelector(),
                        comparisonNode.getOperator(),
                        comparisonNode.getArguments()
                )
        );
        return result;
    }

}
