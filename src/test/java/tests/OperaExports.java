package tests;

import org.testng.annotations.Test;

import assertions.AssertiveFunctions;
import pages.AdminHomePage;
import pages.AdminReports;
import pages.InventoryPages;
import pages.LoginPage;
import pages.PractitionerHomePage;
import pages.PractitionerReports;
import variables.Variables;

public class OperaExports extends BaseTest {
	
	@Test(enabled = true, priority = 1)
    public void practitionerOrderReport_Export() {
        page.getInstance(LoginPage.class).login(Variables.practitioner, Variables.actualPass);
        page.getInstance(PractitionerHomePage.class).enter_OrderReportPage();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathOpera, Variables.orderReport);
        page.getInstance(PractitionerReports.PractitionerOrderReportPage.class).exportFile();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathOpera, Variables.orderReport);
    }

    @Test(enabled = true, priority = 2)
    public void adminOrderReport_Export() throws InterruptedException {
        page.getInstance(LoginPage.class).login(Variables.admin, Variables.actualPass);
        page.getInstance(AdminHomePage.class).enter_ReportPage();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathOpera, Variables.orderReport);
        page.getInstance(AdminReports.AdminOrderReportPage.class).exportFile();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathOpera, Variables.orderReport);
    }

    @Test(enabled = true, priority = 3)
    public void lotNumber_RecallReport() throws InterruptedException {
    	page.getInstance(LoginPage.class).login(Variables.admin, Variables.actualPass);
        page.getInstance(AdminReports.class).enter_LotNumberRecallReportPage();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathOpera, Variables.lotNumberRecallReport);
        page.getInstance(AdminReports.LotNumberRecallReport.class).exportCSV();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathOpera, Variables.lotNumberRecallReport);
    }

    @Test(enabled = true, priority = 4)
    public void expired_LotNumber() {
        page.getInstance(LoginPage.class).login(Variables.admin, Variables.actualPass);
        page.getInstance(InventoryPages.class).enter_ExpiredLotNumber();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathOpera, Variables.expiredLotReport);
        page.getInstance(InventoryPages.ExpiredLotNumber.class).exportFile();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathOpera, Variables.expiredLotReport);
    }

    @Test(enabled = false, priority = 5) // need 2 rewrite locators
    public void thresholdReport() {
        page.getInstance(LoginPage.class).login(Variables.admin, Variables.actualPass);
        page.getInstance(InventoryPages.class).enter_ThresholdReport();
        page.getInstance(AssertiveFunctions.class).isFileDownloaded(Variables.downloadFilepathOpera, Variables.thresholdReport);
        page.getInstance(InventoryPages.ThresholdReport.class).generateExportList();
        page.getInstance(AssertiveFunctions.class).assertExport(Variables.downloadFilepathOpera, Variables.thresholdReport);
    }
}
