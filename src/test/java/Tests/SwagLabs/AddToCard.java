package Tests.SwagLabs;

import Base.BaseTest;
import Base.MyActions;
import Base.WebConstants;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class AddToCard extends BaseTest {

    @Test(description = "add products to cart and check if this is added correctly")
    public void addToCart() throws InterruptedException {
        test = extent.createTest("Add To Card");
        //Select the products
        ProductsPage product = new ProductsPage();
        product.selectProduct();

        wait.until(ExpectedConditions.visibilityOfElementLocated(WebConstants.ADD_TO_CART_BTN));
        MyActions.scrollAndClick(WebConstants.ADD_TO_CART_BTN, "ADD_TO_CART_BTN");

        wait.until(ExpectedConditions.visibilityOfElementLocated(WebConstants.CART_BADGE));
        MyActions.scrollAndClick(WebConstants.CART_ICONE, "CART_ICONE");

    }

}
