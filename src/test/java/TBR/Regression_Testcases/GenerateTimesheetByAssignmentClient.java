package TBR.Regression_Testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.TestUtil;

public class GenerateTimesheetByAssignmentClient extends RegressionSuiteBase{
	public static String jobTitle = "qwertyjob";
	public static String clientUsed = "Watsons";
	public static String CandidateUsed = "Sherlock";
	@DataProvider
	public Object[][] timesheetGenerateData(){
		return TestUtil.getDataIntoHashTable(TimesheetsExcel, "GenerateTimeSheetByAssignmnet");
	}
	@Test(dataProvider="timesheetGenerateData")
	public void generateTimeSheet(Hashtable<String, String>data) throws IOException, InterruptedException{
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		getObject("payBillX").click();
		getObject("timeSheetLinkX").click();
		getObject("timesheetGenerateX").click();
		//moves on to Step 1- Filtering By Assignment
		getObjectById("timesheetCandidateNameId").click();
		Thread.sleep(3000);
		getObjectById("timesheetCandidateNameId").sendKeys(data.get("CandidateName"));
		
		getObjectByLinkText("sherlockCandidateLt").click();
		getObjectById("timesheetAssignmentTitleId").click();
		Thread.sleep(3000);
		getObjectById("timesheetAssignmentTitleId").sendKeys(data.get("AssignmentTitle"));
		
		getObjectByLinkText("qwertyJobLt").click();
		getObject("timesheetByAssignmentNextX").click();
		//moves on to step 2-choose by date
		getObjectById("timesheetChooseDateRangeId").click();
		Thread.sleep(3000);
		Select monthByAssignment = new Select(getObject("candidatePassportMonthX"));
		monthByAssignment.selectByVisibleText("Sep");
	    Select yearByAssignment = new Select(getObject("candidatePassportYearX"));
	    yearByAssignment.selectByValue("2015");
	    getObject("timesheetSep2X").click();
	    getObject("timesheetDateRangeFinishX").click();
	    Thread.sleep(6000);
	    
	    //now click on all time sheets
	    getObject("allTimeSheetsX").click();
	    getObject("timesheetFilterX").click();
	    getObject("timesheetFilterX").sendKeys(data.get("TimeSheetFilter"));
	    String jobName = getObject("firstSearchedJobX").getText();
	    System.out.println(jobName);
		//checking the searched job and appeared job are same and if same then the time sheet is generated successfully
	    Assert.assertEquals(jobName, jobTitle);
	    System.out.println("matched! timesheet is generated for "+jobName);
	    
	    //moving on to Generate Client TimeSheet Approval
	    getObject("timesheetGenerateClientApprovalX").click();
	    Thread.sleep(3000);
	    //Step 1 Choose Client
	    getObjectById("timesheetByClientNameId").sendKeys(data.get("ClientName"));
	    getObjectByLinkText("watsonsClientLt").click();
	    getObject("timesheetByClientNextX").click();
	    //Step 2 Create New TimeSheet Approval
	    getObject("timesheetByClientGenerateX").click();
	    Thread.sleep(8000);
	  
	    
	    //moving on to Invoice Generation
	    getObject("invoiceLinkX").click();
	    getObject("invoiceGenerateNewInvoiceX").click();
	    //moving on to step 1 create new invoice
	    getObjectById("invoiceClientNameId").sendKeys(data.get("InvoiceClientName"));
        waitForElementClickableLinkText(10, "watsonsClientLt");
	    getObjectByLinkText("watsonsClientLt").click();
	    getObjectById("invoiceDateEndingId").click();
	    Select monthInvoice = new Select(getObject("monthDatePickerX"));
	    monthInvoice.selectByVisibleText("Sep");
	    Select yearInvoice = new Select(getObject("yearDatePickerX"));
	    yearInvoice.selectByValue("2015");
	    getObject("timesheetSep2X").click();
	    getObject("invoiceGenerateX").click();
	    getObject("invoiceAllInvoicesX").click();
	    getObject("invoiceFilterX").click();
	    getObject("invoiceFilterX").sendKeys(data.get("ClientNameInvoice"));
	    getObjectByLinkText("watsonsClientLt").click();
	    String FirstClientInvoice = getObjectText("ivoiceFirstClientX");
	    System.out.println("client displayed on the screen is "+FirstClientInvoice);
	    Assert.assertEquals(FirstClientInvoice, clientUsed);
	    System.out.println("clients matched! Invoice generated successfully");
	    Thread.sleep(5000);
	   
	    //moving on to payslip for generating new payslip
	    waitForElement(10, "payslipLinkX");
	    getObject("payslipLinkX").click();
	    getObject("payslipGenerateNewPayslipX").click();
	   
	    //step 1 Create New PaySlip
	    Thread.sleep(5000);
	    getObjectById("payslipCandidateNameId").sendKeys(data.get("PayslipCandidateName"));
	    getObjectByLinkText("sherlockCandidateLt").click();
	    getObjectById("payslipInvoiceDateEndingId").click();
	    Select monthForPayslip = new Select(getObject("monthDatePickerX"));
	    monthForPayslip.selectByVisibleText("Sep");
	    Select yearForPayslip = new Select(getObject("yearDatePickerX"));
	    yearForPayslip.selectByValue("2015");
	    getObject("timesheetSep2X").click();
	    getObject("payslipGenerateX").click();
	    getObject("payslipAllPayslipsX").click();
	    getObject("payslipsFilterX").click();
	    getObject("payslipsFilterX").sendKeys(data.get("PaySlipSearchCandidateName"));
	    getObjectByLinkText("sherlockCandidateLt").click();
	    String paySlipFirstCandidate = getObjectText("payslipsFirstCandidateX");
	    Assert.assertEquals(paySlipFirstCandidate, CandidateUsed);
	    System.out.println("candidates matched! payslip generated successfully");
	    
	    
	    
	    
	    
	
	    
	    
	}
}
