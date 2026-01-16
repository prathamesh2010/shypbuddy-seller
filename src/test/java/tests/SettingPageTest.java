package tests;

import org.testng.annotations.Test;
import pages.SettingsPage;

public class SettingPageTest extends BaseTest {

    //SettingsPage settingsPage;

    @Test
    public void settingsFullFlowTest() throws InterruptedException {

    	SettingsPage set = new SettingsPage (driver);
    	
        // Initialize page object
       // settingsPage = new SettingsPage(driver);

        // 1️⃣ Open settings page
        set.openSettings();

        // 2️⃣ Change partner preference
        set.partnerpref("Bluedart Surface");

        // 3️⃣ Click Add button
        set.addButton();

        // 4️⃣ Open Rate Master popup, scroll & close
        set.viewRatesAndScrollThenClose();
    }
}
 