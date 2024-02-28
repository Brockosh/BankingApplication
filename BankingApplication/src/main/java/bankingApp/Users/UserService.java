package bankingApp.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

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

    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}