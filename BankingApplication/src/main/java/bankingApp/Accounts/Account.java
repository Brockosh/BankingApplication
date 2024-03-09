package bankingApp.Accounts;

import bankingApp.Exceptions.InsufficientFundsException;
import bankingApp.Users.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {

    public enum AccountType {
        SAVINGS, CHECKING, BUSINESS
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_number", nullable = false, unique = true)
    private Integer accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType type;

    @Column(name = "balance", nullable = false)
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Account() {
    }

    public Account(String accountName, Integer accountNumber, AccountType type, double balance) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
    }

    public Account(UUID id, String accountName, Integer accountNumber, AccountType type, double balance) {
        this.id = id;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.type = type;
        this.balance = balance;
    }


    // Getters
    public UUID getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Business methods
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
        logTransaction("deposit", amount); // Assume this method logs the transaction
    }

    private void logTransaction(String deposit, double amount) {

        System.out.println(amount + deposit);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (this.balance < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        this.balance -= amount;
        logTransaction("withdraw", amount); // Logging the transaction
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountNumber=" + accountNumber +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }
}