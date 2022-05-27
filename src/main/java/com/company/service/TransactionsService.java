package com.company.service;

import com.company.dto.request.TransactionsRequestDTO;
import com.company.dto.response.CardResponseDTO;
import com.company.dto.response.ClientResponseDTO;
import com.company.dto.response.TransactionsResponseDTO;
import com.company.entity.TransactionsEntity;
import com.company.enums.StatusEnum;
import com.company.mapper.TransactionsMapper;
import com.company.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
        transactionsRepository.save(entity);
        return toDTO(entity);
    }

    public PageImpl<TransactionsResponseDTO> getByCardIdAndPagination(int page, int size, String cid) {
        Pageable pageable = PageRequest.of(page, size);


        Page<TransactionsMapper> entityPage = transactionsRepository.getByCardId(pageable, cid, StatusEnum.ACTIVE);

        List<TransactionsMapper> entityList = entityPage.getContent();
        List<TransactionsResponseDTO> playListDTO = new LinkedList<>();
        entityList.forEach(entity -> {
            playListDTO.add(toDTOMapper(entity));
        });

        return new PageImpl<>(playListDTO, pageable, entityPage.getTotalElements());
    }

    private TransactionsResponseDTO toDTOMapper(TransactionsMapper entity) {
        TransactionsResponseDTO responseDTO = new TransactionsResponseDTO();
        responseDTO.setId(entity.getT_id());

        CardResponseDTO fromCard = new CardResponseDTO();
        fromCard.setId(entity.getFcr_id());
        fromCard.setNumber(entity.getFcr_number());

        ClientResponseDTO fromClient = new ClientResponseDTO();
        fromClient.setId(entity.getFcl_id());
        fromClient.setName(entity.getFcl_name());
        fromClient.setSurname(entity.getFcl_surname());

        fromCard.setClient(fromClient);

        CardResponseDTO toCard = new CardResponseDTO();
        toCard.setId(entity.getTcr_id());

        ClientResponseDTO toClient = new ClientResponseDTO();
        toClient.setId(entity.getTcl_id());
        toClient.setName(entity.getTcl_name());
        toClient.setSurname(entity.getTcl_surname());

        toCard.setClient(toClient);
        toCard.setNumber(entity.getTcr_number());

        responseDTO.setToCard(toCard);
        responseDTO.setFromCard(fromCard);
        responseDTO.setAmount(entity.getT_amount());
        responseDTO.setStatus(entity.getT_status());
        responseDTO.setCreatedDate(entity.getT_createdDate());
        return responseDTO;
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
