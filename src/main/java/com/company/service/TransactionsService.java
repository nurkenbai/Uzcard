package com.company.service;

import com.company.dto.request.TransactionsRequestDTO;
import com.company.dto.response.TransactionsResponseDTO;
import com.company.entity.CardEntity;
import com.company.entity.TransactionsEntity;
import com.company.enums.StatusEnum;
import com.company.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private CardService cardService;


    public TransactionsResponseDTO create(TransactionsRequestDTO requestDTO) {
        cardService.get(requestDTO.getFromCardId(), requestDTO.getAmount());
        cardService.get(requestDTO.getToCardId());
        cardService.paymentMinus(requestDTO.getAmount(), requestDTO.getFromCardId());
        cardService.paymentPlus(requestDTO.getAmount(), requestDTO.getToCardId());
        TransactionsEntity entity = new TransactionsEntity();
        entity.setAmount(requestDTO.getAmount());
        entity.setFromCardId(requestDTO.getFromCardId());
        entity.setToCardId(requestDTO.getToCardId());
        entity.setStatus(StatusEnum.ACTIVE);
        return toDTO(entity);
    }

    private TransactionsResponseDTO toDTO(TransactionsEntity entity) {
        TransactionsResponseDTO responseDTO = new TransactionsResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setFromCardId(entity.getFromCardId());
        responseDTO.setToCardId(entity.getToCardId());
        responseDTO.setAmount(entity.getAmount());
        responseDTO.setStatus(entity.getStatus());
        responseDTO.setCreatedDate(entity.getCreatedDate());
        return responseDTO;
    }
}
