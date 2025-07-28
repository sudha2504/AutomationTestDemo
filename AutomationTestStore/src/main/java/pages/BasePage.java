package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public String categoryName;
    public String menuItemToChoose;
    public String subMenuItemToChoose;
    public String Currency;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void gotoUrl(String url){
        driver.get(url);
    }

    // Locators
    public BaseElement categoryElement(){
        By category = By.xpath(String.format("//ul[contains(@class,'categorymenu')]/li/a[contains(text(),'%s')]", categoryName));
        return new BaseElement(driver, category );
    }

    public BaseElement menuElement(){
        By menu = By.xpath(String.format("//ul[@id='main_menu_top']//a/span[contains(text(),'%s')]", menuItemToChoose));
         return new BaseElement(driver, menu );
    }

    public BaseElement subMenuElement(){
        By menu = By.xpath(String.format("//ul[@id='main_menu_top']//li/a/span[contains(text(),'%s')]", subMenuItemToChoose));
        return new BaseElement(driver, menu );
    }

    public BaseElement currencyhover(){
        By currency = By.xpath("//ul[@class='nav language pull-left']");
        return new BaseElement(driver, currency );
    }

    public BaseElement chooseCurrency(){
        By currencychoose = By.xpath(String.format("//a[contains(text(),'%s')]", Currency));
        return new BaseElement(driver, currencychoose);
    }

    public ProductListingPage click_on_category(String categoryName){
        this.categoryName = categoryName;
        categoryElement().click();
        return new ProductListingPage(driver);
    }

    public void hoverToMenu(String menuItem){
        menuItemToChoose = menuItem;
        menuElement().hover();
    }

    public void clickonSubMenuItem(String subMenu){
        subMenuItemToChoose = subMenu;
        subMenuElement().click();
    }

    public void chooseCurrency(String currencychoose){
        currencyhover().hover();
        Currency = currencychoose;
        chooseCurrency().click();

    }
}
