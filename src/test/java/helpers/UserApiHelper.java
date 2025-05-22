package helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CourierModel;

public class UserApiHelper {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth";

    @Step("Создание пользователя через API")
    public static Response register(CourierModel courier) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(BASE_URL + "/register");
    }

    @Step("Логин пользователя через API")
    public static Response login(CourierModel courier) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(BASE_URL + "/login");
    }

    @Step("Удаление пользователя через API")
    public static void delete(String accessToken) {
        RestAssured.given()
                .header("Authorization", accessToken)
                .when()
                .delete(BASE_URL + "/user");
    }
}
