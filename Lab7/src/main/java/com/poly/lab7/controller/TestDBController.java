package com.poly.lab7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class TestDBController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/testdb")
    public String testConnection() {
        try (Connection conn = dataSource.getConnection()) {
            return "✅ Kết nối thành công đến SQL Server! Database: "
                    + conn.getCatalog();
        } catch (Exception e) {
            return "❌ Kết nối thất bại: " + e.getMessage();
        }
    }
}
