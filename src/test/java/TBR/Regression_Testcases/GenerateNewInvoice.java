package TBR.Regression_Testcases;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateNewInvoice extends RegressionSuiteBase {
	public static String clientUsed = "Watsons";
	@Test
	public void generateNewInvoice(){
		
		//moving on to Invoice Generation
	    getObject("invoiceLinkX").click();
	    getObject("invoiceGenerateNewInvoiceX").click();
	    
	    //moving on to step 1 create new invoice
	    getObjectById("invoiceClientNameId").sendKeys("InvoiceClientName");
        waitForElementClickableLinkText(10, "watsonsClientLt");
	    getObjectByLinkText("watsonsClientLt").click();
	    getObjectById("invoiceDateEndingId").click();
	    Select monthInvoice = new Select(getObject("monthDatePickerX"));
	    monthInvoice.selectByVisibleText("Dec");
	    Select yearInvoice = new Select(getObject("yearDatePickerX"));
	    yearInvoice.selectByValue("2015");
	    getObject("dec31X").click();
	    getObject("invoiceGenerateX").click();
	    getObject("invoiceAllInvoicesX").click();
	    getObject("invoiceFilterX").click();
	    getObject("invoiceFilterX").sendKeys("ClientNameInvoice");
	    getObjectByLinkText("watsonsClientLt").click();
	    String FirstClientInvoice = getObjectText("ivoiceFirstClientX");
	    System.out.println("client displayed on the screen is "+FirstClientInvoice);
	    Assert.assertEquals(FirstClientInvoice, clientUsed);
	    System.out.println("clients matched! Invoice generated successfully");

	}
}
