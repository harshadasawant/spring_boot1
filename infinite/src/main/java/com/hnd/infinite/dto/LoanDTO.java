package com.hnd.infinite.dto;

import java.time.LocalDate;
public class LoanDTO {

    private Integer loanId;
    private Double amount;
    private LocalDate loanIssueDate;
    private CustomerDTO customer;
    private String status;
    public Integer getLoanId() {
        return loanId;
    }
    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public LocalDate getLoanIssueDate() {
        return loanIssueDate;
    }
    public void setLoanIssueDate(LocalDate loanIssueDate) {
        this.loanIssueDate = loanIssueDate;
    }
    public CustomerDTO getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "LoanDTO [loanId=" + loanId + ", amount=" + amount + ", loanIssueDate=" + loanIssueDate + ", customer="
                + customer + ", status=" + status + "]";
    }

}