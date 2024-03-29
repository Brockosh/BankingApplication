package bankingApp.Accounts;

import bankingApp.Users.User;
import bankingApp.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public Optional<Account> createAccount(AccountCreationDTO accountCreationDTO) {
        Optional<User> user = userRepository.findById(accountCreationDTO.getUserId());
        return user.map(u -> {
            Account newAccount = accountCreationDTO.getAccount();
            newAccount.setUser(u);
            if (newAccount.getAccountNumber() == null || accountRepository.existsByAccountNumber(newAccount.getAccountNumber())) {
                newAccount.setAccountNumber(AccountNumberGenerator.generateUniqueAccountNumber(accountRepository));
            }
            return accountRepository.save(newAccount);
        });
    }

    public Optional<Account> getAccountById(UUID id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> updateAccount(UUID id, Account accountDetails) {
        return accountRepository.findById(id).map(existingAccount -> {
            existingAccount.setAccountName(accountDetails.getAccountName());
            existingAccount.setAccountNumber(accountDetails.getAccountNumber());
            existingAccount.setType(accountDetails.getType());
            existingAccount.setBalance(accountDetails.getBalance());
            return accountRepository.save(existingAccount);
        });
    }

    @Transactional
    public Optional<Account> patchUpdateAccount(UUID id, Map<String, Object> updates) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            return Optional.empty();
        }

        Account accountToUpdate = accountOptional.get();
        updates.forEach((key, value) -> {
            switch (key) {
                case "accountName":
                    accountToUpdate.setAccountName((String) value);
                    break;
                case "accountNumber":
                    accountToUpdate.setAccountNumber((Integer) value);
                    break;
                case "type":
                    accountToUpdate.setType(Account.AccountType.valueOf((String) value));
                    break;
                case "balance":
                    accountToUpdate.setBalance((Double) value);
                    break;
            }
        });

        Account updatedAccount = accountRepository.save(accountToUpdate);
        return Optional.of(updatedAccount);
    }

    public boolean deleteAccount(UUID id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}