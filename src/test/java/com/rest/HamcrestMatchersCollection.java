package com.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersCollection {

    //Collection Methods
    @org.testng.annotations.Test
    public void test1()
    {
        //exapmles of collection assesrtion using Hamcrest methods

                given().
                        baseUri("https://api.postman.com"). //base uri
                        header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                when().
                        get("/workspaces").
                then().
                        assertThat().statusCode(200). // Validating the response code
                        body("workspaces.name",contains("My Workspace","amitqam_workspace","WorkSpace2"), //check if all elemnets present in same order
                        "workspaces.name",containsInAnyOrder("My Workspace","WorkSpace2","amitqam_workspace"), //check if all elemnets present in any order
                        "workspaces.name",hasSize(3),//checks the size of the list returned at the given path
                        "workspaces.name",hasItem("My Workspace"), //
                        "workspaces.name",not(hasItem("amitqam_workspace1")) , //checks if the given item is present in the list returned at the given path
                        "workspaces.name",everyItem(containsStringIgnoringCase("workspace"))
                );
    }

}
