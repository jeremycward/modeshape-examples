package com.giga.warehouse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


public class JettyServer {
    private final Server server ;

    public JettyServer(int port) {
        this.server = new Server(port);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/modeshape-rest-example");

        webapp.setWar("./target/modeshape-rest-example.war");
        server.setHandler(webapp);


    }

    public void start() throws Exception{
        server.start();
    }
    public void startAndBlock() throws Exception{
        server.start();
        server.join();
    }


    public static void main(String[] args)throws Exception {
        JettyServer jettyServer = new JettyServer(8181);
        jettyServer.startAndBlock();

    }


}
