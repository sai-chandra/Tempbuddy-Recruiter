package TBR.Regression.Client;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import TBR.TestBase.TestBase;

public class ClientRegressionSuiteBase extends TestBase{
	
	@BeforeSuite
	public void clientSuiteBase() throws IOException{
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException{
	//Thread.sleep(6000);
	driver.quit();
	}

}
