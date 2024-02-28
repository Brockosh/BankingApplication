package bankingApp.Accounts;

import java.util.UUID;

public class AccountCreationDTO {
    private UUID userId;
    private Account account;

    public UUID getUserId() { return userId; }
    public Account getAccount() { return account; }
}