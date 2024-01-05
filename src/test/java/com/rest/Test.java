package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test {

    //Getting the complte response for status code 200
    @org.testng.annotations.Test
    public void test1()
    {
        //Storing the response in the Response Variable and printing it after converting it as String
        Response res =
                given().
                    baseUri("https://api.postman.com"). //base uri
                    header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                when().
                    get("/workspaces").
                then().
                    log().all(). //logging the complete response
                    assertThat().statusCode(200). // Validating the response code
                    extract().response(); // Extracting the complete response
       System.out.println("My Response to print - " +res.asString());

    }

    //Fethcing the name of workspace Method 1
    @org.testng.annotations.Test
    public void test2()
    {
        //Storing the response in the Response Variable and printing it after converting it as String
        Response res =
                given().
                        baseUri("https://api.postman.com"). //base uri
                        header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                when().
                        get("/workspaces").
                then().
                        assertThat().statusCode(200). // Validating the response code
                        extract().response();// Extracting the complete response
        System.out.println("Workspace Name = "  + res.path("workspaces[0].name"));
    }

    //Fethcing the name of workspace Method 2 using JsonPath Object
    @org.testng.annotations.Test
    public void test3()
    {
        //Storing the response in the Response Variable and printing it after converting it as String
        Response res =
                given().
                        baseUri("https://api.postman.com"). //base uri
                        header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                when().
                        get("/workspaces").
                then().
                        assertThat().statusCode(200). // Validating the response code
                        extract().response();// Extracting the complete response
        JsonPath jpath = new JsonPath(res.asString());
        System.out.println("Workspace Name = "  + jpath.getString("workspaces[1].name"));
    }

    //Fethcing the name of workspace Method 3 using JsonPath Object
    @org.testng.annotations.Test
    public void test4()
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
                        extract().response().asString();
        System.out.println("Workspace Name = "  + JsonPath.from(res).getString("workspaces[2].name"));
    }

    //Fethcing the name of workspace Method 4  using JsonPath Object
    @org.testng.annotations.Test
    public void test5()
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
    }

}
