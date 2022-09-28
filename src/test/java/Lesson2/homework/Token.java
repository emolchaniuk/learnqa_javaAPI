package Lesson2.homework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        System.out.println("We got following token from response: " + token);
        System.out.println("We got following amount of seconds from response: " + seconds);


        //2. сделать один запрос с token ДО того, как задача готова, убедится в правильности поля status
        JsonPath response2 = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status = response2.getString("status");
        System.out.println("We got following status from first response with token: " + status);
        String state = "Job is NOT ready";

         if (status.equals(state)) {
            System.out.println("Status is correct: Job is NOT ready yet");
        } else {
            System.out.println("Status is incorrect: already ready");
        }


        //3. подождать нужное количество секунд
        try{
            System.out.println("Programm is sleeping");
            long time =Long.parseLong(response.getString("seconds"));
            Thread.sleep(time*1000); //т.к. таймер в милисекундах!!!
        } catch (InterruptedException e){
            System.out.println("no timer");
        }


        //4.сделать один запрос c token ПОСЛЕ того, как задача готова, убедиться в правильности поля status и наличии поля result
        JsonPath response3 = RestAssured
                        .given()
                        .queryParam("token", token)
                        .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                        .jsonPath();

        String status2 = response3.getString("status");
        System.out.println("We got following status from second response with token: " + status2);
        String readyJob = "Job is ready";


        String result = response3.getString("result");


        if (status2.equals(readyJob)) {
            System.out.println("Result is positive - Task is ready!!!");
        } else {
        System.out.println("Result is negative - Task is not ready ");
    }

        if (result != null) {
            System.out.println("Response contains 'result'. It is: " + result);
        } else {
            System.out.println("Something gone wrong, no 'result' in response");
        }


}}
