package com.brock.bankingApp;

public class TransactionAnalysisData {
    private Transaction transaction;
    private int recentTransactionsCount;
    private int label;

    public TransactionAnalysisData(Transaction transaction, int recentTransactionsCount, int label) {
        this.transaction = transaction;
        this.recentTransactionsCount = recentTransactionsCount;
        this.label = label;
    }

    // Getters
    public Transaction getTransaction() {
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
