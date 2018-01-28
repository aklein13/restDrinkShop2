package com.example.restejbjpa;

import static com.jayway.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.apache.commons.lang3.StringUtils.containsOnly;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.ws.rs.core.MediaType;

import com.example.restejbjpa.domain.Buyer;
import com.example.restejbjpa.domain.Company;
import com.example.restejbjpa.domain.Drink;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public class DrinkRestTest {

  private static final String NAME = "Fanta";
  private static final String NAME1 = "Mirinda";
  private static final String NAME2 = "Sprite";
  private static final String NAME3 = "Cola";
  private static final String COUNTRY = "Fanta";
  private static final int AMOUNT = 150;
  private static final int AGE = 15;
  private static final int AGE2 = 20;
  private static final double PRICE = 2.5;

  @BeforeClass
  public static void setUp() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.basePath = "/restejbjpa/api";
  }

  public void clearDrinks() {
    delete("/drink").then();
  }

  public void sendDrink(Drink drink) {
    given().
        contentType(MediaType.APPLICATION_JSON).
        body(drink).
        when().
        post("/drink").then();
  }

  public int getSize() {
    return get("/drink").then().extract().jsonPath().getList("$").size();
  }

  @Test
  public void addSimpleDrink() {
    clearDrinks();
    Drink d1 = new Drink(NAME, PRICE, AMOUNT);
    sendDrink(d1);
    assertEquals(1, getSize());
//    get("/drink/").then().assertThat().statusCode(404);
    ValidatableResponse response = get("/drink").then();
    response.assertThat().statusCode(200);
    response.body("[0].name", equalTo(NAME),
        "[0].amount", equalTo(AMOUNT)
    );
  }

  @Test
  public void deleteAll() {
    Drink d1 = new Drink(NAME, PRICE, AMOUNT);
    sendDrink(d1);
    delete("/drink").then().assertThat().statusCode(200);
    assertEquals(0, getSize());
  }

  @Test
  public void addDrink() {
    clearDrinks();
    get("/drink").then().assertThat().statusCode(200);
    Company c1 = new Company(NAME, COUNTRY);
    Buyer b1 = new Buyer(NAME, NAME2, AGE);
    Buyer b2 = new Buyer(NAME, NAME3, AGE2);
    List<Buyer> buyers = new ArrayList<>();
    buyers.add(b1);
    buyers.add(b2);
    Drink d1 = new Drink(NAME, PRICE, AMOUNT, c1, buyers);
    ValidatableResponse response = given().
        contentType(MediaType.APPLICATION_JSON).
        body(d1).
        when().
        post("/drink").then();
    response.assertThat().statusCode(200);
    response.body("name", equalTo(NAME),
        "amount", equalTo(AMOUNT),
        "buyers.size()", equalTo(2),
        "buyers.get(0).lastName", equalTo(NAME2),
        "buyers.get(0).age", equalTo(AGE),
        "buyers.get(1).age", equalTo(AGE2),
        "buyers.get(0).firstName", equalTo(NAME),
        "buyers.get(1).lastName", equalTo(NAME3),
        "company.name", equalTo(NAME),
        "company.country", equalTo(COUNTRY)
    );
  }

  @Test
  public void searchByName() {
    clearDrinks();
    Drink d1 = new Drink(NAME, PRICE, AMOUNT);
    Drink d2 = new Drink(NAME2, PRICE, AMOUNT);
    sendDrink(d2);
    sendDrink(d1);
    ValidatableResponse response = get("/drink/query/name/" + NAME).then();
    response.assertThat().statusCode(200);
    response.body("[0].name", equalTo(NAME));
  }

  @Test
  public void searchByCompanyName() {
    clearDrinks();
    Company c1 = new Company(NAME1, COUNTRY);
    Company c2 = new Company(NAME2, COUNTRY);
    Drink d1 = new Drink(NAME, PRICE, AMOUNT, c1);
    Drink d2 = new Drink(NAME, PRICE, AMOUNT, c2);
    sendDrink(d2);
    sendDrink(d1);
    ValidatableResponse response = get("/drink/query/findByCompany/" + NAME1).then();
    response.assertThat().statusCode(200);
    response.body("[0].company.name", equalTo(NAME1));
  }

  @Test
  public void init() {
    clearDrinks();
    post("/drink/init").then();
    assertEquals(3, getSize());
  }

}