package TBR.Regression_Testcases;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ExportInvoices extends RegressionSuiteBase {
	
	@Test
	public void exportInvoices() throws InterruptedException{
		
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
	
		getObject("payBillX").click();
		getObject("invoiceLinkX").click();
		getObject("exportInvoicesX").click();
		
		//moves on to Create New ExportInvoices Wizard Step 1 Export
		//for Invoice Export  Date Starting
		getObjectById("exportDateStartingInvoiceId").click();
		Select monthForExportCandidatesStart = new Select(getObject("exportDateStartingInvoiceMonthX"));
		monthForExportCandidatesStart.selectByVisibleText("Sep");
		Select yearForExportTimesheetstart = new Select(getObject("exportDateStartingInvoiceYearX"));
		yearForExportTimesheetstart.selectByValue("2015");
		getObject("exportDateStartingSep3X").click();
		Thread.sleep(5000);
		
		//For Invoice Export Date Ending
		getObjectById("exportDateEndingInvoiceId").click();
		Select monthForExportCandidatesEnd = new Select(getObject("exportDateEndingInvoiceMonthX"));
		monthForExportCandidatesEnd.selectByVisibleText("Sep");
		Select yearForExportTimesheetend = new Select(getObject("exportDateEndingInvoiceYearX"));
		yearForExportTimesheetend.selectByValue("2015");

		getObject("exportDateStartingSep3X").click();
		getObjectById("exportClientNameInvoiceId").sendKeys("Watsons");
		getObjectByLinkText("watsonsClientLt").click();
		
		getObject("exportInvoiceExportX").click();
	
	
		
	}

}
