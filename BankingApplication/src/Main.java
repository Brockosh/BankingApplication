//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();

        Human brock = new Human("Brock O'Shea");
        bank.addCustomer(brock);

        bank.createAccountForCustomer(brock.getId(), "My Savings", Account.AccountType.SAVINGS, 500.00);
        bank.createAccountForCustomer(brock.getId(), "My Business", Account.AccountType.BUSINESS, 200.00);

        bank.transfer(brock.getPersonalAccounts().get(0).getId(), brock.getPersonalAccounts().get(1).getId(),350);

        bank.displayAccountsForCustomer(brock.getId());
    }
}