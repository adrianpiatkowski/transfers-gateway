package com.microservice.transfersgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private Long customerId;
    private String nrb;
    private String currency;
    private BigDecimal availableFunds;
}