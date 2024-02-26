package com.brock.bankingApp.Transactions;

public class TransactionAnalysisData {
    private TransactionData transaction;
    private int recentTransactionsCount;
    private int label;

    public TransactionAnalysisData(TransactionData transaction, int recentTransactionsCount, int label) {
        this.transaction = transaction;
        this.recentTransactionsCount = recentTransactionsCount;
        this.label = label;
    }

    // Getters
    public TransactionData getTransaction() {
        return transaction;
    }

    public int getRecentTransactionsCount() {
        return recentTransactionsCount;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
