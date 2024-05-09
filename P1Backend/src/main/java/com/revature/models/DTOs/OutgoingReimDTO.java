package com.revature.models.DTOs;

public class OutgoingReimDTO {

    private int reimId;
    private int amount;
    private String description;
    private String status;
    private int userId;

    public OutgoingReimDTO() {
    }

    public OutgoingReimDTO(int  reimId, String description, int amount, String status, int userId) {
        this.reimId = reimId;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public int getReimId() {
        return reimId;
    }

    public void setReimId(int reimId) {
        this.reimId = reimId;
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
        return "OutgoingReimDTO{" +
                "reimbId=" + reimId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }

}
