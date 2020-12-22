package Base;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyActions extends BaseTest{

    public static boolean isTestable(String flag) {
        if (flag != null) {
            flag = flag.trim();
            if (flag.equalsIgnoreCase("on"))
                return true;
            else return false;
        } else return false;
    }

    public static void scrollAndClick(By by, String locatorName) throws AssertionError{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String locatorValue = by.toString();
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
            WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
            js.executeScript("arguments[0].click()",element);
            test.log(Status.PASS, "Clicked on "+locatorName);
        } catch (Throwable e){
            Assert.fail("Failed to click, variable = [name: "+ locatorName +", value:"+locatorValue+"].");
            test.log(Status.FAIL, "Failed to click "+ locatorName+ e);
        }
    }

    public static void type(By by, String locatorName, String content) throws AssertionError {
        String locatorValue = by.toString();
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
            WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
            element.sendKeys(content);
            test.log(Status.PASS, "Enter: " + content +" as "+locatorName);
        } catch (Exception e) {
            Assert.fail("Failed to type:'" + content + "',variable={name:" + locatorName + ",value:" + locatorValue + "}.");
            test.log(Status.FAIL, "Failed to type: '" + content + "',variable={name:" + locatorName + ",value:" + locatorValue + "}.");
        }
    }

    public static void verifyContent(By by, String locatorName, String content) throws AssertionError {
        String locatorValue = by.toString();
        WebElement element = null;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
            element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));

        } catch (Exception e) {
            Assert.fail("Failed to find element,variable={name:" + locatorName + ",value:" + locatorValue + "}.");
            test.log(Status.FAIL, " Failed to find element,variable={name:" + locatorName + ",value:" + locatorValue + "}." + e);
        }
        String elementText = element.getText().trim();
        String elementValue = element.getAttribute("value");
        boolean foundContentFlag = false;
        if (!content.equalsIgnoreCase(elementText)) {
            if (content.equalsIgnoreCase(elementValue))
                foundContentFlag = true;
        } else
            foundContentFlag = true;
        if (foundContentFlag == false) {
            Assert.fail("Failed to find content '" + content + "' at element '" + locatorName + "'.Actual content found : " + elementText + " , " + elementValue + " .");
            test.log(Status.FAIL, " Failed to find content " + content + "' at element '" + locatorName);
        } else {
            System.out.println("Label found. Expected '" + content + "', actual : " + elementText + " , " + elementValue + ".");
            test.log(Status.PASS, "Label: " + content+ " - is displayed");
        }
    }
}
