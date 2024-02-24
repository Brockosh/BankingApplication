package com.brock.bankingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

//    @PostMapping
//    public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction) {
//        boolean isApproved = transactionService.attemptTransaction(transaction);
//        if (isApproved) {
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable UUID id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTransaction(@PathVariable UUID id, @RequestBody Transaction updatedTransaction) {
        Transaction existingTransaction = transactionService.getTransactionById(id);
        if (existingTransaction != null) {
            transactionService.updateTransaction(id, updatedTransaction);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        Transaction existingTransaction = transactionService.getTransactionById(id);
        if (existingTransaction != null) {
            transactionService.deleteTransaction(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
