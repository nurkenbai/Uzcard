package com.company.repository;

import com.company.entity.TransactionsEntity;
import com.company.enums.StatusEnum;
import com.company.mapper.TransactionsMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, String> {
    @Query("SELECT t.id as t_id,fcr.id as fcr_id,fcr.number as fcr_number,fcr.phone as fcr_phone," +
            " fcl.id as fcl_id,fcl.name as fcl_name,fcl.surname as fcl_surname, " +
            " tcr.id as tcr_id,fcr.number as tcr_number,tcr.phone as tcr_phone," +
            " tcl.id as tcl_id,fcl.name as tcl_name,fcl.surname as tcl_surname," +
            " t.amount as t_amount,t.status as t_status,t.createdDate as t_createdDate FROM TransactionsEntity as t" +
            " inner join t.fromCard as fcr" +
            " inner join fcr.client as fcl" +
            " inner join t.toCard as tcr" +
            " inner join tcr.client as tcl" +
            " where fcr.id=:cid  and t.status=:status" +
            " order by t.createdDate")
    Page<TransactionsMapper> getByCardId(Pageable pageable, @Param("cid") String cid, @Param("status")StatusEnum status);
}