package api;

import dataProvider.models.task.MRData;
import dataProvider.models.task.ResponseTask;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
//import lombok.var;
import org.hamcrest.Matchers;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class TestClassWorkTask {
    private RequestSpecification requestSpecTask() {
        return given()
                .baseUri("http://ergast.com/api")
                .basePath("/f1/2017/circuits.json")
                .contentType(ContentType.JSON);
    }
    @Test
    public void taskCircuitsSize() {
        var responseTask = requestSpecTask()
                .when().get()
                .then().statusCode(200)//проверка кода ответа 200?
                .contentType(ContentType.JSON) //проверка типа ответа Json?
                .body("MRData.CircuitTable.Circuits.size()", Matchers.equalTo(20))
                .and().body("MRData.CircuitTable.Circuits", hasSize(20))//дублирование проверки 20ти шт другим способом
                ;
        System.out.println("/MRData.CircuitTable/  .Circuits has size: " +responseTask);
    }

    @Test
    public void taskCompareHeader() {
        var responseTask = requestSpecTask()
                .param("text", "test")
                .when().get()
                .then()
                .statusCode(200)//проверка кода ответа 200?
                .contentType("application/json")  //проверка типа ответа Json?
                .header("Content-Length", "4551")//проверка наличия такого заголовка в ответе
                .extract().header("Content-Length");
        // такого хедера с этим значением
        System.out.println("Header has value: " +responseTask);
    }

    @Test
    public void taskCompareName() {
        var responseTask = requestSpecTask()
                .when().get()
                .then()
                .statusCode(200)
                .body("MRData.CircuitTable.Circuits.find{it.circuitId== 'americas'}.circuitName",
                        Matchers.equalTo("Circuit of the Americas"));//проверка соответствия "circuitName"
                //для "Circuit of the Americas" в  объекте, где (circuitId== 'americas')
 //       System.out.println("Header has value: " +responseTask);

    }


}