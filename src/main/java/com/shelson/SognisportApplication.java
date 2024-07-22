/*
 * Copyright (c) 2024, Shelson Ferrari
 *
 * Licensed under the MIT License and the Apache License, Version 2.0 (the "Licenses"); you may not use this file except in
 * compliance with the Licenses. You may obtain a copy of the Licenses at
 *
 * MIT License:
 * https://opensource.org/licenses/MIT
 *
 * Apache License, Version 2.0:
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licenses is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the Licenses for the specific language governing permissions and limitations under the Licenses.
 */
package com.shelson;

import java.sql.SQLException;

import jakarta.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Main class for the application.
 * This class initializes the Spring Boot application.
 */
@SpringBootApplication
public class shelsonApplication {

    private static org.h2.tools.Server server;
    private static final Logger logger = LoggerFactory.getLogger(shelsonApplication.class);

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(shelsonApplication.class, args);

        final String[] h2ServerConfiguration = new String[] {
            "-tcpPort", "8092",
            "-tcpAllowOthers"
        };

        try {
            server = org.h2.tools.Server.createTcpServer(h2ServerConfiguration).start();
            logger.info("H2 server started and listening on port 8092");
        } catch (SQLException e) {
            logger.error("Failed to start H2 server", e);
        }
    }

    @Bean
    ApplicationShutdownHook applicationShutdownHook() {
        return new ApplicationShutdownHook();
    }

    @Component
    public static class ApplicationShutdownHook {

        @PreDestroy
        public void onShutdown() {
            logger.info("Shutting down H2 server...");
            if (server != null) {
                server.stop();
                logger.info("H2 server stopped.");
            }
        }
    }
}
