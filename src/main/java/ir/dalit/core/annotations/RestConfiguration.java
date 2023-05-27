package ir.dalit.core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RestConfiguration {
    String prefix() default "api";
    int maxThread() default 10;
    int minThread() default 5;
    int idleTimeout() default 1000;
}
