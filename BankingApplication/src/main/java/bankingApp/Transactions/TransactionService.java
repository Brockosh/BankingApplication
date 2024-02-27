package bankingApp.Transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

//    public boolean attemptTransaction(Transaction transaction) {
//        int recentTransactionsCount = transactionDAO.countTransactionsInLast24Hours(transaction.getAccountNumber());
//        boolean isSuspicious = SuspiciousTransactionDetector.isTransactionSuspicious(transaction, recentTransactionsCount);
//        transaction.setSuspicious(isSuspicious);
//
//        if (!isSuspicious) {
//            transactionDAO.addTransaction(transaction);
//            return true;
//        } else {
//            return false;
//        }
//    }

//    private TransactionAnalysisData prepareAnalysisData(Transaction transaction, int recentTransactionsCount) {
//        return new TransactionAnalysisData(transaction, recentTransactionsCount);
//    }

    public void addTransaction(Transaction transaction) {
        int transactionCount = transactionDAO.countTransactionsInLast24Hours(transaction.getAccountNumber());
        //boolean isSuspicious = myMLModel(transaction, transactionCount);
        //transaction.setSuspicious(isSuspicious);
        transactionDAO.addTransaction(transaction);
    }

    public Transaction getTransactionById(UUID transactionId) {
        return transactionDAO.getTransactionById(transactionId);
    }

    public void updateTransaction(UUID transactionId, Transaction transaction) {
        transactionDAO.updateTransaction(transactionId, transaction);
    }

    public void deleteTransaction(UUID transactionId) {
        transactionDAO.deleteTransaction(transactionId);
    }
}
