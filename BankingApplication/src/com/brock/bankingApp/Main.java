package com.brock.bankingApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        DatabaseConnector dbConn = new DatabaseConnector();

        dbConn.connect();



        Bank bank = new Bank();

        Human brock = new Human("Brock O'Shea");
        bank.addCustomer(brock);
        bank.createAccountForCustomer(brock.getId(), "Brock's Banking", Account.AccountType.SAVINGS, 870);
        bank.displayAccountDetails(brock.getPersonalAccounts().get(0).getId());


//        com.brock.bankingApp.HumanDAO hD = new com.brock.bankingApp.HumanDAO();
//        hD.deleteAllHumans();

    }
}