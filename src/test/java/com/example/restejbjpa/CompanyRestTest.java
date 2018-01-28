package com.example.restejbjpa;

import com.example.restejbjpa.domain.Company;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;

public class CompanyRestTest {

  private static final String NAME = "Fanta Comp";
  private static final String COUNTRY = "Poland";

  @BeforeClass
  public static void setUp() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.basePath = "/restejbjpa/api";
  }

  public void clearCompanies() {
    delete("/company").then();
  }

  public void sendCompany(Company company) {
    given().
        contentType(MediaType.APPLICATION_JSON).
        body(company).
        when().
        post("/company").then();
  }

  public int getSize() {
    return get("/company").then().extract().jsonPath().getList("$").size();
  }

  @Test
  public void addCompany() {
    clearCompanies();
    Company c1 = new Company(NAME, COUNTRY);
    sendCompany(c1);
    assertEquals(1, getSize());
    ValidatableResponse response = get("/company").then();
    response.assertThat().statusCode(200);
    response.body("[0].name", equalTo(NAME),
        "[0].country", equalTo(COUNTRY)
    );
  }

  @Test
  public void deleteAll() {
    Company c1 = new Company(NAME, COUNTRY);
    sendCompany(c1);
    delete("/company").then().assertThat().statusCode(200);
    assertEquals(0, getSize());
  }

}