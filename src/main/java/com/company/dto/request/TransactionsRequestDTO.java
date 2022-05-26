package com.company.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionsRequestDTO {
    private String fromCardId;
    private String toCardId;
    private Long amount;
}
