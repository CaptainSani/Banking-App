package com.sani.World.Banking.App.infrastructure.controller;


import com.itextpdf.text.DocumentException;
import com.sani.World.Banking.App.domain.entity.Transaction;
import com.sani.World.Banking.App.service.impl.BankStatementImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/statement")
@RequiredArgsConstructor
public class TransactionController {

    private final BankStatementImpl bankStatement;

    @GetMapping
    public List<Transaction> generateStatement(@RequestParam String accountNumber,
                                               @RequestParam String startDate,
                                               @RequestParam String endDate)throws DocumentException, FileNotFoundException{
        return bankStatement.generateStatement(accountNumber,startDate,endDate);
    }
}
