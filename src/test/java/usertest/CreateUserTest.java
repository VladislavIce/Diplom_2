package usertest;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.*;

import static io.restassured.RestAssured.given;

public class CreateUserTest {

    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();

    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();

    public String accessToken;

    // Создание нового пользователя
    @Before
    public void successfulCreationOfNewUser() {
        ValidatableResponse createUser = request.successfulСreation(user);
        accessToken = response.successCreate(createUser);
    }

    // Создание нового пользователя с существующими данными
    @Test
    public void creatingUserWithExistingData() {
        ValidatableResponse createUser = request.successfulСreation(user);
        response.errorWhenCreating2(createUser);
    }

    // Создание нового пользователя без поля Name
    @Test
    public void creatingNewUserWithoutNecessarilyField(){
        IncompleteUser incompleteUser = new IncompleteUser(user.getEmail(), user.getPassword());
        ValidatableResponse failCreateUser = request.errorWhenCreating(incompleteUser);
        response.errorWhenCreating(failCreateUser);
    }

    // Удаление созданого пользовтеля
    @After
    public void deleteUser(){
        ValidatableResponse deleteUser = request.deleteUser(accessToken);
        response.successDeleteUser(deleteUser);
    }
}















