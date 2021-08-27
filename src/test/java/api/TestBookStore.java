package api;

import dataProvider.models.books.*;
import dataProvider.models.user.ResUserProvider;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.ConfigFileReader;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;



/*import org.testng.annotations.Test;*/
//-------------BookStore Tests--------------------
public class TestBookStore {
    String baseUrl = "https://demoqa.com/BookStore/v1";
    static ConfigFileReader configFileReader= new ConfigFileReader();
    String checkedISBN = configFileReader.getIsbn();


    private RequestSpecification requestSpecBook() {
        //вариант RequestSpecification через метод requestSpecBook()
        return given().baseUri(baseUrl)
                .contentType(ContentType.JSON);
    };
    ResponseSpecification responseSpecBook = new ResponseSpecBuilder()
            //вариант RequestSpecification через билдер()
            .expectContentType(ContentType.JSON)
            .expectResponseTime(Matchers.lessThan(5000L))//ответное время не более 5сек
            .build();

    @Test // Ищем все книги
    public void getBookStoreBooks() {
        ResponseBooks responseBooks = //запись ответа в переменную responseBooks
                requestSpecBook()
                .basePath("/Books")
                .when().get()
                        .then()
                .spec(responseSpecBook)
                .statusCode(200)
                .header("Content-Length", "4514")//соответстие значения в хедере ответа
                .body("books[0].author", Matchers.equalTo("Richard E. Silverman"))//соответстие значения в теле ответа
                .body("books", hasSize(8))
                .extract().body().as(ResponseBooks.class);//Deserializing JSON response to POJO/DTO class
        System.out.println(responseBooks.getBooks().get(0).getAuthor());//вариант вывода первого автора
        System.out.println(responseBooks.getBooks().size()+" books are there in library!");
    }
    @Test //(dependsOnMethods = "getBookStoreBooks")// Ищем одну книгу по нужному параметру ISBN
    public void getOneBook() {
        BooksItem responseBook = requestSpecBook()
                .basePath("/Book")
                .param("ISBN", 9781449325862L)
                .when().get()
                .then()
                .statusCode(200)
                .body("isbn", Matchers.equalTo(checkedISBN))
                .extract().as(BooksItem.class);//Deserializing JSON response to POJO class
        System.out.println(responseBook.getIsbn());
    }
    @Test (dependsOnMethods = "getOneBook")// по userId Добавляем выбранную книгу в ЛистКниг/коллекцию юзера
    public void addBookToUserList() {
                requestSpecBook()
                .basePath("/Books")
                .header("Authorization", ("Bearer "+ ResUserProvider.getSessionToken()))
                .when()
                .body(ReqAddBookToUserList.getDefaultRequest())
                .post()
                .then()
                .body("message", Matchers.equalTo("ISBN supplied is not available in Books Collection!"))
                .log().all();
        System.out.println("Чекнуть тело запроса addBookToUserList:" + ReqAddBookToUserList.getDefaultRequest());
    }
    @Test (dependsOnMethods = "addBookToUserList")//Replacement one book by UserId and ISBN from User's Collection to another ISBN1
    public void putOneBook() {
        requestSpecBook()
                .basePath("/Book/")
                .header("Authorization", ("Bearer " + ResUserProvider.getSessionToken()))
                .when()
                .body(ReqPutBook.getDefaultRequest())
                .delete()
                .then()
                .body("message", Matchers.equalTo("ISBN supplied is not available in User's Collection!"))
                .log().all();
        System.out.println("Чекнуть тело запроса putOneBook: "+ ReqPutBook.getDefaultRequest());
    }
    @Test (dependsOnMethods = "putOneBook")//Delete one book by UserId and ISBN from User's Collection!
    public void delOneBook() {
        requestSpecBook()
                .basePath("/Book")
                .header("Authorization", ("Bearer " + ResUserProvider.getSessionToken()))
                .when()
                .body(ReqDeleteBook.getDefaultRequest())
                .delete()
                .then()
                .body("message", Matchers.equalTo("ISBN supplied is not available in User's Collection!"))
                .log().all();
        System.out.println("Чекнуть тело запроса delOneBook: "+ReqDeleteBook.getDefaultRequest());
    }
    @Test (dependsOnMethods = "delOneBook")// Удаляет ЛистКниг/коллекцию юзера, которую мы якобы добавили ранее через addListOfBooks
    public void delUserListOfBooks() {
        requestSpecBook()
                .basePath("/Books")
                .header("Authorization", ("Bearer " + ResUserProvider.getSessionToken()))
                .param("UserId", ResUserProvider.getSessionUserId())
                .when().delete()
                .then()
                .statusCode(204)
                .log().all();
        System.out.println();
    }
}
/*@BeforeTest
public void setFilter() {//передать лисенеры  в фильтры аллюра
    RestAssured.filters(new AllureRestAssured());
}*/