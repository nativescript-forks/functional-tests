package groceries.Screens;

import functional.tests.core.mobile.basetest.MobileContext;
import org.openqa.selenium.By;

public class ErrorDialog {
    private MobileContext context;

    public ErrorDialog(MobileContext context) {
        this.context = context;
    }

    private By btnOk() {
        return this.context.locators.byText("OK");
    }

    public void ok() {
        this.context.find.byLocator(btnOk()).click();
    }
}
