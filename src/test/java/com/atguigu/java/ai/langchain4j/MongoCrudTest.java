package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MongoCrudTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入文档
     */
//    @Test
//    public void testInsert() {
//        mongoTemplate.insert(new ChatMessages(1L, "测试聊天记录"));
//    }
    @Test
    public void testInsert2() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("聊天记录列表");
        mongoTemplate.insert(chatMessages);
    }

    @Test
    public void testFindById() {
        ChatMessages chatMessages = mongoTemplate.findById("6910a14675b4fc5792021c51", ChatMessages
                .class);
        System.out.println(chatMessages);
    }

    @Test
    public void testUpdate() {
        Criteria criteria = Criteria.where("_id").is("6910a14675b4fc5792021c51");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content", "新的聊天记录,update");

        // 修改或（如果没有就）新增
        mongoTemplate.updateFirst(query, update, ChatMessages.class);
    }

    /**
     * 测试不存在就新增，运行没报错，但是也没插入成功
     */
    @Test
    public void testUpdate2() {
        Criteria criteria = Criteria.where("_id").is("1000");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content", "测试插入聊天记录");
        // 修改或（如果没有就）新增
        mongoTemplate.updateFirst(query, update, ChatMessages.class);
    }

    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("_id").is("6910a738c8ba9e7d94f2fbd7");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }
}
