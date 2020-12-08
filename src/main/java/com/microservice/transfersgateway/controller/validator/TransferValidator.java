package com.microservice.transfersgateway.controller.validator;

import com.microservice.commons.Transfer;
import com.microservice.transfersgateway.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferValidator {

    public void validateTransfer(Transfer transfer, Map<String, AccountDto> allAccounts) {
        if (!allAccounts.containsKey(transfer.getSenderAccount())) {
            log.warn("Not found given sender account: {}", transfer.getSenderAccount());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Not found given sender account: " + transfer.getSenderAccount()
            );
        }

        AccountDto accountDTO = allAccounts.get(transfer.getSenderAccount());
        if (accountDTO.getAvailableFunds().compareTo(transfer.getAmount()) < 0) {
            log.warn("Not enough resources on sender account (available resources: {}, requested amount: {}",
                    accountDTO.getAvailableFunds(), transfer.getAmount()
            );
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not enough resources on given account");
        }
    }
}