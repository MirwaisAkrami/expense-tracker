package com.example.expensetracker.models;

public class Expense {
    private Double amount;
    private String description;
    private String userEmail;
    private String address;

    public Expense(Double amount, String description, String userEmail, String address) {
        this.amount = amount;
        this.description = description;
        this.userEmail = userEmail;
        this.address = address;
    }

    public Expense() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
