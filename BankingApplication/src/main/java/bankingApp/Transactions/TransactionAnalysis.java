//package bankingApp.Transactions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TransactionAnalysis {
//
//    public static void main(String[] args) {
//        List<TransactionAnalysisData> analysisDataList = generateLabeledTransactions(100);
//
//        // Log the results
//        logTransactionAnalysisResults(analysisDataList);
//    }
//
//    private static List<TransactionAnalysisData> generateLabeledTransactions(int count) {
//        List<TransactionAnalysisData> analysisDataList = new ArrayList<>();
//
//        for (int i = 0; i < count; i++) {
//            TransactionData transaction = TransactionGenerator.generateTransactionData();
//            int recentTransactionsCount = TransactionGenerator.generateRecentTransactionCount();
//
//            // Determine if the transaction is suspicious
//            boolean isSuspicious = SuspiciousTransactionDetector.isTransactionSuspicious(transaction, recentTransactionsCount);
//            int label = isSuspicious ? 1 : 0;
//
//            TransactionAnalysisData analysisData = new TransactionAnalysisData(transaction, recentTransactionsCount, label);
//            analysisDataList.add(analysisData);
//        }
//
//        return analysisDataList;
//    }
//
//    private static void logTransactionAnalysisResults(List<TransactionAnalysisData> analysisDataList) {
//        int suspiciousCount = 0;
//
//        for (TransactionAnalysisData analysisData : analysisDataList) {
//            if (analysisData.getLabel() == 1) {
//                suspiciousCount++;
//                logTransactionDetails(analysisData.getTransaction(), analysisData.getRecentTransactionsCount());
//            }
//        }
//
//        // After processing all transactions, log the counts
//        System.out.println("--------------------------------------------------");
//        System.out.println("Total Transactions Processed: " + analysisDataList.size());
//        System.out.println("Suspicious Transactions: " + suspiciousCount);
//        System.out.println("Normal Transactions: " + (analysisDataList.size() - suspiciousCount));
//    }
//
//    private static void logTransactionDetails(TransactionData transaction, int recentTransactionsCount) {
//        System.out.println(transaction);
//        if (transaction.isSuspicious()) {
//            System.out.println("Reasons for suspicion:");
//            if (SuspiciousTransactionDetector.checkTransactionAmount(transaction)) System.out.println("- High Transaction Amount");
//            if (SuspiciousTransactionDetector.checkTransactionTime(transaction)) System.out.println("- Unusual Transaction Time");
//            if (SuspiciousTransactionDetector.checkForeignTransaction(transaction)) System.out.println("- Sudden Foreign Transaction");
//            if (SuspiciousTransactionDetector.checkDeviceAnomalies(transaction)) System.out.println("- Device Anomalies");
//            if (SuspiciousTransactionDetector.checkRecentAccountChange(transaction)) System.out.println("- Recent Account Change");
//            if (SuspiciousTransactionDetector.checkPaymentMethodForHighAmount(transaction)) System.out.println("- Rare Payment Method for High Amount");
//            if (SuspiciousTransactionDetector.checkDeviceAndLocationMismatch(transaction)) System.out.println("- Device and Location Mismatch");
//            if (SuspiciousTransactionDetector.checkRecentTransactionCount(recentTransactionsCount)) System.out.println("- Recent transactions = " + recentTransactionsCount);
//            System.out.println("-----------------------------------");
//        }
//    }
//}
