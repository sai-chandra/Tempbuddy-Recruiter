package TBR.Rough_Work;

import java.io.IOException;

import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class RoughMatchCandidate extends TestBase{

	@Test
	public void roughMatchJobCandidate() throws IOException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		driver.get("https://staging.tempbuddy.com/web/rec/display/candidates/1078/");
		 getObjectById("matchCandidateSearchId").clear();
		   getObjectById("matchCandidateSearchId").click();
		    getObjectById("matchCandidateSearchId").sendKeys("tempBuddy");
		
		
		
	}
}
