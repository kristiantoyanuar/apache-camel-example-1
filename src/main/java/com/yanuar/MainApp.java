package com.yanuar;

import com.yanuar.service.HelloWorld;
import com.yanuar.service.HelloWorldImpl;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.camel.component.bean.BeanComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.main.Main;


/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        JndiRegistry jndiRegistry = new JndiRegistry(true);
        jndiRegistry.bind("helloWorldService", new HelloWorldImpl());

        CamelContext camelContext = new DefaultCamelContext(jndiRegistry);
        camelContext.addRoutes(new HelloWorldRouteBuilder());

        camelContext.start();

        HelloWorld helloWorld = new ProxyBuilder(camelContext).endpoint("direct:helloWorld").build(HelloWorld.class);
        String result = helloWorld.greeting();
        System.out.println(result);

        camelContext.stop();
    }

}

