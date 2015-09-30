package TBR.Regression.JobsSection;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;

public class DontMatchSaveJob extends JobsRegressionSuiteBase{

	@DataProvider
	public Object[][] getSaveJobData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowChangeMarginDontMatch");
	}
    
	@Test(dataProvider="getSaveJobData")
	public void dontMatchSaveJob(Hashtable<String, String> data) throws InterruptedException{
		
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
		
		/*Add New Job-Step6: Match Jobs*/
		LOGS.debug("on Step6: Match Jobs");
	    System.out.println("moves on to Step 6 Match Jobs");
	    getObjectById("dontMatchId").click();
	    getObjectByCss("finishCss").click();
	    LOGS.debug("end of Step6: Match Jobs");
	    Thread.sleep(5000);
	    
	    /*This method is to check job operation success message*/
	    jobOperationSuccess();
	    
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
		System.out.println("Success! if the before and after conditions are not same then all jobs increment is working");
      }

	    @AfterMethod
	    public void screenShot(){
		CaptureScreenShot.captureScreenShot(driver, "DontMatchSaveJob");
	}
}
