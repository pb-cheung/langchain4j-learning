package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToolsTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Value("${PROXY_HOST}")
    private String proxyHost;

    @Value("${PROXY_PORT}")
    private String proxyPort;

    @Value("${ENABLE_PROXY:false}")
    private boolean enableProxy;

    // 设置代理
    private void setProxyIfEnabled() {
        if (enableProxy) {
            System.setProperty("https.proxyHost", proxyHost);
            System.setProperty("https.proxyPort", proxyPort);
            System.out.println("代理已启用，代理地址：https://" + proxyHost + ":" + proxyPort);
        } else {
            System.out.println("代理未启用");
        }
    }

    @Test
    public void testCalculatorTools() {
        setProxyIfEnabled();

        String answer = separateChatAssistant.chat(3, "1+2等于几，475695037565的平方根式多少？");
        // 答案：3， 689706.4865
        System.out.println("answer = " + answer);
    }
}
