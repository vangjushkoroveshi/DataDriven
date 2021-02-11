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

public class Login extends BaseTest {

    @Test(description = "Login with valid credential")
    public void login(){
        test = extent.createTest("Login Page");
        List<Login_Data> testDataList = ExcelUtils.getLogin();
        for (Login_Data testData : testDataList) {
            if (MyActions.isTestable(testData.getExecution())) {
                MyActions.type(WebConstants.USERNAME_INPUT, "USERNAME_INPUT", testData.getUsername());
                MyActions.type(WebConstants.PASSWORD_INPUT, "PASSWORD_INPUT", testData.getPassword());
                MyActions.scrollAndClick(WebConstants.LOGIN_BUTTON, "LOGIN_BUTTON");
                wait.until(ExpectedConditions.visibilityOfElementLocated(WebConstants.PRODUCTS_LABEL));
                Assert.assertEquals(driver.findElement(WebConstants.PRODUCTS_LABEL).getText(),"Products");
            }
        }
    }
}

