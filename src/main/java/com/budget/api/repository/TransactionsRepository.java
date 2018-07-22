package com.budget.api.repository;

import com.budget.api.entity.Transaction;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transaction,Long> {

  List<Transaction> findAllByDateBetween(Date start, Date end);
}
