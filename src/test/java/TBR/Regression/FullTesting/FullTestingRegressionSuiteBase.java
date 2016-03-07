package TBR.Regression.FullTesting;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

import TBR.TestBase.TestBase;

public class FullTestingRegressionSuiteBase extends TestBase{
	
	public String HoursFixed = null;
	public String jobTitle = null;

	@BeforeSuite
	public void regressionSuiteBase() throws IOException{
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException{
	//Thread.sleep(6000);
	//driver.quit();
	}
	
	@AfterTest
	public void close(){
	driver.close();
	logger.log(LogStatus.INFO, "Browser closed");
	
	}
	
	/*Add New Job-Step1: Client Information*/
	public void step1ClientInformation(Hashtable<String, String> data){
		LOGS.debug("on Step1: Client Information");
	    System.out.println("moves on to Create New Job Step1: Client Information");
	    LOGS.debug("Entering Client name and click next in Step 1: Client Information");
	    waitForElementClickableId(10, "cNameId");
	    getObjectById("cNameId").sendKeys(data.get("cnameS1"));
	    getObjectByLinkText("watsonsClientLt").click();
	    getObjectByCss("cNextCss").click();
	    LOGS.debug("End of Step 1:Client Information");
    }
	
    /*Add New Job-Step 2:Basic Information & Step 3:Billing Information*/
	/*Add New Job-step 2 Basic Information*/
	public void step2Step3AddNewJob(Hashtable<String, String> data) throws InterruptedException{
		LOGS.debug("on Step2: Basic Information");
		explicitWaitId("biTitleId");
	    getObjectById("biTitleId").sendKeys(data.get("cTitleS2"));
	    jobTitle = (data.get("cTitleS2"));
	    LOGS.debug(jobTitle);
	    System.out.println(jobTitle);
	    // getObjectById("biPurchaseId").sendKeys(data.get("cPurchaseS"));
	    getObject("biPurchaseX").sendKeys(data.get("cPurchaseS"));
	    getObjectById("biReqAvailbilityId").click();
	    
	    //select date month year and time
	    dateMonthYear();
	   /* Select month = new Select(getObject("biMonthX"));
	    month.selectByVisibleText("Dec");
	    Select year = new Select(getObject("biYearX"));
	    year.selectByValue("2016"); 
	    waitForElementClickable(10, "dec31stX");
	    clickAction("dec31stX");
	    //getObject("dec31stX").click();
	    //getObject("biDateMatchCandidateX").click();
	    Select weekPattern = new Select(getObject("biNumWeekPattern"));
	    weekPattern.selectByValue("1");
	    dragDrop("biTime9X", "bitime5X");
	    System.out.println("drag and drop success");*/
	    //now click on reset to clear the values selected
	    //getObject("biResetCalX").click();
	    
	    waitForElementClickable(10, "biCalConfirmX");
	    getObject("biCalConfirmX").click();
	    
	    HoursFixed = hoursFixed("hoursRequiredId");
	    LOGS.debug("number of hours fixed are"+HoursFixed);
	    System.out.println("number of hours fixed are"+HoursFixed);
	    
	    Thread.sleep(3000);
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
	    LOGS.debug("End of Step 2:Basic Information");
	    
	    /*Add New Job-Step3: Billing Information*/
	    LOGS.debug("on Step3: Billing Information");
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
	    getObjectByCss("nextStep3Css").click();
	    System.out.println("total id after bill rate entered, after margin locked locked, auto generated value is = "+getObjectById("totalId").getAttribute("value"));
	    
	    //multiplying Hours Fixed and bill rate and storing them in c
	    LOGS.debug("multiplying Hours Fixed and bill rate and storing them in c");
	    String c = getTotal(HoursFixed, billRate);
	    LOGS.debug("number of hours multiplied by bill rate is = "+c);
	    System.out.println("number of hours multiplied by bill rate is = "+c);
	    
	    //checking actual value and expected value for totalId
	    LOGS.debug("checking actual value and expected value for totalId");
	    Assert.assertEquals(c, getObjectByIdValue("totalId"));
	    LOGS.debug("hours fixed multiplied by bill rate is equal to the total Id value");
	    System.out.println("hours fixed multiplied by bill rate is equal to the total Id value");
	    
	    //calculating Margin
	    LOGS.debug("Now calculating Margin");
	    String margin = getMargin(payRate, billRate);
	    LOGS.debug("total margin is = "+margin);
	    System.out.println("total margin is = "+margin);
	    
	    //checking actual value and expected value for margin
	    LOGS.debug("checking actual value and expected value for margin");
	    Assert.assertEquals(margin, getObjectByIdValue("marginId"));
	    System.out.println("formula used here is bill rate - payrate divided by payrate * 100");
	    LOGS.debug("end of Step3: Billing Information");
	   }
	
	/*Add New Job-Step4: Set Approvers*/
	public void step4SetApprovers(Hashtable<String, String> data) throws InterruptedException{
		LOGS.debug("on Step4: Set Approvers");
		waitForElementClickable(10, "costConfirmButtonX");
		getObject("costConfirmButtonX").click();
		waitForElementClickableId(10, "selectAgencyApproverId");
	    getObjectById("selectAgencyApproverId").click();
	    getObject("addAgencyApproverX").click();
	    getObject("newAgencyApproverX").click();
	    waitForElement(10, "newApproverCloseX");
	    getObject("newApproverCloseX").click();
	    Thread.sleep(2000);
	    getObjectById("selectClientContactId").click();
	    getObject("addClientContactX").click();
	    getObject("newClientContactX").click();
	    waitForElement(10, "newClientCloseX");
	    getObject("newClientCloseX").click();
	    Thread.sleep(3000);
	    //waitForElementClickableCss(10, "nextStep4cSS");
	    getObjectByCss("nextStep4cSS").click();
	    LOGS.debug("end of Step4: Set Approvers");
	}
	
	/*Add New Job-Step5: Criteria*/
	public void step5Criteria(Hashtable<String, String> data){
		LOGS.debug("on Step5: Criteria");
		System.out.println("moves on to Step 5 Criteria");
		waitForElementClickable(10, "tagsX");
	    getObject("tagsX").click();
	    getObject("categoryX").click();
	    getObject("categoryEditFieldX").sendKeys(data.get("CategoryStep5"));
	    getObject("categoryEditFieldX").sendKeys(Keys.RETURN);
	    getObjectByCss("nextStep5Css").click();
	    LOGS.debug("end of Step5: Criteria");
	}    
	    
	/*confirm wizard and send wizard that comes after finishing Step6: Assign Job to Candidate*/
	public void ConfirmSendWizards(){
		waitForElementClickableCss(10, "assignCandidateYesCss");
		getObjectByCss("assignCandidateYesCss").click();
	    System.out.println("clicked on assign candidate yes");
	    waitForElementClickableLinkText(40, "assignCandidateClientLt");
	    getObjectByLinkText("assignCandidateClientLt").click();
	    System.out.println("clicked on assign client");
	    getObjectByLinkText("assignCandidateCommentsLt").click();
	    System.out.println("clicked on assign comments");
	    getObjectByLinkText("assignCandidateLt").click();
	    System.out.println("clicked on assign candidate");
	    getObjectByCss("assignSendCandidateCss").click();
	    System.out.println("success");
	}
	
	/*Add New Job-Step2: Basic Information => Required Availability*/
	public void requiredAvailability(Hashtable<String, String> data) throws InterruptedException{
		LOGS.debug("on Step2: Basic Information");
		explicitWaitId("biTitleId");
	    getObjectById("biTitleId").sendKeys(data.get("cTitleS2"));
	    jobTitle = (data.get("cTitleS2"));
	    LOGS.debug(jobTitle);
	    System.out.println(jobTitle);
	    // getObjectById("biPurchaseId").sendKeys(data.get("cPurchaseS"));
	    getObject("biPurchaseX").sendKeys(data.get("cPurchaseS"));
	    getObjectById("biReqAvailbilityId").click();
	    
	    //date month year time
	    dateMonthYear();
		/* Select month = new Select(getObject("biMonthX"));
		 month.selectByVisibleText("Dec");
		 waitForElementClickable(10, "biYearX");
		 Select year = new Select(getObject("biYearX"));
		 year.selectByValue("2016");
		 getObject("AddNewTemplatedec31X").click();
		 Select weekPattern = new Select(getObject("biNumWeekPattern"));
		 weekPattern.selectByValue("1");
		 dragDrop("biTime9X", "bitime5X");
		 System.out.println("drag and drop success");*/
		 //now click on reset to clear the values selected
		 //getObject("biResetCalX").click();
		 waitForElementClickable(10, "biCalConfirmX");
		 getObject("biCalConfirmX").click();
		 
		 HoursFixed = hoursFixed("hoursRequiredId");
		 LOGS.debug("number of hours fixed are"+HoursFixed);
		 System.out.println("number of hours fixed are"+HoursFixed);
		 
		 Thread.sleep(3000);
		    
		 //waitForElementClickable(10, "biAdvancedX");
		 clickAction("biAdvancedX");
		 Thread.sleep(3000);
		 //getObject("biAdvancedX").click();
		 waitForElementClickable(10, "biStrictInX");
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
		 LOGS.debug("End of Step 2:Basic Information");
	}
	
	/*Match Candidate: looks for "ALL POSITIONS ARE NOW FILLED" success message and assertsEquals*/
	public void matchCandidateSuccessMessage(){
		 waitForElement(120, "matchCandidateSuccessMessageX");
		 String successMessage = getObjectText("matchCandidateSuccessMessageX");
		 LOGS.debug(successMessage);
		 System.out.println(successMessage);
		 Assert.assertEquals(successMessage, "ALL POSITIONS ARE NOW FILLED");
		 LOGS.debug("a candidate has been matched successfully");		
	}
	
	/*Add New Job-Step6: Match Jobs for Advertise Pool*/
	public void step6AdvertisePool(){
		LOGS.debug("on Step6: Match Jobs");
		System.out.println("moves on to Step 6 Match Jobs");
		waitForElementClickable(10, "jobPayRateOverwriteX");
		getObject("jobPayRateOverwriteX").click();
		getObjectById("jobAdvertisePoolId").click();
	    waitForElementClickableId(10, "jobSelectPoolId");
	    selectOptionById("jobSelectPoolId", "Sai Pool");
	    //selectOptionById("jobSelectPoolId", "Kevin Users");
	    getObjectByCss("finishCss").click();
	}
	
	/*Advertise Job Step-1 Select Job*/
	public void step1AdvertisePoolSelectJob() throws InterruptedException{
		waitForElementClickableCss(10, "advertiseJobNextCss");
	    getObjectByCss("advertiseJobNextCss").click();
	    Thread.sleep(3000);
	    //moves on to Advertise Job Step-2 Choose Candidates
	    waitForElementClickableId(10, "candidateSelectAllButtonLeftId");
	    clickActionId("candidateSelectAllButtonLeftId");
	    System.out.println("clicked");
	    //getObjectById("candidateSelectAllButtonLeftId").click();
	    Thread.sleep(4000);
	    waitForElementClickable(20, "candidateListFirstOptionX");
	    dragDropClickMoveRelease("candidateListFirstOptionX", "selectedCandidatesAreaId");
	    waitForElementClickable(10, "candidateListFirstOptionX");
	    dragDropClickMoveRelease("candidateListFirstOptionX", "selectedCandidatesAreaId");
	    waitForElementClickable(10, "candidateListFirstOptionX");
	    dragDropClickMoveRelease("candidateListFirstOptionX", "selectedCandidatesAreaId");
	    getObjectByCss("chooseCandidatesStep2NextCss").click();
	}
	
	/*Advertise Job Step-4 Set Message*/
	public void step4AdvertiseJobSetMessage(Hashtable<String, String> data){
		LOGS.debug("moves on to Advertise Job Step-4 Set Message");
	    waitForElementClickableId(10, "enterMessageAdvertieJobId");
	    getObjectById("enterMessageAdvertieJobId").sendKeys(data.get("EnterMessage"));
	    getObjectByCss("setMessageStep4FinishX").click();
	    System.out.println("here");
	}
	
	/*Add New Job-Step6: Match Jobs*/
	public void step6MatchJob() throws InterruptedException{
	LOGS.debug("on Step6: Match Jobs");
    System.out.println("moves on to Step 6 Match Jobs");
    waitForElementClickableId(10, "dontMatchId");
    getObjectById("dontMatchId").click();
    getObjectByCss("finishCss").click();
    LOGS.debug("end of Step6: Match Jobs");
    Thread.sleep(5000);
	}
	
	/*moves on to Step 6 Match Jobs for Assign Job*/
	public void step6MatchJobsForAssignJob(Hashtable<String, String> data) throws InterruptedException{
	LOGS.debug("on Step6: Match Jobs");
    System.out.println("moves on to Step 6 Match Jobs");
    waitForElement(10, "jobPayRateOverwriteX");
    getObject("jobPayRateOverwriteX").click();
    getObjectById("assignCandidateId").click();
    getObjectById("candidateNameStep6").click();
    getObjectById("candidateNameStep6").sendKeys(data.get("CandidateName"));
    waitForElementClickableLinkText(10, "sherlockCandidateLt");
    getObjectByLinkText("sherlockCandidateLt").click();
    Thread.sleep(5000);
    getObjectByCss("finishCss").click();
    LOGS.debug("end of Step6: Match Jobs");
    Thread.sleep(10000);
	}
	
	/*This method is to check job operation success message*/
	public void jobOperationSuccess() throws InterruptedException{
		LOGS.debug("Now checking whether the job is saved successfully or not by the success message");
		Thread.sleep(5000);
		//waitForElement(10, "JobOperationSuccessX");
	    //String SuccessMessage = getObjectText("JobOperationSuccessX");
		String SuccessMessage = getObjectByCss("joboperationSuccessCss").getText();
	    LOGS.debug(SuccessMessage);
	    System.out.println(SuccessMessage);
	    
	    Assert.assertEquals(SuccessMessage, "job\nOperation success");
	    LOGS.debug("success message matched and therefore Job is created successfully");
	    System.out.println("success message matched and therefore Job is created successfully");
	    Thread.sleep(5000);
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
	    LOGS.debug("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
	}
	
	/*Add New Job-step 2 Basic Information*/
	public void step2BasicInformation(Hashtable<String, String> data) throws InterruptedException{
		LOGS.debug("on Step2: Basic Information");
		explicitWaitId("biTitleId");
		getObjectById("biTitleId").sendKeys(data.get("cTitleS2"));
		jobTitle = (data.get("cTitleS2"));
		LOGS.debug(jobTitle);
		System.out.println(jobTitle);
		// getObjectById("biPurchaseId").sendKeys(data.get("cPurchaseS"));
		getObject("biPurchaseX").sendKeys(data.get("cPurchaseS"));
		getObjectById("biReqAvailbilityId").click();
		Select month = new Select(getObject("biMonthX"));
		month.selectByVisibleText("Dec");
		Select year = new Select(getObject("biYearX"));
		year.selectByValue("2015");
		//waitForElementClickable(10, "biDateMatchCandidateX");
		Thread.sleep(3000);
		getObject("dec31stX").click();
		Select weekPattern = new Select(getObject("biNumWeekPattern"));
		weekPattern.selectByValue("1");
		getObject("tues10AmX").click();
		getObject("biCalConfirmX").click();
		    
		HoursFixed = hoursFixed("hoursRequiredId");
		LOGS.debug("number of hours fixed are"+HoursFixed);
		System.out.println("number of hours fixed are"+HoursFixed);
		
		Thread.sleep(3000);
		getObject("biAdvancedX").click();
		getObject("biStrictInX").click();
		getObject("biStrictOutX").click();
		//getObject("biAlignInX").click();
		//getObject("biAlignOutX").click();
		getObjectById("biMinBillHoursId").clear();
		getObjectById("biMinBillHoursId").sendKeys(data.get("cMinBillHours"));
		getObject("biAdvanced2X").click();
		//number of positions on Step 2
		getObjectById("biNumPositionId").clear();
		getObjectById("biNumPositionId").sendKeys(data.get("cNumPos"));
		getObjectById("biDescriptionId").sendKeys(data.get("cDescription"));
		getObjectByCss("biNextCss").click();
		System.out.println("clicked on next in Step 2");
		LOGS.debug("End of Step 2:Basic Information");
	}
	
	/*Add New Job-step 3 Billing Information*/
	public void step3BillingInformation(Hashtable<String, String> data) throws InterruptedException{
		LOGS.debug("on Step3: Billing Information");
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
	    getObjectByCss("nextStep3Css").click();
	    System.out.println("total id after bill rate entered, after margin locked locked, auto generated value is = "+getObjectById("totalId").getAttribute("value"));
	    
	    //multiplying Hours Fixed and bill rate and storing them in c
	    LOGS.debug("multiplying Hours Fixed and bill rate and storing them in c");
	    String c = getTotal(HoursFixed, billRate);
	    LOGS.debug("number of hours multiplied by bill rate is = "+c);
	    System.out.println("number of hours multiplied by bill rate is = "+c);
	    
	    //checking actual value and expected value for totalId
	    LOGS.debug("checking actual value and expected value for totalId");
	    Assert.assertEquals(c, getObjectByIdValue("totalId"));
	    LOGS.debug("hours fixed multiplied by bill rate is equal to the total Id value");
	    System.out.println("hours fixed multiplied by bill rate is equal to the total Id value");
	    
	    //calculating Margin
	    LOGS.debug("Now calculating Margin");
	    String margin = getMargin(payRate, billRate);
	    LOGS.debug("total margin is = "+margin);
	    System.out.println("total margin is = "+margin);
	    
	    //checking actual value and expected value for margin
	    LOGS.debug("checking actual value and expected value for margin");
	    Assert.assertEquals(margin, getObjectByIdValue("marginId"));
	    System.out.println("formula used here is bill rate - payrate divided by payrate * 100");
	    LOGS.debug("end of Step3: Billing Information");
	   }
	
	    //Calendar for slecting date/month/year for creating jobs
	    public void dateMonthYear(){
	    	 Select monthpicker = new Select(getObject("monthPickerX"));
	 	    WebElement month_selected = monthpicker.getFirstSelectedOption();
	 	    String month = month_selected.getText();
	 	    System.out.println("hi i am here");
	 	    System.out.println(month);
	 	    
	 	    if(month.equalsIgnoreCase("Jan")||month.equalsIgnoreCase("Mar")||month.equalsIgnoreCase("May")||month.equalsIgnoreCase("Aug")||month.equalsIgnoreCase("Jul")||month.equalsIgnoreCase("Dec")||month.equalsIgnoreCase("Oct"))
	 	    {
	 	    	getObject("date31CalendarViewX").click();
	 	    	//waitForElementClickable(10, "date31TimeViewX");
	 	    	//getObject("date31TimeViewX").click();
	 	    	dragDrop("date31TimeClick9amX", "date31TimeClick5pmX");
	 	    }
	 	    else if(month.equalsIgnoreCase("Feb"))
	 	    {
	 	    	System.out.println("hi i am here in feb");
	 	    	//getObject("date29CalendarViewX").click();
	 	    	getObject("date28CalendarViewX").click();
	 	    	dragDrop("date28TimeClick9amX", "date28TimeClick5pmX");
	 	    }
	 	    else
	 	    {
	 	    	getObject("date30CalendarViewX").click();
	 	    	//getObject("date30TimeViewX").click();
	 	    	dragDrop("date30TimeClick9amX", "date30TimeClick5pmX");
	 	    }
	    }
	}




