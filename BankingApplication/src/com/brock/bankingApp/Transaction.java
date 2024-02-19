package com.brock.bankingApp;

import java.util.Date;

public class Transaction {
    public enum Type {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    private String accountNumber;
    private Type type;
    private double amount;
    private Date transactionDate;
    private String destinationAccount;

    // Constructor
    public Transaction(String accountNumber, Type type, double amount, Date transactionDate, String destinationAccount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.destinationAccount = destinationAccount;
    }

}
