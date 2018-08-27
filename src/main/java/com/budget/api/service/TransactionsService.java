package com.budget.api.service;

import com.budget.api.entity.Transaction;
import com.budget.api.exceptions.ResourceNotFoundException;
import com.budget.api.repository.TransactionsRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

  private static final String TRANSACTION_NOT_FOUND_MESSAGE = "Transaction with id %d not found";
  private TransactionsRepository transactionsRepository;

  @Autowired
  public TransactionsService(TransactionsRepository transactionsRepository) {
    this.transactionsRepository = transactionsRepository;
  }

  public List<Transaction> findAllByDate(Optional<LocalDate> start, Optional<LocalDate> end) {
    return transactionsRepository.findAllByDateBetween(
        start.orElse(LocalDate.now().withDayOfMonth(1)),
        end.orElse(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())));
  }

  public Transaction save(Transaction transaction) {
    return transactionsRepository.save(transaction);
  }

  public Transaction findById(Long id) {
    Optional<Transaction> optionalTransaction = transactionsRepository.findById(id);
    return optionalTransaction
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format(TRANSACTION_NOT_FOUND_MESSAGE, id)));
  }

  public void delete(Long id) {
    transactionsRepository.deleteById(id);
  }
}
