package orders;
import Utilities.TestDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseTest;
import java.util.LinkedHashMap;

public class RegisterGuestTest extends BaseTest {

    @Test(dataProvider = "getUserData", dataProviderClass = TestDataProvider.class, groups="sanity")
    public void registerNewUser(LinkedHashMap<String, String> userData){

        String registrationMsg = login.continueToRegister(userData);
        Assert.assertTrue(registrationMsg.equalsIgnoreCase("YOUR ACCOUNT HAS BEEN CREATED!"), "Message mismatched {} " + registrationMsg);
        System.out.println("User " + userData.get("loginname") + " is created with password " + userData.get("password"));

    }
}
