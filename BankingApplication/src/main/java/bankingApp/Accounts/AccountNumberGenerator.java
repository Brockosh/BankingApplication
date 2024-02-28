package bankingApp.Accounts;

import java.util.Random;

public class AccountNumberGenerator {

    public static Integer generateUniqueAccountNumber(AccountRepository accountRepository) {
        Random random = new Random();
        int accountNumber = random.nextInt(999999999);
        while (accountRepository.existsByAccountNumber(accountNumber)) {
            accountNumber = random.nextInt(999999999);
        }
        return accountNumber;
    }
}
