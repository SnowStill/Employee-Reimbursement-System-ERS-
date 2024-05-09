package com.revature.controllers;

import com.revature.models.DTOs.IncomingUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
//approving our frontend to talk to this controller
//we're ALSO saying that we're going to allow session data to be passed back and forth
@CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE}, allowCredentials = "true")
public class UserController {

    //autowire user service
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody IncomingUserDTO userDTO, HttpSession session) {
        System.out.println(session.getAttribute("userId"));
        System.out.println(session.getAttribute("role"));
        if (session.getAttribute("userId") == null) {
            return ResponseEntity.status(402).body("You must be logged in to create a user");
        }
        if (!session.getAttribute("role").equals("Employee")) {

            return ResponseEntity.status(403).body("You must be an employee to create a user");
        }
        if(userService.getUserbyUsername(userDTO.getUsername()).isPresent()){
            return ResponseEntity.status(400).body("Username already exists");
        }
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.status(201).body("User created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return ResponseEntity.status(401).body("You must be logged in to view users");
        }

        if (!session.getAttribute("role").equals("Manager")) {
            return ResponseEntity.status(403).body("You must be a manager to view users");
        }
        return ResponseEntity.status(200).body(userService.getUsers());
    }
    //delete use by ID
    @DeleteMapping("/delete/{userId}")
    //@CrossOrigin(origins = "http://localhost:3000/users/delete/", methods = {RequestMethod.DELETE}, allowCredentials = "true")
    public ResponseEntity<String> deleteUser(@PathVariable int userId, HttpSession session){

        //TODO: take in HttpSession to do the necessary checks
        if(session.getAttribute("userId").equals(userId)){
            return ResponseEntity.status(401).body("you can not delete yourself!");
        }
        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok("User was deleted!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody IncomingUserDTO userDTO, HttpSession session){

        //Get the User object from the service (which talks to the DB)
        Optional<User> optionalUser = userService.loginUser(userDTO);
        System.out.println("optionalUser: " + optionalUser);
        //If login fails (which will return an empty optional), tell the user they failed
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(401).body("Login Failed!");
        }

        //If login succeeds store the user info in our session
        User u = optionalUser.get();

        //Storing the user info in our session
        session.setAttribute("userId", u.getUserId());
        session.setAttribute("username", u.getUsername()); //probably won't use this
        session.setAttribute("role", u.getRole());
        //Hypothetical role save to session
        //session.setAttribute("role", u.getRole());

        System.out.println("session: " + session.getAttribute("userId"));
        System.out.println("session: " + session.getAttribute("role"));

        //Finally, send back a 200 (OK) as well as a OutgoingUserDTO
        return ResponseEntity.ok(new OutgoingUserDTO(u.getUserId(), u.getFirstName(), u.getLastName(), u.getUsername(), u.getRole()));

    }
}
