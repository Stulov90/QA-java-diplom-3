package api;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    public static final String CREATE_USER = "api/auth/register";
    public static final String DATA_USER = "api/auth/user";

    public static RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);
    }
}

