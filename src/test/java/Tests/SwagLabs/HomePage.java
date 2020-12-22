package Tests.SwagLabs;

import Base.BaseTest;
import Base.WebConstants;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomePage extends BaseTest {

    @Test(description = "Display all products name and price")
    public void verifyProducts(){
        test = extent.createTest("HomePage");
        // login in the system
        Login sl = new Login();
        sl.login();

        List<WebElement> products = driver.findElements(WebConstants.PRODUCTS_LIST);
        Assert.assertEquals(products.size(), 6);

        List<WebElement> productsName = driver.findElements(WebConstants.PRODUCTS_NAME);
        List<WebElement> productsPrice = driver.findElements(WebConstants.PRODUCTS_PRICE);

        for (int i =0; i < products.size(); i++){
            System.out.println("ProductsPage: "+ i);
            System.out.println("Name: "+productsName.get(i).getText());
            System.out.println("Price: "+productsPrice.get(i).getText());
        }
    }
}
