package Lesson2.homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.lang.Thread;

import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;

public class Token {

    @Test
    public void token () {

        //1. Создать задачу
        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();

        response.prettyPrint();

        //2. сделать один запрос с token ДО того, как задача готова, убедится в правильности поля status

        Map < String, String> dataForRequest = new HashMap<>();
        dataForRequest.put(response.getBody());
        System.out.println(dataForRequest);

        Response response2 = RestAssured
                .given()
                .queryParam(dataForRequest)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .andReturn();

        response2.prettyPrint();

        if (response2.getBody("status").equals("Job is NOT ready")) {
            System.out.println("Status is correct: Job is NOT ready");
        } else {
            System.out.println("Status is incorrect: already ready");
        }

        //3. подождать нужное количество секунд
        Thread.sleep(response.body("seconds"));

        //4.сделать один запрос c token ПОСЛЕ того, как задача готова, убедиться в правильности поля status и наличии поля result


        Response response3 = RestAssured
                        .given()
                        .queryParam(dataForRequest)
                        .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                        .jsonPath();

        response3.prettyPrint();

        if (response3.body("result")) {
            System.out.println("result is positive");
        } else {
            System.out.println("Result is negative");
        }








    }

}
