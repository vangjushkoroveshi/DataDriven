package Tests.SwagLabs;

import Base.BaseTest;
import Base.MyActions;
import Base.WebConstants;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsPage extends BaseTest{

    @Test(priority = 1,description = "Navigate to product page and validate if elements are being displayed")
    public void selectProduct(){
        test = extent.createTest("Product Page");

        Login sl = new Login();
        sl.login();

        //Click on the first product
        List<WebElement> products = driver.findElements(WebConstants.PRODUCTS_NAME);
        products.get(0).click();
    }

    @Test(priority = 2, description = "Navigate to product page and validate if elements are being displayed")
    public void validateProducts(){
        test = extent.createTest("Validate Products");
        wait.until(ExpectedConditions.visibilityOfElementLocated(WebConstants.PRODUCT_IMG));
        MyActions.verifyContent(WebConstants.PRODUCT_IMG,"PRODUCT_IMG","");
        MyActions.verifyContent(WebConstants.PRODUCTS_DETAILS_PRICE,"PRODUCTS_PRICE","$29.99");
        MyActions.verifyContent(WebConstants.ADD_TO_CART_BTN,"ADD_TO_CART_BTN","ADD TO CART");
        MyActions.verifyContent(WebConstants.PRODUCTS_DETAILS_NAME,"PRODUCTS_DETAILS_NAME", "Sauce Labs Backpack");
    }
}
