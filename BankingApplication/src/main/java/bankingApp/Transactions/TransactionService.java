package bankingApp.Transactions;

import bankingApp.Accounts.Account;
import bankingApp.Accounts.AccountRepository;
import bankingApp.Exceptions.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Transactional
    public Transaction createAndProcessTransaction(Transaction transaction) {
        // Retrieve accounts involved in the transaction
        Account sendingAccount = accountRepository.findById(transaction.getSendingAccount()).orElseThrow();
        System.out.println("Found by ID" + sendingAccount.getId());
        Account receivingAccount;
        try {
            receivingAccount = accountRepository.findById(transaction.getReceivingAccount()).orElseThrow();
            System.out.println("Found by ID" + receivingAccount.getId());
        } catch (Exception e) {
            System.out.println("Error retrieving receiving account: " + e.getMessage());
            throw e;
        }
        if (sendingAccount.getBalance() >= transaction.getAmount()) {
            sendingAccount.setBalance(sendingAccount.getBalance() - transaction.getAmount());
            receivingAccount.setBalance(receivingAccount.getBalance() + transaction.getAmount());

            // Save updated account states
            accountRepository.save(sendingAccount);
            accountRepository.save(receivingAccount);

            return transactionRepository.save(transaction);
        } else {
            // Handle insufficient funds
            throw new InsufficientFundsException("Account " + sendingAccount.getId() + " does not have sufficient funds.");
        }
    }

    @Transactional
    public Transaction deposit(Transaction transaction) {
        // Assume transaction.setTransferType has been set to DEPOSIT before this call

        System.out.println("Deposit amount" + transaction.getAmount());
        Account account = accountRepository.findById(transaction.getReceivingAccount())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + transaction.getAmount());
        accountRepository.save(account);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction withdraw(Transaction transaction) throws InsufficientFundsException {
        Account account = accountRepository.findById(transaction.getSendingAccount())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < transaction.getAmount()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        account.setBalance(account.getBalance() - transaction.getAmount());
        accountRepository.save(account);
        return transactionRepository.save(transaction);
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