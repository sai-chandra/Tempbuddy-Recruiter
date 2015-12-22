package TBR.Regression.PayandBill;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateNewPayslip extends PayandBillRegressionSuitebase {

	public static String CandidateUsed = "Sherlock";
	@Test
	public void generatePaySlip() throws IOException, InterruptedException{
		browserUrl();
		
		//moving on to payslip for generating new payslip
		getObject("payBillX").click();
	    waitForElement(10, "payslipLinkX");
	    getObject("payslipLinkX").click();
	    waitForElementClickable(10, "payslipGenerateNewPayslipX");
	    getObject("payslipGenerateNewPayslipX").click();
	    Thread.sleep(3000);
		
	    //step 1 Create New PaySlip
	    getObjectById("payslipCandidateNameId").sendKeys("Sherlock");
	    getObjectByLinkText("sherlockCandidateLt").click();
	    Thread.sleep(3000);
	    getObjectById("payslipInvoiceDateEndingId").click();
	    Select monthForPayslip = new Select(getObject("monthDatePickerX"));
	    monthForPayslip.selectByVisibleText("Dec");
	    Select yearForPayslip = new Select(getObject("yearDatePickerX"));
	    yearForPayslip.selectByValue("2015");
	    waitForElementClickable(10, "dec8x");
	    getObject("dec8x").click();
	    getObject("payslipGenerateX").click();
	    Thread.sleep(8000);
	    
	    //moving on to All PaySlips
	    getObject("payslipAllPayslipsX").click();
	    getObject("payslipsFilterX").click();
	    getObject("payslipsFilterX").sendKeys("Sherlock");
	    Thread.sleep(3000);
        // waitForElementClickableLinkText(10, "sherlockCandidateLt");
	    //getObjectByLinkText("sherlockCandidateLt").click();
	    String paySlipFirstCandidate = getObjectText("payslipsFirstCandidateX");
	    Assert.assertEquals(paySlipFirstCandidate, CandidateUsed);
	    System.out.println("candidates matched! payslip generated successfully");

	}

}
