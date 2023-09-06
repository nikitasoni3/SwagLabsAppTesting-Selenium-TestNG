package Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstName;
    @FindBy(id = "last-name")
    WebElement lastName;
    @FindBy(id = "postal-code")
    WebElement postalCode;
    @FindBy(id = "continue")
    WebElement continueBtn;
    @FindBy(id = "cancel")
    WebElement cancelBtn;
    @FindBy(id = "finish")
    WebElement finishBtn;
    @FindBy(xpath = "//button[contains(text(), 'Checkout')]")
    WebElement checkoutBtn;

    public void goToCheckOutPage() throws IOException {
        logger.info("Clicked on checkout button and went to checkout page");
        checkoutBtn.click();
    }
    public void enterUserInformationAndPlaceOrder(String first_name, String last_name, String postal_code) throws IOException {
        logger.info("Entered user information: firstname, lastname and postal code");
        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);
        postalCode.sendKeys(postal_code);
        logger.info("Clicked on continue button");
        continueBtn.click();
        logger.info("Clicked on finish button");
        finishBtn.click();
        logger.info("Taken the ss of successfully placed order");
        takeScreenshot("OrderPlaced.png");
    }
    public void enterUserInformationAndCancelOrder(String first_name, String last_name, String postal_code) throws IOException {
        logger.info("Entered user information: firstname, lastname and postal code");
        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);
        postalCode.sendKeys(postal_code);
        logger.info("Clicked on continue button");
        continueBtn.click();
        logger.info("Clicked on cancel button");
        cancelBtn.click();
        logger.info("Taken the ss of successfully canceled order");
        takeScreenshot("OrderCanceled.png");
    }
}
