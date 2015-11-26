package io.kombat.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PUT {

    String value() default "";

    String consumes() default "application/x-www-form-urlencoded";

    String produces() default "text/html";
}
