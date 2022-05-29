package com.company.repository.custom;

import com.company.dto.request.CardFilterRequestDTO;
import com.company.dto.request.TransactionsFilterRequestDTO;
import com.company.entity.CardEntity;
import com.company.entity.TransactionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TransactionsCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<TransactionsEntity> filter(TransactionsFilterRequestDTO filter) {
        StringBuilder sql = new StringBuilder("SELECT  c FROM  TransactionsEntity as c ");
        if (filter != null) {
            sql.append(" WHERE c.status = '" + filter.getStatus().name() + "'");
        } else {
            sql.append(" WHERE c.status = 'ACTIVE'");
        }


        if (filter.getFromAmount() != null && filter.getToAmount() != null) {
            sql.append(" AND  c.amount between " + filter.getFromAmount() + " AND " + filter.getToAmount());
        } else if (filter.getFromAmount() != null) {
            sql.append(" AND  c.amount > " + filter.getFromAmount());
        } else if (filter.getToAmount() != null) {
            sql.append(" AND  c.amount < " + filter.getToAmount());
        }

        if (filter.getFromDate() != null && filter.getToDate() != null) {
            sql.append(" and date(t.createdDate) between :fromDate and :toDate ");
        } else if (filter.getFromDate() != null) {
            sql.append(" and date(t.createdDate) > :fromDate ");
        } else if (filter.getToDate() != null) {
            sql.append(" and date(t.createdDate) < :toDate ");
        }

        if (filter.getProfileName() != null) {
            sql.append(" and t.profileName = :profileName ");
        }

        Query query = entityManager.createQuery(sql.toString(), TransactionsEntity.class);
        List<TransactionsEntity> transactionsEntities = query.getResultList();

        return transactionsEntities;
    }
}
