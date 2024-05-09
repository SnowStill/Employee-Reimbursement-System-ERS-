package com.revature.services;

import com.revature.models.DTOs.OutgoingUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.DAOs.UserDAO;

import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserDAO userDao;

    @Autowired
    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public User registerUser(IncomingUserDTO userDTO) throws IllegalArgumentException {

        if (userDTO.getUsername().isBlank() || userDTO.getUsername() == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        if (userDTO.getPassword().isBlank() || userDTO.getPassword() == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        //get the user from the user input. The role is set to employee by default
        User user = new User(userDTO.getFirstname(), userDTO.getLastname(), userDTO.getUsername(), userDTO.getPassword(), "Employee");
        return userDao.save(user);
    }

    public Optional<User> loginUser(IncomingUserDTO userDTO) throws IllegalArgumentException {
        System.out.println("userDTO: " + userDTO);
        return userDao.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
    }

    public List<OutgoingUserDTO> getUsers() {
        List<User> users = userDao.findAll();
        List<OutgoingUserDTO> outUsers = new ArrayList<OutgoingUserDTO>();

        //convert regular User objects to OutgoingUserDTO objects
        for (User user : users) {
            OutgoingUserDTO outU = new OutgoingUserDTO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getRole());
            outUsers.add(outU);
        }
        return outUsers;
    }

    public void deleteUser(int userId) {
        userDao.deleteById(userId);
    }
    public Optional<User> getUserbyUsername(String username) {
        return userDao.getByUsername(username);
    }

    public Optional<User> getUserById(int userId) {
        return userDao.findById(userId);
    }
}

