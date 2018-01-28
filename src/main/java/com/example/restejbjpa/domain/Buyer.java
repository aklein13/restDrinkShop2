package com.example.restejbjpa.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class Buyer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;
  public String firstName;
  public String lastName;
  public int age;

  @ManyToMany(mappedBy = "buyers", fetch = FetchType.EAGER)
  public List<Drink> drinks = new ArrayList<>();

  public Buyer(String firstName, String lastName, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public Buyer() {
    super();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

//  TODO workaround for recursion here?
  public List<Drink> getDrinks() {
    return null;
  }

  public List<Drink> listDrinks() {
    return drinks;
  }

  public void setDrinks(List<Drink> drinks) {
    this.drinks = drinks;
  }

//  public void addDrinks(List<Drink> drinks) {
//    this.setDrinks(drinks);
//    for (Drink drink : drinks) drink.getBuyers().add(this);
//  }
}
