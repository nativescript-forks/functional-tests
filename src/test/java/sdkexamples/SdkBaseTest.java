package sdkexamples;

import functional.tests.core.mobile.basetest.MobileTest;
import functional.tests.core.mobile.element.UIElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import sdkexamples.Screens.SdkMainPage;

public abstract class SdkBaseTest extends MobileTest {

    protected SdkMainPage mainPage;

    protected abstract String subMainPage();

    protected abstract Object[][] data();

    @BeforeClass
    public void beforeSdkBaseTestTestsClass() {
        this.mainPage = new SdkMainPage(this.subMainPage(), this.context);
    }

    @AfterMethod(alwaysRun = true)
    public void afterSdkBaseTestMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SKIP) {
            result.setStatus(ITestResult.FAILURE);
            this.log.info("Set test result from SKIP to FAILURE.");
        }
        try {
            UIElement btnBack = this.mainPage.btnBack();
            if (btnBack != null) {
                this.mainPage.navigateBack(btnBack);
            }else {

            }
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            result.setStatus(ITestResult.FAILURE);
        }
    }
}
