package com.company.repository;

import com.company.entity.CardEntity;
import com.company.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, String> {
    Optional<CardEntity> findByNumber(String number);

    int chengStatus(StatusEnum status, String id);

    Optional<CardEntity> findById(String id, StatusEnum active);

    List<CardEntity> findAll(StatusEnum active);

    List<CardEntity> findByClientIdAndStatus(String cid, StatusEnum active);

    List<CardEntity> findByPhoneAndStatus(String phone, StatusEnum active);

    @Transactional
    @Modifying
    @Query("update CardEntity set phone = :phone where id = :cid")
    int assignPhone(@Param("phone") String phone, @Param("cid") String cid);

    @Query("select c.balance from CardEntity as c where c.number=:number")
    public Optional<Long> getBalance(@Param("number") String number);

    @Transactional
    @Modifying
    @Query("update CardEntity set balance = balance-:amount where id = :cid")
    int paymentMinus(@Param("amount") Long amount, @Param("cid") String cid);

    @Transactional
    @Modifying
    @Query("update CardEntity set balance = balance+:amount where id = :cid")
    int paymentPlus(@Param("amount") Long amount, @P("cid") String cid);
}