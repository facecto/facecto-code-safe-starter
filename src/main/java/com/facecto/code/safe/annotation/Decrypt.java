package com.facecto.code.safe.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation Decrypt
 *
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.0 (2021/8/08)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface Decrypt {
}

