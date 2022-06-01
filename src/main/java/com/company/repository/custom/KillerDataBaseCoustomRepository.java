package com.company.repository.custom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@Slf4j
@RequiredArgsConstructor
public class KillerDataBaseCoustomRepository {
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASS;

    public Boolean drop(Object tableName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "DROP TABLE If exists " + tableName + " cascade";
//            drop table if exists profile cascade;
            stmt.executeUpdate(sql);
            log.info("Table deleted in given database..." + tableName);
        } catch (SQLException e) {
            log.warn("Not found drop table "+tableName);
        }
        return true;
    }
}
