package com.brock.bankingApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demoData(AccountService accountService, UserService userService) {
        return args -> {

            UUID idToUse = UUID.randomUUID();
            System.out.println(idToUse);

            Human brock = new Human(idToUse, "Brock O'Shea");
            userService.addUser(brock);

            Account brockAccount = new Account("Brock's Banking", 123456, Account.AccountType.SAVINGS, 870.00);
            accountService.addAccount(brockAccount, brock.getId());

            System.out.println("Pre-populated some demo data.");
        };
    }
}