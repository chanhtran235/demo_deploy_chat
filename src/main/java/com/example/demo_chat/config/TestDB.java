package com.example.demo_chat.config;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class TestDB {
    @Value("${JDBC_DATABASE_URL}")
    private String jdbcUrl;

    @Value("${DATABASE_USERNAME}")
    private String dbUsername;

    @Value("${DATABASE_PASSWORD}")
    private String dbPassword;

    @PostConstruct
    public void logDatabaseDetails() {
        System.out.println("============= KIỂM TRA BIẾN MÔI TRƯỜNG================= =====");
        System.out.println("JDBC URL: " + jdbcUrl);
        System.out.println("Database Username: " + dbUsername);
        System.out.println("Database Password: " + dbPassword);
        System.out.println("====================================");
    }
}
