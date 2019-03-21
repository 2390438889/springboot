package com.learn.springboot.base;

/**
 * @author Hearts
 * @date 2019/3/20
 * @desc
 */
@FunctionalInterface
public interface BGFunction<T, U, V, R> {

    R getValue(T t, U u, V v);
}
