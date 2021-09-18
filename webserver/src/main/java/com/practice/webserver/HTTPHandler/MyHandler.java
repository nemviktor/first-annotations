package com.practice.webserver.HTTPHandler;

import com.practice.webserver.HttpMEthods;
import com.practice.webserver.Server.Server;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyHandler implements HttpHandler {

    Server server;

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {

        Method method = server.getRoutes().get(t.getHttpContext().getPath());
        String response = null;
        try {
            response = (String) method.invoke(new Routes());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
