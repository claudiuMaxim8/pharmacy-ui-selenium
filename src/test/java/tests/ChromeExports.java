package tests;
import org.testng.annotations.Test;

import BaseTest.BaseTest;
import assertions.AssertiveFunctions;
import pages.AdminHomePage;
import pages.AdminReports;
import pages.InventoryPages;
import pages.LoginPage;
import pages.PractitionerHomePage;
import pages.PractitionerReports;
import variables.Variables;

public class ChromeExports extends BaseTest {
	
	String admin = getCredentials().get("admin");
	String practitioner = getCredentials().get("practitioner");
	String CSR = getCredentials().get("CSR");
	
	@Test(enabled = true, priority = 1)
    public void practitionerOrderReport_Export() {
        page.getInstance(LoginPage.class).login(practitioner.split(":")[0], practitioner.split(":")[1]);
        page.getInstance(PractitionerHomePage.class).enter_OrderReportPage();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathChrome, Variables.orderReport);
        page.getInstance(PractitionerReports.PractitionerOrderReportPage.class).exportFile();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathChrome, Variables.orderReport);
    }

    @Test(enabled = true, priority = 2)
    public void adminOrderReport_Export() throws InterruptedException {
        page.getInstance(LoginPage.class).login(admin.split(":")[0], admin.split(":")[1]);
        page.getInstance(AdminHomePage.class).enter_ReportPage();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathChrome, Variables.orderReport);
        page.getInstance(AdminReports.AdminOrderReportPage.class).exportFile();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathChrome, Variables.orderReport);
    }

    @Test(enabled = true, priority = 3)
    public void lotNumber_RecallReport() throws InterruptedException {
    	page.getInstance(LoginPage.class).login(admin.split(":")[0], admin.split(":")[1]);
        page.getInstance(AdminReports.class).enter_LotNumberRecallReportPage();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathChrome, Variables.lotNumberRecallReport);
        page.getInstance(AdminReports.LotNumberRecallReport.class).setStartDate("8/1/2018");
        page.getInstance(AdminReports.LotNumberRecallReport.class).setEndDate("2/29/2020");
        page.getInstance(AdminReports.LotNumberRecallReport.class).exportCSV();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathChrome, Variables.lotNumberRecallReport);
    }

    @Test(enabled = true, priority = 4)
    public void expired_LotNumber() {
        page.getInstance(LoginPage.class).login(admin.split(":")[0], admin.split(":")[1]);
        page.getInstance(InventoryPages.class).enter_ExpiredLotNumber();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathChrome, Variables.expiredLotReport);
        page.getInstance(InventoryPages.ExpiredLotNumber.class).exportFile();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathChrome, Variables.expiredLotReport);
    }

    @Test(enabled = true, priority = 5) // need 2 rewrite locators
    public void thresholdReport() {
        page.getInstance(LoginPage.class).login(admin.split(":")[0], admin.split(":")[1]);
        page.getInstance(InventoryPages.class).enter_ThresholdReport();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathChrome, Variables.thresholdReport);
        page.getInstance(InventoryPages.ThresholdReport.class).generateExportList();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathChrome, Variables.thresholdReport);
    }
}
