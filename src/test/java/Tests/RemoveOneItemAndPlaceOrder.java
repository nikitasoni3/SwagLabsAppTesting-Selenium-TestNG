package Tests;

import AbstractComponents.AbstractComponent;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.HomeProductPage;
import Pages.LoginPage;
import Utilities.ConfigReader;
import Utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class RemoveOneItemAndPlaceOrder{
    static WebDriver driver;
    LoginPage login;
    HomeProductPage homeProduct;
    HomePage home;
    CheckoutPage checkOut;
    ConfigReader con;
    ExcelReader excel;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        login = new LoginPage(driver);
        homeProduct = new HomeProductPage(driver, "Purchase3");
        home= new HomePage(driver);
        checkOut = new CheckoutPage(driver);
        con = new ConfigReader();
        excel = new ExcelReader();
        login.goToUrl(con.getUrl());
    }

    @Test
    public void removeOneItemFromCartAndPlaceOrder() throws IOException {
        login.loginApplication(excel.getUsername(), excel.getPassword());
        login.takeLoginStatusScreenshot();
        homeProduct.selectItemAddToCart();
        homeProduct.goToCartPage();
        homeProduct.takeScreenshot("BeforeRemovingItem.png");
        homeProduct.removeOneItemFromCart();
        homeProduct.goToCartPage();
        homeProduct.takeScreenshot("AfterRemovingItem.png");
        checkOut.goToCheckOutPage();
        checkOut.enterUserInformationAndPlaceOrder(excel.getFirstName(), excel.getLastName(), excel.getPostalCode());
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}


