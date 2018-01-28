package com.example.restejbjpa;

import static com.jayway.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

import javax.ws.rs.core.MediaType;

import com.example.restejbjpa.domain.Drink;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class DrinkRestTest {

  private static final String NAME = "Fanta";
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

    get("/drink").then().assertThat().statusCode(200);

    Drink d1 = new Drink(NAME, PRICE, AMOUNT, null, null);

    ValidatableResponse response = given().
        contentType(MediaType.APPLICATION_JSON).
        body(d1).
        when().
        post("/drink").then();
    response.assertThat().statusCode(200);
    System.out.println(response);
  }

}