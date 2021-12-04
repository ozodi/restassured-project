package com.cydeo.day06;

import com.cydeo.pojo.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.cydeo.pojo.SpartanWithID;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;

    // http://www.omdbapi.com/?i=tt3896198&apikey=310dc4e3
public class MovieAPI_HomeworkTest {


    @BeforeAll
    public static void setup(){
        baseURI = "http://www.omdbapi.com";
    }

    @AfterAll
    public static void  tearDown(){
        reset();
    }

    @Test
    public void testMovies(){
        //http://www.omdbapi.com/?i=tt3896198&apikey=310dc4e3

        JsonPath jp =given()
                .log().all()
                .queryParam("apikey","310dc4e3")
                .queryParam("s","The Mandalorian").
        when()
                .get("")
                .prettyPeek()
                .jsonPath()
                            ;
        //what is the jason path

        Movie m1 = jp.getObject("Search[0]",Movie.class);
        System.out.println("m1 = "+ m1);



    }


}
























