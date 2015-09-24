package TBR.Regression_Testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.TestUtil;

public class ConfirmCandidateClientFromAssignment extends RegressionSuiteBase{
	
	@DataProvider
	public static Object[][] getJobData(){
	return TestUtil.getDataIntoHashTable(JobsExcel, "FromAssignmentFindMatch");
	}
	
	@Test(dataProvider="getJobData") 
    public void example(Hashtable<String, String>data) throws InterruptedException, IOException{
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	login_Valid();

	JobsCommon jobs = new JobsCommon();
	jobs.jobsFlowAssignCandidate(data);

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