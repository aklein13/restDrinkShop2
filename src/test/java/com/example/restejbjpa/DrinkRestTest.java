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
  private static final String NAME2 = "Sprite";
  private static final String COUNTRY = "Fanta";
  private static final int AMOUNT = 150;
  private static final double PRICE = 2.5;

  @BeforeClass
  public static void setUp() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.basePath = "/restejbjpa/api";
  }

  @Test
  public void deleteAll() {
    Drink d1 = new Drink(NAME, PRICE, AMOUNT, null, null);
    given().contentType(MediaType.APPLICATION_JSON)
        .body(d1)
        .when()
        .post("/drink");
    delete("/drink").then().assertThat().statusCode(200);
    int size = get("/drink").then().extract().jsonPath().getList("$").size();
    assertEquals(0, size);
  }


  @Test
  public void addGame() {
    delete("/drink").then();
    get("/drink").then().assertThat().statusCode(200);
    Company c1 = new Company(NAME, COUNTRY);
    Buyer b1 = new Buyer(NAME, NAME2, AMOUNT);
    Buyer b2 = new Buyer(NAME, NAME2, AMOUNT);
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
        "buyers.get(0).lastName", equalTo(NAME2)
        );
  }

}