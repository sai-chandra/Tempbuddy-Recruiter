package TBR.Regression.FullTesting;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.Email;
import TBR.TestUtil.HTMLParser;
import TBR.TestUtil.TestUtil;

public class AssignJob extends FullTestingRegressionSuiteBase{
	
	//Candidate : Jack
	@DataProvider
	public Object[][] getAssignJobData(){
    //return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAssignParameters");
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAssignCandidate");
	}
    
	@Test(dataProvider="getAssignJobData")
	public void assignJobToCandidate(Hashtable<String, String> data) throws InterruptedException{
		
		logger =report.startTest("AssignJob");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login.*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "AssignJob"));
		logger.log(LogStatus.PASS, path);
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		LOGS.debug("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
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
		waitForElementClickable(5, "addNewJobX");
		getObject("addNewJobX").click();
		Thread.sleep(5000);

		step1ClientInformation(data);
		
		step2Step3AddNewJob(data);
		
		step4SetApprovers(data);
		
		step5Criteria(data);

		/*moves on to Step 6 Match Jobs*/
		LOGS.debug("on Step6: Match Jobs");
	    System.out.println("moves on to Step 6 Match Jobs");
	    waitForElement(10, "jobPayRateOverwriteX");
	    getObject("jobPayRateOverwriteX").click();
	    getObjectById("assignCandidateId").click();
	    getObjectById("candidateNameStep6").click();
	    getObjectById("candidateNameStep6").sendKeys(data.get("CandidateName"));
	    getObjectByLinkText("jackCandidateLt").click();
	    Thread.sleep(5000);
	    getObjectByCss("finishCss").click();
	    LOGS.debug("end of Step6: Match Jobs");
	    Thread.sleep(10000);
	    
	    /*confirm wizard and send wizard that comes after finishing Step6: Assign Job to Candidate*/
	    ConfirmSendWizards();
	    Thread.sleep(15000);
	    
	    /*goes to candidate profile and refreshes*/
	    waitForElementClickable(10, "candLinkX");
	    Thread.sleep(3000);
	    getObject("candLinkX").click();
	    waitForElementClickable(10, "allCandidatesX");
	    getObject("allCandidatesX").click();
	    Thread.sleep(10000);
	    waitForElementClickable(30, "searchCandidateX");
	    getObject("searchCandidateX").sendKeys(data.get("CandidateName"));
	    //getObject("searchCandidateX").sendKeys("jack");
	    Thread.sleep(18000);
	    waitForElementClickable(10, "candidateFirstEyeIconX");
	    getObject("candidateFirstEyeIconX").click();
	    waitForElementClickable(10, "candidateNameFieldX");
	    String candidateNameAppeared = getObjectText("candidateNameFieldX");
	    
	    Thread.sleep(20000);
	    /*Assert equals to, for checking is it the same candidate we are looking for*/
	    Assert.assertEquals(candidateNameAppeared, "jack (jacky )");
	    System.out.println("candidates matched");
	    Thread.sleep(10000);
	    driver.navigate().refresh();
	    Thread.sleep(5000);
	    waitForElementClickable(15, "candidateNameFieldX");
	    String candidateNameAppearedAfterRefresh = getObjectText("candidateNameFieldX");
	    
	    /*Assert equals to, for checking is it the same candidate we are looking for*/
	    Assert.assertEquals(candidateNameAppearedAfterRefresh, "jack (jacky )");
	    System.out.println("candidates matched after refresh");
	    
	    //click on dash board
	    getObject("dashBoardLinkX").click();
	    Thread.sleep(12000);
	    
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		
		//verifies whether the unassigned jobs number is equal to the unassigned jobs number after assigning Job to a candidate
		Assert.assertEquals(countUnassignJobsNumBefore, countUnassignJobsNumAfter);
		System.out.println("if the before and after conditions are equal then the job is successfully saved and Assigned");
		
		/*on Dashboard=>Jobs=>All Jobs*/
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
		
		//click on dash board
	    getObject("dashBoardLinkX").click();
	    Thread.sleep(12000);
	    
		//gets the unassigned jobs number from the dash board
		String countUnassignJobsNumAfterAssigned = getObjectById("unassignedJobsCountId").getText();
	    System.out.println("unassigned job number after saving a job and Assigning to a candidate is "+countUnassignJobsNumAfterAssigned);
		
	    //verifies whether the unassigned jobs number is equal to the unassigned jobs number after assigning Job to a candidate
		Assert.assertEquals(countUnassignJobsNumBefore, countUnassignJobsNumAfterAssigned);
		System.out.println("if the before and after conditions are equal then the job is successfully saved and Assigned");
		 
		System.out.println("Reading email");
		 Email email = new Email();
		       String contentEmail;
		try {
		System.out.println("try");
		contentEmail = email.receiveAndDeleteMultiPart("jack.tempbuddy@gmail.com", "exercise", "New Assignment from Staging agency via TempBuddy");
		System.out.println(contentEmail);
		HTMLParser html = new HTMLParser();
		System.out.println("parser");
		System.out.println("Looking for button with ID button_"+data.get("CandidateAction")+"_assignment");
		String decideAssignmentURL = html.getTagAttr("#button_"+data.get("CandidateAction")+"_assignment", "href", contentEmail);
		goToUrl(decideAssignmentURL);
		System.out.println(decideAssignmentURL);
		String AssignmentRejected = getObjectCssText("CandidateRejectCss");
	    System.out.println(AssignmentRejected);
	    Assert.assertEquals(AssignmentRejected, "Assignment Rejected. TempBuddy will notify Staging agency");
	    System.out.println("Reject messages matched");
		} catch (Exception e) {
		System.out.println("catch");
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		System.out.println("Finish");
		
		//Reading email from Client
	    System.out.println("Reading email");
	    Email emailClient = new Email();
	    String contentEmailClient;
	    try {
	    System.out.println("try");
	    contentEmailClient = emailClient.receiveAndDeleteMultiPart("s.padmanabuni@tempbuddy.co", "tempbuddy", "Worker confirmation - Input Required");
	    System.out.println(contentEmailClient);
	    HTMLParser html1 = new HTMLParser();
	    System.out.println("parser");
	    System.out.println("Looking for button with ID button_"+data.get("CandidateAction")+"_assignment");
	    String decideAssignmentURL1 = html1.getTagAttr("#button_"+data.get("CandidateAction")+"_assignment", "href", contentEmailClient);
	    goToUrl(decideAssignmentURL1);
	    System.out.println(decideAssignmentURL1);
	    String LinkExpired = getObjectCssText("ClientlinkExpiredCss");
	    System.out.println(LinkExpired);
	    Assert.assertEquals(LinkExpired, "The link has expired");
	    System.out.println("Link expired message matched");
	    } catch (Exception e) {
	    System.out.println("catch");
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    }
	    System.out.println("Finish");
	    }
	
	    
	    @AfterMethod
	    public void screenShot(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "AssignJob");
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "AssignJob", image);
		}
		report.endTest(logger);
		report.flush();
}
}
