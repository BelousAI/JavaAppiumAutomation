package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
        SAVED_ELEMENT = "xpath://XCUIElementTypeCell";
    }

    public iOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
