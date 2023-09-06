package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderCompletePage {
    WebDriver driver;
   @FindBy(css = "h2[class='complete-header']")
    WebElement orderConfirmationElement;

    public OrderCompletePage(WebDriver driver){
        this.driver=driver;
    }
    public void getOrderConfirmationMessage(){
        String odrCompleteMessage = orderConfirmationElement.getText();
        System.out.println(odrCompleteMessage);
    }

}

