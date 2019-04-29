package com.example.phoebe.youtiao.socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.ServletContext;

@Configuration
public class WebSocketConfig {

//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
//
//
//    @Bean
//    public ServerEndpointExporter endpointExporter() {
//        return new ServerEndpointExporter();
//    }
//
//    @Bean
//    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
//        return new ServletContextAware() {
//
//            @Override
//            public void setServletContext(ServletContext servletContext) {
//                ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
//                serverEndpointExporter.setApplicationContext(applicationContext);
//                try {
//                    serverEndpointExporter.afterPropertiesSet();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };
//    }
}

