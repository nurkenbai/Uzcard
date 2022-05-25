package com.company.dto.response;

import com.company.dto.request.ClientRequestDTO;
import com.company.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientResponseDTO extends ClientRequestDTO {
    private  String id;
    private  StatusEnum status;
    private LocalDateTime createdDate;
}
