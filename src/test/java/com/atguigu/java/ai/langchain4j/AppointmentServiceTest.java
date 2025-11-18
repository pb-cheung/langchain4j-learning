package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.entity.Appointment;
import com.atguigu.java.ai.langchain4j.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    void testGetOne() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("110101199001010000");
        appointment.setDepartment("心血管科");
        appointment.setDate("2023-07-01");
        appointment.setTime("上午");
        appointment.setDoctorName("张三医生");

        Appointment appointmentDB = appointmentService.getOne(appointment);
        System.out.println(appointmentDB);
    }

    @Test
    void testSave() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("110101199001010000");
        appointment.setDepartment("心血管科");
        appointment.setDate("2023-07-01");
        appointment.setTime("上午");
        appointment.setDoctorName("张三医生");

        appointmentService.save(appointment);
    }

    @Test
    void removeById() {
        appointmentService.removeById(1L);
    }
}
