package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import lib.Assertions;
import lib.apiCoreRequests;

public class UserDeleteTest extends BaseTestCase {

    private final apiCoreRequests apiCoreRequests = new apiCoreRequests();

    @Epic(value = "Деманд - 'Удаление пользователя', ссылка в Jira - ...")
    @Test
    @Description(value = "Тест проверяет, что мы не можем удалить пользователей с id 1 - 5, даже, если мы авторизованы под этими пользователями ")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testDeleteUserId2() {

        Map<String, String> userData = new HashMap<>();
        userData.put("email", "vinkotov@example.com");
        userData.put("password", "1234");

        Response makeLogin = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/login", userData);

        String cookie = makeLogin.getCookie("auth_sid");
        String token = makeLogin.getHeader("x-csrf-token");

        Response response = apiCoreRequests
                .makeDeleteRequest("https://playground.learnqa.ru/api/user/2", userData, token, cookie);


        Assertions.assertResponseCodeEquals(response, 400);
        Assertions.assertResponseTextEquals(response, "Please, do not delete test users with ID 1, 2, 3, 4 or 5.");
    }

    @Epic(value = "Деманд - 'Удаление пользователя', ссылка в Jira - ...")
    @Issue(value = "Ссылка на баг в Jira ....")
    @Test
    @Description(value = "Тест проверяет, что мы можем удалить любых пользователей, чьи id не 1-5, если мы авторизованы под этими пользователями ")
    public void testDeleteOwnUser() {

        //generate user
        Map<String, String> userData = DataGenerator.getRegistrationData();

        Response responseCreateAuth = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/", userData);

        String userId = responseCreateAuth.jsonPath().getString("id");

        //login
        Map<String, String> authData = new HashMap<>();
        authData.put("email", userData.get("email"));
        authData.put("password", userData.get("password"));

        Response responseGetAuth = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/login", authData);

        //delete user

        String cookie = responseGetAuth.getCookie("auth_sid");
        String token = responseGetAuth.getHeader("x-csrf-token");
        String url = "https://playground.learnqa.ru/api/user/" + userId;

        Response response = apiCoreRequests
                .makeDeleteRequest(url, userData, token, cookie);


        Assertions.assertResponseCodeEquals(response, 200);


        //Get user by id

        Response responseAfterDeletion = apiCoreRequests
                .makeGetRequest(url, token, cookie);

        Assertions.assertResponseCodeEquals(responseAfterDeletion, 404);
        Assertions.assertResponseTextEquals(responseAfterDeletion, "User not found");
    }


    @Epic(value = "Деманд - 'Удаление пользователя', ссылка в Jira - ...")
    @Test
    @Description(value = "Тест проверяет, что мы не можем удалить любых пользователей, если мы авторизованы под другим пользователем. Только того, под кем сейчас авторизованы(кроме 1-5)")
    public void testDeleteUserIdByAnotherUser() {

        //login by any user
        Map<String, String> userData = new HashMap<>();
        userData.put("email", "tka@rambler.ru");
        userData.put("password", "1234");

        Response makeLogin = apiCoreRequests
                .makePostRequest("https://playground.learnqa.ru/api/user/login", userData);

        String cookie = makeLogin.getCookie("auth_sid");
        String token = makeLogin.getHeader("x-csrf-token");

        //try to delete another one

        String url3 = "https://playground.learnqa.ru/api/user/" + 15;
        Response response = apiCoreRequests
                .makeDeleteRequest(url3, userData, token, cookie);


        Assertions.assertResponseCodeEquals(response, 400);
        System.out.println(response.asString());
    }


}
