package ru.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.practice.services.FileMonitor;
import ru.practice.services.impl.FileMonitor_Impl;
import ru.practice.services.impl.FileReader_impl;

@SpringBootApplication
public class Application {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);

    }
}