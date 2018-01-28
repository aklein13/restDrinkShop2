package com.example.restejbjpa.domain;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
        @NamedQuery(name = "drink.all", query = "Select g from Drink g"),
        @NamedQuery(name = "drink.deleteAll", query = "Delete from Drink")}
//        @NamedQuery(name = "drink.findByYor", query = "Select b from Drink b where b.yearOfRelease = :yearOfRelease")}
        //	@NamedQuery(name = "drink.findByOwner",
//	query = "Select a.firstName, a.lastName, b.name from Drink b JOIN b.buyer a where a.firstName = :firstName")
//	}
)
@XmlRootElement
public class Drink {

    private String Name = "";
    private double Price;
    private int Amount;
    private int id;

    private Company company;
    private List<Buyer> buyer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Buyer> getBuyer() {
        return buyer;
    }

    public void setBuyer(List<Buyer> buyer) {
        this.buyer = buyer;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        this.Price = price;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        this.Amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
