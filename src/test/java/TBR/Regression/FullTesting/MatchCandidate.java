package TBR.Regression.FullTesting;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;

public class MatchCandidate extends FullTestingRegressionSuiteBase{
	
	//candidate : game
	@DataProvider
	public Object[][] getMatchCandidateJobData(){
	return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowMatchCandidateJob");
	}
	
	@Test(dataProvider="getMatchCandidateJobData")
	public void matchCandidate(Hashtable<String, String> data) throws InterruptedException{

		logger =report.startTest("MatchCandidate");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "MatchCandidate"));
		logger.log(LogStatus.PASS, path);
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBeforeAssign = getObjectById("unassignedJobsCountId").getText();
		LOGS.debug("unassignedjobs number before saving a job is "+countUnassignJobsNumBeforeAssign);
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBeforeAssign);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		Thread.sleep(15000);
		
		explicitWaitId("allJobsCountValueId");
		
		String str = getObjectById("allJobsCountValueId").getText();
		int allJobsValueBefore = Integer.valueOf(str.split(" ")[7]);
		LOGS.debug("the count value of all assigned and unassigned jobs before saving a new one is: "+allJobsValueBefore);
		System.out.println("the count value of all assigned and unassigned jobs before saving a new one is: "+allJobsValueBefore);
		
		/*navigates back to the home page dashboard page*/
		driver.navigate().back();
		
		waitForElement(5, "jobsLinkX");
		getObject("jobsLinkX").click();
		waitForElementClickable(10, "addNewJobX");
		getObject("addNewJobX").click();
		Thread.sleep(5000);
		
		step1ClientInformation(data);
		
		step2Step3AddNewJob(data);
		   
		step4SetApprovers(data);
		
		step5Criteria(data);
		
		/*Add New Job-Step6: Match Jobs*/
		LOGS.debug("on Step6: Match Jobs");
	    System.out.println("moves on to Step 6 Match Jobs");
	    waitForElementClickableId(10, "matchId");
	    getObjectById("matchId").click();
	    getObjectByCss("finishCss").click();
	    LOGS.debug("end of Step6: Match Jobs");
	    Thread.sleep(5000);
	    
	    //waits for the job title to appear on the screen and then assert checks the job created and the job appeared on the dash board are same or not
	    waitForElement(10, "titleJobDashX");
	    String jobTitleText = getObject("titleJobDashX").getText();
	    System.out.println(jobTitleText);
	    Assert.assertEquals(jobTitleText, jobTitle);
	    System.out.println("the job saved and the job appeared are to be same");
	    
	    getObjectById("matchCandidateSearchId").clear();
	    getObjectById("matchCandidateSearchId").click();
	    getObjectById("matchCandidateSearchId").sendKeys(data.get("CandidateSearch"));
	    Thread.sleep(8000);
	    
	    getObjectByLinkText("gameCandidateLt").click();
	    Thread.sleep(5000);
	    
	    waitForElementClickable(40, "matchCandidateCheckBoxX");
	    getObject("matchCandidateCheckBoxX").click();
	    getObject("matchCandidateAddSymbolX").click();
	    Thread.sleep(6000);
	    
	    getObject("matchCandidateYesX").click();
	    Thread.sleep(4000);
	    getObjectByCss("matchCandidateSendMessageCss").click();
	    
	    /*Match Candidate: looks for "ALL POSITIONS ARE NOW FILLED" success message and assertsEquals*/
	    matchCandidateSuccessMessage();
	    
	    /*click Dash board*/
	    getObject("dashBoardLinkX").click();
	    Thread.sleep(8000);
	    
	    String countUnassignJobsNumAfterAssign = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after assigning a job is "+countUnassignJobsNumAfterAssign);
		
	    Assert.assertEquals(countUnassignJobsNumBeforeAssign, countUnassignJobsNumAfterAssign);
		LOGS.debug("if the before and after conditions are equal then the job is successfully saved and assigned");
		System.out.println("if the before and after conditions are equal then the job is successfully saved and assigned");
		
	    /*on Dashboard=>Jobs=>All Jobs*/
		LOGS.debug("click on Jobs");
		getObject("jobsLinkX").click();
		LOGS.debug("click on All Jobs");
		getObject("allJobsX").click();
		Thread.sleep(15000);
		explicitWaitId("allJobsCountValueId");
		String str1 = getObjectById("allJobsCountValueId").getText();
		int allJobsValueAfterJobSaved = Integer.valueOf(str1.split(" ")[7]);
		LOGS.debug("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
		System.out.println("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
		
		//checks whether the all jobs count is increased by one or not in the All Jobs List
		Assert.assertEquals(allJobsValueAfterJobSaved, allJobsValueBefore+1);
		LOGS.debug("Success! both the values are equal, then all jobs increment is working");
		System.out.println("Success! both the values are equal, then all jobs increment is working");
     }
	
	    @AfterMethod
	    public void screenShot(ITestResult result){
	    if(result.getStatus()==ITestResult.FAILURE)
	     {
			String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "MatchCandidate");
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "MatchCandidate", image);
		 }
		report.endTest(logger);
		report.flush();
}
}