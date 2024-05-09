package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="reimbursements")
public class Reim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbId;
    private int amount;
    private String description;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public Reim() {
    }

    public Reim(int reimbId, String description, int amount, String status, User user) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public Reim(String description, int amount, String status, User user){
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.user = user;
    }


    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }



}