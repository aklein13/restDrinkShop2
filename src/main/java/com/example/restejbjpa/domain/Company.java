package com.example.restejbjpa.domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
    @NamedQuery(name = "company.all", query = "Select c from Company c"),
    @NamedQuery(name = "company.deleteAll", query = "Delete from Company "),
}
)
@XmlRootElement
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String country;

  public Company() {
    super();
  }

  public Company(String name, String country) {
    this.name = name;
    this.country = country;
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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
