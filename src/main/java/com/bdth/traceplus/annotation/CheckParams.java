package com.bdth.traceplus.annotation;

import java.lang.annotation.*;

/**
 * @author weiming.zhu
 * @date 2019/6/15 15:03
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckParams {
    String type() default "";
    String value() default "";
}
