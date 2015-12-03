package TBR.Regression.PayandBill;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenerateNewInvoice extends PayandBillRegressionSuitebase{
	
	public static String clientUsed = "Watsons";
	@Test
	public void generateNewInvoice() throws InterruptedException{
		
		browserUrl();
		
		getObject("payBillX").click();
		
		//moving on to Invoice Generation
	    getObject("invoiceLinkX").click();
	    getObject("invoiceGenerateNewInvoiceX").click();
	    Thread.sleep(2000);
	    
	    //moving on to step 1 create new invoice
	    getObjectById("invoiceClientNameId").sendKeys("Watsons");
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
	    getObject("invoiceFilterX").sendKeys("Watsons");
	    Thread.sleep(3000);
	    //getObjectByLinkText("watsonsClientLt").click();
	    String FirstClientInvoice = getObjectText("invoiceFirstClientX");
	    System.out.println("client displayed on the screen is "+FirstClientInvoice);
	    Assert.assertEquals(FirstClientInvoice, clientUsed);
	    System.out.println("clients matched! Invoice generated successfully");

	}

}
