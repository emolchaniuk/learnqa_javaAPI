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
        JsonPath response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response.getString("token");
        String seconds = response.getString("seconds");
        System.out.println(token);
        System.out.println(seconds);

        //2. сделать один запрос с token ДО того, как задача готова, убедится в правильности поля status

        JsonPath response2 = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status = response2.getString("status");
        System.out.println(status);
        String state = new String("Job is NOT ready");

         if (status.equals(state)) {
            System.out.println("Status is correct: Job is NOT ready yet");
        } else {
            System.out.println("Status is incorrect: already ready");
        }

        //3. подождать нужное количество секунд

//        try {
//            Long l1 = new Long(seconds);
//            System.out.println(l1);
//        }
//        catch (NumberFormatException e) {
//            System.err.println("Неправильный формат строки!");
//        }
        long time = Long.parseLong("seconds", 10);
        Thread.sleep(time);



        //4.сделать один запрос c token ПОСЛЕ того, как задача готова, убедиться в правильности поля status и наличии поля result


        JsonPath response3 = RestAssured
                        .given()
                        .queryParam("token", "token")
                        .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                        .jsonPath();

        String status2 = response3.getString("status");
        System.out.println(status2);

        String result = response3.getString("result");
        String readyJob = new String("Job is ready");

        if (result.equals(readyJob)) {
            System.out.println("Result is positive - Task is ready");
        } else {
        System.out.println("Result is negative - Task is not ready ");
    }




}}
