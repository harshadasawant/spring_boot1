package com.hnd.infinite.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Services {
    @Id
    private Integer serviceId;
    private String serviceName;
    private Integer serviceCost;
    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public Integer getServiceCost() {
        return serviceCost;
    }
    public void setServiceCost(Integer serviceCost) {
        this.serviceCost = serviceCost;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getServiceId() == null) ? 0 : this.getServiceId().hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Services other = (Services) obj;
        if (this.getServiceId() == null) {
            if (other.getServiceId() != null)
                return false;
        } else if (!this.getServiceId().equals(other.getServiceId())) {
            return false;
        }
        return true;
    }

}

