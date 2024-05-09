package com.revature.models.DTOs;

import com.revature.models.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class IncomingReimDTO {
    private int amount;
    private String description;
    private String status;
    private int userId;

    public IncomingReimDTO() {
    }

    public IncomingReimDTO(String description, int  amount, String status, int userId) {

        this.amount = amount;
        this.description = description;
        this.status = status;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "IncomingReimDTO{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }

}
