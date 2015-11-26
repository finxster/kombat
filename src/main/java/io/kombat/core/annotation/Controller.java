package io.kombat.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Controller {

    String value() default "/";

    String produces() default "text/html";

}
