package TBR.Rough_Work;

import java.io.IOException;

import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class CandidateInformationRough extends TestBase{
	
  @Test
  public void candidateInfoRough() throws IOException{
	  initialize();
	  openBrowser();
	  driver.get(CONFIG.getProperty("testSiteName"));
	  login_Valid();
	  getObject("candLinkX").click();
	  getObject("allCandidatesX").click();
	  getObject("searchCandidateX").click();
	  getObject("searchCandidateX").sendKeys("tomHarris");
	  getObjectByLinkText("tomharrisLt").click();
  }

}
