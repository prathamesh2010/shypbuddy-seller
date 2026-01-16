package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TransactionPage;
import utality.FileDownloadUtil;

public class TransactionTest extends BaseTest {

    TransactionPage transaction;

    /* ================== OPEN PAGE ================== */

    @Test
    public void openTransactionPageTest() throws InterruptedException {
        transaction = new TransactionPage(driver, wait);
        transaction.openTransactionPage();
    }

    /* ================== WALLET ================== */

    @Test(dependsOnMethods = "openTransactionPageTest", alwaysRun = true)
    public void walletTransactionTest() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();

        transaction.walletTransactions("Excel Format", "AWB123456");

        boolean downloaded =
                FileDownloadUtil.waitForFileDownload(".xlsx", 15);

        softAssert.assertTrue(
                downloaded,
                "Wallet Excel file was NOT downloaded"
        );

        softAssert.assertAll();
    }

    /* ================== ORDER ================== */

    @Test(dependsOnMethods = "walletTransactionTest", alwaysRun = true)
    public void orderTransactionTest() {

        SoftAssert softAssert = new SoftAssert();

        transaction.orderTransactions("CSV Format", "SHYC3793821994");

        boolean downloaded =
                FileDownloadUtil.waitForFileDownload(".csv", 15);

        softAssert.assertTrue(
                downloaded,
                "Order CSV file was NOT downloaded"
        );

        softAssert.assertAll();
    }

    /* ================== CREDIT ================== */

    @Test(dependsOnMethods = "orderTransactionTest", alwaysRun = true)
    public void creditTransactionTest() {

        SoftAssert softAssert = new SoftAssert();

        transaction.creditTransactions("Excel Format","SHYC3793821994");

        boolean downloaded =
                FileDownloadUtil.waitForFileDownload(".xlsx", 15);

        softAssert.assertTrue(
                downloaded,
                "Credit Excel file was NOT downloaded"
        );

        softAssert.assertAll();
    }
}
