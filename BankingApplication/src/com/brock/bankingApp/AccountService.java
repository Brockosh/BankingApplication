package com.brock.bankingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountDAO accountDAO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account accountToAdd, UUID customerId) {
        accountDAO.addAccount(accountToAdd, customerId);
        return accountToAdd;
        //Fix this null check issue
    }

    public Account getAccountById(UUID id) {
        return accountDAO.getAccountById(id);
    }

    public Account updateAccount(UUID id, Account updatedAccount) {
        Account existingAccount = accountDAO.getAccountById(id);
        if (existingAccount != null) {
            accountDAO.updateAccount(id, updatedAccount);
            return accountDAO.getAccountById(id);
        }
        return null;
    }

    public boolean deleteAccount(UUID id){
        Account existingAccount = accountDAO.getAccountById(id);

        if(existingAccount != null){
            accountDAO.deleteAccount(id);
            return true;
        }
        return false;
    }
}