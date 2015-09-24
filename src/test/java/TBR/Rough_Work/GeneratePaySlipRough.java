package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class GeneratePaySlipRough extends TestBase{
	public static String CandidateUsed = "Sherlock";
	@Test
	public void generatePaySlip() throws IOException, InterruptedException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		 //moving on to payslip for generating new payslip
		getObject("payBillX").click();
	    waitForElement(10, "payslipLinkX");
	    getObject("payslipLinkX").click();
	    getObject("payslipGenerateNewPayslipX").click();
	    Thread.sleep(3000);
		  //step 1 Create New PaySlip
	    getObjectById("payslipCandidateNameId").sendKeys("Sherlock");
	    getObjectByLinkText("sherlockCandidateLt").click();
	    Thread.sleep(3000);
	    getObjectById("payslipInvoiceDateEndingId").click();
	    Select monthForPayslip = new Select(getObject("monthDatePickerX"));
	    monthForPayslip.selectByVisibleText("Sep");
	    Select yearForPayslip = new Select(getObject("yearDatePickerX"));
	    yearForPayslip.selectByValue("2015");
	    Thread.sleep(4000);
	    getObject("dateDatePickerSep3X").click();
	    getObject("payslipGenerateX").click();
	    Thread.sleep(8000);
	    getObject("payslipAllPayslipsX").click();
	    getObject("payslipsFilterX").click();
	    getObject("payslipsFilterX").sendKeys("Sherlock");
       // waitForElementClickableLinkText(10, "sherlockCandidateLt");
	    //getObjectByLinkText("sherlockCandidateLt").click();
	    String paySlipFirstCandidate = getObjectText("payslipsFirstCandidateX");
	    Assert.assertEquals(paySlipFirstCandidate, CandidateUsed);
	    System.out.println("candidates matched! payslip generated successfully");

	}

}
