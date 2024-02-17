import java.util.UUID;

public class Account {

    public enum AccountType {
        SAVINGS, CREDIT, BUSINESS
    }
    private UUID id;

    private String accountName;
    private Integer accountNumber;
    private AccountType type;
    private double balance;

    public Account(String aName, Integer aNum, AccountType type, double bal)
    {
        this.id = UUID.randomUUID();
        this.accountName = aName;
        this.accountNumber = aNum;
        this.type = type;
        this.balance = bal;
    }
    public Account(UUID id, String aName, Integer aNum, AccountType type, double bal)
    {
        this.id = id;
        this.accountName = aName;
        this.accountNumber = aNum;
        this.type = type;
        this.balance = bal;
    }


    // Getters and Setters

    public UUID getId()
    {
        return id;
    }
    public String getAccountName() {
        return accountName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    public AccountType getType() { return this.type; }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    //endRegion

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            // Add transaction logging down the track
        } else {
            System.out.println("Cannot deposit negative amount.");
        }

    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            // Add transaction logging down the track
            return true;
        }
        System.out.println("Insufficient funds or negative amount.");
        return false;
    }


}
