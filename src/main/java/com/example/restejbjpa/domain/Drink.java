package com.example.restejbjpa.domain;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = "drink.all", query = "Select d from Drink d"),
    @NamedQuery(name = "drink.deleteAll", query = "Delete from Drink"),
    @NamedQuery(name = "drink.findByName", query = "Select d from Drink d where d.name = :name"),
    @NamedQuery(name = "drink.findByCompany",
        query = "Select d from Drink d JOIN d.company c where c.name = :name"),
}
)
@XmlRootElement
public class Drink {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;
  public String name = "";
  public double price;
  public int amount;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  public Company company;

  public Drink(String name, double price, int amount) {
    this.name = name;
    this.price = price;
    this.amount = amount;
  }

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  public List<Buyer> buyers;

  public Drink() {
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public List<Buyer> getBuyers() {
    return buyers;
  }

  public void setBuyers(List<Buyer> buyer) {
    this.buyers = buyer;
  }

  public void addBuyers(List<Buyer> buyers) {
    this.setBuyers(buyers);
    for (Buyer buyer : buyers) {
      buyer.listDrinks().add(this);
    }
  }

  public Drink(String name, double price, int amount, Company company, List<Buyer> buyers) {
    this.name = name;
    this.price = price;
    this.amount = amount;
    this.company = company;
    this.buyers = buyers;
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

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

}
