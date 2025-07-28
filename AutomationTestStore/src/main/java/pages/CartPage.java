package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private String prdtId;

    public CartPage(WebDriver driver){
        super(driver);
    }


    public BaseElement checkoutButton(){
        By checkout = By.cssSelector(String.format("a[id='cart_checkout1']"));
        return new BaseElement(driver, checkout );
    }

    public BaseElement removeItem(){

        By remove = By.xpath(String.format("//a[contains(@href,'remove=%s')]", prdtId));
        return new BaseElement(driver, remove );
    }

    public BaseElement modifyItem(){

        By remove = By.xpath(String.format("//input[contains(@id,'cart_quantity%s')]", prdtId));
        return new BaseElement(driver, remove );
    }

    public BaseElement updateCartButton(){

        By update = By.cssSelector("button#cart_update");
        return new BaseElement(driver, update );
    }

    public CheckoutPage clickCheckout(){
        BaseElement checkout = checkoutButton();
        checkout.click();
        return new CheckoutPage(driver);
    }

    public void removeItemfromCart(String productId){
        prdtId = productId;
        removeItem().click();
    }

    public void modifyItemfromCart(String productId, String count){
        prdtId = productId;
        modifyItem().inputText(count);
    }

    public void updateCart(){
        updateCartButton().click();
    }






}


