package TBR.Regression.FullTesting;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.TestUtil;

public class CheckRosterViewForUnassignedJob extends FullTestingRegressionSuiteBase{
	
	@DataProvider
	public Object[][] getCheckRosterViewForUnassignedJobData(){
	return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAssignParameters");
	//return TestUtil.getDataIntoHashTable(JobsExcel, "checkRosterViewForUnassignedJob");
	}
	
	@Test(dataProvider= "getCheckRosterViewForUnassignedJobData")
	public void checkRosterViewForUnassignedJob(Hashtable<String, String>data) throws InterruptedException{
	
	/*browserUrl() opens up a browser, goes to Staging url & performs login.*/
	browserUrl();
	
	waitForElement(5, "jobsLinkX");
	getObject("jobsLinkX").click();
	waitForElementClickable(5, "addNewJobX");
	getObject("addNewJobX").click();
		
	/*Step1:Client Information*/
	step1ClientInformation(data);
	
	/*Step2:Basic Information*/
	step2BasicInformation(data);
	
	/*Step3:Billing Information*/
	step3BillingInformation(data);
	
	/*Step4:Set Approvers*/
	step4SetApprovers(data);
	
	/*Step5:Criteria*/
	step5Criteria(data);
	
	/*Step6:Match Jobs*/
	step6MatchJobsForAssignJob(data);
	
	/*Scheduling
	waitForElementClickable(10, "rosterViewIconX");
	getObject("rosterViewIconX").click();
	waitForElement(10, "rosterViewTabX");
	getObject("rosterViewTabX").click();
	getObject("rosterViewClientX").click();*/
	
	
	}

}
