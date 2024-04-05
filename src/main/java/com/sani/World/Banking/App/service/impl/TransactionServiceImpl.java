package com.sani.World.Banking.App.service.impl;

import com.sani.World.Banking.App.domain.entity.Transaction;
import com.sani.World.Banking.App.payload.request.TransactionRequest;
import com.sani.World.Banking.App.repository.TransactionRepository;
import com.sani.World.Banking.App.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public void saveTransactions(TransactionRequest transactionRequest) {

        Transaction transaction = Transaction.builder()
                .transactionType(transactionRequest.getTransactionType())
                .accountNumber(transactionRequest.getAccountNumber())
                .amount(transactionRequest.getAmount())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);

        System.out.println("Transaction saved successfully");

    }
}
