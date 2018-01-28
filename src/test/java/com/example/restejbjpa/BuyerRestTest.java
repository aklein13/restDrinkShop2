package com.example.restejbjpa;

import com.example.restejbjpa.domain.Buyer;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;

public class BuyerRestTest {

  private static final String NAME = "Tomasz";
  private static final String NAME2 = "Kowalski";
  private static final int AGE = 20;

  @BeforeClass
  public static void setUp() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.basePath = "/restejbjpa/api";
  }

  public void clearBuyers() {
    delete("/buyer").then();
  }

  public void sendBuyer(Buyer buyer) {
    given().
        contentType(MediaType.APPLICATION_JSON).
        body(buyer).
        when().
        post("/buyer").then();
  }

  public int getSize() {
    return get("/buyer").then().extract().jsonPath().getList("$").size();
  }

//  @Test
//  public void addBuyer() {
//    clearBuyers();
//    Buyer b1 = new Buyer(NAME, NAME2, AGE);
//    sendBuyer(b1);
//    assertEquals(1, getSize());
//    ValidatableResponse response = get("/buyer").then();
//    response.assertThat().statusCode(200);
//    response.body("[0].firstName", equalTo(NAME),
//        "[0].lastName", equalTo(NAME),
//        "[0].age", equalTo(AGE)
//    );
//  }

  @Test
  public void deleteAll() {
    Buyer b1 = new Buyer(NAME, NAME2, AGE);
    sendBuyer(b1);
    delete("/buyer").then().assertThat().statusCode(200);
    assertEquals(0, getSize());
  }

}