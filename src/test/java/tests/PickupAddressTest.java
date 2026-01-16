package tests;

import org.testng.annotations.Test;
import pages.PickupAddressPage;

public class PickupAddressTest extends BaseTest {

    @Test
    public void addNewAddress() throws InterruptedException {

        PickupAddressPage addresses = new PickupAddressPage(driver, wait);

        addresses.createAddress();

        addresses.fillDetails(
            "jip",
            "4562589456",
            "102 jivdani park 2",
            "gaskopri",
            "401305",
            "virar",
            "maharashtra",
            "india"
        );

        addresses.submitAddress();
        Thread.sleep(3000);
    }
}
