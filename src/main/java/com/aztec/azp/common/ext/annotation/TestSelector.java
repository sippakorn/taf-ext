package com.aztec.azp.common.ext.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestSelector {
    int startIndex() default -1;

    int endIndex() default -1;

    String[] testIds() default {};

    double randomPercentExecute() default 100d;
}
