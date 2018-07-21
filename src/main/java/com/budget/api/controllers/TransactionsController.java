package com.budget.api.controllers;

import com.budget.api.entity.Transaction;
import com.budget.api.service.TransactionsService;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

  private TransactionsService transactionService;

  @Autowired
  public TransactionsController(TransactionsService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping
  public List<Transaction> findAll(
      @RequestParam(name = "start", required = false) Optional<LocalDate> start,
      @RequestParam(name = "end", required = false) Optional<LocalDate> end) {
    return transactionService.findAllByDate(start, end);
  }

  @PostMapping
  public ResponseEntity create(@Valid @RequestBody Transaction transaction) {
    Transaction savedTransaction = transactionService.save(transaction);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedTransaction.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{id}")
  public Transaction findById(@PathVariable("id") Long id) {
    return transactionService.findById(id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    transactionService.delete(id);
  }

}
