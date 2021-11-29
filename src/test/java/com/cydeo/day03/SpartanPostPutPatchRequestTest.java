package com.cydeo.day03;

import com.cydeo.utility.SpartanTestBase;
import org.junit.jupiter.api.Test;
import com.cydeo.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class SpartanPostPutPatchRequestTest extends SpartanTestBase {

    @Test
    public void testAdd1DataStringBody() {


        String strBody = "{\n" +
                "                    \"name\":\"API POST\",\n" +
                "                    \"gender\":\"Male\",\n" +
                "                    \"phone\":1231231231\n" +
                "          }";

        given()
                .log().all()
                .contentType(ContentType.JSON) //Content type reques header set to json, telling the server
                .body(strBody).
                when()
                .post("/spartans").

                then()
                .log().all()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("API POST"))
                .body("data.phone", is(1231231231))

        ;

    }



        @Test
        public void strEdt() {


            String strEdit = "{\n" +
                    "                    \"name\":\"Beast\",\n" +
                    "                    \"gender\":\"Male\",\n" +
                    "                    \"phone\":1231231231\n" +
                    "          }";

            given()
                    .log().all()
                    .contentType(ContentType.JSON) //Content type reques header set to json, telling the server
                    .body(strEdit).
            pathParam("id",118).
                    when()
                    .patch("/spartans/{id}").

                    then()

                    .log().all()
                    .statusCode(204)

            ;


        }

        @Test
        public void delete(){


            given()
                    .log().all()
                    .contentType(ContentType.JSON) //Content type reques header set to json, telling the server
                    .pathParam("id",118).
                    when()
                    .delete("/spartans/{id}").

                    then()

                    .log().all()
                    .statusCode(204)

            ;


        }


}
