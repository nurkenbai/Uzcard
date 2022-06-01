package com.company.repository.custom;

import com.company.entity.CardEntity;
import com.company.mapper.KillerDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class KillerDataBaseCoustomRepository {
    private final EntityManager entityManager;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASS;

    public Boolean drop(Object tableName,Object tableEntity) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sel = "SELECT  c.id as t_id FROM " + tableEntity + " as c";
            Query query = entityManager.createQuery(sel);
            List<String > cardEntityList = query.getResultList();
            for (String id : cardEntityList) {
                String sql = "DELETE FROM " + tableName + " where id='" + id + "'";
                stmt.executeUpdate(sql);
            }

            log.info("Table deleted in given database..." + tableName);
        } catch (SQLException e) {
            log.warn("Not found drop table " + tableName);
        }
        return true;
    }
}
