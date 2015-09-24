package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class ExportPayslipRough extends TestBase{


	@Test
	public void exportPayslipRough() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		 //moving on to payslip for generating new payslip
		getObject("payBillX").click();
		getObject("payslipLinkX").click();
		waitForElementClickable(10, "exportPayslipX");
		getObject("exportPayslipX").click();
		
		//for payslip Export  Date Starting
		getObjectById("exportDateStartingPayslipId").click();
		Select monthForExportCandidatesStart = new Select(getObject("exportMonthDatePickerPayslipX"));
		monthForExportCandidatesStart.selectByVisibleText("Sep");
		Select yearForExportTimesheetstart = new Select(getObject("exportYearDatePickerPayslipX"));
		yearForExportTimesheetstart.selectByValue("2015");
		getObject("exportDateStartingSep3X").click();
		Thread.sleep(5000);
		
		//For payslip Export Date Ending
		getObjectById("exportDateEndingPayslipId").click();
		Select monthForExportCandidatesEnd = new Select(getObject("exportMonthDatePickerPayslipX"));
		monthForExportCandidatesEnd.selectByVisibleText("Sep");
		Select yearForExportTimesheetend = new Select(getObject("exportYearDatePickerPayslipX"));
		yearForExportTimesheetend.selectByValue("2015");
	
		getObject("exportDateStartingSep3X").click();
		getObjectById("exportPayslipCandidateNameId").sendKeys("Sherlock");
		getObjectByLinkText("sherlockCandidateLt").click();
		
		//click on Next Step 1
		getObjectByCss("exportNextCss").click();
		
		//moving on to Create New ExportPaySlips Step 2 
		//getObjectById("exportStyleSheetPayslipId").sendKeys("");
		getObjectByCss("exportPayslipExportCss").click();
		
		

}
}
