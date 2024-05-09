package com.revature.controllers;
import com.revature.models.DTOs.IncomingReimDTO;
import com.revature.services.ReimService;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH}, allowCredentials = "true")
public class ReimController {
    private ReimService reimService;
    private UserService userService;
    @Autowired
    public ReimController(ReimService reimService, UserService userService) {
        this.reimService = reimService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createReimbursement(@RequestBody IncomingReimDTO reimDTO, HttpSession session) {
        System.out.println(reimDTO.toString());
        if(session.getAttribute("userId") == null) {
            return new ResponseEntity<>("You must be logged in to create a reimbursement", HttpStatus.UNAUTHORIZED);
        }
        if(session.getAttribute("role").equals("Manager")) {
            return new ResponseEntity<>("You must be an employee to create a reimbursement", HttpStatus.FORBIDDEN);
        }
        if(!reimDTO.getStatus().equals("Pending") && !reimDTO.getStatus().equals("Approved") && !reimDTO.getStatus().equals("Declined")) {
            return new ResponseEntity<>("Reimbursement status is not valid!", HttpStatus.BAD_REQUEST);
        }
        if(userService.getUserById(reimDTO.getUserId()).isEmpty()) {
            return new ResponseEntity<>("The account id does not exist.", HttpStatus.BAD_REQUEST);
        }
        try {
            reimService.createReimbursement(reimDTO);
            return new ResponseEntity<>("Reimbursement created", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listall")
    public ResponseEntity<?> getAllReimbursements(HttpSession session){
        if(session.getAttribute("userId") == null) {
            return new ResponseEntity<>("You must be logged in to view reimbursements", HttpStatus.UNAUTHORIZED);
        }

        if (!session.getAttribute("role").equals("Manager")) {
            return new ResponseEntity<>("You must be a manager to view reimbursements", HttpStatus.FORBIDDEN);
        }
        //System.out.println(reimService.getAllReimbursements().toString());
        return new ResponseEntity<>(reimService.getAllReimbursements(), HttpStatus.OK);
    }

    @GetMapping("/listown")
    public ResponseEntity<?> getOwnReimbursements(HttpSession session){

        if(session.getAttribute("userId") == null) {
            return new ResponseEntity<>("You must be logged in to view reimbursements", HttpStatus.UNAUTHORIZED);
        }

        if (!session.getAttribute("role").equals("Employee")) {
            return new ResponseEntity<>("You must be an employee to view your reimbursements", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(reimService.getReimbursementsByUserId((int) session.getAttribute("userId")), HttpStatus.OK);
    }

    @GetMapping("/listallpending")
    public ResponseEntity<?> getAllPendingReimbursements(HttpSession session){
        if(session.getAttribute("userId") == null) {
            return new ResponseEntity<>("You must be logged in to view reimbursements", HttpStatus.UNAUTHORIZED);
        }

        if (!session.getAttribute("role").equals("Manager")) {
            return new ResponseEntity<>("You must be a manager to view all pending reimbursements", HttpStatus.FORBIDDEN);
        }
        System.out.println(reimService.getReimbursementsByStatus("Pending").toString());
        return new ResponseEntity<>(reimService.getReimbursementsByStatus("Pending"), HttpStatus.OK);
    }

    @GetMapping("/listallownpending")
    public ResponseEntity<?> getOwnPendingReimbursements(HttpSession session){
        if(session.getAttribute("userId") == null) {
            return new ResponseEntity<>("You must be logged in to view reimbursements", HttpStatus.UNAUTHORIZED);
        }

        if (!session.getAttribute("role").equals("Employee")) {
            return new ResponseEntity<>("You must be an employee to view your pending reimbursements", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(reimService.getReimbursementsByStatusAndUserId("Pending", (int)session.getAttribute("userId")), HttpStatus.OK);
    }

    @PatchMapping("/approve/{reimId}")
    public ResponseEntity<String> approveReimbursement(@PathVariable int reimId, HttpSession session) {

        try {
            reimService.updateReimbursementStatus(reimId, "Approved");
            return new ResponseEntity<>("Reimbursement resolved", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/decline/{reimId}")
    public ResponseEntity<String> declineReimbursement(@PathVariable int reimId, HttpSession session) {

        try {
            reimService.updateReimbursementStatus(reimId, "Declined");
            return new ResponseEntity<>("Reimbursement resolved", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
