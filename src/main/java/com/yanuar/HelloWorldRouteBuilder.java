package com.yanuar;

import com.yanuar.service.HelloWorld;
import com.yanuar.service.HelloWorldImpl;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by yanuar on 1/24/16.
 */
public class HelloWorldRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:helloWorld")
                .log("greeting...")
                .to("bean:helloWorldService");
    }
}
