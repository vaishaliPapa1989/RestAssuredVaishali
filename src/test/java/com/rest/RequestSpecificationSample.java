package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationSample {
    // Request Specification is a interface provided by restassured which
    // can be used to reduce the number of lines of coding from each tests case by moving all the
    // request specification steps under the Before class annotation which can be used as
    // in all tests. The request specifications can also be used along with request spec builder
    // static variable RequestSpecification of Restassured

    RequestSpecification requestSpecification;

    @BeforeClass
    public void requestSpecTemplate(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.postman.com");
        requestBuilder.addHeader("X-Api-Key","PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5");
        requestBuilder.log(LogDetail.ALL);

        requestSpecification = requestBuilder.build(); // like this multiple request spec can be created
    }

// Using Request Spec via given().spec(spec) method
    @Test
    public void test1(){
        given().spec(requestSpecification).get("/workspaces").
                then().
                assertThat().statusCode(200). // Validating the response code
                extract().response().path("workspaces[2].name");
    }

    // Using Request Spec via given(spec).get()method
    @Test
    public void test2(){
        given(requestSpecification).get("/workspaces").
                then().
                assertThat().statusCode(200). // Validating the response code
                extract().response().path("workspaces[2].name");
    }
}
