package yarden.cerebri.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * This class manages and contains all of the setup for the embedded Jetty HTTP web server.
 */
public class WebServer {

    private Server server;

    private static final String INDEX = "/";
    private static final String INDEX_HTML = "index.html";
    private static final String RESOURCES_LOCATION = "./src/main/resources";

    /**
     * Creates the server and registers the connector and handlers.
     *
     * @param port port the web server will listen on
     */
    public WebServer(int port) {
        server = new Server();
        registerConnector(port);
        registerHandlers();
    }

    /**
     * Starts the web service
     *
     * @throws Exception
     */
    public void start() throws Exception {
        server.start();
    }

    /**
     * @param port port the webserver will listen on
     */
    private void registerConnector(int port) {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[]{connector});
    }

    /**
     * Binds the available handlers to the server
     */
    private void registerHandlers() {
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{initResourceHandler(), initServletHandler()});
        server.setHandler(handlers);
    }

    /**
     * @return servlet handler to serve the POST request
     */
    private ServletHandler initServletHandler() {
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(PostServlet.class, INDEX);
        return servletHandler;
    }

    /**
     * @return resource handler to serve the welcome page.
     */
    private ResourceHandler initResourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{INDEX_HTML});
        resourceHandler.setResourceBase(RESOURCES_LOCATION);
        return resourceHandler;
    }

    /**
     * Shutdown the web server
     *
     * @throws Exception
     */
    public void stop() throws Exception {
        server.stop();
    }
}
