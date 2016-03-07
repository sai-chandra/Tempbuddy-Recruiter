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

public class CloneJob extends FullTestingRegressionSuiteBase{
	
 @DataProvider
 public Object[][] getCloneJobData(){
 return TestUtil.getDataIntoHashTable(JobsExcel, "CloneJobData");
 }
 
 @Test(dataProvider="getCloneJobData")
 public void cloneJob(Hashtable<String, String> data) throws InterruptedException{
 
	    logger =report.startTest("CloneJob");   
	 
	    /*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "CloneJob"));
		logger.log(LogStatus.PASS, path);
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		Thread.sleep(20000);
		String copy1 = getObject("allJobsCopyRightX").getText();
		System.out.println("here is the copyright "+copy1);
	
		String str = getObjectById("allJobsCountValueId").getText();
		System.out.println(str);
		System.out.println("hi");
		int allJobsValueBefore = Integer.valueOf(str.split(" ")[7]);
		LOGS.debug("the count value of all assigned and unassigned jobs before saving a new one is: "+allJobsValueBefore);
		System.out.println("the count value of all assigned and unassigned jobs before saving a new one is: "+allJobsValueBefore);
		
		//goes back to the home page dashboard page
		driver.navigate().back();
		
		waitForElement(5, "jobsLinkX");
		getObject("jobsLinkX").click();
		waitForElementClickable(10, "addNewJobX");
		getObject("addNewJobX").click();
		
		/*moves on to Create New Job Step1: Client Information*/
		waitForElementClickableId(10, "cNameId");
	    System.out.println("moves on to Create New Job Step1: Client Information");
	    getObjectById("cNameId").sendKeys(data.get("cnameS1"));
	    waitForElementClickableLinkText(10, "watsonsClientLt");
	    getObjectByLinkText("watsonsClientLt").click();
	    
	    //click on Clone Job
	    getObject("cCloneJobX").click();
	    getObjectById("cloneCopyFromId").sendKeys(data.get("CopyFrom"));
	    getObjectByLinkText("CloneJobLt").click();
	    Thread.sleep(5000);
	    waitForElementClickableId(10, "cloneNewJobTitleId");
	    getObjectById("cloneNewJobTitleId").click();
	    getObjectById("cloneNewJobTitleId").clear();
	    //getObjectById("cloneNewJobTitleId").clear();
	    Thread.sleep(5000);
	    getObjectById("cloneNewJobTitleId").sendKeys(data.get("NewJobTitle"));
	    getObjectById("cloneStartDayId").click();
	    Thread.sleep(5000);
	    //waitForElement(10, "cloneStartDayMonthX");
	    getSelectedByTextXpath("cloneStartDayMonthX", "Oct");
	    getSelectedByTextXpath("cloneStartDayYearX", "2016");
	    getObject("cloneStartDaySep30X").click();
	    populateFieldById("cloneJobsPositionsId", "1");
	    getObjectByCss("cloneButtonFinishCss").click();
	    
	    //Moves on to Create New Job wizard, Step 6:Match Job
	    //stores the expiry Date value
	    Thread.sleep(4000);
	    String expiryDate = getObjectByIdValue("jobExpireDateId");
	    System.out.println(expiryDate);
	    getObjectByCss("finishCss").click();
	    Thread.sleep(4000);
	    waitForElement(10, "JobOperationSuccessX");
	    String SuccessMessage = getObjectText("JobOperationSuccessX");
	    System.out.println(SuccessMessage);
	    
	    Assert.assertEquals(SuccessMessage, "job\nOperation success");
	    System.out.println("success message matched and therefore Job is created successfully");
	    Thread.sleep(5000);
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		
		//checks if the unassigned jobs number is not equal to the after value
		checkUnassignedJobIncrement(countUnassignJobsNumBefore, countUnassignJobsNumAfter,Integer.valueOf(data.get("cNumPos")));
		
		getObject("allJobsX").click();
		Thread.sleep(16000);
		explicitWaitId("allJobsCountValueId");
		
		String str1 = getObjectById("allJobsCountValueId").getText();
		int allJobsValueAfterJobSaved = Integer.valueOf(str1.split(" ")[7]);
		LOGS.debug("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
		System.out.println("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
		
		//checks whether the all jobs count is increased by one or not in the All Jobs List
		Assert.assertEquals(allJobsValueAfterJobSaved, allJobsValueBefore+1);
		LOGS.debug("Success! both the values are equal, then all jobs increment is working");
		System.out.println("Success! both the values are equal, then all jobs increment is working");
        
		waitForElementClickable(10, "allJobsFilterX");
		getObject("allJobsFilterX").sendKeys(data.get("JobsFilter"));
		Thread.sleep(10000);
		String newJobTitle = (data.get("JobsFilter"));
		System.out.println(newJobTitle);
		Thread.sleep(15000);
	    
		String displayedJobTitle = getObjectText("allJobsFirstCellTitleX");
		System.out.println(displayedJobTitle);
	    Assert.assertEquals(displayedJobTitle, newJobTitle);
	    System.out.println("Job cloned and job displayed matched!");
	    }
 
 		@AfterMethod
 		public void screenShot(ITestResult result){
 			if(result.getStatus()==ITestResult.FAILURE)
 			{
 				String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "CloneJob");
 				String image = logger.addScreenCapture(screenshot_path);
 				logger.log(LogStatus.FAIL, "CloneJob", image);
 			}
 			report.endTest(logger);
 			report.flush();
	
 }
}