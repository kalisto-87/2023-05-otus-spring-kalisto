package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.service.TestService;

@ComponentScan
public class MainApplication {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainApplication.class);
        TestService testService = applicationContext.getBean(TestService.class);
        testService.startTest();

    }
}