package com.brock.bankingApp;

import java.util.UUID;

public class AccountCreationDTO {
    private UUID customerId;
    private Account account;

    public UUID getCustomerID() { return customerId; }
    public Account getAccount() { return account; }
}