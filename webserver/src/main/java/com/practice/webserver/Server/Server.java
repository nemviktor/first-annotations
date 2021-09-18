package com.practice.webserver.Server;

import com.practice.webserver.CustomAnnotation.WebRoute;
import com.practice.webserver.HTTPHandler.MyHandler;
import com.practice.webserver.HTTPHandler.Routes;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

public class Server {

    public void start() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        creatCustomContext(server);
        server.setExecutor(null);
        server.start();
    }

    private void creatCustomContext(HttpServer server) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class classRoutes = Class.forName("com.practice.webserver.HTTPHandler.Routes");
//        Object objRoutes = classRoutes.newInstance();

        for(Method m: classRoutes.getMethods()) {
            if(m.isAnnotationPresent(WebRoute.class)) {
                String path = m.getAnnotation(WebRoute.class).path();
                server.createContext(path, new MyHandler());
            }
        }
    }
}
