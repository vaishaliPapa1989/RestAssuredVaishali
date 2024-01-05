package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;

public class RequestSpecificationDefault {
    // Default Request specification can be created once for a class and be used in all the tests
    @BeforeClass
    public void requestSpecTemplate(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.postman.com");
        requestBuilder.addHeader("X-Api-Key","PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5");
        requestBuilder.log(LogDetail.ALL);
        RestAssured.requestSpecification = requestBuilder.build(); // This statement will create default request specification which will be implicitly called by all tests
    }


    @org.testng.annotations.Test
    public void test1(){
        given().
                when().get("/workspaces").
                then().
                assertThat().statusCode(200). // Validating the response code
                extract().response().path("workspaces[2].name");
    }


    @Test
    public void test2(){
        given().get("/workspaces").
                then().
                assertThat().statusCode(200). // Validating the response code
                extract().response().path("workspaces[2].name");
    }
}
