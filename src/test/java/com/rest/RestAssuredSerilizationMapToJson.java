package com.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredSerilizationMapToJson {

    @BeforeClass
    public void requestSpecTemplate(){
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.postman.com");
        requestBuilder.addHeader("X-Api-Key","PMAK-656e931117271661601dd67e-9ca3405d747276a829ea16d0602a043bc5");
        requestBuilder.addHeader("Content-Type","application/json");
        requestBuilder.log(LogDetail.ALL);

        RestAssured.requestSpecification =   requestBuilder.build(); // like this multiple request spec can be created

        ResponseSpecBuilder responseBuilder= new ResponseSpecBuilder();
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectContentType("application/json; charset=utf-8");
        responseBuilder.log(LogDetail.ALL);

        RestAssured.responseSpecification = responseBuilder.build();
    }

    @Test
    public void Test1() throws JsonProcessingException {
        HashMap<String,Object> mainObject = new HashMap<>();

        HashMap<String, String> nestedObject = new HashMap<>();
        nestedObject.put("name","myWorkspace6");
        nestedObject.put("type","personal");
        nestedObject.put("description","map to Json1");

        mainObject.put("workspace",nestedObject);

        // explicitly doing the serilization using object mapper from fasterxml jackson databind
        ObjectMapper objMapper = new ObjectMapper();
        String mainObjStr = objMapper.writeValueAsString(mainObject);
        //-------------- this will throw an exception JsonProcessingException
        given().
                body(mainObject).
        when().
        post("/workspaces").then().assertThat().
                body("workspace.name",equalTo("myWorkspace6"));




    }

}
