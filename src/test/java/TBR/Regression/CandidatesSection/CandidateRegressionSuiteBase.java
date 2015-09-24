package TBR.Regression.CandidatesSection;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import TBR.TestBase.TestBase;

public class CandidateRegressionSuiteBase extends TestBase {
	
	@BeforeSuite
	public void clientSuiteBase() throws IOException{
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException{
	//Thread.sleep(6000);
	//driver.close();
	}

}
