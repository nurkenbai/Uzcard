package com.company.dto.response;

import com.company.dto.request.ClientRequestDTO;
import com.company.enums.StatusEnum;
import lombok.Data;

@Data
public class ClientResponseDTO extends ClientRequestDTO {
    private  String id;
    private  StatusEnum status;
}
