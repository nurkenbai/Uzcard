package com.company.service;

import com.company.dto.request.ClientRequestDTO;
import com.company.dto.response.ClientResponseDTO;
import com.company.entity.ClientEntity;
import com.company.enums.StatusEnum;
import com.company.exeption.ItemNotFoundException;
import com.company.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientResponseDTO create(ClientRequestDTO requestDTO) {
        ClientEntity entity = new ClientEntity();
        entity.setName(requestDTO.getName());
        entity.setSurname(requestDTO.getSurname());
        entity.setPhone(requestDTO.getPhone());
        entity.setStatus(StatusEnum.ACTIVE);
        clientRepository.save(entity);
        return toDTO(entity);
    }

    public ClientResponseDTO getById(String id) {
        ClientEntity entity = clientRepository.findById(id).orElseThrow(() -> {
            log.warn("Client id not found");
            throw new ItemNotFoundException("Client id not found");
        });
        return toDTO(entity);
    }

    public List<ClientResponseDTO> getAll() {
        List<ClientResponseDTO> dtoList = new LinkedList<>();
        clientRepository.findAll().stream().forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public Boolean chengStatus(StatusEnum status, String id) {
        clientRepository.chengStatus(status, id);
        return null;
    }

    private ClientResponseDTO toDTO(ClientEntity entity) {
        ClientResponseDTO responseDTO = new ClientResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setPhone(entity.getPhone());
        responseDTO.setStatus(entity.getStatus());
        return responseDTO;
    }


}
