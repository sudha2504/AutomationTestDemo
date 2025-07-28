package pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(css= "#loginFrm_loginname")
    WebElement login;

    @FindBy(css= "#loginFrm_password")
    WebElement pwd;

    By loginButtonLocator = By.cssSelector("button[title='Login']");
    By newsletter = By.cssSelector("input#AccountFrm_newsletter1");
    By policy = By.cssSelector("input#AccountFrm_agree");

    public BaseElement loginButton(){
        return new BaseElement(driver, loginButtonLocator );
    }

    public BaseElement continueButton(){
        By continueButton = By.cssSelector("button[title='Continue']");
        return new BaseElement(driver, continueButton );
    }

    public BaseElement accountcreation(){

        By order = By.cssSelector("span.maintext");
        return new BaseElement(driver, order);
    }


    public AccountPage login_to_application(String username, String password){

        login.sendKeys(username);
        pwd.sendKeys(password);
        loginButton().click();
        return new AccountPage(driver);
    }

    public void registerUser(HashMap<String, String> userData){

        Iterator<Map.Entry<String, String>> iterator = userData.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            if ((entry.getKey() == "country_id") | (entry.getKey() == "zone_id")) {
                By input = By.cssSelector(String.format("select[name='%s']", entry.getKey()));
                Select select = new Select(new BaseElement(driver, input).webElement);
                select.selectByValue(entry.getValue());
            }
            else {
                By input = By.cssSelector(String.format("input[name='%s']", entry.getKey()));
                if (entry.getKey() == "email") {
                    String value = entry.getValue() + UUID.randomUUID().toString().substring(0, 8) + "@fakergmail.com";
                    entry.setValue(value);
                }else if (entry.getKey() == "loginname") {
                    String value = RandomStringUtils.randomAlphanumeric(10);
                    entry.setValue(value);
                }
                new BaseElement(driver, input).inputText(entry.getValue());
            }
        }

        new BaseElement(driver, newsletter).click();
        new BaseElement(driver, policy).click();
    }

    public String continueToRegister(LinkedHashMap<String, String> userData){
        continueButton().click();
        registerUser(userData);
        continueButton().click();
        return (accountcreation().getTextValue());
    }
}
