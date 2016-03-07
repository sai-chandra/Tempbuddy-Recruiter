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

public class DontMatchSaveJob extends FullTestingRegressionSuiteBase{

	@DataProvider
	public Object[][] getSaveJobData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowChangeMarginDontMatch");
	}
    
	@Test(dataProvider="getSaveJobData")
	public void dontMatchSaveJob(Hashtable<String, String> data) throws InterruptedException{
		
		logger =report.startTest("DontMatchSaveJob");
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "DontMatchSaveJob"));
		logger.log(LogStatus.PASS, path);
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		LOGS.debug("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
        String copy = getObject("allJobsCopyRightX").getText();
		System.out.println("here is the copyright value" +copy);
        Thread.sleep(16000);
		
		waitForElementId(40, "allJobsCountValueId");
	
		
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
	    waitForElementClickableId(10, "dontMatchId");
	    getObjectById("dontMatchId").click();
	    getObjectByCss("finishCss").click();
	    LOGS.debug("end of Step6: Match Jobs");
	    Thread.sleep(5000);
	    
	    /*This method is to check job operation success message*/
	    jobOperationSuccess();
	    
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		
		//checks if the unassigned jobs number is increasing or not soon after a Job is added
		checkUnassignedJobIncrement(countUnassignJobsNumBefore, countUnassignJobsNumAfter, Integer.valueOf(data.get("cNumPos")));
		
	    /*on Dashboard=>Jobs=>All Jobs*/
		LOGS.debug("click on All Jobs");
		getObject("allJobsX").click();
		 String copy1 = getObject("allJobsCopyRightX").getText();
			System.out.println("here is the copyright value" +copy1);
		Thread.sleep(16000);
		String str2 = getObjectById("allJobsCountValueId").getText();
		System.out.println("here is the string value" +str2);
		waitForElementId(40, "allJobsCountValueId");
		
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
	    String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "DontMatchSaveJob");
	    String image = logger.addScreenCapture(screenshot_path);
	    logger.log(LogStatus.FAIL, "DontMatchSaveJob", image);
	    }
	    report.endTest(logger);
	    report.flush();
	}
}
