package bankingApp;

import bankingApp.Accounts.AccountService;
import bankingApp.Users.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demoData(AccountService accountService, UserService userService) {
        return args -> {


        };
    }
}