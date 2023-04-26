package framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static int retryLimit = 1;

    @Override
    public boolean retry(ITestResult result){
        if(result.getStatus() != ITestResult.SUCCESS){
            if(count < retryLimit){
                count++;
                result.setStatus(ITestResult.FAILURE);
                return true;
            }else{
                result.setStatus(ITestResult.FAILURE);
            }
        }else{
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
