package biz.grundner.spring.web.component;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MvcComponent {

    @AliasFor("template")
    String value() default "";

    @AliasFor("value")
    String template() default "";
}
