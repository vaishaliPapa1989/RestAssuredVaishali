package com.rest;


import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class LoggingExamples {
    @Test
    public void test1() {
        given().
                baseUri("https://api.postman.com"). //base uri
                header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                log().all(). // Validating everything from request
        when().
                get("/workspaces").
        then().
                log().all(). // Validating everything from response
                assertThat().statusCode(200) // Validating the response code

        ;
    }

    @Test
    public void test2() {
        given().
                baseUri("https://api.postman.com"). //base uri
                header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header

        when().
                get("/workspaces").
        then().
                log().ifValidationFails().statusCode(200) // logs only if validation fails or u can also use iferror()

        ;
    }

    //Blacklisting information in Headers such as user session,  cookies, api keys
    @Test
    public void test3() {
        given().
                baseUri("https://api.postman.com"). //base uri
                header("X-Api-Key", "PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5"). //paasing the api key as header
                //config(config().logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key"))). //blacklisting single header or u can use blacklistheaders to blackinst multiple headers
                log().all().
        when().
                get("/workspaces").
        then().
                log().ifValidationFails().statusCode(201) // logs only if validation fails or u can also use iferror()

        ;
    }
}
