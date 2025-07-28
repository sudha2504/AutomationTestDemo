package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public BaseElement confirmOrderbutton(){
        By confirm = By.cssSelector(String.format("button[title='Confirm Order']"));
        return new BaseElement(driver, confirm);
    }

    public ConfirmationPage confirmOrder(){

        BaseElement confirm = confirmOrderbutton();
        confirm.click();
        confirm.getUrl("https://automationteststore.com/index.php?rt=checkout/success");
        return new ConfirmationPage(driver);
    }




}


