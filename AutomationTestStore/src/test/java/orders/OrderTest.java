package orders;
import Utilities.PageListing;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import Utilities.TestDataProvider;

public class OrderTest extends BaseTest {

      public String orderId;

      @Test(groups={"sanity"})
      public void test_order_complete() throws InterruptedException {

            login.login_to_application("tomsam", "Pwd@12345");
            Assert.assertEquals("My Account", driver.getTitle());

            ProductListingPage prdtlisting = login.click_on_category("Apparel");
            ProductPage prdtpage = prdtlisting.viewSelectedProduct("115");
            CartPage cartpage = prdtpage.addToCart();
            CheckoutPage chkout = cartpage.clickCheckout();
            ConfirmationPage confirmpage = chkout.confirmOrder();
            Assert.assertEquals("YOUR ORDER HAS BEEN PROCESSED!", confirmpage.checkMessageDisplayed().toUpperCase(), "Order is unsuccessful Expected : {} Actual: {} "
                    .format("Your Order Has Been Processed!", confirmpage.checkMessageDisplayed().toUpperCase()));
            System.out.println(confirmpage.getOrderId());
            orderId = confirmpage.getOrderId();

      }

      @Test(dependsOnMethods = "test_order_complete")
      public void verify_order_details(){

            login.login_to_application("tomsam", "Pwd@12345");

            login.gotoUrl(OrderHistoryPage.URL);
            OrderHistoryPage orderPage = new OrderHistoryPage(driver);

            orderPage.verifyOrderHistory(orderId);
      }


      @Test
      public void test_modify_cart_order_complete() throws InterruptedException {

            login.login_to_application("tomsam", "Pwd@12345");

            System.out.println(driver.getTitle());
            Assert.assertEquals("My Account", driver.getTitle());

            ProductListingPage prdtlisting = login.click_on_category("Apparel");
            ProductPage prdtpage = prdtlisting.viewSelectedProduct("115");
            CartPage cartpage = prdtpage.addToCart();

            cartpage.click_on_category("Makeup");
            prdtlisting.viewSelectedProduct("53");
            prdtpage.addToCart();

            cartpage.click_on_category("Fragrance");
            prdtlisting.viewSelectedProduct("102");
            prdtpage.addToCart();

            cartpage.modifyItemfromCart("102", "2");
            cartpage.updateCart();

            cartpage.removeItemfromCart("53");
            cartpage.updateCart();

            CheckoutPage chkout = cartpage.clickCheckout();
            ConfirmationPage confirmpage = chkout.confirmOrder();
            Assert.assertEquals("YOUR ORDER HAS BEEN PROCESSED!", confirmpage.checkMessageDisplayed().toUpperCase(), "Order is unsuccessful Expected : {} Actual: {} "
                    .format("Your Order Has Been Processed!", confirmpage.checkMessageDisplayed().toUpperCase()));
            System.out.println(confirmpage.getOrderId());
            orderId = confirmpage.getOrderId();

      }

      @Test(dataProvider = "Currency", dataProviderClass = TestDataProvider.class)
      public void test_checkPrice(String Currency, String CurrencySymbol) throws InterruptedException {

//            login.login_to_application("tomsam", "Pwd@12345");
            login.chooseCurrency(Currency);
            System.out.println(driver.getTitle());
            Assert.assertTrue(PageListing.checkCurrency(driver,CurrencySymbol));

      }
}

