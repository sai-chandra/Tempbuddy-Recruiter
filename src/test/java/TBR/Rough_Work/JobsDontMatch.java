package TBR.Rough_Work;

import java.util.Hashtable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.Regression_Testcases.RegressionSuiteBase;
import TBR.TestUtil.TestUtil;

public class JobsDontMatch extends RegressionSuiteBase{
	public static int myno = 0;
	@DataProvider
	public Object[][] getJobsDontMatchData(){
		return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowDontMatchSave");
	}
	@Test(dataProvider="getJobsDontMatchData")
	public void jobsFlowAssignJobRough(Hashtable<String, String>data) throws InterruptedException{
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		//WebWaitId("allJobsCountValueId");
		Thread.sleep(5000);
		//myno = Integer.valueOf(getObjectById("allJobsCountValueId").getText().split("")[7]);
		String allJobsValueBefore = getObjectById("allJobsCountValueId").getText();
		System.out.println("the count value of all assigned and unassigned jobs before saving a new one is: "+allJobsValueBefore);
		//goes back to the home page dashboard page
		driver.navigate().back();
		
		waitForElement(5, "jobsLinkX");
		getObject("jobsLinkX").click();
		getObject("addNewJobX").click();
		Thread.sleep(5000);
		
		/*moves on to Create New Job Step1: Client Information*/
	    System.out.println("moves on to Create New Job Step1: Client Information");
	    getObjectById("cNameId").sendKeys(data.get("cnameS1"));
	    getObjectByLinkText("clientTemp2Lt").click();
	   
		getObjectByCss("cNextCss").click();
		
		/*moves on to step 2 basic information*/
		explicitWaitId("biTitleId");
	    getObjectById("biTitleId").sendKeys(data.get("cTitleS2"));
	    String jobTitle = (data.get("cTitleS2"));
	    System.out.println(jobTitle);
	    // getObjectById("biPurchaseId").sendKeys(data.get("cPurchaseS"));
	    getObject("biPurchaseX").sendKeys(data.get("cPurchaseS"));
	    getObjectById("biReqAvailbilityId").click();
	    Select month = new Select(getObject("biMonthX"));
	    month.selectByVisibleText("Oct");
	    Select year = new Select(getObject("biYearX"));
	    year.selectByValue("2015");
	    getObject("biDateX").click();
	    Select weekPattern = new Select(getObject("biNumWeekPattern"));
	    weekPattern.selectByValue("1");
	    dragDrop("biTime9X", "bitime5X");
	    System.out.println("drag and drop success");
	    //now click on reset to clear the values selected
	    //getObject("biResetCalX").click();
	    getObject("biCalConfirmX").click();
	    
	    String HoursFixed = hoursFixed("hoursRequiredId");
	    System.out.println("number of hours fixed are"+HoursFixed);
	    
	    /*brings back to the step 2 Basic information*/
	    getObject("biAdvancedX").click();
	    getObject("biStrictInX").click();
	    getObject("biStrictOutX").click();
	    getObject("biAlignInX").click();
	    getObject("biAlignOutX").click();
	    getObjectById("biMinBillHoursId").clear();
	    getObjectById("biMinBillHoursId").sendKeys(data.get("cMinBillHours"));
	    getObject("biAdvanced2X").click();
	    
	    //number of positions on Step 2
	    getObjectById("biNumPositionId").clear();
	    getObjectById("biNumPositionId").sendKeys(data.get("cNumPos"));
	    getObjectById("biDescriptionId").sendKeys(data.get("cDescription"));
	    getObjectByCss("biNextCss").click();
	    System.out.println("clicked on next in Step 2");
	    
	    //movin on to  Step 3 billing information
	    getObjectById("templateNameId").click();
	    getObject("newTemplateX").click();
	    getObject("closeNewTemplateX").click();
	    getObjectById("billingTypeId").sendKeys(data.get("BillingTypeStep3"));
	    getObjectById("payRateId").click();
	    getObjectById("payRateId").clear();
	    getObjectById("payRateId").sendKeys(data.get("PayRateStep3"));
	    getObjectById("payRateLockId").click();
	    System.out.println("total id after the pay rate is entered and locked, auto generated value is = "+getObjectById("totalId").getAttribute("value"));
	    getObjectById("billRateLockId").click();
	    getObjectById("billRateId").clear();
	    getObjectById("billRateId").sendKeys(data.get("BillRateStep3"));
	    getObjectById("billRateLockId").click();
	    //following is the value of pay rate
	    System.out.println("payrate value entered is = "+getObjectByIdValue("payRateId"));
	    //following is the value of bill rate
	    System.out.println("billrate value entered is = "+getObjectByIdValue("billRateId"));
	    
	    String billRate = getObjectById("billRateId").getAttribute("value");
	    String payRate = getObjectById("payRateId").getAttribute("value");
	    
	    getObjectById("marginId").click();
	    getObjectById("marginLockId").click();
	    getObjectById("bookingFeeId").clear();
	    System.out.println("cleared");
	    getObjectById("feeFrequencyId").sendKeys(data.get("FeeFrequencyStep3"));
	    getObjectById("bookingFeeId").sendKeys(data.get("BookingFeeStep3"));
	    System.out.println("entered booking fee");
	    //System.out.println(getObjectById("totalId").getAttribute("value"));
	    //getObjectById("grossProfitId").click();
	    //getObjectByIdValue("totalId");
	    //getObjectById("totalId").click();
	    getObjectByCss("nextStep3Css").click();
	    System.out.println("total id after bill rate entered, after margin locked locked, auto generated value is = "+getObjectById("totalId").getAttribute("value"));
	    //multiplying Hours Fixed and bill rate and storing them in c 
	    String c = getTotal(HoursFixed, billRate);
	    System.out.println("number of hours multiplied by bill rate is = "+c);
	    
	    //checking actual value and expected value for totalId
	    Assert.assertEquals(c, getObjectByIdValue("totalId"));
	    System.out.println("hours fixed multiplied by bill rate is equal to the total Id value");
	    
	    //calculating Margin
	    String margin = getMargin(payRate, billRate);
	    System.out.println("total margin is = "+margin);
	    
	    //checking actual value and expected value for margin
	    Assert.assertEquals(margin, getObjectByIdValue("marginId"));
	    System.out.println("formula used here is bill rate - payrate divided by payrate * 100");
	    System.out.println("moves on to Step 4 ");
	    
	    /*moves on to the Step 4 Set Approvers*/
	    getObjectById("selectAgencyApproverId").click();
	    getObject("addAgencyApproverX").click();
	    getObject("newAgencyApproverX").click();
	    getObject("newApproverCloseX").click();
	    getObjectById("selectClientContactId").click();
	    getObject("addClientContactX").click();
	    getObject("newClientContactX").click();
	    getObject("newClientCloseX").click();
	    getObjectByCss("nextStep4cSS").click();
	    
	    //moves on to criteria Step 5
	    System.out.println("moves on to Step 5 Criteria");
	    getObject("tagsX").click();
	    getObject("categoryX").click();
	    getObject("categoryEditFieldX").sendKeys(data.get("CategoryStep5"));
	    getObject("categoryEditFieldX").sendKeys(Keys.RETURN);
	    getObjectByCss("nextStep5Css").click();
	    
	    //moves on to Step 6 Match Jobs
	    System.out.println("moves on to Step 6 Match Jobs");
	    getObjectById("dontMatchId").click();
	    //getObjectById("matchId").click();
	    getObjectByCss("finishCss").click();
	    Thread.sleep(5000);
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		
		//checks if the unassigned jobs number + 1 is equal to the after value
		checkUnassignedJobIncrement(countUnassignJobsNumBefore, countUnassignJobsNumAfter, Integer.valueOf(data.get("cNumPos")));
		/*Integer n = Integer.valueOf(countUnassignJobsNumBefore);
		Integer add = n+1;//countUnassignJobsNumBefore+1;
		System.out.println(add);
		String after = String.valueOf(add);
		System.out.println(countUnassignJobsNumBefore+1);
		System.out.println(countUnassignJobsNumAfter);
		
		System.out.println("after value is "+after);
		//if(after==countUnassignJobsNumAfter)
		if(after.equals(countUnassignJobsNumAfter)){
			System.out.println("Success");
		}else{
			System.out.println("false");
		}*/
		//checks both are not equal
		Assert.assertNotEquals(countUnassignJobsNumBefore, countUnassignJobsNumAfter);
		System.out.println("if the before and after conditions are not equal then the job is successfully saved");
		
		//waitForElement(15, "jobsLinkX");
		//System.out.println("here");
		//Thread.sleep(5000);
		/*the count of total jobs saved in a string after saving the job is created*/
		//getObject("jobsLinkX").click();
		System.out.println("here");
		getObject("allJobsX").click();
		//WebWaitId("allJobsCountValueId");
		Thread.sleep(5000);
		String allJobsValueAfterJobSaved = getObjectById("allJobsCountValueId").getText();
		System.out.println("the count value of all assigned and unassigned jobs after saving a new one is: "+allJobsValueAfterJobSaved);
		
		Assert.assertNotEquals(allJobsValueAfterJobSaved, allJobsValueBefore);
		System.out.println("if the before and after conditions are not same then all jobs increment is working");


}
}