package TBR.Rough_Work;

import java.io.IOException;

import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class Roughest extends TestBase{
	@Test
	public void check() throws InterruptedException, IOException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
	//moves on to Assignments
	getObject("assignLinKX").click();
	waitForElementClickable(10, "assignmentJobsFilterX");
	getObject("assignmentJobsFilterX").click();
	getObject("assignmentJobsFilterX").sendKeys("jobLatestSep");
	Thread.sleep(4000);
	getObject("assignmentFirstRowLinkX").click();
	getObject("assignmentInformationProgressX").click();
	getObject("assignmentProgressConfirmCandidateX").click();
	//confirm candidate
	getObjectByCss("assignmentProgressYesCss").click();
	getObject("assignmentProgressConfirmClientX").click();
	waitForElementCss(10, "assignmentProgressYesCss");
	//confirm client
	getObjectByCss("assignmentProgressYesCss").click();
	System.out.println("successfull");

}
}