package api;

import dataProvider.models.user.ReqUserAccount;
import dataProvider.models.user.ResGenToken;
import dataProvider.models.user.ResUserAccount;
import dataProvider.models.user.ResUserProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.*;
import utils.ConfigFileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestUserAccount {
    ConfigFileReader configFileReader = new ConfigFileReader();
    String baseUrl = configFileReader.getPropertyFromFile("urlAccount");  // https://demoqa.com/Account/v1;

    //-------------User's Account Tests--------------------
RequestSpecification requestSpecAccount = new RequestSpecBuilder()
//вариант RequestSpecification через RequestSpecBuilder
        .setBaseUri(baseUrl)
        .setContentType(ContentType.JSON)
        .build();
ResponseSpecification responseSpecAccount = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .expectContentType(ContentType.JSON)
        .expectResponseTime(Matchers.lessThan(5000L))//ответное время не более 5сек
        .build();
    @BeforeTest (description = "Registration new user")
    public void userReg(){
        ResUserAccount resUserAccount =
                given()
                .spec(requestSpecAccount)
                .basePath("/User")
                .when().body(ReqUserAccount.getDefaultRequest()).post()//получаем данные из ДТО-файла "ReqUserAccount"
                .then()
                .statusCode(201)
                .log().all()//Выводит весь ответ
                .extract().as(ResUserAccount.class);//Deserializing JSON response to POJO class проверка соответствия имен
        assertThat(resUserAccount.username, Matchers.equalTo(ReqUserAccount.getDefaultRequest().userName));
        System.out.println("Чекнуть запрос: " + ReqUserAccount.getDefaultRequest());
        ResUserProvider.setSessionUserId(resUserAccount.getUserID());//сеттим юзерАйДи во статический класс
        /*assertThat(resUserAccount
                .isNotNull()
                .extracting(ResUserAccount::getUsername)
                .isEqualTo(ReqUserAccount.getDefaultRequest().userName);*/
    }
    @Test  (description = "Session Token generation by userid and password")
    public void userGenerateToken() {
        ResGenToken resGenToken =
                given()
                        .spec(requestSpecAccount)
                        .basePath("/GenerateToken")
                        .when().body(ReqUserAccount.getDefaultRequest()).post()//получаем данные из ДТО-файла "ReqAuthUser"
                        .then()
                        .spec(responseSpecAccount)
                        .body("status", Matchers.equalTo("Success"))
                        .body("result", Matchers.containsString("User authorized successfully."))
                        //проверка наличия "result" в ключе ответа
                        .log().all()
                        .extract().as(ResGenToken.class);//взяли весь ответ для экземпляра класса ResGenToken
        ResUserProvider.setSessionToken(resGenToken.getToken());//сеттим токен во статический класс
    }

    @Test (dependsOnMethods = "userGenerateToken", description = "Is user authorized or not")
    public void userAuthorized() {
            given()
            .header("Authorization", ("Bearer "+ResUserProvider.getSessionToken()))
            .spec(requestSpecAccount)
            .basePath("/Authorized")
            .when().body(ReqUserAccount.getDefaultRequest()).post()//получаем данные из ДТО-файла "ReqAuthUser"
            .then()
            .spec(responseSpecAccount)
            .body(Matchers.equalTo("true"))//проверка наличия TRUE/FALSE в ответе
            .log().all();//Выводит весь ответ с статус-лайн и хидеррами
}
    @Test ( dependsOnMethods = "userAuthorized",
            description = "Get exist User by {UUID}-userId with his book list/collection")
    public void userExistGet() {
        given()
                .spec(requestSpecAccount)
                .header("Authorization", "Bearer "+ResUserProvider.getSessionToken())
                .basePath("/User/"+ResUserProvider.getSessionUserId())
                .when().get()
                .then()
                .statusCode(200)
                .log().body();
        System.out.println("Видим, что юзер exist!");
    }

    @AfterTest (description = "AfterClass //Delete User by {UUID}-userId")
    public void userDelete() {
        given()
                .spec(requestSpecAccount)
                .header("Authorization", "Bearer "+ResUserProvider.getSessionToken())
                .basePath("/User/"+ResUserProvider.getSessionUserId())
                .when().delete()
                .then()
                .statusCode(204)
                .log().all();
        System.out.println("Чекнуть входящий UUID запроса: "+ResUserProvider.getSessionUserId());
    }

}
