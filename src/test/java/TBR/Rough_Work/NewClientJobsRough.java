package TBR.Rough_Work;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.FunctionalTestcases.FunctionalSuiteBase;
import TBR.TestBase.TestBase;
import TBR.TestUtil.TestUtil;

public class NewClientJobsRough extends FunctionalSuiteBase{
	
    @DataProvider
	public Object[][] getAddNewClientData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsClientAdd");
	}
	@Test(dataProvider="getAddNewClientData")
	public void addNewClient(Hashtable<String, String>data) throws InterruptedException{
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		
		//Thread.sleep(10000);
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		WebWaitId("allJobsCountValueId");
		String allJobsValueBefore = getObjectById("allJobsCountValueId").getAttribute("value");
		System.out.println("the count value of all assigned and unassigned jobs before saving a new one is"+allJobsValueBefore);
		//goes back to the home page dashboard page
		driver.navigate().back();
		
		getObject("jobsLinkX").click();
		getObject("addNewJobX").click();
		getObject("newForClientX").click();
		Thread.sleep(1000);
		getObjectById("nameClientId").sendKeys(data.get("cname"));
		getObjectById("addressClientId").sendKeys(data.get("caddress"));
		Select selectSector = new Select(getObjectById("sectorClientId"));
		selectSector.selectByVisibleText(data.get("sectorClient"));
		getObjectById("sectorClientId").sendKeys(data.get("sectorClient"));
		getObjectById("phoneClient").sendKeys(data.get("phoneNumClient"));
		getObjectById("phoneExClientId").sendKeys(data.get("extensionClient"));
		getObjectById("refClient").sendKeys(data.get("externalRef"));
		getObject("nextClientX").click();
		
		/*comes on to Create new Client Step 2*/ 
		getObject("addContactX").click();
		System.out.println("moves on to Add contact screen");
		explicitWaitId("acNameId");
		getObjectById("acNameId").sendKeys(data.get("acName"));
		getObjectById("acCompanyPosId").sendKeys(data.get("acCompany"));
		getObjectById("acEmailId").sendKeys(data.get("acEmail"));
		getObjectById("acPhoneId").sendKeys(data.get("acPhone"));
		getObjectById("acExtenId").sendKeys(data.get("acExten"));
		getObjectById("acAlterPhone1").sendKeys(data.get("acAlterPhone"));
		getObjectById("acAlterPhone2").sendKeys(data.get("acAlterPhone2"));
		getObject("acAddX").click();
		getObject("nextS2X").click();
		
		/*moves on to Create New Client Step 3: Additional Locations*/
		System.out.println("moves on to Create new Client Step3: Additional Locations");
	    getObject("addLocS3X").click();
	    getObject("finishS3X").click();
	    
	    Thread.sleep(5000);
	    
	    /*moves on to Create New Job Step1: Client Information*/
	    System.out.println("moves on to Create New Job Step1: Client Information");
	    getObjectById("cNameId").sendKeys(data.get("cnameS1"));
	    getObjectByLinkText("clientTempLt").click();
	   // getObjectByLinkText("cAppleClientLt").click();
	    getObject("cNextX").click();
	    
	   Thread.sleep(5000);
	   /*moves on to step 2 basic information*/
	    getObjectById("biTitleId").sendKeys(data.get("cTitleS2"));
	    String jobTitle = (data.get("cTitleS2"));
	    System.out.println(jobTitle);
	   // getObjectById("biPurchaseId").sendKeys(data.get("cPurchaseS"));
	    getObject("biPurchaseX").sendKeys(data.get("cPurchaseS"));
	    getObjectById("biReqAvailbilityId").click();
	    getObject("biMonthX").sendKeys(data.get("cMonthName"));
	    getObject("biYearX").sendKeys(data.get("cYear"));
	    getObject("biNumWeekPattern").sendKeys(data.get("cWeekPattern"));
	    dragDrop("biTime1X", "biTime2X");
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
	    //on job location new button
	    getObject("biNewJobLocX").click();
	    Thread.sleep(1000);
	    
	    getObjectById("biLocLabelId").sendKeys(data.get("cLocLabelS2"));
	    getObjectById("biAddId").sendKeys(data.get("cAddLocS2"));
	    getObjectById("biAlPhonesId").sendKeys(data.get("cAddlocPhoneS2"));
	    getObject("biAlAddX").click();
	    //brings back to Step 2 Basic information
	    getObjectById("biDescriptionId").sendKeys(data.get("cDescription"));
	    System.out.println("here");
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
	    System.out.println(getObjectById("totalId").getAttribute("value"));
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
	    //String b = getObjectByIdValue("billRateId");
	    //getObjectById("costArrowId").click();
	    getObjectById("marginId").click();
	    getObjectById("marginLockId").click();
	    getObjectById("bookingFeeId").clear();
	    System.out.println("cleared");
	    getObjectById("bookingFeeId").sendKeys(data.get("BookingFeeStep3"));
	    System.out.println("entered booking fee");
	    System.out.println(getObjectById("totalId").getAttribute("value"));
	    //getObjectById("grossProfitId").click();
	    //getObjectByIdValue("totalId");
	    //getObjectById("totalId").click();
	    getObjectByCss("nextStep3Css").click();
	    
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
	    
	    
	    
	    System.out.println("moves on to step 4");
	    
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
	    getObjectById("passportId").sendKeys(data.get("PassportStep5"));
	    getObjectById("manualHandlingCertId").sendKeys(data.get("ManualCertStep5"));
	    getObjectById("gardaVettingId").sendKeys(data.get("GardaVettingStep5"));
	    getObjectById("trainingCompleteId").sendKeys(data.get("TrainingStep5"));
	    getObjectById("proofToWorkId").sendKeys(data.get("ProofToWorkStep5"));
	    getObjectById("certificationExpiryId").sendKeys(data.get("CertificationExpiry"));
	    getObjectByCss("nextStep5Css").click();
	    
	    //moves on to Step 6 Match Jobs
	    System.out.println("moves on to Step 6 Match Jobs");
	    getObjectById("assignCandidateId").click();
	    getObjectById("candidateNameStep6").click();
	    getObjectById("candidateNameStep6").sendKeys(data.get("CandidateName"));
	    //Select select = new Select(getObjectById("candidateNameStep6"));
	    //select.getFirstSelectedOption();
	    getObjectByLinkText("candidateNameStep6Lt").click();
	    //explicitWait("candidateName1Step6Lt");
	    //getSelectedByText("candidateNameStep6", "tempBuddy ( s.padmanabuni@tempbuddy.co )");
	    //getObjectByLinkText("candidateName1Step6Lt").click();
	    //explicitWaitCss("finishCss");
	    //explicitWaitCss("finishCss");
	    Thread.sleep(5000);
	     getObjectByCss("finishCss").click();
	    //getObject("finishX").click();
	    
	    Thread.sleep(5000);
	   
	    System.out.println("clicked on finish 1 in step 6");
	    //getObjectByCss("finishCss").click();
	   // System.out.println("clicked on finish 2");
	   // explicitWaitXpath("assignCandidateYesX");
	    //getObject("finishX").click();
	    /*on confirm wizard: do you want to assign the selected job to this candidate*/
	    explicitWaitXpath("assignAutoConfirmClientYesX");
	    getObject("assignAutoConfirmClientYesX").click();
	    getObject("assignSendEmailClientYesX").click();
	    getObject("assignAutoConfirmCandidateYesX").click();
	    //getObject("assignAutoConfirmCandidateNoX").click();
	    //getObject("assignCandidateYesX").click();
	    getObjectByCss("assignCandidateYesCss").click();
	    System.out.println("clicked on assign candidate yes");
	    //explicitWaitXpath("assignCandidateClientX");
	    //explicitWait("assignCandidateClientLt");
	    getObjectByLinkText("assignCandidateClientLt").click();
	    //getObject("assignCandidateClientX").click();
	    System.out.println("clicked on assign client");
	   // explicitWaitXpath("assignCandidateCommentsX");
	    getObjectByLinkText("assignCandidateCommentsLt").click();
	    System.out.println("clicked on assign comments");
	    //explicitWaitXpath("assignCandidateX");
	    getObjectByLinkText("assignCandidateLt").click();
	    System.out.println("clicked on assign candidate");
	    //getObject("assignSendCandidateX").click();
	    getObjectByCss("assignSendCandidateCss").click();
	    System.out.println("success");
	    
	    Thread.sleep(5000);
	    //explicitWaitId("unassignedJobsCountId");
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		
		Assert.assertNotEquals(countUnassignJobsNumBefore, countUnassignJobsNumAfter);
		System.out.println("if the before and after conditions are not equal then the job is successfully saved");
		
		/*the count of total jobs saved in a string after saving the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		WebWaitId("allJobsCountValueId");
		String allJobsValueAfterJobSaved = getObjectById("allJobsCountValueId").getAttribute("value");
		System.out.println("the count value of all assigned and unassigned jobs after saving a new one is"+allJobsValueAfterJobSaved);
		
		Assert.assertNotEquals(allJobsValueAfterJobSaved, allJobsValueBefore);
		System.out.println("if the before and after conditions are not same then all jobs increment is working");
		
	    
        //clicks on All jobs and searches for the recently created job
		//getObject("allJobsX").click();
		


}
}

