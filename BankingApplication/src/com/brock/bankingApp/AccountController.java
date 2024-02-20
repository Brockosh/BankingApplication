package com.brock.bankingApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountCreationDTO accountCreationDTO){
        Account newAccount = accountService.addAccount(accountCreationDTO.getAccount(), accountCreationDTO.getCustomerID());
        if(newAccount != null){
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID accountId){
        Account account = accountService.getAccountById(accountId);
        System.out.println("Request for account with ID: " + accountId);
        if (account != null){
            return new ResponseEntity<>(account, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID accountId, @RequestBody Account updatedAccount) {
        Account existingAccount = accountService.updateAccount(accountId, updatedAccount);
        if (existingAccount != null) {
            return new ResponseEntity<>(existingAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID accountId){
        Account accountToDelete = accountService.getAccountById(accountId);
        if (accountToDelete != null){
           accountService.deleteAccount(accountId);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}