package ir.dalit.core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MicroServiceRunner {
    String basePackage();
    int port() default 8080;

}
