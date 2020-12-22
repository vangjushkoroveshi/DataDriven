package Tests.SwagLabs;

import Base.BaseTest;
import Base.MyActions;
import Base.WebConstants;
import ExcelReader.Data.Login_Data;
import ExcelReader.ExcelUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Validate extends BaseTest {

    @Test(description = "Login with valid credential")
    public void valid(){
        test = extent.createTest("Login Page");
        Boolean  aBoolean =  driver.getPageSource().contains(driver.getTitle());
        Assert.assertTrue(aBoolean);
    }
}

