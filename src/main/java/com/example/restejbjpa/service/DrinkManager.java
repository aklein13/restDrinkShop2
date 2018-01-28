package com.example.restejbjpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.restejbjpa.domain.Buyer;
import com.example.restejbjpa.domain.Company;
import com.example.restejbjpa.domain.Drink;

@Stateless
public class DrinkManager {

  @PersistenceContext
  EntityManager em;

  public void addDrink(Drink drink) {
    em.persist(drink);
  }

  public Drink getDrink(int id) {
    return em.find(Drink.class, id);
  }

  public List<Drink> init() {
    List<Buyer> buyers = new ArrayList<>();
    Company c1 = new Company("Pepsi Company", "USA");
    Company c2 = new Company("Koca-Kola", "China");
    Buyer b1 = new Buyer("Stefan", "Kowalski", 22);
    Buyer b2 = new Buyer("Stefan", "Nowak", 20);
    buyers.add(b1);
    buyers.add(b2);
    Drink d1 = new Drink("Pepsi", 5.5, 10, c1, b1);
    Drink d2 = new Drink("Sprite", 15.1, 100, c2, buyers);
    Drink d3 = new Drink("Cola", 21.3, 5, c2, b2);
    addDrink(d1);
    addDrink(d2);
    addDrink(d3);
    return getAll();
  }

  @SuppressWarnings("unchecked")
  public List<Drink> getAll() {
    return em.createNamedQuery("drink.all").getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Drink> getWithFilters(Map request) {
    List<Drink> drinkList = em.createNamedQuery("drink.all").getResultList();
    if (drinkList.size() == 0 || request.isEmpty()) return null;
    List<Drink> output = drinkList;
    for (Object filter : request.entrySet()) {
      Map.Entry temp = (Map.Entry) filter;
      System.out.println(temp.getKey().toString());
      switch (temp.getKey().toString()) {
        case "minPrice":
          for (Drink drink : drinkList) {
            if (drink.getPrice() < Double.parseDouble(temp.getValue().toString())) {
              output.remove(drink);
            }
          }
          break;
        case "maxPrice":
          for (Drink drink : drinkList) {
            if (drink.getPrice() > Double.parseDouble(temp.getValue().toString())) {
              output.remove(drink);
            }
          }
          break;
        case "maxAmount":
          for (Drink drink : drinkList) {
            if (drink.getAmount() > Integer.parseInt(temp.getValue().toString())) {
              output.remove(drink);
            }
          }
          break;
        case "minAmount":
          for (Drink drink : drinkList) {
            if (drink.getAmount() < Integer.parseInt(temp.getValue().toString())) {
              output.remove(drink);
            }
          }
          break;
        case "name":
          for (Drink drink : drinkList) {
            if (!drink.getName().toLowerCase().contains(temp.getValue().toString().toLowerCase())) {
              output.remove(drink);
            }
          }
          break;
      }
    }
    return output;
  }

  @SuppressWarnings("unchecked")
  public List<Drink> findByName(String name) {
    return em.createNamedQuery("drink.findByName").setParameter("name", name).getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Drink> findByCompany(String name) {
    return em.createNamedQuery("drink.findByCompany").setParameter("name", name).getResultList();
  }

  public Boolean deleteDrink(int id) {
    Drink drink = em.find(Drink.class, id);
    if (drink == null) return false;
    em.remove(drink);
    return true;
  }

  public void deleteAll() {
    em.createNamedQuery("drink.deleteAll").executeUpdate();
  }

}
