package com.brock.bankingApp;

import java.time.ZonedDateTime;
import java.util.Random;

public class TransactionData {
    private TransactionUtils.TransferType type;
    private double amount;
    private ZonedDateTime transactionTime;
    private TransactionUtils.Country transactionLocation;
    private TransactionUtils.DeviceType device;
    private TransactionUtils.PaymentMethod paymentMethod;
    private boolean recentChangeInAccountDetails;
    private boolean suspicious;

    public TransactionData(TransactionUtils.TransferType type, double amount,
                           ZonedDateTime transactionTime, TransactionUtils.Country transactionLocation,
                           TransactionUtils.DeviceType device, TransactionUtils.PaymentMethod paymentMethod,
                           boolean recentChangeInAccountDetails, boolean suspicious) {
        this.type = type;
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.transactionLocation = transactionLocation;
        this.device = device;
        this.paymentMethod = paymentMethod;
        this.recentChangeInAccountDetails = recentChangeInAccountDetails;
        this.suspicious = suspicious;
    }

    public TransactionUtils.TransferType getType() {
        return type;
    }

    public void setType(TransactionUtils.TransferType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(ZonedDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionUtils.Country getTransactionLocation() {
        return transactionLocation;
    }

    public void setTransactionLocation(TransactionUtils.Country transactionLocation) {
        this.transactionLocation = transactionLocation;
    }

    public TransactionUtils.DeviceType getDevice() {
        return device;
    }

    public void setDevice(TransactionUtils.DeviceType device) {
        this.device = device;
    }

    public TransactionUtils.PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TransactionUtils.PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isRecentChangeInAccountDetails() {
        return recentChangeInAccountDetails;
    }

    public void setRecentChangeInAccountDetails(boolean recentChangeInAccountDetails) {
        this.recentChangeInAccountDetails = recentChangeInAccountDetails;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public void setSuspicious(boolean suspicious) {
        this.suspicious = suspicious;
    }
}
