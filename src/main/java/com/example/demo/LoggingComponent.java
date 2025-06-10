package com.example.demo;


import org.springframework.stereotype.Component;

@Component
public class LoggingComponent {


        public void logInfo(String message) {
            System.out.println("[INFO] " + message);
        }


        public void logError(String message) {
            System.err.println("[ERROR] " + message);
        }
    }



