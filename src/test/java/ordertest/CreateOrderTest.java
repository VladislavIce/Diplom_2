package ordertest;

import io.restassured.response.ValidatableResponse;
import order.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.CreateUserData;
import user.User;
import user.UserRequest;
import user.UserResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateOrderTest {

    CreateOrderBody createOrderBody = new CreateOrderBody(new ArrayList<>(List.of("61c0c5a71d1f82001bdaaa6d")));
    CreateOrderBody createOrderBody1 = new CreateOrderBody();
    CreateOrderBody createOrderBody2 = new CreateOrderBody(new ArrayList<>(List.of("61c0c5a71d1f82001bdaaa6@@@@@@d")));

    CreateUserData createUser = new CreateUserData();
    User user = createUser.random();

    OrderRequest orderRequest = new OrderRequest();
    OrderResponse orderResponse = new OrderResponse();
    UserRequest request = new UserRequest();
    UserResponse response = new UserResponse();


    public String accessToken;

    // Создание нового пользователя
    @Before
    public void successfulCreationOfNewUser() {
        ValidatableResponse createUser = request.successfulСreation(user);
        accessToken = response.successCreate(createUser);
    }

    // Создание заказа без авторизацией пользователя
    @Test
    public void createOrdersNotAuthorizationUser(){
        ValidatableResponse successCreateOrder =  orderRequest.creatingOrderWithoutUserAuthorization(createOrderBody);
        orderResponse.successfulCreateOrders(successCreateOrder);
    }


    // Создание заказа с авторизацией пользователя
    @Test
    public void createOrdersTheAuthorizationUser(){
        ValidatableResponse createOrder = orderRequest.createOrdersTheAuthorization(createOrderBody, accessToken);
        orderResponse.successfulCreateOrders(createOrder);
    }

    // Создание заказа без ингритиента
    @Test
    public void creatingOrderWithoutIngredients(){
        ValidatableResponse createOrder = orderRequest.creatingOrderWithoutUserAuthorization(createOrderBody1);
        orderResponse.failedCreateOrders(createOrder);
    }

    // Создание заказа с неверным хешем ингредиентов
    @Test
    public void creatingOrderWithIncorrectHashIngredients(){
        ValidatableResponse createOrder =  orderRequest.creatingOrderWithoutUserAuthorization(createOrderBody2);
        orderResponse.createOrderInvalidHash(createOrder);
    }

    // Удаление созданого пользовтеля
    @After
    public void deleteUser(){
        ValidatableResponse deleteUser = request.deleteUser(accessToken);
        response.successDeleteUser(deleteUser);
    }
}
