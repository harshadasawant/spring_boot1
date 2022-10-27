package com.hnd.infinite.entity;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer customerId;
    @Column(name="emailid")
    private String emailId;
    private String name;
    private LocalDate dateOfBirth;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="customer_service",
            joinColumns=@JoinColumn(name="cust_id"),
            inverseJoinColumns=@JoinColumn(name="serv_id")
    )
    private Set<Services> bankServices;
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Set<Services> getBankServices() {
        return bankServices;
    }
    public void setBankServices(Set<Services> bankServices) {
        this.bankServices = bankServices;
    }
    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (this.getCustomerId() == null) {
            if (other.getCustomerId() != null)
                return false;
        } else if (!this.getCustomerId().equals(other.getCustomerId())) {
            return false;
        }
        return true;
    }
}