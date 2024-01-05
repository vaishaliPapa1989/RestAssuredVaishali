package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResponseSpecificationDefault {

//    RequestSpecification requestSpecification;
//    ResponseSpecification responseSpecification;

    @BeforeClass
    public void requestSpecTemplate(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.postman.com");
        requestBuilder.addHeader("X-Api-Key","PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5");
        requestBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification =   requestBuilder.build(); // like this multiple request spec can be created

        ResponseSpecBuilder responseBuilder= new ResponseSpecBuilder();
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectContentType("application/json; charset=utf-8");
        responseBuilder.log(LogDetail.ALL);

        RestAssured.responseSpecification = responseBuilder.build();
    }

    // Using Request Spec via given().spec(spec) method
    @Test
    public void test1(){
        given().get("/workspaces");
    }

    // Using Request Spec via given(spec).get()method
    @Test
    public void test2(){
        given().get("/workspaces").
                then().
                extract().response().path("workspaces[2].name");
    }
}
