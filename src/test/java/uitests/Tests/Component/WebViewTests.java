package uitests.Tests.Component;

import functional.tests.core.mobile.basetest.MobileTest;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import uitests.Screens.Components.WebViewBasePage;

public class WebViewTests extends MobileTest {

    private WebViewBasePage webViewBasePage;

    @BeforeClass(alwaysRun = true)
    public void beforeStylesTestsClass() {
        this.webViewBasePage = new WebViewBasePage(this.context);
    }

    @Test(groups = {"android", "ios"})
    public void webView_01() throws Exception {
        this.webViewBasePage.navToPage("webtest");
        double tolerance = 0.1;
        if (this.settings.platformVersion == 4.2) {
            tolerance = 11;
        }
        this.compareScreens(this.settings.defaultTimeout * 5, tolerance);
        this.assertImagesResults();
    }

    @Test(groups = {"android", "ios"})
    public void webView_02() throws Exception {
        this.webViewBasePage.navigateToMainPage();
        UIElement webViewButton = this.wait.waitForVisible(this.locators.byText("webview"));
        this.webViewBasePage.navigateTo(webViewButton);
        this.assertScreen(this.settings.defaultTimeout * 5, 10);
    }

    @Test(groups = {"android", "ios"})
    public void webView_02_query() throws Exception {
        this.webViewBasePage.navigateBack();
        this.webViewBasePage.navigateTo(this.wait.waitForVisible(this.locators.byText("query")));
        if (this.settings.platformVersion <= 4.3) {
            this.webViewBasePage.tapRelativeBtn();
            this.webViewBasePage.tapAbsoluteBtn();
            this.webViewBasePage.tapFileBtn();
            this.webViewBasePage.tapStringBtn();
        } else {
            Assert.assertNotNull(this.webViewBasePage.srcWebView());

            this.webViewBasePage.tapRelativeBtn();
            Assert.assertNotNull(this.webViewBasePage.strResult());
            Assert.assertNotNull(this.webViewBasePage.strFooBar());
            Assert.assertNotNull(this.webViewBasePage.strUrlTypeRelative());

            this.webViewBasePage.tapAbsoluteBtn();
            Assert.assertNotNull(this.webViewBasePage.strResult());
            Assert.assertNotNull(this.webViewBasePage.strFooBar());
            Assert.assertNotNull(this.webViewBasePage.strUrlTypeAbsolute());

            this.webViewBasePage.tapFileBtn();
            Assert.assertNotNull(this.webViewBasePage.strResult());
            Assert.assertNotNull(this.webViewBasePage.strFooBar());
            Assert.assertNotNull(this.webViewBasePage.strUrlTypeFilePrefix());

            this.webViewBasePage.tapStringBtn();
            Assert.assertNotNull(this.webViewBasePage.str());
        }
    }
}