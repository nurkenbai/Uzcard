package com.company.service;

import com.company.dto.request.CardRequestDTO;
import com.company.dto.response.CardResponseDTO;
import com.company.dto.response.ClientResponseDTO;
import com.company.entity.CardEntity;
import com.company.entity.ClientEntity;
import com.company.enums.StatusEnum;
import com.company.exeption.ItemNotFoundException;
import com.company.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    private final int min = 1000;
    private final int max = 9999;


    public CardResponseDTO create(CardRequestDTO requestDTO) {
        CardEntity entity = new CardEntity();
        entity.setNumber(getCardNumber());
        entity.setBalance(requestDTO.getBalance());
        entity.setClientId(entity.getClientId());
        if (requestDTO.getBalance() > 0) {
            entity.setStatus(StatusEnum.ACTIVE);
        }else {
            entity.setStatus(StatusEnum.BLOCK);
        }
        return toDTO(entity);
    }
    public CardResponseDTO getById(String id) {
        CardEntity entity = cardRepository.findById(id,StatusEnum.ACTIVE).orElseThrow(() -> {
            log.warn("Client id not found");
            throw new ItemNotFoundException("Client id not found");
        });
        return toDTO(entity);
    }

    public CardResponseDTO getByCardNumber(String id) {
        CardEntity entity = cardRepository.findByNumber(id).orElseThrow(() -> {
            log.warn("Client id not found");
            throw new ItemNotFoundException("Client id not found");
        });
        return toDTO(entity);
    }

    public List<CardResponseDTO> getAll() {
        List<CardResponseDTO> dtoList = new LinkedList<>();
        cardRepository.findAll(StatusEnum.ACTIVE).stream().forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public Boolean chengStatus(StatusEnum status, String id) {
        int n = cardRepository.chengStatus(status, id);
        return n>0;
    }

    private String getCardNumber() {
        int a = (int) (Math.random() * (max - min + 1) + min);
        int b = (int) (Math.random() * (max - min + 1) + min);
        int c = (int) (Math.random() * (max - min + 1) + min);
        String cardNumber = "8600-" + a + "-" + b + "-" + c;

        Optional<CardEntity> optional = cardRepository.findByNumber(cardNumber);
        if (optional.isEmpty()) {
            getCardNumber();
        }
        return cardNumber;
    }

    private CardResponseDTO toDTO(CardEntity entity) {
        CardResponseDTO responseDTO = new CardResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setNumber(entity.getNumber());
        responseDTO.setCreatedDate(entity.getCreatedDate());
        responseDTO.setStatus(entity.getStatus());
        return responseDTO;
    }
}
