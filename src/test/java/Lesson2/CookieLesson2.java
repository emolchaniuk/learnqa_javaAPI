package Lesson2;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CookieLesson2 {

    @Test
    public void cookieTest () {

        Map<String,String> data = new HashMap<>();
        data.put("login", "secret_login5");
        data.put("password", "secret_pass");

//        Response response = RestAssured
//                .given()
//                .body(data)
//                .when()
//                .post("https://playground.learnqa.ru/api/get_auth_cookie")
//                .andReturn();
//
//        System.out.println("\nPretty text:"); //если пусто, значит сервер не выдал нам текста в ответе
//        response.prettyPrint();
//
//        System.out.println("\nHeaders:"); //set cookie установть куки - название, зачение, дата актуальности, домен, пас
//        Headers responseHeaders = response.getHeaders();
//        System.out.println(responseHeaders);


//        String responseCookie = response.getCookie("auth_cookie");
//        System.out.println(responseCookie);


        Response responseForGet = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        String responseCookie = responseForGet.getCookie("auth_cookie");

        Map <String, String> cookies = new HashMap<>();
        if (responseCookie != null) {
            cookies.put("auth_cookie", responseCookie);
        }



        Response responseForCheck = RestAssured
                .given()
                .body(data)
                .cookies(cookies)
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();

        responseForCheck.print();




    }
}
