package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends BasePage {

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public BaseElement checkMessage(){
        By message = By.xpath("//span[@class='maintext']");
        return new BaseElement(driver, message);
    }

    public BaseElement checkOrder(){

        By order = By.xpath("//p[contains(normalize-space(), 'has been created!')]");
        return new BaseElement(driver, order);
    }

    public String checkMessageDisplayed(){

        BaseElement msg = checkMessage();
        return msg.getTextValue().toUpperCase();
    }

    public String getOrderId(){
        BaseElement order = checkOrder();
        String confirmationText = order.getTextValue();
        int start = confirmationText.indexOf("#");
        int end = confirmationText.indexOf(" has");
        String orderId = confirmationText.substring(start, end);

        return orderId;
    }
}
