package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.io.IOException;

public class LoginPage extends AbstractComponent {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //PageFactory
    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    public void loginApplication(String user, String pass) {
        logger.info("Entered credentials");
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
        logger.info("Successfully Logged in");
    }
    public void goToUrl(String url){
        logger.info("Navigated to the url");
        driver.get(url);
        logger.info("Maximized the window");
        driver.manage().window().maximize();
    }
    public void takeLoginStatusScreenshot() throws IOException {
        logger.info("Taken the ss for login status");
        takeScreenshot("LoginStatus.png");
    }
}
