package com.brock.bankingApp.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {

    private final HumanDAO humanDAO;

    @Autowired
    public UserService(HumanDAO humanDAO) {
        this.humanDAO = humanDAO;
    }

    public Human addUser(Human user) {
        if (user.getId() == null){
            user.setRandomID();
        }
        System.out.println("Generated ID: " + user.getId());
        humanDAO.addHuman(user);
        return user;
    }

    public Human getUserById(UUID id) {
        return humanDAO.getHumanById(id);
    }

    public Human updateUser(UUID id, Human updatedUser) {
        // Check if the user exists
        Human existingUser = humanDAO.getHumanById(id);
        if (existingUser != null) {
            humanDAO.updateHuman(id, updatedUser.getFullName());
            return humanDAO.getHumanById(id);
        }
        return null;
    }

    public boolean deleteUser(UUID id) {
        Human existingUser = humanDAO.getHumanById(id);
        if (existingUser != null) {
            humanDAO.deleteHuman(id);
            return true;
        }
        return false;
    }
}
