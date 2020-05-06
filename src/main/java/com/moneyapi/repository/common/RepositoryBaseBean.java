package com.moneyapi.repository.common;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class RepositoryBaseBean {

    @PersistenceContext
    protected EntityManager entityManager;

    protected <U> JPAQuery<U> select(Expression<U> expr) {
        return new JPAQuery<>(entityManager).select(expr);
    }

    protected JPAQuery<Tuple> select(Expression<?>... exprs) {
        return new JPAQuery<>(entityManager).select(exprs);
    }

    protected JPAUpdateClause update(EntityPath entityPath) {
        return new JPAUpdateClause(entityManager, entityPath);
    }

    protected JPADeleteClause delete(EntityPath entityPath) {
        return new JPADeleteClause(entityManager, entityPath);
    }

    protected <T extends Comparable> OrderSpecifier<T> getSortedColumn(Path<?> entityPathBase, String fieldName, DirectionType orderDirectionType) {
        Path<Object> filePath = Expressions.path(Object.class, entityPathBase, fieldName);
        Order order = orderDirectionType == DirectionType.ASC ? Order.ASC : Order.DESC;

        return new OrderSpecifier(order, filePath);
    }

}
