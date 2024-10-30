package api;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static api.Client.*;
import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

public class UserSteps {
    public static User createUserWithRandomData() {
        return new User(
                RandomizerForCreateUser.RANDOM_EMAIL,
                RandomizerForCreateUser.RANDOM_PASSWORD,
                RandomizerForCreateUser.RANDOM_NAME
        );
    }

    @Step("Создание уникального пользователя")
    public static Response createUser(User user) {
        return spec()
                .body(user)
                .when()
                .post(CREATE_USER);
    }

    @Step("Удаление пользователя")
    public static void deleteUser(@Param(mode = HIDDEN) String accessToken) {
        spec()
                .header("Authorization", accessToken)
                .when()
                .delete(DATA_USER);
    }
}

