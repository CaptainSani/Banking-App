package com.sani.World.Banking.App.service;

import com.sani.World.Banking.App.payload.request.CreditAndDebitRequest;
import com.sani.World.Banking.App.payload.request.EnquiryRequest;
import com.sani.World.Banking.App.payload.request.TransferRequest;
import com.sani.World.Banking.App.payload.response.BankResponse;



public interface UserService {

    BankResponse creditAccount (CreditAndDebitRequest request);

    BankResponse debitAccount (CreditAndDebitRequest request);

    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);

    String nameEnquiry(EnquiryRequest enquiryRequest);
    BankResponse transfer(TransferRequest request);
}
