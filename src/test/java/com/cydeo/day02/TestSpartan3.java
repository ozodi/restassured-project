package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.helpers.AnnotationRegistry.reset;

public class TestSpartan3 {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://50.17.84.26:8000";
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }


    @Test
    public void testAllSpartan(){

        Response response = get("/spartans");

        //response.prettyPrint();

        Assertions.assertEquals(200,response.getStatusCode());

        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        System.out.println("response.path(\"[0],id\") = " + response.path("[0].gender"));

        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));

        // Get all the id (instead of just one) and store it into List<Integer>

        List<Integer> idList = response.path("id");
        System.out.println("idList = " + idList);
    }

   // Send request to Get / spartans and provide accept header as application xml
    // and check status code 200 and content type application xml

    @Test
    public void testGetXMLResponse(){

        //RestAssured use method chaining extensively to combine all part of requests
        // and verify the response in one shot
        // here since we need to provide additional header information to get xml response
        // we will start learning some method chaining to see
        /// how we can provide additional information to the request

        Response response =given()
                            .header("Accept",ContentType.XML)
                        .when()
                            .get("/spartans");

        response.prettyPrint();

    }

    // SEND REQUEST TO GET http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male

    @Test
    public void testSearch(){

        Response response = given()
                            .queryParam("nameContains","Ea") // this is how you provide one query param
                            .queryParam("gender","Male")// this is how you provide another query
                        .when()
                            .get("/spartans/search");

        response.prettyPrint();

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        System.out.println("response.path(\"content[0].name\") = " + response.path("content[0].name"));

        System.out.println("response.path(\"content[1].id\") = " + response.path("content[1].id"));

    }


}
