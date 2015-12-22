package TBR.Regression.FullTesting;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;

public class AdvertisePoolByAssignment extends FullTestingRegressionSuiteBase{
	
	@DataProvider
	public Object[][] getAdvertiseJobPoolByAssignmentData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAdvertisePool");
	}
	
	@Test(dataProvider="getAdvertiseJobPoolByAssignmentData")
	public void jobFlowByPoolRough(Hashtable<String, String> data) throws InterruptedException, IOException{

		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		LOGS.debug("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		Thread.sleep(12000);
		String allJobsValueBefore = getObjectById("allJobsCountValueId").getText();
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
		
		/*Add New Job-Step6: Match Jobs for Advertise Pool*/
		step6AdvertisePool();
	    Thread.sleep(5000);
	    /*moves on to Advertise Job Step-1 Select Job*/
	    LOGS.debug("on Advertise Job Step-1 Select Job");
	    step1AdvertisePoolSelectJob();
	    
	    /*moves on to Advertise Job Step-3 Schedule Advertisement*/
	    LOGS.debug("on Advertise Job Step-3 Schedule Advertisement");
	    waitForElementClickableId(10, "NoOfCandidatesPerPositionId");
	    populateFieldById("NoOfCandidatesPerPositionId", data.get("NoOfCandidatesPerPosition"));
	    populateFieldById("timeIntervalBetweenAdvertsId", data.get("TimeIntervalBetweenAdverts"));
	    selectOptionById("actionWhenCandidateAcceptsId", data.get("ActionCandidateAccepts"));
	    getObjectByCss("scheduleAdvertisementStep3nextCss").click();
	    
	    /*Advertise Job Step-4 Set Message*/
	    step4AdvertiseJobSetMessage(data);
	    
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
	  	Thread.sleep(12000);
	    String allJobsValueAfterJobSaved = getObjectById("allJobsCountValueId").getText();
	  	LOGS.debug("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
	  	System.out.println("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
	  		
	  	Assert.assertNotEquals(allJobsValueAfterJobSaved, allJobsValueBefore);
	  	LOGS.debug("Success! if the before and after conditions are not same then all jobs increment is working");
	  	System.out.println("if the before and after conditions are not same then all jobs increment is working");

	    }
	    
	    @AfterMethod
        public void screenShot(){
        CaptureScreenShot.captureScreenShot(driver, "AdvetisePoolByAssignment");
        }
}
