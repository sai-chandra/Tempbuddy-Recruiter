package TBR.Regression_Testcases;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import TBR.TestBase.TestBase;

public class RegressionSuiteBase extends TestBase {
	
	@BeforeSuite
	public void regressionSuiteBase() throws IOException{
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException{
	//Thread.sleep(6000);
	driver.close();
	}
	
	
}
