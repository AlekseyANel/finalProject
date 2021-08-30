package com.demoqa;

import business.layer.com.demoqa.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigFileReader;
import utils.DriverFactory;
import utils.waiters.WaiterForElement;

import java.util.concurrent.TimeUnit;


public class BookTestUI {
    public WebDriver webDriver;
    ConfigFileReader configFileReader = new ConfigFileReader();
    String email = configFileReader.getEmail();
    String userName = configFileReader.getUser();
    String password = configFileReader.getPassword();
    String baseUrl = configFileReader.getUrlDemoQA();
    MainPage mainPage;
    LoginPage loginPage;
    LinksPage linksPage;
    BooksPage booksPage;
    BookPage bookPage;
    ProfilePage profilePage;
    LogoutPage logoutPage;

    //@Test
    @BeforeClass(groups = {"first"},
            description = "//открываем мейнпедж, регистрируемся. Хотя ранее регистрировались и авторизировались через АйПиАй")
    public void startUp() throws InterruptedException {
        //Initializing Driver
        webDriver = new ChromeDriver();//DriverFactory.getWebDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
//        webDriver.manage().window().setSize(new Dimension(1280, 768));

        mainPage = PageFactory.initElements(webDriver, MainPage.class);
        mainPage.navigateToMainPage();

        loginPage = PageFactory.initElements(webDriver, LoginPage.class); //второй вариант
        loginPage.login(userName, password);

        WaiterForElement waiterForElement = new WaiterForElement(webDriver);
        //ожидаем полной регистрации
        waiterForElement.waiterByXLocator("//button[contains(text(),'Log out')]");

    }

/*    @BeforeMethod(groups = {"first"})
    public void startMethod() {
        mainPage.navigateToMainPage();
    }*/

    @Test(groups = {"first"}, description = "//get two books to our collection")
    public void getBooks() throws InterruptedException {
        booksPage = PageFactory.initElements(webDriver, BooksPage.class);
        Assert.assertTrue(booksPage.isPageOpened());
        booksPage.zakazBook();//observe book by author Zakas

        bookPage = PageFactory.initElements(webDriver, BookPage.class);
        Assert.assertTrue(bookPage.isPageOpened());
        bookPage.addBook();
        bookPage.back();

//        webDriver.get(baseUrl+"/books");
        Assert.assertTrue(booksPage.isPageOpened());
        booksPage.haverbekeBook();//observe book by author Haverbeke

        Assert.assertTrue(bookPage.isPageOpened());
        bookPage.addBook();
        bookPage.back();
    }

    @Test(groups = {"first"},
            dependsOnMethods = "getBooks",
            description = "//delete second book from collection  and check")
    public void deleteBook() {
        profilePage = PageFactory.initElements(webDriver, ProfilePage.class);
        Assert.assertTrue(profilePage.isPageOpened());
        Assert.assertTrue(profilePage.getZakasBook());//проверка наличия книги в коллекции
        Assert.assertTrue(profilePage.getHaverbekeBook());//проверка наличия книги в коллекции
        Assert.assertEquals(profilePage.getCollectionSize(), 2);//проверка размера коллекции до удаления
        profilePage.secondBookDel();
        Assert.assertEquals(profilePage.getCollectionSize(), 1);//проверка размера коллекции после удаления
    }

    @Test(groups = {"first"}, description = "check some functionality")
    public void checkLinks() {
        linksPage = PageFactory.initElements(webDriver, LinksPage.class);
        Assert.assertTrue(linksPage.isPageOpened());
        linksPage.newTab();
        linksPage.created();
    }

    @AfterGroups (groups = {"first"})
    public void totalQuit() {
        webDriver.get(baseUrl + "/login");
        logoutPage = PageFactory.initElements(webDriver, LogoutPage.class);
        LogoutPage.logout();
        DriverFactory.closeAllDriver();

    }

}