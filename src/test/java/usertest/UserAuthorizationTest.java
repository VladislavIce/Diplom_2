package usertest;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;

import static io.restassured.RestAssured.given;

public class UserAuthorizationTest {




    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();
    IncompleteUser incompleteUser = new IncompleteUser(user.getEmail(), user.getPassword());

    IncompleteUser incompleteUser1 = new IncompleteUser(incompleteUser.invalidLogin(), incompleteUser.getPassword());
    IncompleteUser incompleteUser2 = new IncompleteUser(incompleteUser.getEmail(), incompleteUser.invalidPass());

    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();
    public String accessToken;


    // Создаем нового пользовтеля
    @Before
    public void successfulCreationOfNewUser() {
        ValidatableResponse createUser = request.successfulСreation(user);
        accessToken = response.successCreate(createUser);
    }

    // Успешная авторизация пользовтеля
    @Test
    public void successfulAuthorizationUser(){
    ValidatableResponse authorization = request.successfulAuthorization(incompleteUser);
    response.successfulAuthorization(authorization);
    }

    // Авторизация пользователя с не валидным Email
    @Test
    public void authorizationWithInvalidEmail(){
        ValidatableResponse failedAuthorization = request.successfulAuthorization(incompleteUser1);
        response.authorizationWithInvalidEmailOrPassword(failedAuthorization);
    }

    // Авторизация пользователя с не валидным Password
    @Test
    public void authorizationWithInvalidPassword(){
    ValidatableResponse failAuthorization = request.successfulAuthorization(incompleteUser2);
    response.authorizationWithInvalidEmailOrPassword(failAuthorization);
    }

    // Удаление пользователя
    @After
    public void deleteUser() {
        ValidatableResponse deleteUser = request.deleteUser(accessToken);
        response.successDeleteUser(deleteUser);
    }
}
