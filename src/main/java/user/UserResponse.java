package user;

import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.*;



public class UserResponse {
    public String successCreate(ValidatableResponse response) {
        String token = response.extract().path("accessToken");
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", notNullValue())
                .body("user.name", notNullValue())
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
        return token;
    }

    public String errorWhenCreating(ValidatableResponse response) {
       String token = response.extract().path("accessToken");
       response.assertThat()
               .statusCode(403)
               .body("success", equalTo(false))
               .body("message", equalTo("Email, password and name are required fields"));
       return token;
    }

    public String errorCreateUserWithExistingData(ValidatableResponse response){
        String token = response.extract().path("accessToken");
        response.assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
        return token;

    }

    public void successfulAuthorization(ValidatableResponse response){
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .body("user.email", notNullValue())
                .body("user.name", notNullValue());
    }

    public void authorizationWithInvalidEmailOrPassword(ValidatableResponse response){
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    // Успешное удаление пользователя 202 Accepted
    public void successDeleteUser(ValidatableResponse response) {
        response.assertThat()
                .statusCode(202)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }

    // Успешное обновление пользователя 200 ОК
    public void successUpdateUser(ValidatableResponse response){
        response.assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", notNullValue())
                .body("user.name", notNullValue());
    }

    // Ошибка при обновлении пользователя 401 Unauthorized
    public void failedUpdateUser(ValidatableResponse response){
        response.assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", notNullValue());
    }





}




