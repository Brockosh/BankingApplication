import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Bank {
    private List<Human> customers;
    private List<Account> activeAccounts;

    public Bank() {
        this.customers = new ArrayList<>();
        this.activeAccounts = new ArrayList<>();
    }


    public void addCustomer(Human human) {
        // Add logic to add a customer to the bank
        customers.add(human);
        // Optionally, save the customer to your database
    }

    public void createAccountForCustomer(UUID customerId, String accountName, Account.AccountType type, double initialBalance) {
        Human human = findCustomerById(customerId);
        if (human != null) {
            int accountNumber = generateUniqueAccountNumber();
            Account newAccount = new Account(accountName, accountNumber, type, initialBalance);
            activeAccounts.add(newAccount);
            human.addAccount(newAccount);
            // Persist the account in the database
        } else {
            System.out.println("Customer not found.");
        }
    }


    public void displayAccountsForCustomer(UUID customerId) {
        Human customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (customer.getPersonalAccounts() == null) {
            System.out.println("No accounts found for customer.");
            return;
        }

        for (Account account : customer.getPersonalAccounts()) {
            System.out.println("Account Name: " + account.getAccountName());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Balance: $" + account.getBalance());
        }
    }

    private Human findCustomerById(UUID customerId) {
        // Assuming `customers` is a List<Human>
        for (Human customer : this.customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private Account findAccountById(Human customer, UUID accountId) {
        for (Account account : customer.getPersonalAccounts()) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    private Account findAccountById( UUID accountId) {

        for (Human customer : customers) {
            for (Account account : customer.getPersonalAccounts()) {
                if (account.getId().equals(accountId)) {
                    return account;
                }
            }
        }
        return null;
    }

    public Account findAccountByNumber(Integer accountNumber) {
        for (Human customer : customers) {
            for (Account account : customer.getPersonalAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }
        }
        System.out.println("Account of number " + accountNumber + " could not be found.");
        return null;
    }



    public void deposit(String accountNumber, double amount) {
        // Update the balance in the database
        // Log the transaction
    }

    public void withdraw(String accountNumber, double amount) {
        // Check if funds are available
        // Update the balance in the database
        // Log the transaction
    }

    public void transfer(Integer fromAccountNumber, Integer toAccountNumber, double amount) {
        Account fromAccount = null;
        Account toAccount = null;

        // Assuming a method findAccountByNumber(String accountNumber) that returns an Account object
        fromAccount = findAccountByNumber(fromAccountNumber);
        toAccount = findAccountByNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            System.out.println("One of the accounts was not found.");
            return;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println("Insufficient funds for the transfer.");
            return;
        }

        // Perform the transfer
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

    }

        public void transfer(UUID fromAccountId, UUID toAccountId, double amount) {
            Account fromAccount = null;
            Account toAccount = null;

            // Assuming a method findAccountById(UUID id) that returns an Account object
            fromAccount = findAccountById(fromAccountId);
            toAccount = findAccountById(toAccountId);

            if (fromAccount == null || toAccount == null) {
                System.out.println("One of the accounts was not found.");
                return;
            }

            if (fromAccount.getBalance() < amount) {
                System.out.println("Insufficient funds for the transfer.");
                return;
            }

            // Perform the transfer
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            // Log the transaction
            //logTransaction(fromAccount, toAccount, amount);
        }



    // UTILITY METHODS
    private boolean accountNumberExists(int accountNumber) {

        for (Account account : activeAccounts) {
            if (account.getAccountNumber() == accountNumber) {
                return true; // The account number already exists
            }
        }

        return false; // The account number does not exist
    }

    public int generateUniqueAccountNumber() {
        Random random = new Random();
        int accountNumber = 1_000_000 + random.nextInt(99_000_000);
        while (accountNumberExists(accountNumber)) {
            accountNumber = 1_000_000 + random.nextInt(99_000_000);
        }
        return accountNumber;
    }
}
