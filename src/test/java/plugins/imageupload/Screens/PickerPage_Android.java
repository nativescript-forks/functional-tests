package plugins.imageupload.Screens;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.basetest.MobileContext;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.mobile.find.Wait;
import functional.tests.core.settings.Settings;
import org.openqa.selenium.By;

import java.util.List;

public class PickerPage_Android extends BasePage {
    public Double platformVersion = this.settings.platformVersion;

    public PickerPage_Android(MobileContext context) {
        super(context);
    }

    private By recentLocator() {
        return this.locators.findByTextLocator("Recent", true);
    }

    /**
     * Verify the home page has loaded
     **/
    public void loaded() {
        this.wait.waitForVisible(recentLocator(), true);
        this.log.info("ImagePicker menu loaded.");
    }

    public void pickImages(int imageCount) {

        By listViewItemsLocator = this.locators.listViewItemsLocator();
        UIElement download = this.find.byText("Download");
        if (download == null) {
            if (platformVersion >= 4 && platformVersion < 5) {
                UIElement viewButton = this.find.byLocator(this.context.locators.textViewLocator());
                viewButton.tap(1);
                this.log.info("Tap view button.");
                UIElement listViewButton = this.find.byText("List view");
                listViewButton.tap(1);
                this.log.info("Tap list view button.");
            } else if (platformVersion >= 5 && platformVersion < 6) {
                openStorage("SDCARD");
            } else if (platformVersion >= 6) {
                openStorage("Internal storage");
            }

            download = this.find.byText("Download");
            download.tap();
        }

        Wait.sleep(2000);
        List<UIElement> listViewItems = this.find.elementsByLocator(listViewItemsLocator);
        this.log.info("ListView items count: " + String.valueOf(listViewItems.size()));
        for (int i = 0; i < imageCount; i++) {
            if (platformVersion >= 4 && platformVersion < 5) {
                listViewItems.get(i).tap(1, Settings.DEFAULT_TAP_DURATION * 5);
            } else {
                listViewItems.get(i).tap(1, Settings.DEFAULT_TAP_DURATION * 3);
            }
            this.log.info("Tap image with id: " + String.valueOf(i));
        }

        if (imageCount > 1) {
            if (platformVersion >= 4) {
                UIElement openButton = this.find.byText("Open", this.settings.shortTimeout);
                openButton.click();
            }
        }
    }

    public void openStorage(String storageLocation) {
        UIElement storage = this.find.byText(storageLocation, this.settings.shortTimeout);
        if (storage == null) {
            UIElement downloads = this.find.byText("Downloads");
            downloads.tap();
            UIElement moreOptions = this.find.byText("More options");
            moreOptions.tap();
            UIElement sdCardkShow = this.find.byText("Show SD card");
            sdCardkShow.tap();
            UIElement showRoot = this.find.byText("Show roots");
            showRoot.tap();
            storage = this.find.byText(storageLocation);
        }
        storage.tap();
    }
}
