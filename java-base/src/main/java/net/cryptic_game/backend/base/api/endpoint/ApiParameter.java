package net.cryptic_game.backend.base.api.endpoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ApiParameter {

    String value();

    ApiParameterSpecialType special() default ApiParameterSpecialType.NORMAL;

    String description() default "";

    boolean optional() default false;
}
