package TBR.FunctionalTestcases;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import TBR.TestBase.TestBase;

public class FunctionalSuiteBase extends TestBase {
	
	@BeforeSuite
	public void functionalSuiteBase() throws IOException{
		
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws IOException{
		//Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		//driver.quit();
	}
}
