package bankingApp.Users;

import bankingApp.Accounts.Account;
import bankingApp.Accounts.AccountNumberGenerator;
import bankingApp.Accounts.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }


//    public User addUser(User user) {
//        return userRepository.save(user);
//    }


    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setFullName(updatedUser.getFullName());
            return userRepository.save(existingUser);
        }).orElse(null);
    }

    @Transactional
    public User addUser(User user) {
        User savedUser = userRepository.save(user);
//        createDefaultAccountsForUser(savedUser);
        return savedUser;
    }

    private void createDefaultAccountsForUser(User user) {
        Account businessAccount = createAndSaveAccount(user, "Business Account", Account.AccountType.BUSINESS, 2500.0);
        Account savingsAccount = createAndSaveAccount(user, "Savings Account", Account.AccountType.SAVINGS, 2500.0);

        user.addAccount(businessAccount);
        user.addAccount(savingsAccount);
        userRepository.save(user);
    }

    private Account createAndSaveAccount(User user, String accountName, Account.AccountType accountType, double initialBalance) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setType(accountType);
        account.setBalance(initialBalance);
        account.setUser(user);
        return accountRepository.save(account);
    }


    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}