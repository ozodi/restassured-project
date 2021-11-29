package com.cydeo.day04;

import com.cydeo.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApp_Test {

    @BeforeAll
    public static void setup(){
        // baseURI : https://library2.cybertekschool.com
        // basePath : /rest/v1
        RestAssured.baseURI = "https://library2.cybertekschool.com" ;
        RestAssured.basePath = "/rest/v1" ;

    }

    @AfterAll
    public static void teardown(){
        reset();
    }


    @Test
    public void testLogin(){
        /**
         * This is the link for doc : https://library2.cybertekschool.com/rest/v1/#/User/post_login
         *
         * POST /login
         * content type : application/x-www-form-urlencoded
         * body :
         *   email      : librarian52@library
         *   password   : Sdet2022*
         *
         * According to the doc
         *  we get 200 status code
         *  json body with 2 key  : token  , redirect_url
         *  content-type json
         */
        given()
                .log().all()
//                .header("Content-Type" , "application/x-www-form-urlencoded")
                .contentType( ContentType.URLENC )  // this line is simple version of above line
                .formParam("email", "librarian52@library" ) // this is how you provide form body
                .formParam("password", "Sdet2022*"). // this is how you provide form body
                when()
                .post("/login").
                then()
                .log().all()
                .statusCode(200)       ;

        // in this POST request we saw different content type known as url encoded form data
        // if the content type is this , restassured make it easy to provide the body
        // using the method called form param , if you have more than one you can keep calling the method
        // and provide the key value pair according to the document
    }

    // In separate test , make a request to POST /login one more time
    // no need for assertion , only save the json value of token key in the response
    // send a request to GET /dashboard_stat
    // provide a header with name x-library-token , value is the value you saved from previous request
    // verify you get 200.

    @Test
    public void testDashboardStats(){

        // send POST request to get the token from the body
        Response response =  given()
                .contentType( ContentType.URLENC )  // this line is simple version of above line
                .formParam("email", "librarian52@library" ) // this is how you provide form body
                .formParam("password", "Sdet2022*"). // this is how you provide form body
                        when()
                .post("/login") ;

        String tokenFromRes =  response.path("token");
        System.out.println("tokenFromRes = " + tokenFromRes);

        // send a request to GET /dashboard_stat
        // provide a header with name x-library-token , value is the value you saved from previous request
        // verify you get 200.
        given()
                .log().all()
                .header("x-library-token", tokenFromRes ).
                when()
                .get("/dashboard_stats").
                then()
                .log().all()
                .statusCode(200) ;

    }





}