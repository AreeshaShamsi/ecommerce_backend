package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DatabaseConnectionChecker {

    @Bean
    CommandLineRunner checkDatabaseConnection(DataSource dataSource) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                if (connection.isValid(2)) {
                    System.out.println("✅ Database Connected Successfully!");
                } else {
                    System.out.println("❌ Database Connection Failed!");
                }
            } catch (Exception e) {
                System.out.println("❌ Error connecting to database");
                e.printStackTrace();
            }
        };
    }
}