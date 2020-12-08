package com.microservice.transfersgateway.connector;

import com.microservice.transfersgateway.mapper.GetAccountsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "accounts")
public interface AccountsConnector {
    @GetMapping("/v1/accounts/")
    GetAccountsResponse getAccounts();
}