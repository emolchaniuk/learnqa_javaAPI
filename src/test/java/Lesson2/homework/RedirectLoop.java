package Lesson2.homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class RedirectLoop {

//    @Test
//    public void redirectLoop() {
//
//
//
//        String requestUrl = "https://playground.learnqa.ru/api/long_redirect";
//        boolean doRequest = true;
//        while (doRequest) {
//            Response response = RestAssured
//                    .given()
//                    .redirects()
//                    .follow(false)
//                    .when()
//                    .get(requestUrl)
//                    .andReturn();
//
//            response.prettyPrint();
//
//            String locationHeader = response.getHeader("Location");
//            System.out.println(locationHeader);
//
//            requestUrl = response.getHeader("Location");
//        }
//
//
//}
    }