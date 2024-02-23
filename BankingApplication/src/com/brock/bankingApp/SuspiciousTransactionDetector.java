package com.brock.bankingApp;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Random;

public class SuspiciousTransactionDetector {

    private static int checkSuspiciousCriteria(Transaction transaction) {
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

        return suspiciousCount;
    }

    public static boolean isTransactionSuspicious(Transaction transaction) {
        int criteriaMet = checkSuspiciousCriteria(transaction);
        return criteriaMet >= 2; // Flag as suspicious if 2 or more criteria are met
    }

    public static boolean checkTransactionAmount(Transaction transaction) {
        double amount = transaction.getAmount();
        boolean isHighAmount = amount > 25000 ||
                (amount > 10000 && transaction.getTransactionLocation() != TransactionUtils.Country.AUSTRALIA) ||
                (amount > 5000 && transaction.getPaymentMethod() == TransactionUtils.PaymentMethod.WIRE_TRANSFER);

        // Simulate the likelihood of this being significantly higher than a "usual" amount
        boolean isSignificantlyHigherThanUsual = new Random().nextInt(100) < 5; // 5% chance

        return isHighAmount || isSignificantlyHigherThanUsual;
    }

    public static boolean checkTransactionTime(Transaction transaction) {
        ZonedDateTime transactionTime = transaction.getTransactionTime();
        LocalTime localTime = transactionTime.toLocalTime();
        boolean isLateNight = (localTime.isAfter(LocalTime.of(23, 0)) || localTime.isBefore(LocalTime.of(4, 0)));

        // Simulate the likelihood of being unusual for the user
        boolean isUnusualForUser = new Random().nextInt(100) < 5; // 5% chance

        return isLateNight || isUnusualForUser;
    }
    public static boolean checkForeignTransaction(Transaction transaction) {
        boolean isForeign = transaction.getTransactionLocation() != TransactionUtils.Country.AUSTRALIA;

        // Simulate new location as more suspicious
        boolean isNewLocationMoreSuspicious = new Random().nextInt(100) < 5; // 5% chance

        return isForeign && isNewLocationMoreSuspicious;
    }

    public static boolean checkDeviceAnomalies(Transaction transaction) {
        TransactionUtils.DeviceType device = transaction.getDevice();
        boolean isUnusualDevice = device == TransactionUtils.DeviceType.ATM || device == TransactionUtils.DeviceType.POS;

        // Simulate a new device for the account as more suspicious
        boolean isNewDeviceForAccount = new Random().nextInt(100) < 5; // 5% chance

        return isUnusualDevice || isNewDeviceForAccount;
    }

    public static boolean checkRecentAccountChange(Transaction transaction) {
        boolean hasRecentChange = transaction.isRecentChangeInAccountDetails() && transaction.getAmount() > 2000;

        // Simulate the significance of the change
        boolean isChangeSignificant = new Random().nextInt(100) < 10; // 10% chance

        return hasRecentChange || isChangeSignificant;
    }

    public static boolean checkPaymentMethodForHighAmount(Transaction transaction) {
        TransactionUtils.PaymentMethod paymentMethod = transaction.getPaymentMethod();
        boolean isRareAndHigh = (paymentMethod == TransactionUtils.PaymentMethod.GIFT_CARD ||
                paymentMethod == TransactionUtils.PaymentMethod.CASH_APP) &&
                transaction.getAmount() > 1000;

        // Simulate the unusualness for the account
        boolean isUnusualMethodAndAmount = new Random().nextInt(100) < 10; // 10% chance

        return isRareAndHigh || isUnusualMethodAndAmount;
    }

    public static boolean checkDeviceAndLocationMismatch(Transaction transaction) {
        boolean isMismatch = transaction.getDevice() == TransactionUtils.DeviceType.ATM &&
                transaction.getTransactionLocation() != TransactionUtils.Country.AUSTRALIA;

        // Simulate a pattern mismatch
        boolean isPatternMismatch = new Random().nextInt(100) < 5; // 5% chance

        return isMismatch || isPatternMismatch;
    }
}
