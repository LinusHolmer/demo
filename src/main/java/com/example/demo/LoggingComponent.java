package com.example.demo;


import org.springframework.stereotype.Component;

/**
 * LoggingComponent is used to log operations in AppUserService
 */

@Component
public class LoggingComponent {


    /**
     * info about the operation
     *
     * @param message - what the operation did
     */
    public void logInfo(String message) {
            System.out.println("[INFO] " + message);
        }

    /**
     * info about error in operation
     *
     * @param message - what went wrong
     */
        public void logError(String message) {
            System.err.println("[ERROR] " + message);
        }
    }



