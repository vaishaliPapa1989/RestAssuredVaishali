package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestAssertion {

    @org.testng.annotations.Test
    public void test1()
    {
        //Storing the response in the Response Variable and printing it after converting it as String
        String res =
                given().
                        baseUri("https://api.postman.com"). //base uri
                        header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                        when().
                        get("/workspaces").
                        then().
                        assertThat().statusCode(200). // Validating the response code
                        extract().response().path("workspaces[2].name");
        System.out.println("Workspace Name = "  + res);
        assertThat(res,equalToIgnoringCase("WorkSpace2"));

    }

    //Failed Assertion
    @org.testng.annotations.Test
    public void test2()
    {
        //Storing the response in the Response Variable and printing it after converting it as String
        String res =
                given().
                        baseUri("https://api.postman.com"). //base uri
                        header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                        when().
                        get("/workspaces").
                        then().
                        assertThat().statusCode(200). // Validating the response code
                        extract().response().path("workspaces[2].name");
        System.out.println("Workspace Name = "  + res);
        assertThat(res,equalToIgnoringCase("WorkSpace21"));
    }

   //Assertion using testNG
    @org.testng.annotations.Test
    public void test3()
    {
        //Storing the response in the Response Variable and printing it after converting it as String
        String res =
                given().
                        baseUri("https://api.postman.com"). //base uri
                        header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                        when().
                        get("/workspaces").
                        then().
                        assertThat().statusCode(200). // Validating the response code
                        extract().response().path("workspaces[2].name");
        System.out.println("Workspace Name = "  + res);
        Assert.assertEquals(res,"WorkSpace2");
    }

}
