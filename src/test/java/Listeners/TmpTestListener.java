package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TmpTestListener implements ITestListener {

    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("----------Start Test----------------\n\n");

    }
    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("----------FINISH Test----------------\n\n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult res) {
    }
        @Override
        public void onTestFailure(ITestResult res) {
            System.out.println(res.getMethod().getMethodName() + " FAILURE !!! \n\n");
        }
        @Override
        public void onTestSkipped(ITestResult res) {
            System.out.println(res.getMethod().getMethodName() + " SKIPPED !!! \n\n");
        }
        @Override
        public void onTestStart(ITestResult res) {
            System.out.println("Start test " + res.getMethod().getMethodName());
        }
        @Override
        public void onTestSuccess(ITestResult res) {
            System.out.println(res.getMethod().getMethodName() + " PASSED !!!  \n\n");
        }


}


