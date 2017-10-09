package ru.anatoli.addressbook.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.anatoli.addressbook.appmanager.ApplicationManager;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        ApplicationManager applicationManager = (ApplicationManager) result.getTestContext().getAttribute("applicationManager");
        saveScreenshot(applicationManager.takeScreenshot());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    public byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }
}
