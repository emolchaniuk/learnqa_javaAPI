package Lesson2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldTest {

    private Map<String, String> params;

    @Test
    public void testRestAssured () {
        params = new HashMap<>();
        params.put("name", "John"); //alt + enter = импортировать класс


//        Response response = RestAssured
//                .given()
//                //.queryParam("name", "John")
//                .queryParams(params)
//                .get("https://playground.learnqa.ru/api/hello") //сеттер с типом метода
//                .andReturn();
//        response.prettyPrint();

        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello") //сеттер с типом метода
                .jsonPath();

//        String answer = response.get("answer");
//        System.out.println(answer);

        String name = response.get("answer2");

        if (name == null) {
            System.out.println("The key 'answer2' is absent");
        } else {
            System.out.println(name);
        }

    }

}
