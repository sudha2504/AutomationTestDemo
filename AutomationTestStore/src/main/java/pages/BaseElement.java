package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseElement {

    WebDriver driver;
    By locator;
    WebElement webElement;
    WebDriverWait wait;

    public BaseElement(WebDriver driver, By locator){

        this.driver = driver;
        this.locator = locator;
        this.webElement= null;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        findElement(locator);

    }

    public void findElement(By Locator){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        this.webElement = element;
    }

    public void click(){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void inputText(String text){
        clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    public void clear(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
    }


    public String getTextValue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void getUrl(String Url){
        wait.until(ExpectedConditions.urlToBe(Url));
    }

    public void hover(){
        Actions action = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        action.moveToElement(webElement).perform();
    }



}
