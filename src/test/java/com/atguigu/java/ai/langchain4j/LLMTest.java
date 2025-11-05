package com.atguigu.java.ai.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LLMTest {
    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Autowired
    private OllamaChatModel ollamaChatModel;

    @Autowired
    private QwenChatModel qwenChatModel;

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
    public void testGPTDemo() {
        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("http://langchain4j.dev/demo/openai/v1")
            .apiKey("demo")
            .modelName("gpt-4o-mini")
            .build();

        String answer = model.chat("你好，你是谁？ ");
        System.out.println(answer);
    }

    @Test
    public void testOpenAIDemo() {
        setProxyIfEnabled();
        String answer = openAiChatModel.chat("你是谁？");
        System.out.println(answer);
    }

    @Test
    public void testOllamaChatModel() {
        String answer = ollamaChatModel.chat("你是谁");
        System.out.println(answer);
    }

    @Test
    public void testQwenChatModel() {
        setProxyIfEnabled();
        String answer = qwenChatModel.chat("你是谁？");
        System.out.println(answer);
    }
}
