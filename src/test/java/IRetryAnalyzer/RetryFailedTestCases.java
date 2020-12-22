package IRetryAnalyzer;

import Base.BaseTest;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

public class RetryFailedTestCases extends BaseTest implements IRetryAnalyzer {
    private int retryCount = 0;
    @Parameters("deploy")
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < this.maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }

}