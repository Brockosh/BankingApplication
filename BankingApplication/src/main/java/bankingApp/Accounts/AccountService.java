package bankingApp.Accounts;

import bankingApp.Users.User;
import bankingApp.Users.UserRepository; // Assuming this is correctly placed in your project structure
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Account> createAccount(AccountCreationDTO accountCreationDTO) {
        Optional<User> user = userRepository.findById(accountCreationDTO.getCustomerID());
        return user.map(u -> {
            Account newAccount = accountCreationDTO.getAccount();
            newAccount.setUser(u);
            return accountRepository.save(newAccount);
        });
    }

    public Optional<Account> getAccountById(UUID id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> updateAccount(UUID id, Account accountDetails) {
        return accountRepository.findById(id).map(existingAccount -> {
            // Copy properties from accountDetails to existingAccount as needed
            // For example: existingAccount.setBalance(accountDetails.getBalance());
            return accountRepository.save(existingAccount);
        });
    }

    public boolean deleteAccount(UUID id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }
}