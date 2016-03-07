package TBR.Regression.PayandBill;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import TBR.TestBase.TestBase;

public class PayandBillRegressionSuitebase extends TestBase {
	@BeforeSuite
	public void regressionSuiteBase() throws IOException{
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException{
	//Thread.sleep(6000);
	driver.quit();
	}
	
	/*click on Pay and Bill and then Timesheet */
	public void payBilltoTimesheet(){
	getObject("payBillX").click();
	getObject("timeSheetLinkX").click();	
	}
}
