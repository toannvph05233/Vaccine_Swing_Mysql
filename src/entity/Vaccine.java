package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

public class Vaccine {
    private int id;
    private String name;
    private double price;
    private int count;
    private Date vaccinDate = new Date();

    public Vaccine() {
    }

    public Vaccine(int id, String name, double price, int count, Date vaccinDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.vaccinDate = vaccinDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Date getVaccinDate() {
        return vaccinDate;
    }

    public void setVaccinDate(Date vaccinDate) {
        this.vaccinDate = vaccinDate;
    }

    public Vaccine(int id, String name, double price, Date vaccinDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vaccinDate = vaccinDate;
    }

    @Override
    public String toString() {
        return name;
    }
}
