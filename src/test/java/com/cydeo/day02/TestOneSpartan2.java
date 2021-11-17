package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;

public class TestOneSpartan2 {

    // Get http://50.17.84.26:8000/api/spartans/21

    // We can breakdown above url to 3 part to tell RestAssured to append at the end of our endpoints


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://50.17.84.26:8000";
        RestAssured.basePath = "/api";

    }


    @Test
    public void testHelloAgain(){
        Response response = get("/hello");

        Assertions.assertEquals(200, response.getStatusCode());


    }



    @Test
    public void testOneSpartan(){

        Response response = get("/spartans/21");

        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        response.prettyPrint();

        System.out.println("response.asString() = " + response.asString());

        //getting header from the response
        // using header ("header name") or getHeader("header name")
        System.out.println("response.header(\"Conten-Type\") = " + response.header("Content-Type"));

        System.out.println("response.getHeader(\"Content-Type\") = " + response.getHeader("Content-Type"));

    }

    @Test
    public void testContentTypeHeader(){

        Response response = get("/spartans/21");

        System.out.println("response.contentType() = " + response.contentType());

        System.out.println("response.getContentType() = " + response.getContentType());

        // write an assertion to verify contentType() =  application/jason

        Assertions.assertEquals("application/json", response.contentType() );

        // Different type of content type is represented in ENUM coming from
        // import io.resrasssured.http.ContentType;
        System.out.println("ContentType.JSON = " + ContentType.JSON);
        System.out.println("ContentType.XML = " + ContentType.XML);
        System.out.println("ContentType.TEXT = " + ContentType.TEXT);

        //now we can simply just replace the string with enum to avoid any types
        // ContentType.JSON return enum, so we need to convert to string before comparison
        Assertions.assertEquals(ContentType.JSON.toString(), response.contentType() );

    }

    @Test
    public void testJSONBody(){

        Response response = get("http://50.17.84.26:8000/api/spartans/2");

         response.prettyPrint();
         // just like navigating through html using xpath to get to certain element
        // you can navigate through json to get the value of certain key using jsonpath
        // the easiest way to get the value using jsonpath is using path method from response object
        // jsonpath to get the id value is just "id"
        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"gender\") = " + response.path("gender"));
        System.out.println("response.path(\"phone\") = " + response.path("phone"));

        // save the json value you got from the key into variables

        int myId = response.path("id");
        String myName = response.path("name");
        String myGender = response.path("gender");
        long myPhone = response.path("phone");

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + myGender);
        System.out.println("myPhone = " + myPhone);

        // write assertions to verify th json body
        Assertions.assertEquals(2,myId);
        Assertions.assertEquals("Nels",myName);
        Assertions.assertEquals("Male",myGender);
        Assertions.assertEquals(4218971348l,myPhone);




    }

}
