package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductListingPage extends BasePage{

    public ProductListingPage(WebDriver driver){
        super(driver);
    }

    public BaseElement viewProductButton(String prdtId){
        By product = By.cssSelector(String.format("div[class*='pricetag jumbotron']>a[data-id='%s']", prdtId));
        return new BaseElement(driver, product );
    }

    public ProductPage viewSelectedProduct(String prdtId){

        BaseElement view = viewProductButton(prdtId);
        view.click();
        ProductPage prdtpage = new ProductPage(driver);
        return prdtpage;

    }



}

