package ir.dalit.core.config;

import ir.dalit.core.annotations.MicroServiceRunner;
import ir.dalit.core.annotations.RestConfiguration;
import ir.dalit.core.exceptions.MicroServiceRunnerClassNotFound;
import ir.dalit.core.exceptions.RestConfigurationAnnotationNotFound;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MicroService {

    private static final Map<Integer, Object> CONFIG_MAP = new HashMap<>();

    private MicroService() {
    }

    public static void run(Class<?> runnerClass) {
        MicroServiceRunner microServiceRunnerAnnotation = runnerClass.getDeclaredAnnotation(MicroServiceRunner.class);
        RestConfiguration restConfigurationAnnotation = runnerClass.getDeclaredAnnotation(RestConfiguration.class);
        addMicroServiceRunnerAnnotation(microServiceRunnerAnnotation);
        addRestConfigurationAnnotation(restConfigurationAnnotation);
        MicroServerConfig.getInstance().executeServer(CONFIG_MAP);
    }

    private static void addMicroServiceRunnerAnnotation(Annotation annotation) {
        if (Objects.isNull(annotation)) {
            throw new MicroServiceRunnerClassNotFound();
        }
        CONFIG_MAP.put(Config.MICRO_SERVICE_RUNNER_ANNOTATION, annotation);
    }

    private static void addRestConfigurationAnnotation(Annotation annotation) {
        if (Objects.isNull(annotation)) {
            throw new RestConfigurationAnnotationNotFound();
        }
        CONFIG_MAP.put(Config.REST_CONFIGURATION_RUNNER_ANNOTATION, annotation);
    }

}
