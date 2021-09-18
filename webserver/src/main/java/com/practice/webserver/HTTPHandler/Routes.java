package com.practice.webserver.HTTPHandler;

import com.practice.webserver.CustomAnnotation.WebRoute;

public class Routes {

    @WebRoute("/test1")
    public String test1() {
        return "This is a msg from test1.";
    }

    @WebRoute("/test2")
    public String test2() {
        return "This is a msg from test2.";
    }
}
