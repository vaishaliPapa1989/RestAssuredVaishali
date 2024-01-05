package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersMap {

    @Test
    public void test1(){
        given().
                baseUri("https://api.postman.com"). //base uri
                header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
        when().
                get("/workspaces").
        then().
                assertThat().statusCode(200). // Validating the response code
                body("workspaces[0]",hasKey("id"),
                "workspaces[0]",hasValue("personal"),
                "workspaces[0]",hasEntry( "name","My Workspace")
        );
    }
}
