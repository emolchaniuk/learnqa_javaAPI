package Lesson2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


public class ServerCodeLesson2 {

    @Test
    public void serverCodes() {
        // get status code 200
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        int statusCode200 = response.getStatusCode();
        System.out.println(statusCode200);


        //Наверное, в одном тесте лучше не добавлять две проверки, не очень правильно?
        //get status code 500
        Response response2 = RestAssured
                .get("https://playground.learnqa.ru/api/get_500")
                .andReturn();

        int statusCode500 = response2.getStatusCode();
        System.out.println(statusCode500);


        //get status code 404
        Response response3 = RestAssured
                .get("https://playground.learnqa.ru/api/liza")
                .andReturn();

        int statusCode404 = response3.getStatusCode();
        System.out.println(statusCode404);


        //get status code 303
        Response response4 = RestAssured
                .given()
                .redirects()
                .follow(true)
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();

        int statusCode303 = response4.getStatusCode();
        System.out.println(statusCode303);



        }
}
