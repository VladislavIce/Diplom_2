package ordertest;

import io.restassured.response.ValidatableResponse;
import order.OrderRequest;
import order.OrderResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import user.CreateUserData;
import user.User;
import user.UserRequest;
import user.UserResponse;

public class GetOrderTest {

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

    // Получение списка заказа с авторизацией
    @Test
    public void getOrderTestAutUser(){
        ValidatableResponse qwerty = orderRequest.getOrderAuthorizationUser(accessToken);
        orderResponse.getOrderAuthorizationUser(qwerty);
    }

    // Получение списка заказа без авторизации
    @Test
    public void getOrderTestNotAutUser(){
        ValidatableResponse qwert1 = orderRequest.getOrderNotAuthorizationUser();
        orderResponse.getOrderNotAuthorizationUser(qwert1);
    }













}
