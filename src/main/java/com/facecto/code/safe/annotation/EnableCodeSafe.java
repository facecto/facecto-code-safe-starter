package com.facecto.code.safe.annotation;

import com.facecto.code.safe.config.SafeAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Annotation EnableCodeSafe
 * @author Jon So, https://cto.pub, https://facecto.com, https://github.com/facecto
 * @version v1.1.1 (2022/01/01)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(SafeAutoConfiguration.class)
@Documented
@Inherited
public @interface EnableCodeSafe {
}
