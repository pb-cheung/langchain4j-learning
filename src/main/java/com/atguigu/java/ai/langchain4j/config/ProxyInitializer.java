package com.atguigu.java.ai.langchain4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProxyInitializer {

    @Autowired
    private ProxyConfig proxyConfig;

    @EventListener
    public void initProxy(ApplicationReadyEvent event) {
        if (proxyConfig.isEnabled()) {
            System.setProperty("https.proxyHost", proxyConfig.getHost());
            System.setProperty("https.proxyPort", String.valueOf(proxyConfig.getPort()));
            System.setProperty("http.proxyHost", proxyConfig.getHost());
            System.setProperty("http.proxyPort", String.valueOf(proxyConfig.getPort()));
            System.out.println("Proxy initialized: " + proxyConfig.getHost() + ":" + proxyConfig.getPort());
        }
    }
}