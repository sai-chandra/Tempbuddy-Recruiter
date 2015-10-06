package TBR.Regression.JobsSection;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.Email;
import TBR.TestUtil.HTMLParser;
import TBR.TestUtil.TestUtil;

public class AssignJob extends JobsRegressionSuiteBase{
	
	@DataProvider
	public Object[][] getAssignJobData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAssignCandidate");
	}
    
	@Test(dataProvider="getAssignJobData")
	public void assignJobToCandidate(Hashtable<String, String> data) throws InterruptedException{
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		LOGS.debug("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		Thread.sleep(5000);
		String allJobsValueBefore = getObjectById("allJobsCountValueId").getText();
		LOGS.debug("the count value of all assigned and unassigned jobs before saving a new one is: "+allJobsValueBefore);
		System.out.println("the count value of all assigned and unassigned jobs before saving a new one is: "+allJobsValueBefore);
		/*navigates back to the home page dashboard page*/
		driver.navigate().back();
		
		waitForElement(5, "jobsLinkX");
		getObject("jobsLinkX").click();
		getObject("addNewJobX").click();
		Thread.sleep(5000);

		step1ClientInformation(data);
		
		step2Step3AddNewJob(data);
		
		step4SetApprovers(data);
		
		step5Criteria(data);

		/*moves on to Step 6 Match Jobs*/
		LOGS.debug("on Step6: Match Jobs");
	    System.out.println("moves on to Step 6 Match Jobs");
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
	    Thread.sleep(10000);
	    
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		
		//checks if the unassigned jobs number is not equal to the after value
		checkUnassignedJobIncrement(countUnassignJobsNumBefore, countUnassignJobsNumAfter);
				
		Assert.assertNotEquals(countUnassignJobsNumBefore, countUnassignJobsNumAfter);
		LOGS.debug("if the before and after conditions are not equal then the job is successfully saved");
		System.out.println("if the before and after conditions are not equal then the job is successfully saved");
		
		/*on Dashboard=>Jobs=>All Jobs*/
		LOGS.debug("click on All Jobs");
		getObject("allJobsX").click();
		Thread.sleep(5000);
		String allJobsValueAfterJobSaved = getObjectById("allJobsCountValueId").getText();
		LOGS.debug("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
		System.out.println("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
		
		Assert.assertNotEquals(allJobsValueAfterJobSaved, allJobsValueBefore);
		LOGS.debug("Success! if the before and after conditions are not same then all jobs increment is working");
		System.out.println("if the before and after conditions are not same then all jobs increment is working");
		
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
		
		/*//Reading email from Client
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
	    Assert.assertEquals(LinkExpired, "This assignment is being reassigned by the agency. Please wait for the next approval email.");
	    System.out.println("Link expired message matched");
	    } catch (Exception e) {
	    System.out.println("catch");
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    }
	    System.out.println("Finish");*/
	    }
	
	    
	    @AfterMethod
        public void screenShot(){
	    CaptureScreenShot.captureScreenShot(driver, "AssignJob");
        }
}
