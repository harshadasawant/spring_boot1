package com.hnd.infinite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class SampleRunner implements CommandLineRunner {
    public static final Log LOGGER = LogFactory.getLog(SampleRunner.class);

    public static void main(String[] args) {
        SpringApplication.run(SampleRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Welcome to CommandLineRunner");
    }
}