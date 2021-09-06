package com.demoqa;

import business.layer.com.demoqa.pages.*;
import dataProvider.models.user.ReqUserAccount;
import io.restassured.http.ContentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigFileReader;
import utils.DriverFactory;

import static io.restassured.RestAssured.given;


public class BookStoreTestUI {
    public WebDriver webDriver;

    ConfigFileReader configFileReader = new ConfigFileReader();
    String userName = configFileReader.getUser();
    String password = configFileReader.getPassword();
    MainPage mainPage;
    LoginPage loginPage;
    BooksPage booksPage;
    BookPage bookPage;
    ProfilePage profilePage;

    @BeforeSuite (description = "New User Registration by API (bypass for recaptcha")
    public void newUserReg () {
        //Registration by API
        given()
                .contentType(ContentType.JSON)
                .baseUri(configFileReader.getUrlAccount())
                .basePath("/User")
                .when().body(ReqUserAccount.getDefaultRequest()).post()//получаем данные из ДТО-файла "ReqUserAccount"
                .then()
                .statusCode(201)
                .log().all();
        System.out.println(ReqUserAccount.getDefaultRequest());

/*        //рекаптча мешает
        regPage = new RegPage(webDriver);
        regPage.isRegPageOpened();
        regPage.registration(firstName, lastName, userName, password);*/
    }

    @BeforeTest(enabled = true,
            description = "Create webDriver. Start Main Page")
    public void startUpDriver() {
        //Initializing Driver
        webDriver = DriverFactory.getWebDriver();
        mainPage = PageFactory.initElements(webDriver, MainPage.class); //второй вариант инициализации
        mainPage.navigateToMainPage();
    }

    @Test(enabled = true, groups = {"first"},
            description = "Login testing")
    public void login() throws InterruptedException {
        loginPage = PageFactory.initElements(webDriver, LoginPage.class); //второй вариант инициализации
        loginPage.login(userName, password);
    }

    @Test(enabled = true,
            dependsOnMethods = "login",
            groups = {"first"},
            description = "get two books to our collection. check two added books")
    public void getBooks() throws InterruptedException {
        booksPage = PageFactory.initElements(webDriver, BooksPage.class);
        Assert.assertTrue(booksPage.isPageOpened());
        booksPage.zakazBook();//observe book by author Zakas
        bookPage = PageFactory.initElements(webDriver, BookPage.class);
        Assert.assertTrue(bookPage.isPageOpened());
        bookPage.addBook();//add book author Zakas to our collection
        bookPage.back();

        Assert.assertTrue(booksPage.isPageOpened());
        booksPage.haverbekeBook();//observe book by author Haverbeke
        Assert.assertTrue(bookPage.isPageOpened());
        bookPage.addBook();//add book author Haverbeke to our collection
        bookPage.back();

        profilePage = PageFactory.initElements(webDriver, ProfilePage.class);
        Assert.assertTrue(profilePage.getZakasBook());//проверка наличия книги в коллекции
        Assert.assertTrue(profilePage.getHaverbekeBook());//проверка наличия книги в коллекции
    }

    @Test(enabled = true, groups = {"first"},
            dependsOnMethods = "getBooks",
            description = "delete only second book from collection and check")
    public void deleteBook() throws InterruptedException {
//        profilePage = PageFactory.initElements(webDriver, ProfilePage.class);
        Assert.assertTrue(profilePage.isPageOpened());
        Assert.assertEquals(profilePage.getCollectionSize(), 2);//проверка размера коллекции до удаления
        profilePage.secondBookDel();
        Assert.assertEquals(profilePage.getCollectionSize(), 1);//проверка размера коллекции после удаления
    }

    @Test(enabled = true, groups = {"first"},
            dependsOnMethods = "deleteBook",
            description = "delete all books from collection and check")
    public void deleteAllBooks() throws InterruptedException {
//        profilePage = PageFactory.initElements(webDriver, ProfilePage.class);
        Assert.assertTrue(profilePage.isPageOpened());
        Assert.assertEquals(profilePage.getCollectionSize(), 1);//проверка размера коллекции до удаления
        profilePage.delAllBooks();
        Assert.assertEquals(profilePage.getCollectionSize(), 0);//проверка размера коллекции после удаления
    }
/*    @Test(enabled = false, groups = {"first"}, description = "check some functionality")
    public void checkLinks() {
        linksPage = PageFactory.initElements(webDriver, LinksPage.class);
        Assert.assertTrue(linksPage.isPageOpened());
        linksPage.newTab();
        linksPage.created();
    }*/
    @Test (enabled = true,
            groups = {"first"},
            dependsOnMethods = "deleteAllBooks",
    description = "logout user testing")
    public void logout() {
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        loginPage.logout();
    }


    @AfterSuite (enabled = true,
            groups = {"first"},
            description = "login for delete user account. total quit")
    public void totalQuit() throws InterruptedException {
//        profilePage = PageFactory.initElements(webDriver, ProfilePage.class);
        loginPage.login(userName, password);
        profilePage.delAccount();
        DriverFactory.closeAllDriver();
    }

}