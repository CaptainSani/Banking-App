package com.sani.World.Banking.App.repository;

import com.sani.World.Banking.App.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
