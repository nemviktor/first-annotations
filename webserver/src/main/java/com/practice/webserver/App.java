package com.practice.webserver;

import com.practice.webserver.Server.Server;

public class App {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();
    }


}
