package com.brock.bankingApp;

import com.brock.bankingApp.Accounts.AccountService;
import com.brock.bankingApp.Users.User;
import com.brock.bankingApp.Users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demoData(AccountService accountService, UserService userService) {
        return args -> {


//
//         UUID idToUse = UUID.randomUUID();
//         UUID secondIdToUse = UUID.randomUUID();
//         System.out.println(idToUse);
//         System.out.println(secondIdToUse);
//
//         User brock = new User(idToUse, "Brock O'Shea");
//         userService.addUser(brock);

         // Account brockAccount = new Account("Brock's Banking", 123456, Account.AccountType.SAVINGS, 870.00);
         // accountService.addAccount(brockAccount, brock.getId());

         // System.out.println("Pre-populated some demo data.");

            //HumanDAO HD = new HumanDAO();
            //HD.deleteAllHumans();

        };
    }
}