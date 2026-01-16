package tests;

import org.testng.annotations.Test;
import pages.EditAddressPage;

public class EditAddressTest extends BaseTest {

	 @Test         
    public void editExistingAddress() {

        EditAddressPage edit = new EditAddressPage(driver, wait);

        edit.editAddress();

        edit.updateAddress(
            "Home",
            "9876543210",
            "New Address Line",
            "Near Mall",
            "400001",
            "Mumbai",
            "Maharashtra",
            "India"
        );
    }
}
