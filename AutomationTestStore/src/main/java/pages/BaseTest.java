package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LoginPage login;

    public BaseTest(){

    }

    public void initialiseDriver() throws IOException {


        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/Utilities/Global.properties"));
        prop.load(fileInputStream);
        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();}
        else if (browser.equalsIgnoreCase("firefox"))  {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @BeforeMethod(alwaysRun = true)
    public void setup_Test() throws IOException {
        initialiseDriver();
        login = new LoginPage(driver);
        driver.get("https://automationteststore.com/index.php?rt=account/login");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown_Test(){
        driver.quit();
    }
}
