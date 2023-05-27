package ir.dalit;

import ir.dalit.core.config.MicroService;
import ir.dalit.core.annotations.MicroServiceRunner;
import ir.dalit.core.annotations.RestConfiguration;

@MicroServiceRunner(basePackage = "ir.dalit" ,port = 9090)
@RestConfiguration(prefix = "/api")
public class Application {
    public static void main(String[] args) {
        MicroService.run(Application.class);
    }
}
