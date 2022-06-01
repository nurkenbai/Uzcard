package com.company.dto.request;

import com.company.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientStatusDTO {
    private StatusEnum status;
}
