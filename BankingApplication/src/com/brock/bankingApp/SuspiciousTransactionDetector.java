package com.brock.bankingApp;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Random;

public class SuspiciousTransactionDetector {

    private static int checkSuspiciousCriteria(TransactionData transaction, int recentTransactionsCount) {
        int suspiciousCount = 0;

        // High Transaction Amounts with Gradation
        suspiciousCount += checkTransactionAmount(transaction) ? 1 : 0;

        // Unusual Transaction Times
        suspiciousCount += checkTransactionTime(transaction) ? 1 : 0;

        // Sudden Foreign Transaction
        suspiciousCount += checkForeignTransaction(transaction) ? 1 : 0;

        // Device Anomalies
        suspiciousCount += checkDeviceAnomalies(transaction) ? 1 : 0;

        // Large Transactions Following Recent Account Changes
        suspiciousCount += checkRecentAccountChange(transaction) ? 1 : 0;

        // Rare Payment Methods for High Amounts
        suspiciousCount += checkPaymentMethodForHighAmount(transaction) ? 1 : 0;

        // Device and Location Mismatch
        suspiciousCount += checkDeviceAndLocationMismatch(transaction) ? 1 : 0;

        if (recentTransactionsCount > 3) {
            suspiciousCount++;
        }

        return suspiciousCount;
    }

    public static boolean isTransactionSuspicious(TransactionData transaction, int recentTransactionsCount) {
        int criteriaMet = checkSuspiciousCriteria(transaction, recentTransactionsCount);
        return criteriaMet >= 2;
    }

    public static boolean checkTransactionAmount(TransactionData transaction) {
        double amount = transaction.getAmount();
        return amount > 25000 ||
                (amount > 10000 && transaction.getTransactionLocation() != TransactionUtils.Country.AUSTRALIA) ||
                (amount > 5000 && transaction.getPaymentMethod() == TransactionUtils.PaymentMethod.WIRE_TRANSFER);
    }

    public static boolean checkTransactionTime(TransactionData transaction) {
        ZonedDateTime transactionTime = transaction.getTransactionTime();
        LocalTime localTime = transactionTime.toLocalTime();
        return (localTime.isAfter(LocalTime.of(23, 0)) || localTime.isBefore(LocalTime.of(4, 0)));
    }

    public static boolean checkForeignTransaction(TransactionData transaction) {
        return transaction.getTransactionLocation() != TransactionUtils.Country.AUSTRALIA;
    }

    public static boolean checkDeviceAnomalies(TransactionData transaction) {
        TransactionUtils.DeviceType device = transaction.getDevice();
        return device == TransactionUtils.DeviceType.ATM || device == TransactionUtils.DeviceType.POS;
    }

    public static boolean checkRecentAccountChange(TransactionData transaction) {
        return transaction.isRecentChangeInAccountDetails() && transaction.getAmount() > 2000;
    }

    public static boolean checkPaymentMethodForHighAmount(TransactionData transaction) {
        TransactionUtils.PaymentMethod paymentMethod = transaction.getPaymentMethod();
        return (paymentMethod == TransactionUtils.PaymentMethod.GIFT_CARD || paymentMethod == TransactionUtils.PaymentMethod.CASH_APP) && transaction.getAmount() > 1000;
    }

    public static boolean checkDeviceAndLocationMismatch(TransactionData transaction) {
        return transaction.getDevice() == TransactionUtils.DeviceType.ATM && transaction.getTransactionLocation() != TransactionUtils.Country.AUSTRALIA;
    }
    public static boolean checkRecentTransactionCount(int transactionCount) {
        return transactionCount > 3;
    }
}
