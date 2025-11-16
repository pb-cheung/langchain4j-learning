package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {

    @Autowired
    SeparateChatAssistant separateChatAssistant;

    @Autowired
    MemoryChatAssistant memoryChatAssistant;

    @Test
    public void testSystemMessage() {
        String answer = separateChatAssistant.chat(3, "我是谁？");
        System.out.println(answer);
    }

    @Test
    public void testSystemMessage2() {
        String answer = separateChatAssistant.chat(4, "今天是几号？");
        System.out.println(answer);
    }

    @Test
    public void testUserMessage() {
        String answer1 = memoryChatAssistant.chat("我是刘皇叔");
        System.out.println(answer1);

        String answer2 = memoryChatAssistant.chat("我今年40了。");
        System.out.println(answer2);

        String answer3 = memoryChatAssistant.chat("你知道我是谁吗？多大年纪了？");
        System.out.println(answer3);
    }

    @Test
    public void testV() {
        String answer1 = separateChatAssistant.chat2(10, "我是环环");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat2(10, "我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserInfo() {
        // 从数据库获取数据
        String userName = "翠花";
        int age = 18;

        String answer = separateChatAssistant.chat3(20, "我是谁，我多大了", userName, age);
        System.out.println(answer);


    }
}
