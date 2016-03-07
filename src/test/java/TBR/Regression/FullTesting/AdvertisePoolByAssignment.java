package TBR.Regression.FullTesting;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;

public class AdvertisePoolByAssignment extends FullTestingRegressionSuiteBase{
	
	@DataProvider
	public Object[][] getAdvertiseJobPoolByAssignmentData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAdvertisePool");
	}
	
	@Test(dataProvider="getAdvertiseJobPoolByAssignmentData")
	public void advertisePoolByAssignment(Hashtable<String, String> data) throws InterruptedException, IOException{
        
		logger =report.startTest("AdvertisePoolByAssignment");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "AdvertisePoolByAssignment"));
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
	  	checkUnassignedJobIncrement(countUnassignJobsNumBefore, countUnassignJobsNumAfter,Integer.valueOf(data.get("cNumPos")));
	  				
	  	//Assert.assertNotEquals(countUnassignJobsNumBefore, countUnassignJobsNumAfter);
	  	//LOGS.debug("if the before and after conditions are not equal then the job is successfully saved");
	  	//System.out.println("if the before and after conditions are not equal then the job is successfully saved");
	  		
	  	/*on Dashboard=>Jobs=>All Jobs*/
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
			String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "AdvertisePoolByAssignment");
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "AdvertisePoolByAssignment", image);
		}
		report.endTest(logger);
		report.flush();
}
}
