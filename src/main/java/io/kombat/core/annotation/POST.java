package io.kombat.core.annotation;

import java.lang.annotation.*;

/**
 * Created by ac-bsilva on 18/11/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface POST {

    String value() default "";

    String consumes() default "application/x-www-form-urlencoded";

    String produces() default "text/html";
}
