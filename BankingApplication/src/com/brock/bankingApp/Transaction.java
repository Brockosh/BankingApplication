package com.brock.bankingApp;

import java.util.Date;
import java.util.UUID;

public class Transaction {

    private String accountNumber;
    private TransactionUtils.TransferType type;
    private double amount;
    private UUID destinationAccount;
    private float transactionTime;
    private TransactionUtils.Country transactionLocation;
    private TransactionUtils.DeviceType device;
    private TransactionUtils.PaymentMethod paymentMethod;
    boolean recentChangeInAccountDetails;

    private boolean isSuspicious;

    // Constructor
    public Transaction(String accountNumber, TransactionUtils.TransferType type, double amount,
                        UUID destinationAccount, float transactionTime, TransactionUtils.Country transactionLocation,
                        TransactionUtils.DeviceType device, TransactionUtils.PaymentMethod paymentMethod,
                        boolean recentChangeInAccountDetails) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.destinationAccount = destinationAccount;
        this.transactionTime = transactionTime;
        this.transactionLocation = transactionLocation;
        this.device = device;
        this.paymentMethod = paymentMethod;
        this.recentChangeInAccountDetails = recentChangeInAccountDetails;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionUtils.TransferType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public UUID getDestinationAccount() {
        return destinationAccount;
    }

    public float getTransactionTime() {
        return transactionTime;
    }

    public TransactionUtils.Country getTransactionLocation() {
        return transactionLocation;
    }

    public TransactionUtils.DeviceType getDevice() {
        return device;
    }

    public TransactionUtils.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isRecentChangeInAccountDetails() {
        return recentChangeInAccountDetails;
    }

    public boolean isSuspicious() {
        return isSuspicious;
    }

    // Setters
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setType(TransactionUtils.TransferType type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDestinationAccount(UUID destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public void setTransactionTime(float transactionTime) {
        this.transactionTime = transactionTime;
    }

    public void setTransactionLocation(TransactionUtils.Country transactionLocation) {
        this.transactionLocation = transactionLocation;
    }

    public void setDevice(TransactionUtils.DeviceType device) {
        this.device = device;
    }

    public void setPaymentMethod(TransactionUtils.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setRecentChangeInAccountDetails(boolean recentChangeInAccountDetails) {
        this.recentChangeInAccountDetails = recentChangeInAccountDetails;
    }

    public void setSuspicious(boolean isSuspicious) {
        this.isSuspicious = isSuspicious;
    }

    public String toString() {
        return "Transaction Details:\n" +
                "-------------------\n" +
                "Account Number: " + accountNumber + "\n" +
                "Type: " + type + "\n" +
                "Amount: $" + String.format("%.2f", amount) + "\n" +
                "Destination Account: " + destinationAccount + "\n" +
                "Transaction Time: " + String.format("%.2f", transactionTime) + "\n" +
                "Transaction Location: " + transactionLocation + "\n" +
                "Device: " + device + "\n" +
                "Payment Method: " + paymentMethod + "\n" +
                "Recent Change in Account Details: " + (recentChangeInAccountDetails ? "Yes" : "No") + "\n";
    }

}
