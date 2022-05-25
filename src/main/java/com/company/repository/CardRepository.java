package com.company.repository;

import com.company.entity.CardEntity;
import com.company.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, String> {
    Optional<CardEntity> findByNumber(String number);

    int chengStatus(StatusEnum status, String id);

    Optional<CardEntity> findById(String id, StatusEnum active);

    List<CardEntity> findAll(StatusEnum active);
}