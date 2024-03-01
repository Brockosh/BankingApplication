package bankingApp.Transactions;

import bankingApp.Exceptions.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        try {
            transaction.setTransferType(TransactionUtils.TransferType.TRANSFER);
            Transaction savedTransaction = transactionService.createAndProcessTransaction(transaction);
            return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
        } catch (InsufficientFundsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred processing the transaction.");
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Transaction transaction) {
        try {
            transaction.setTransferType(TransactionUtils.TransferType.DEPOSIT);
            transaction.setSendingAccount(transaction.getReceivingAccount());
            transaction.setTransactionTime(ZonedDateTime.now());

            Transaction completedTransaction = transactionService.deposit(transaction);
            return new ResponseEntity<>(completedTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during the deposit: " + e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Transaction transactionDetails) {
        try {
            transactionDetails.setTransferType(TransactionUtils.TransferType.WITHDRAWAL);
            transactionDetails.setSendingAccount(transactionDetails.getReceivingAccount());
            transactionDetails.setTransactionTime(ZonedDateTime.now());

            Transaction transaction = transactionService.withdraw(transactionDetails);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (InsufficientFundsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during the withdrawal: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable UUID id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable UUID id, @RequestBody Transaction updatedTransaction) {
        boolean isUpdated = transactionService.updateTransaction(id, updatedTransaction);
        if (isUpdated) {
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        boolean isDeleted = transactionService.deleteTransaction(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}