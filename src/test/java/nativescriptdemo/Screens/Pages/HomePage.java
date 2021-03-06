package nativescriptdemo.Screens.Pages;

import functional.tests.core.enums.PlatformType;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.mobile.helpers.NavigationHelper;
import io.appium.java_client.SwipeElementDirection;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage {

    private int waitTimeOut = this.settings.shortTimeout;
    private GetStartedPage getStartedPage;

    public HomePage() {
        super();
        this.getStartedPage = new GetStartedPage();
    }

    public void load() {
        this.getStartedPage.loaded();
        this.getStartedPage.tapGetStarted();
        if (this.loaded() == false) {
            Assert.fail("Home page NOT loaded!");
        }
    }

    public Boolean loaded() {
        UIElement element = this.btnSideDrawer();
        if (element != null) {
            this.log.info("Home page loaded.");
            return true;
        } else {
            this.log.error("Home page NOT loaded!");
            return false;
        }
    }

    public Boolean unloaded() {
        if (this.loaded()) {
            this.log.error("Home page NOT unloaded! It is still visible.");
            return false;
        } else {
            this.log.info("Home page unloaded. It is not visible.");
            return true;
        }
    }

    public void tapSideDrawer() {
        if (this.settings.platformVersion >= 11.0 && this.settings.platform == PlatformType.iOS) {
            this.client.driver.swipe(10, 100, 200, 100, 250);
            this.log.info("Open SideDrawer with swipe.");
        } else {
            this.btnSideDrawer().tap();
            this.log.info("Tap on SideDrawer button.");
        }
    }


    @Override
    public boolean navigateTo(String example) {
        UIElement element;
        if (this.settings.platform == PlatformType.Android) {
            element = this.gestures.swipeInWindowToElement(SwipeElementDirection.DOWN, this.locators.byText(example), 5, 250, 0);
        } else {
            element = NavigationHelper.scrollTo(example, this.context, 5);
        }

        return super.navigateTo(element);
    }

    private UIElement btnSideDrawer() {
        if (this.settings.platform == PlatformType.iOS) {
            if (this.settings.platformVersion >= 11.0) {
                return this.find.byLocator(By.id("ActionBar"));
            } else {
                return this.find.byLocator(By.id("ic_menu_main"));
            }
        } else if (this.settings.platform == PlatformType.Android) {
            return this.wait.waitForVisible(this.locators.imageButtonLocator());
        } else {
            return null;
        }
    }
}
