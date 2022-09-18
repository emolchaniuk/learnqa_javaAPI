package Lesson2.homework;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Redirect {

    @Test
    public void redirect() {
        Response response = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get("https://playground.learnqa.ru/api/long_redirect")
                .andReturn();


        String locationHeader = response.getHeader("Location");
        System.out.println(locationHeader);

        Response response2 = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get(locationHeader)
                .andReturn();

        String locationHeader2  = response2.getHeader("Location");
        System.out.println(locationHeader2);

        Response response3 = RestAssured
                .given()
                .redirects()
                .follow(false)
                .when()
                .get(locationHeader2)
                .andReturn();

        String locationHeader3  = response3.getHeader("Location");
        System.out.println(locationHeader3);





    }
}