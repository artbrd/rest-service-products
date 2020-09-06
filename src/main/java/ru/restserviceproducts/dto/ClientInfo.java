package ru.restserviceproducts.dto;

public class ClientInfo {
    private Long salary;
    private Long claim;
    private Boolean isDebtor;

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getClaim() {
        return claim;
    }

    public void setClaim(Long claim) {
        this.claim = claim;
    }

    public Boolean getIsDebtor() {
        return isDebtor;
    }

    public void setIsDebtor(Boolean debtor) {
        isDebtor = debtor;
    }
}
