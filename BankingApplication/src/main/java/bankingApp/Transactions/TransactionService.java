package bankingApp.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Optional<Transaction> getTransactionById(UUID id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public boolean updateTransaction(UUID id, Transaction updatedTransaction) {
        return transactionRepository.findById(id).map(transaction -> {
            transaction.setTransferType(updatedTransaction.getTransferType());
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setReceivingAccount(updatedTransaction.getReceivingAccount());
            transaction.setTransactionTime(updatedTransaction.getTransactionTime());
            transaction.setTransactionLocation(updatedTransaction.getTransactionLocation());
            transaction.setDevice(updatedTransaction.getDevice());
            transaction.setPaymentMethod(updatedTransaction.getPaymentMethod());
            transactionRepository.save(transaction);
            return true;
        }).orElse(false);
    }

    public boolean deleteTransaction(UUID id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}