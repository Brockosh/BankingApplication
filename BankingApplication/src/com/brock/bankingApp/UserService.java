package com.brock.bankingApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final HumanDAO humanDAO; // Assuming you have a HumanDAO class

    @Autowired
    public UserService(HumanDAO humanDAO) {
        this.humanDAO = humanDAO;
    }

    // Add a new user
    public Human addUser(Human user) {
        // You might want to perform some checks or transformations here
        humanDAO.addHuman(user);
        return user;
    }

    // Retrieve a user by ID
    public Human getUserById(UUID id) {
        return humanDAO.getHumanById(id);
    }

    // Update a user's information
    public Human updateUser(UUID id, Human updatedUser) {
        // Check if the user exists
        Human existingUser = humanDAO.getHumanById(id);
        if (existingUser != null) {
            // Assuming you have a method in HumanDAO to update a user
            humanDAO.updateHuman(id, updatedUser.getFullName());
            // Return the updated user
            return humanDAO.getHumanById(id);
        }
        return null;
    }

    // Delete a user
    public boolean deleteUser(UUID id) {
        // Check if the user exists before attempting to delete
        Human existingUser = humanDAO.getHumanById(id);
        if (existingUser != null) {
            humanDAO.deleteHuman(id);
            return true;
        }
        return false;
    }
}
