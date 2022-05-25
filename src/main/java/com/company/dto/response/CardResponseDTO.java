package com.company.dto.response;

import com.company.dto.request.CardRequestDTO;
import com.company.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardResponseDTO extends CardRequestDTO {
    private String id;
    private String number;
    private LocalDateTime createdDate;
    private StatusEnum status;

}
