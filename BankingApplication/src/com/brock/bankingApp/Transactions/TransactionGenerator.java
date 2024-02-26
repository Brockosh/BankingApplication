package com.brock.bankingApp.Transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class TransactionGenerator {

    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            List<TransactionData> trainingDataList = generateLabelledTransactions(50000);
            saveTransactionsToJson(trainingDataList, "new_transactions_training.json");

            List<TransactionData> testingDataList = generateLabelledTransactions(15000);
            saveTransactionsToJson(testingDataList, "new_transactions_testing.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveTransactionsToJson(List<TransactionData> analysisDataList, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.writeValue(new File(filename), analysisDataList);
    }


    public static Transaction generateTransaction() {
        String accountNumber = UUID.randomUUID().toString();
        TransactionUtils.TransferType type = TransactionUtils.TransferType.values()[random.nextInt(TransactionUtils.TransferType.values().length)];
        double amount = generateRandomAmount();
        UUID destinationAccount = UUID.randomUUID();
        ZonedDateTime transactionTime = generateRandomTransactionTime();
        TransactionUtils.Country transactionLocation = generateRandomCountry();
        TransactionUtils.DeviceType device = generateRandomDevice();
        TransactionUtils.PaymentMethod paymentMethod = generateRandomPaymentMethod();
        boolean recentChangeInAccountDetails = random.nextInt(100) < 1;

        return new Transaction(accountNumber, type, amount, destinationAccount, transactionTime,
                transactionLocation, device, paymentMethod, recentChangeInAccountDetails);
    }

    private static List<TransactionData> generateLabelledTransactions(int count) {
        List<TransactionData> dataList = new ArrayList<>();
        int suspiciousCount = 0;
        int notSuspiciousCount = 0;
        int targetCount = count / 2; // Target count for each type of transaction

        while (suspiciousCount < targetCount && notSuspiciousCount < targetCount) {
            TransactionData transaction = generateTransactionData();

            // Determine if the transaction is suspicious
            boolean isSuspicious = SuspiciousTransactionDetector.isTransactionSuspicious(transaction, generateRecentTransactionCount());

            // Set the suspicious flag of the transaction
            transaction.setSuspicious(isSuspicious);

            // Add the transaction to the list and update the counts
            if (isSuspicious && suspiciousCount < targetCount) {
                dataList.add(transaction);
                suspiciousCount++;
            } else if (!isSuspicious && notSuspiciousCount < targetCount) {
                dataList.add(transaction);
                notSuspiciousCount++;
            }
        }

        // If the counts are still unequal, add transactions until they match
        while (suspiciousCount < targetCount) {
            TransactionData transaction = generateTransactionData();
            transaction.setSuspicious(true); // Set the suspicious flag
            dataList.add(transaction);
            suspiciousCount++;
        }

        while (notSuspiciousCount < targetCount) {
            TransactionData transaction = generateTransactionData();
            transaction.setSuspicious(false); // Set the non-suspicious flag
            dataList.add(transaction);
            notSuspiciousCount++;
        }

        return dataList;
    }

    public static TransactionData generateTransactionData() {
        TransactionUtils.TransferType type = TransactionUtils.TransferType.values()[random.nextInt(TransactionUtils.TransferType.values().length)];
        double amount = generateRandomAmount();
        ZonedDateTime transactionTime = generateRandomTransactionTime();
        TransactionUtils.Country transactionLocation = generateRandomCountry();
        TransactionUtils.DeviceType device = generateRandomDevice();
        TransactionUtils.PaymentMethod paymentMethod = generateRandomPaymentMethod();
        boolean recentChangeInAccountDetails = random.nextInt(100) < 1;

        return new TransactionData(type, amount, transactionTime, transactionLocation, device, paymentMethod, recentChangeInAccountDetails, false);
    }

    private static double generateRandomAmount() {
        double chance = random.nextDouble();
        if (chance < 0.60) {
            return Math.round((1 + (999) * random.nextDouble()) * 100.0) / 100.0;
        } else if (chance < 0.90) {
            return Math.round((1000 + (4000) * random.nextDouble()) * 100.0) / 100.0;
        } else if (chance < 0.95) {
            return Math.round((5000 + (20000) * random.nextDouble()) * 100.0) / 100.0;
        } else if (chance < 0.98) {
            return Math.round((25000 + (75000) * random.nextDouble()) * 100.0) / 100.0;
        } else {
            return Math.round((100000 + (900000) * random.nextDouble()) * 100.0) / 100.0;
        }
    }

    public static ZonedDateTime generateRandomTransactionTime() {
        ZoneId sydneyZone = ZoneId.of("Australia/Sydney");
        ZonedDateTime now = ZonedDateTime.now(sydneyZone);

        int hour;
        if (random.nextFloat() < 0.9) {
            // 90% chance to generate a time between 5 AM and 11 PM
            hour = 5 + random.nextInt(18);
        } else {
            // 10% chance to generate a time between 11 PM and 5 AM
            // This will generate an hour between 0 and 4 or between 23 and 23
            if (random.nextBoolean()) {
                hour = random.nextInt(5);
            } else {
                hour = 23;
            }
        }

        // Generate minutes (0 to 59)
        int minutes = random.nextInt(60);

        return now.withHour(hour).withMinute(minutes).withSecond(0).withNano(0);
    }

    private static TransactionUtils.Country generateRandomCountry() {
        return random.nextInt(800) == 0 ? TransactionUtils.Country.values()[random.nextInt(TransactionUtils.Country.values().length)] : TransactionUtils.Country.AUSTRALIA;
    }

    private static TransactionUtils.DeviceType generateRandomDevice() {
        int chance = random.nextInt(100);
        if (chance < 90) {
            return new TransactionUtils.DeviceType[]{TransactionUtils.DeviceType.MOBILE, TransactionUtils.DeviceType.DESKTOP, TransactionUtils.DeviceType.TABLET}[random.nextInt(3)];
        } else {
            return new TransactionUtils.DeviceType[]{TransactionUtils.DeviceType.ATM, TransactionUtils.DeviceType.POS, TransactionUtils.DeviceType.SMART_WATCH}[random.nextInt(3)];
        }
    }

    private static TransactionUtils.PaymentMethod generateRandomPaymentMethod() {
        int chance = random.nextInt(100);
        if (chance < 60) {
            return new TransactionUtils.PaymentMethod[]{TransactionUtils.PaymentMethod.CREDIT_CARD, TransactionUtils.PaymentMethod.DEBIT_CARD}[random.nextInt(2)];
        } else if (chance < 80) {
            return new TransactionUtils.PaymentMethod[]{TransactionUtils.PaymentMethod.APPLE_PAY, TransactionUtils.PaymentMethod.GOOGLE_WALLET}[random.nextInt(2)];
        } else if (chance < 85) {
            return new TransactionUtils.PaymentMethod[]{TransactionUtils.PaymentMethod.PAYPAL, TransactionUtils.PaymentMethod.ONLINE_BANKING}[random.nextInt(2)];
        } else {
            TransactionUtils.PaymentMethod[] others = {TransactionUtils.PaymentMethod.AMAZON_PAY, TransactionUtils.PaymentMethod.WIRE_TRANSFER, TransactionUtils.PaymentMethod.CASH_APP, TransactionUtils.PaymentMethod.VENMO, TransactionUtils.PaymentMethod.GIFT_CARD};
            return others[random.nextInt(others.length)];
        }
    }

    public static int generateRecentTransactionCount() {
        // Simulate a 10% chance that there are more than 3 transactions in the last 24 hours
        return random.nextInt(10) < 1 ? 4 + random.nextInt(5) : random.nextInt(4);
    }


}
