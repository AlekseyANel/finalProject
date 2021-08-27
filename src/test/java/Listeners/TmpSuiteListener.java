package Listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class TmpSuiteListener implements ISuiteListener {
    @Override
    public void onStart(ISuite iSuite) {
        System.out.println("----------START Suite----------------\n\n");

    }

    @Override
    public void onFinish(ISuite iSuite) {
        System.out.println("----------FINISH SUITE----------------\n\n");

    }

}


