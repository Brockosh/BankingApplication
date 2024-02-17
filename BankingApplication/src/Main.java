//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DatabaseConnector dbConn = new DatabaseConnector();

        dbConn.connect();



        Bank bank = new Bank();

        Human brock = new Human("Brock O'Shea");
        bank.addCustomer(brock);
        bank.createAccountForCustomer(brock.getId(), "Brock's Banking", Account.AccountType.SAVINGS, 870);
        bank.displayAccountDetails(brock.getPersonalAccounts().get(0).getId());


//        HumanDAO hD = new HumanDAO();
//        hD.deleteAllHumans();

    }
}