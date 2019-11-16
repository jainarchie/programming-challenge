package com.shepherd.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ChallengeApplication {

    public static Logger logger = LoggerFactory.getLogger(ChallengeApplication.class);

    public static void main(String[] args) {

        logger.info("********************************** Starting ChallengeApplication at {}**********************************", new Date());
        SpringApplication.run(ChallengeApplication.class, args);
        logger.info("********************************** Successfully Started ChallengeApplication at {}**********************************", new Date());
    }

}
