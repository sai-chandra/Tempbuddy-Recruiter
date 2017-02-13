package TBR.Regression.Client;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import TBR.TestBase.TestBase;

public class ClientRegressionSuiteBase extends TestBase{
	
	@BeforeSuite
	public void clientSuiteBase() throws IOException{
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException{
		if(con!=null){
			con=null;
		}
	//Thread.sleep(6000);
	//driver.quit();
	}
	
	@AfterTest
	public void close(){
		driver.close();
	}

}
