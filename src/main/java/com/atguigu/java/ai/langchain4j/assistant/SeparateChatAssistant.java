package com.atguigu.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(
    wiringMode = EXPLICIT,
    chatModel = "qwenChatModel",
    chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {

    @SystemMessage("你是我的好朋友，请用东北话回答问题。今天是{{current_date}}")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
