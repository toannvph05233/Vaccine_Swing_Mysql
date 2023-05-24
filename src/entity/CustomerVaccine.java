package entity;

import java.util.Date;

public class CustomerVaccine {
    private int id;
    private Date vaccinDate = new Date();
    private Date injectAgain = new Date();
    private Customer customer;
    private Vaccine vaccine;

    public CustomerVaccine(int id, Date vaccinDate, Date injectAgain, Customer customer, Vaccine vaccine) {
        this.id = id;
        this.vaccinDate = vaccinDate;
        this.injectAgain = injectAgain;
        this.customer = customer;
        this.vaccine = vaccine;
    }

    public CustomerVaccine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getVaccinDate() {
        return vaccinDate;
    }

    public void setVaccinDate(Date vaccinDate) {
        this.vaccinDate = vaccinDate;
    }

    public Date getInjectAgain() {
        return injectAgain;
    }

    public void setInjectAgain(Date injectAgain) {
        this.injectAgain = injectAgain;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
}
