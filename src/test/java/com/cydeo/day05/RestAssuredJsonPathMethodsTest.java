package com.cydeo.day05;

import com.cydeo.pojo.SpartanWithID;
import com.cydeo.utility.SpartanTestBase;
import com.cydeo.utility.SpartanUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RestAssuredJsonPathMethodsTest extends SpartanTestBase {

    /**
     * There are two ways to get the response and extract json data
     *
     * path("your jsonpath goes here") return type is T(generic)
     * and decided by your variable data type you store
     *   int myId = response.path("id")
     *
     *  There is a type(class) in RestAssured : JsonPath
     *  that have lots of methods to extract json body from the response
     *  like getInt getString getDouble getObject getList and so on.....
     *  In order to get JsonPath object out of response ,
     *  we call a method called jsonPath() from the response
     *  for example :
     *  JsonPath jp =  response.jsonPath("your acual path goes here")  ;
     *  jp.getInt()  jp.getLong() and so on
     *
     *  The meaning of the word json path when we use in differnet places
     *  json path : -->> when inside the " " means the actual path to get the value (like xpath)
     *  JsonPath  : -->> the RestAssured class that have lots of methods
     *  jsonPath() : -->> the method of Response object to obtain JsonPath object out of response
     *
     */

    // Send Request to GET /spartans/{id}
    // and extract the data id , name , phone
    @Test
    public void testOneSpartan(){

        // let's get first id exist in our system so we do not have to deal with data issue
        // send request to get all data and grab the first on e
        int firstId = get("/spartans").path("id[0]");
        System.out.println("firstId = " + firstId);

        // Send Request to GET /spartans/{id}
        Response response= given()
                .log().uri()  // only log the request url by choice
                .pathParam("id", firstId).
                when()
                .get("/spartans/{id}")
                .prettyPeek();
//         // save the id from the response
//         int myId = response.path("id") ;
        /** this is the response
         * {
         *     "id": 898,
         *     "name": "Updated Name",
         *     "gender": "Male",
         *     "phone": 9999999999
         * }
         */

        // Get JsonPath object out of response object
        JsonPath jp = response.jsonPath();
        int  myId      =  jp.getInt("id") ;
        String  myName =  jp.getString("name") ;
        long myPhone   =  jp.getLong("phone") ;

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myPhone = " + myPhone);

        // store this json result into a Map object
        // the path to get the entire body is empty string because
        // the entire response is json object already!
        // no need for a path to navigate to this json
        // so this method will create a map object
        // and add all the key of json as key and all value of json as value
        // the return that map object
        Map<String,Object> responseBodyAsMap =  jp.getMap("");
        System.out.println("responseBodyAsMap = " + responseBodyAsMap);
        //how to access phone number field out of this map


    }

    // Send Request GET /spartans/search?nameContains=Ea&gender=Male
    // get JsonPath object out of response so you can use specialized methods
    // get totalElement field value using getX method
    // get 3rd element phone using getX method
    // get last element name using getX method
    // save first json in json array into Map using getX method
    // remember getX("your path to the element goes here just like xpath")

    @Test
    public void testSearchExtractData(){

        Response response = given()
                .log().uri()
                .queryParams("nameContains", "Ea")
                .queryParams("gender", "Male").
                when()
                .get("/spartans/search")
                //.prettyPeek()
                ;
        JsonPath jp = response.jsonPath();
        // get the value of totalElement key
        int total = jp.getInt("totalElement");
        System.out.println("total = " + total);
        // get 3rd element phone using getX method  : content[2].phone
        long thirdPhone =  jp.getLong("content[2].phone") ;
        System.out.println("thirdPhone = " + thirdPhone);

        // get last element name using getX method content[-1].name
        String lastName = jp.getString("content[-1].name") ;
        System.out.println("lastName = " + lastName);

        // save first json in json array into Map using getX method : content[0]
        Map<String,Object> firstJsonAsMap = jp.getMap(" content[0]") ;
        System.out.println("firstJsonAsMap = " + firstJsonAsMap);

        // Get the name of all people and save it into list  : content.name
        //List<String> allNames = jp.getList("content.name") ;
        // in this version of getList method , you have option to make it obvious
        // to specify the class type you want each item to have as List item data type
        List<String> allNames = jp.getList("content.name" , String.class  );
        System.out.println("allNames = " + allNames);

        // save all phone number into the list and make it obvious what kind of list you want to get
        List<Long> allPhones = jp.getList("content.phone" , Long.class) ;


        // continue from this task : now try to match this json objects into POJO
        // in order to match json result with 4 fields , you need to have POJO class with 4 matching fields
        // create a class called SpartanWithID
        // add encapsulated fields id, name , gender, phone
        // add no arg constructor (no need for 4 args constructor because we don't create object ourselves)
        // optionally add toString method so we can print it worked.
        // now we can use  jp.getObject("the path here" , SpartanWithID.class)
        // to save it into  SpartanWithID object  --> another de-serialization!

        // store first json in the result as SpartanWithID POJO  : content[0]
        // getObject method accept jsonPath to the jsonObject to be converted
        // and the class type you want to convert to and return object of that type
        // with all the field value already filled up by matching the key value
        SpartanWithID sp1 = jp.getObject("content[0]", SpartanWithID.class ) ;
        System.out.println("sp1 = " + sp1);




    }








}