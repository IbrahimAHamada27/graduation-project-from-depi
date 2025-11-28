package Test;

import BaseTest.BaseTestClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTestClass {

    @Test
    public void VerifyThatUserCanPlaceOrdersuccessfully() {


      /*  homePage.scrollToImage();
         homePage.incrementButtonImage();
         homePage.addtoCart();
         homePage.clickOnCheckOutButton();
         homePage.clickOnCheckOutButton();
        homePage.sendKeyToTextBox();
        Assert.assertTrue(homePage.getActualResult().contains(homePage.getExpectedMessage()));
*/


    }

    @Test
    public void PlaceOrder(){

        HomePage.scrollToProductImage();
        HomePage.clickOnProduct();
        HomePage.addToCart();
        HomePage.clickCheckoutButton();
        HomePage.login("01002879935","Ahmed343420#");
        HomePage.mergeItems();
        HomePage.proceedToPayment();
        HomePage.placeOrder();
        Assert.assertTrue(HomePage.getActualResult().contains(HomePage.getExpectedMessage()));


    }

}
