package yarden.cerebri;

import yarden.cerebri.server.WebServer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class serves as the entry point for the entire application.
 */
public class Main {

    private static final int DEFAULT_PORT = 8080;

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Instantiates and starts the web server.
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        WebServer server = new WebServer(parsePort(args));
        try {
            server.start();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * @param args program arguments
     * @return user specified port if present and readable, otherwise default port is used.
     */
    private static int parsePort(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "No port defined in input arguments. Using default port value: " + DEFAULT_PORT);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Unable to parse port. Using default port value: " + DEFAULT_PORT);
        }
        return DEFAULT_PORT;
    }
}
