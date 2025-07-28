package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{

    public ProductPage(WebDriver driver){
        super(driver);
    }

    public BaseElement addToCartButton(){
        By product = By.cssSelector(String.format("a[class='cart']"));
        return new BaseElement(driver, product );
    }

    public CartPage addToCart(){
        BaseElement addTocart = addToCartButton();
        addTocart.click();
        CartPage cartpage =  new CartPage(driver);
        return cartpage;
    }

}
