//package com.brock.bankingApp;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TransactionAnalysis {
//
////    public static void main(String[] args) {
////        List<Transaction> transactions = generateTransactions(100); // Generate 10 transactions for analysis
////        int suspiciousCount = 0;
////
////        for (Transaction transaction : transactions) {
////            boolean isSuspicious = SuspiciousTransactionDetector.isTransactionSuspicious(transaction);
////            transaction.setSuspicious(isSuspicious);
////            logTransactionDetails(transaction);
////            if (isSuspicious) {
////                suspiciousCount++;
////            }
////        }
//
//        // After processing all transactions, log the counts
//        System.out.println("--------------------------------------------------");
//        System.out.println("Total Transactions Processed: " + transactions.size());
//        System.out.println("Suspicious Transactions: " + suspiciousCount);
//        System.out.println("Normal Transactions: " + (transactions.size() - suspiciousCount));
//    }
//
//
////    private static List<Transaction> generateTransactions(int count) {
////        List<Transaction> transactions = new ArrayList<>();
////        for (int i = 0; i < count; i++) {
////            transactions.add(TransactionGenerator.generateTransaction());
////        }
////        return transactions;
////    }
//
//    private static void logTransactionDetails(Transaction transaction) {
//        System.out.println(transaction); // Assuming Transaction.toString() method prints all relevant details
//        if (transaction.isSuspicious()) {
//            System.out.println("Reasons for suspicion:");
//            if (SuspiciousTransactionDetector.checkTransactionAmount(transaction)) System.out.println("- High Transaction Amount");
//            if (SuspiciousTransactionDetector.checkTransactionTime(transaction)) System.out.println("- Unusual Transaction Time");
//            if (SuspiciousTransactionDetector.checkForeignTransaction(transaction)) System.out.println("- Sudden Foreign Transaction");
//            if (SuspiciousTransactionDetector.checkDeviceAnomalies(transaction)) System.out.println("- Device Anomalies");
//            if (SuspiciousTransactionDetector.checkRecentAccountChange(transaction)) System.out.println("- Recent Account Change");
//            if (SuspiciousTransactionDetector.checkPaymentMethodForHighAmount(transaction)) System.out.println("- Rare Payment Method for High Amount");
//            if (SuspiciousTransactionDetector.checkDeviceAndLocationMismatch(transaction)) System.out.println("- Device and Location Mismatch");
//            System.out.println("-----------------------------------");
//        }
//    }
//}
