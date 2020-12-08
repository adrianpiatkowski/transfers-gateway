package com.microservice.transfersgateway.controller;

import com.microservice.commons.Transfer;
import com.microservice.transfersgateway.controller.request.TransferRequest;
import com.microservice.transfersgateway.controller.validator.TransferValidator;
import com.microservice.transfersgateway.dto.AccountDto;
import com.microservice.transfersgateway.provider.AccountsProvider;
import com.microservice.transfersgateway.service.TransferProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferProducer transferProducer;
    private final AccountsProvider accountsProvider;
    private final TransferValidator transferValidator;

    @PostMapping
    public void saveTransfer(@RequestBody TransferRequest request) {
        log.info("Received transfer request: {}", request);
        Transfer transfer = new Transfer();
        transfer.setAmount(request.getAmount());
        transfer.setRecipientAccount(request.getRecipientAccount());
        transfer.setSenderAccount(request.getSenderAccount());
        transfer.setTitle(request.getTitle());

        Map<String, AccountDto> allAccounts = accountsProvider.getAllAccounts();
        transferValidator.validateTransfer(transfer, allAccounts);

        transferProducer.sendTransfer(transfer);
    }
}