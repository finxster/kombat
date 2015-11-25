package io.kombat.core.annotation;

import java.lang.annotation.*;

/**
 * Created by ac-bsilva on 18/11/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface GET {

    String value() default "";

    String produces() default "text/html";

}
