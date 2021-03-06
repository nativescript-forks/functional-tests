package nativescriptdemo.Screens.Examples;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.basetest.MobileContext;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class AreaSeriesPage extends BasePage {

    public AreaSeriesPage(MobileContext context) {
        super(context);
    }

    public void loaded() {
        UIElement element = this.wait.waitForVisible(this.locators.byText("Area series", false, false), this.settings.shortTimeout, true);
        if (element != null) {
            this.log.info("Area series page loaded.");
        } else {
            Assert.fail("Area series page NOT loaded!");
        }
    }
}
