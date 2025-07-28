package pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLOutput;

public class OrderHistoryPage extends BasePage{

    public static String URL = "https://automationteststore.com/index.php?rt=account/history";

    public OrderHistoryPage(WebDriver driver){
        super(driver);
    }

    public BaseElement orderHistoryButton(){
        By orderHistory = By.cssSelector(String.format("a[id='cart_checkout1']"));
        return new BaseElement(driver, orderHistory );
    }

    public void verifyOrderHistory(String orderId){

        String pageSource = driver.getPageSource();

        Document doc = Jsoup.parse(pageSource);
        Elements divs = doc.select("div");

        for (Element div : divs) {
            if (div.text().contains("Order ID:")) {
                String text = div.text();  // text is "Order ID:#59182"
                String orderOnPage = text.substring(text.indexOf("#"));  // returns "59182"
                if (orderOnPage.equalsIgnoreCase(orderId)) {
                    System.out.printf(String.format("Order %s found", orderId));
                    break;
                }

            }

    }}

}
