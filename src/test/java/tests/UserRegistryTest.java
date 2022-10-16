package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import lib.apiCoreRequests;

import java.util.HashMap;
import java.util.Map;

public class UserRegistryTest extends BaseTestCase {

    private final apiCoreRequests apiCoreRequests = new apiCoreRequests();
    private String url = "https://playground.learnqa.ru/api/user/";
    @Test
    public void testCreateUserWithExistingEmail () {

        String email = "vinkotov@example.com";

        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData = DataGenerator.getRegistrationData(userData);

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user/")
                .andReturn();

        Assertions.assertResponseCodeEquals(responseCreateAuth, 400);
        Assertions.assertResponseTextEquals(responseCreateAuth, "Users with email '" + email + "' already exists");

    }
    @Test
    public void testCreateUserSuccessfully () {

        String email = DataGenerator.getRandomEmail();

        Map<String, String> userData = DataGenerator.getRegistrationData();

        Response responseCreateAuth = RestAssured
                .given()
                .body(userData)
                .post("https://playground.learnqa.ru/api/user/")
                .andReturn();

        Assertions.assertResponseCodeEquals(responseCreateAuth, 200);
        Assertions.assertJsonHasField(responseCreateAuth, "id");

    }

    @Test
    public void testCreateUserWithInvalidEmail () {

        String email = "vinkotovexample.com";

        Map<String, String> userData = new HashMap<>();
        userData.put("email", email);
        userData = DataGenerator.getRegistrationData(userData);

        Response responseCreateAuth = apiCoreRequests
                .createUserWithInvalidEmail(url, userData);

        Assertions.assertResponseCodeEquals(responseCreateAuth, 400);
        Assertions.assertResponseTextEquals(responseCreateAuth, "Invalid email format");

    }


    @ParameterizedTest
    @CsvSource({
            "elfedoro,elfedoro,elfedoro,tka7771@rambler.ru,",
            "elfedoro,elfedoro,elfedoro,,123",
            "elfedoro,elfedoro,,tka7771@rambler.ru,123",
            "elfedoro,,elfedoro,tka7771@rambler.ru,123",
            ",elfedoro,elfedoro,tka7771@rambler.ru,123",
    })

    public void testCreateUserWithoutOneField (String username, String firstName, String lastName, String email, String password) {

        Map<String, String> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("firstName", firstName);
        userData.put("lastName", lastName);
        userData.put("email", email);
        userData.put("password", password);


        Response responseWithoutOneField = apiCoreRequests
                .createUserWithoutOneField(url, userData);

        Assertions.assertResponseCodeEquals(responseWithoutOneField, 400);
        Assertions.assertResponseHasPart(responseWithoutOneField, "The following required params are missed: ");

    }

    @Test
    public void testWithShortUsername () {
        Map<String, String> userData = new HashMap<>();
        userData = DataGenerator.getRegistrationData(userData);
        userData.put("username", "u");

        Response responseWithShortUsername = apiCoreRequests
                .createUserWithShortUsername(url, userData);

        Assertions.assertResponseCodeEquals(responseWithShortUsername, 400);
        Assertions.assertResponseTextEquals(responseWithShortUsername, "The value of 'username' field is too short");

    }

    @Test
    public void testWithLongUsername () {
        Map <String, String> userData = new HashMap<>();
        String longUsername = "lizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazalizalizazaa";
        userData = DataGenerator.getRegistrationData(userData);
        userData.put("username", longUsername);

        Response responseWithLongUsername = apiCoreRequests
                .createUserWithLongUsername(url, userData);


        Assertions.assertResponseCodeEquals(responseWithLongUsername, 400);
        Assertions.assertResponseTextEquals(responseWithLongUsername, "The value of 'username' field is too long");


    }






}
