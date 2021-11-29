package com.cydeo.day03;

import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.helpers.AnnotationRegistry.reset;




public class SpartanMethodChainTest extends SpartanTestBase {

    @Test
    public void getOneSpartanTest(){

        // in one shot,
        // send request to GET / spartan/ {id} with id of 1
        // log everything
        // verify status Code is 200
        // ContentType is json
        // json body: id is 1, name

                        given()
                                .log().all()
                                .pathParam("id",1)
                                .accept( ContentType.JSON ).
                             when()
                                 .get("/spartans/{id}").
                             then()  // this is where assertions start
                                    .statusCode( is(200))
                                   // .header("Content-Type", "application/json")// asserting header is application json
                             //   .header("Content-Type",ContentType.JSON.toString())
                                .contentType( ContentType.JSON ) // This does same thing as above lines
                                .body( "id" , is(1) )
                                .body("name", is("someone"))
                        ;

    }


    @Test
    public void testSearch(){

        //verify status Code is 200
        //content type is json
        // "TotalElement": 3
        // jsonArray hasSize of 3
        // all names has item Sean
        // every gender is Male
        // every name should contain ignoring case ea

        given()
                .log().all() // logging everything about request
                .queryParam("nameContains","Ea")
                .queryParam("gender","Male").
        when()
                .get("/spartans/search").
        then()
                .log().all()// this is logging everything about response
                .statusCode( 200 )
                .contentType(ContentType.JSON)
                .body("totalElement", is(2))
                .body("content", hasSize(2))
                .body("content.name",hasItem("Sean"))
                .body("content.gender", everyItem( is("Male") ) )
                .body("content.name", everyItem(containsStringIgnoringCase("ea")))

                ;





    }


}
