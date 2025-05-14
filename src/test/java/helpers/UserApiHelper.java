package helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class UserApiHelper {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth";

    @Step("Создание пользователя через API")
    public static Response register(String email, String password, String name) {
        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("password", password);
        body.put("name", name);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post(BASE_URL + "/register");
    }

    @Step("Удаление пользователя через API")
    public static void delete(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + "/user");
    }

    @Step("Логин пользователя через API")
    public static Response login(String email, String password) {
        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("password", password);

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post(BASE_URL + "/login");
    }
}