package com.kirylkhrystsenka.schoolapp.apphelper.sqlhelper;
@FunctionalInterface
public interface UnsafeFunction<T,K> {
    K apply(T arg) throws Exception;
}
