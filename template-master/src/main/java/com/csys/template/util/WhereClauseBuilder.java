package com.csys.template.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import org.springframework.lang.Nullable;

import java.util.function.Function;


public class WhereClauseBuilder implements Predicate, Cloneable {

    private final BooleanBuilder delegate;

    public WhereClauseBuilder() {
        this.delegate = new BooleanBuilder();
    }

    public WhereClauseBuilder(Predicate pPredicate) {
        this.delegate = new BooleanBuilder(pPredicate);
    }

    public WhereClauseBuilder and(Predicate right) {
        return new WhereClauseBuilder(delegate.and(right));
    }

    public <V> WhereClauseBuilder optionalAnd(@Nullable V pValue, LazyBooleanExpression pBooleanExpression) {
        return applyIfNotNull(pValue, this::and, pBooleanExpression);
    }

    private <V> WhereClauseBuilder applyIfNotNull(@Nullable V pValue, Function<Predicate, WhereClauseBuilder> pFunction, LazyBooleanExpression pBooleanExpression) {
        if (pValue != null) {
            return new WhereClauseBuilder(pFunction.apply(pBooleanExpression.get()));
        }

        return this;
    }

    public <V> WhereClauseBuilder booleanAnd(@Nullable Boolean pValue, LazyBooleanExpression pBooleanExpression) {
        return applyIfTrue(pValue, this::and, pBooleanExpression);
    }

    private <V> WhereClauseBuilder applyIfTrue(@Nullable Boolean pValue, Function<Predicate, WhereClauseBuilder> pFunction, LazyBooleanExpression pBooleanExpression) {
        if (pValue) {
            return new WhereClauseBuilder(pFunction.apply(pBooleanExpression.get()));
        }

        return this;
    }

    @Override
    public Predicate not() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <R, C> R accept(Visitor<R, C> v, C context) {
        if (delegate != null) {
            return delegate.accept(v, context);
        } else {
            return null;
        }
    }

    @Override
    public Class<? extends Boolean> getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
