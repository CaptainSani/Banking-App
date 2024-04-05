package com.sani.World.Banking.App.service;

import com.sani.World.Banking.App.payload.request.TransactionRequest;

public interface TransactionService {

    void saveTransactions(TransactionRequest transactionRequest);
}
