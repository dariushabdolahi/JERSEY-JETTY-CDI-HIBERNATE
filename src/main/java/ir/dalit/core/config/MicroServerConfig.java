package ir.dalit.core.config;

import ir.dalit.core.annotations.MicroServiceRunner;
import ir.dalit.core.annotations.RestConfiguration;
import ir.dalit.core.exceptions.ServeCouldNotStart;
import org.eclipse.jetty.cdi.CdiDecoratingListener;
import org.eclipse.jetty.cdi.CdiServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.Map;
import java.util.Objects;

public class MicroServerConfig {

    private static final MicroServerConfig MICRO_SERVER_CONFIG = new MicroServerConfig();

    static {
        org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger();
        org.slf4j.bridge.SLF4JBridgeHandler.install();
    }

    private MicroServerConfig() {
    }

    public static MicroServerConfig getInstance() {
        return MICRO_SERVER_CONFIG;
    }

    private void runServer(int port, String prefix, String basePackage, int maxThread, int minThread, int idleTimeout) throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThread, minThread, idleTimeout);
        Server server = craeteServer(port, setPrefix(prefix), basePackage, threadPool);
        server.start();
        server.join();
    }

    private String setPrefix(String prefix) {
        if (Objects.isNull(prefix)) {
            return "api";
        }
        return prefix;
    }

    public Server craeteServer(int port, String prefix, String basePackage, QueuedThreadPool threadPool) {
        Server server = new Server(threadPool);
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(port);
        server.addConnector(serverConnector);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        setupWeld(context);
        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/" + prefix + "/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("jersey.config.server.provider.packages", basePackage);
        server.setHandler(context);
        return server;
    }

    private void setupWeld(ServletContextHandler context) {
        context.setInitParameter(CdiServletContainerInitializer.CDI_INTEGRATION_ATTRIBUTE, CdiDecoratingListener.MODE);
        context.addServletContainerInitializer(new CdiServletContainerInitializer());
        context.addServletContainerInitializer(new org.jboss.weld.environment.servlet.EnhancedListener());
    }

    public void executeServer(Map<Integer, Object> configMap) {
        try {
            MicroServiceRunner microServerConfig = (MicroServiceRunner) configMap.get(Config.MICRO_SERVICE_RUNNER_ANNOTATION);
            RestConfiguration restConfiguration = (RestConfiguration) configMap.get(Config.REST_CONFIGURATION_RUNNER_ANNOTATION);
            runServer(microServerConfig.port(),
                    restConfiguration.prefix().replace("/","").trim(),
                    microServerConfig.basePackage(),
                    restConfiguration.maxThread(),
                    restConfiguration.minThread(),
                    restConfiguration.idleTimeout());
        } catch (Exception e) {
            throw new ServeCouldNotStart();
        }
    }
}
