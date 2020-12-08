package com.microservice.transfersgateway.mapper;

import com.microservice.transfersgateway.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountsResponse {
    private List<AccountDto> accounts;
}