package com.microservice.transfersgateway.provider;

import com.microservice.transfersgateway.connector.AccountsConnector;
import com.microservice.transfersgateway.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsProvider {

    private final AccountsConnector accountsConnector;
    private Map<String, AccountDto> accountNrbMap = new LinkedHashMap<>();

    public Map<String, AccountDto> getAllAccounts() {
        return accountsConnector.getAccounts()
                .getAccounts()
                .stream()
                .collect(Collectors.toMap(AccountDto::getNrb, account -> new AccountDto(
                        account.getId(),
                        account.getCustomerId(),
                        account.getNrb(),
                        account.getCurrency(),
                        account.getAvailableFunds()
                )));
    }
}