import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Human {

    private UUID id;
    private String fullName;
    private List<Account> personalAccounts;

    public Human(String fullName)
    {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.personalAccounts = new ArrayList<>();
    }

    public void addAccount(Account newAccount) {

        personalAccounts.add(newAccount);
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Account> getPersonalAccounts() {
        return personalAccounts;
    }


    public boolean transferMoney(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccountByNumber(fromAccountNumber);
        Account toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount != null && toAccount != null && fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }



    //Utility functions
    private Account findAccountByNumber(String accountNumber) {
        for (Account account : personalAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Cannot find account of number " + accountNumber);
        return null;
    }

}
