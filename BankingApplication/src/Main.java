//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Initialize the bank
        Bank bank = new Bank();

        // Create a human/customer (assuming the Bank class has a method to handle this)
        Human brock = new Human("Brock O'Shea");
        bank.addCustomer(brock);

        // Add accounts for Brock through the bank
        bank.createAccountForCustomer(brock.getId(), "My Savings", Account.AccountType.SAVINGS, 500.00);
        bank.createAccountForCustomer(brock.getId(), "My Business", Account.AccountType.BUSINESS, 200.00);

        bank.transfer(brock.getPersonalAccounts().get(0).getId(), brock.getPersonalAccounts().get(1).getId(),350);

        // Assuming Bank class has methods to fetch and display account details for a customer
        bank.displayAccountsForCustomer(brock.getId());


    }

}