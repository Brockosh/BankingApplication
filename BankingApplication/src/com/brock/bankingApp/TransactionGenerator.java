package com.brock.bankingApp;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class TransactionGenerator {

    private static final Random random = new Random();

    public static Transaction generateTransaction() {
        String accountNumber = UUID.randomUUID().toString();
        TransactionUtils.TransferType type = TransactionUtils.TransferType.values()[random.nextInt(TransactionUtils.TransferType.values().length)];
        double amount = generateRandomAmount();
        UUID destinationAccount = UUID.randomUUID();
        float transactionTime = generateRandomTransactionTime();
        TransactionUtils.Country transactionLocation = generateRandomCountry();
        TransactionUtils.DeviceType device = generateRandomDevice();
        TransactionUtils.PaymentMethod paymentMethod = generateRandomPaymentMethod();
        boolean recentChangeInAccountDetails = random.nextInt(100) < 1;

        return new Transaction(accountNumber, type, amount, destinationAccount, transactionTime,
                transactionLocation, device, paymentMethod, recentChangeInAccountDetails);
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

    private static float generateRandomTransactionTime() {
        // Generate hours (0 to 23)
        int hours = random.nextInt(24);

        // Generate minutes (0 to 59)
        int minutes = random.nextInt(60);

        // Combine hours and minutes into a float format
        return hours + (float) minutes / 100;
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


}
