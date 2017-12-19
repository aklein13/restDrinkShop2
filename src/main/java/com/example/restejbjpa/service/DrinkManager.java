package com.example.restejbjpa.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

//    @SuppressWarnings("unchecked")
//    public List<Object[]> getGamesOfOwnerByOwnerName(String firstName) {
//        return em.createNamedQuery("drink.findByOwner").setParameter("firstName", firstName).getResultList();
//    }

  @SuppressWarnings("unchecked")
  public List<Drink> getAll() {
    return em.createNamedQuery("drink.all").getResultList();
  }

  @SuppressWarnings("unchecked")
  public List<Drink> getWithFilters(Map request) {
    List<Drink> drinkList = em.createNamedQuery("drink.all").getResultList();
    if (drinkList.size() == 0 || request.isEmpty()) {
      return null;
    }
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
//    Double minPrice = Double.parseDouble(request.get("minPrice") == null ? "0.0" : request.get("minPrice").toString());
//    List<Drink> outputList = new ArrayList<>();
//    for (Drink drink : drinkList) {
//      if (drink.getPrice() > minPrice) {
//        outputList.add(drink);
//      }
//    }
//    return outputList;
  }

  public Boolean deleteDrink(int id) {
    Drink drink = em.find(Drink.class, id);
    if (drink == null) {
      return false;
    }
    em.remove(drink);
    return true;
  }

  public void deletAll() {
    em.createNamedQuery("drink.deleteAll").executeUpdate();
  }

}
