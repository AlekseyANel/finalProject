package com.demoqa;

import business.layer.com.demoqa.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DriverFactory;


public class ElementsTestUI {
    public WebDriver webDriver;

    MainPage mainPage;
    LinksPage linksPage;
    ButtonsPage buttonsPage;


    @BeforeTest(description = "Create webDriver. Start Main Page, OtherPages")
    public void startUpDriver () {
        //Initializing Driver
        webDriver = DriverFactory.getWebDriver();
        mainPage = PageFactory.initElements(webDriver, MainPage.class); //второй вариант инициализации
        mainPage.navigateToMainPage();
        }

    @Test(enabled = true,
            groups = {"links"},
            description = "check 'LinksPage'")
    public void linksPage() {
        //Initializing LinksPage elements
        linksPage = PageFactory.initElements(webDriver, LinksPage.class);
        linksPage.homeNewTab();
        linksPage.created();
        linksPage.noContent();
        linksPage.notFound();
            }

    @Test(enabled = true,
            groups = {"buttons"},
            description = "check 'buttonsPage'")
    public void buttons() {
        //Initializing ButtonsPage elements
        buttonsPage = PageFactory.initElements(webDriver, ButtonsPage.class);
        buttonsPage.doubleClickBtn();
        buttonsPage.rightClickBtn();
        buttonsPage.clickMe();
    }

    @AfterTest (enabled = true,
            groups = {"buttons","links"},
            description = "browser quits")
    public void totalQuit() {
        DriverFactory.closeAllDriver();
    }

}