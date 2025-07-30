package Utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

public class PageListing {

    public PageListing(){

    }

    public static Boolean checkCurrency(WebDriver driver, String Currency){

        driver.get("https://automationteststore.com/");
        String pageSource = driver.getPageSource();

        Document doc = Jsoup.parse(pageSource);
        Elements divs = doc.select("div.oneprice");


        for (Element div : divs) {
            boolean b = div.text().contains(Currency);
            if (!b) {
               return false;}
            }

        return true;
    }


    }
