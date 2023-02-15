/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.template.util;

import com.querydsl.core.types.dsl.BooleanExpression;

  @FunctionalInterface
    public interface LazyBooleanExpression
    {
        BooleanExpression get();
    }