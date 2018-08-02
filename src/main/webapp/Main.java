package main.webapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;


public class Main {
    public static void main(String[] args) throws Exception {
    	
    	ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(9999);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        //jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "rest");
        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "rest");
        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_CLASSNAMES, MultiPartFeature.class.getCanonicalName());
        
        //package de teste
        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "test");
        
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
