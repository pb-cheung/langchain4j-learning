package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.Assistant;
import com.atguigu.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private Assistant assistant;

    @Autowired
    private QwenChatModel qwenChatModel;

    @Value("${PROXY_HOST:localhost}")
    private String proxyHost;

    @Value("${PROXY_PORT:8899}")
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
    public void testChatMemory() {
        String answer1 = assistant.chat("我是pb");
        System.out.println(answer1);

        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);
    }

    @Test
    /**
     * 聊天记忆的简单实现
     */
    public void testChatMemory2() {
        // 第一轮
        UserMessage userMessage1 = UserMessage.userMessage("我是pb");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        // 输出大模型的回复
        System.out.println(aiMessage1.text());

        // 第二轮
        UserMessage userMessage2 = UserMessage.userMessage("你知道我是谁吗？");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage1, userMessage2)); // 把上一轮的信息一并发送给大模型
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        // 输出大模型的回复
        System.out.println(aiMessage2.text());
    }

    @Test
    public void testChatMemory3() {
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();

        String answer1 = assistant.chat("我的名字是pb");
        System.out.println(answer1);

        String answer2 = assistant.chat("你知道我是谁吗？");
        System.out.println(answer2);
    }

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    public void testChatMemory4() {
        String answer1 = memoryChatAssistant.chat("我的名字是pb");
        System.out.println(answer1);

        String answer2 = memoryChatAssistant.chat("你知道我是谁吗？");
        System.out.println(answer2);
    }

    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testChatMemory5() {
        // setProxyIfEnabled();

        String answer1 = separateChatAssistant.chat(1,"我是刘皇叔");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat(1,"你知道我是谁吗？");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat(3,"你知道我是谁吗？");
        System.out.println(answer3);
    }
}
