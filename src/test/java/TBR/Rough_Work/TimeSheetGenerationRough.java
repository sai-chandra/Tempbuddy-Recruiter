package TBR.Rough_Work;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;
import TBR.TestUtil.TestUtil;

public class TimeSheetGenerationRough extends TestBase{
	
	public static WebDriver driver1 = null;
	@DataProvider
	public Object[][] timesheetGenerateData(){
    return TestUtil.getDataIntoHashTable(TimesheetsExcel, "TimeSheetGenerationFlow1");
	}
	@Test(dataProvider="timesheetGenerateData")
	public void timeSheetGeneration(Hashtable<String, String>data) throws InterruptedException, IOException{
		initialize();
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
		/*CREATING A CLIENT*/
		//clicks on Clients and add new Client
		waitForElementClickable(10, "clientsLinkx");
		getObject("clientsLinkx").click();
		getObject("addNewClientX").click();
		
		/*on Step 1 Basic information*/
		waitForElementClickableId(10, "nameClientId");
		Thread.sleep(3000);
		getObjectById("nameClientId").sendKeys(data.get("Name"));
		String clientName = getObjectById("nameClientId").getAttribute("value");
		System.out.println("the name of the client is" +clientName);
		getObjectById("addressClientId").sendKeys(data.get("Address"));
		getSelectedByText("sectorClientId", "Banking");
		getObjectById("phoneClient").sendKeys(data.get("PhoneNumberClient"));
		getObjectById("phoneExClientId").sendKeys(data.get("Extension"));
		getObjectById("refClient").sendKeys(data.get("ExternalRef"));
		getObject("nextClientX").click();
		System.out.println("success on Step 1 Basic Information");
		Thread.sleep(3000);
		//5
		//moves on to step 2 Contact information
		waitForElementClickable(10, "addContactX");
		getObject("addContactX").click();
		System.out.println("moves on to Add contact screen");
		waitForElementId(10, "acNameId");
		getObjectById("acNameId").sendKeys(data.get("AddContactName"));
		getObjectById("acCompanyPosId").sendKeys(data.get("CompanyPosition"));
		getObjectById("acEmailId").sendKeys(data.get("AddContactEmail"));
		getObjectById("acPhoneId").sendKeys(data.get("AddContactPhone"));
		getObjectById("acExtenId").sendKeys(data.get("AddContactExten"));
		getObjectById("acAlterPhone1").sendKeys(data.get("AddContactAlternativePhone"));
		getObjectById("acAlterPhone2").sendKeys(data.get("AddContactAlternativePhone1"));
		//driver.findElement(By.cssSelector(".btn.btn.btn-success")).click();
		getObjectByCss("addContactButtonCss").click();
		getObject("nextS2X").click();
		System.out.println("success on Step 2 Contact Information");
		//12
		/*moves on to Create New Client Step 3: Additional Locations*/
		System.out.println("success on Step 3 Additional Location");
		
	    getObject("addLocS3X").click();
	    waitForElementClickableId(10, "clientCreatLocationLabelId");
	    getObjectById("clientCreatLocationLabelId").sendKeys(data.get("AddLocationLabel"));
        getObjectById("clientCreateAddressId").sendKeys(data.get("AddClientAddress"));
        getObjectById("clientCreatePhonesId").sendKeys(data.get("AddClientsPhones"));
        waitForElementClickable(10, "clientCreateAddX");
        getObject("clientCreateAddX").click();
        //on finish creates a new client
	    getObjectByCss("finishS3Css").click();
	    Thread.sleep(10000);
	    //15
	    /*CREATING A WORKER*/
	    //clicks on candidates and add new candidate
	    getObject("candLinkX").click();
		getObject("addNewCandidateX").click();
		//moves on to step 1 Basic Information
		//waitForElementId(10, "candidateNameId");
		Thread.sleep(3000);
		getObjectById("candidateNameId").sendKeys(data.get("CandidateName"));
		String candidateName = getObjectById("candidateNameId").getAttribute("value");
		System.out.println("name of the new candidate is= "+candidateName);
		getObjectById("candidateEmailId").sendKeys(data.get("CandidateEmail"));
		getObjectById("candidatePhoneNumberId").sendKeys(data.get("PhoneNumber"));
		getObjectById("candidateDobId").click();
		Thread.sleep(5000);
		Select monthDob = new Select(getObject("candidateMonthX"));
		monthDob.selectByVisibleText("Oct");
	    Select yearDob = new Select(getObject("candidateYearX"));
	    yearDob.selectByValue("1991");
	    getObject("candidateDayDateX").click();
		getObjectById("candidateAddressId").click();
	    getObjectById("candidateAddressId").sendKeys(data.get("CandidateAddress"));
	    waitForElementClickable(10, "candidateNextStep1X");
	    getObject("candidateNextStep1X").click();
	    //moving on to Step 2 LogIn Details
	    getObjectById("candidateUsernameId").sendKeys(data.get("CandidateUsername"));
	    getObjectById("candidatePasswordId").sendKeys(data.get("CandidatePassword"));
	    Thread.sleep(4000);
	    getObjectByCss("candidateNextStepCss").click();
	    System.out.println("clicked on next in step 2");
	   // Thread.sleep(3000);
	    //moving on to Step 3 Employment Information
	    //waitForElementId(10, "candidateNiNumberId");
	    getObjectById("candidateNiNumberId").sendKeys(data.get("NiNumber"));
	    getObjectById("candidateStatusId").sendKeys(data.get("CandidateStatus"));
	    getObject("candidateNextStep3X").click();
		//moving on to Step 4 Social Media (Optional)
	    getObjectById("candidateFacebookId").sendKeys(data.get("Facebook"));
	    getObjectById("candidateTwitterId").sendKeys(data.get("Twitter"));
	    getObjectById("candidateLinkedinId").sendKeys(data.get("LinkedIn"));
	    getObjectById("candidateSkypeId").sendKeys(data.get("Skype"));
	    getObject("candidateNextStep4X").click();
	  //moving on to Step 5 Criteria
	    //waitForElement(10, "candidateCategoryX");
	    Thread.sleep(3000);
	    getObject("candidateCategoryX").click();
	    getObject("candidateCategoryX").sendKeys(data.get("Category"));
	    getObject("candidateCategoryX").sendKeys(Keys.RETURN);
	    getObjectById("candidatePassportId").click();
	    Select month = new Select(getObject("candidatePassportMonthX"));
	    month.selectByVisibleText("Oct");
	    Select year = new Select(getObject("candidatePassportYearX"));
	    year.selectByValue("2020");
	    getObject("candidatePassportDateX").click();
	    Thread.sleep(3000);
	    getObjectById("candidateManualCertId").click();
	    Select month1 = new Select(getObject("candidateManualMonthX"));
	    month1.selectByVisibleText("Jul");
	    Select year1 = new Select(getObject("candidateManualYearX"));
	    year1.selectByValue("2015");
	    getObject("candidateManualDateX").click();
	    getObjectById("candidateGardaVettingId").sendKeys(data.get("GardaVetting"));
	    getObjectById("candidateTrainingId").sendKeys(data.get("TrainingComplete"));
	    getObjectById("candidateProofOfRightToWorkId").sendKeys(data.get("ProofRightToWork"));
	    getObjectById("candidateCertificationExpiry").click();
	    Select month2 = new Select(getObject("candidateCertificationMonthX"));
	    month2.selectByVisibleText("Sep");
	    Select year2 = new Select(getObject("candidateCertificationYearX"));
	    year2.selectByValue("2018");
	    getObject("candidateCertificationDateX").click();
	   // getObject("candidateFinishStep5X").click();
	    getObjectByCss("candidateFinishStep5Css").click();
	    Thread.sleep(10000);
	    
	    
	    
	    //31
	    /*CREATING A JOB*/
	    //Clicking on Jobs and then Add new Job
	    getObject("jobsLinkX").click();
	    getObject("addNewJobX").click();
	    /*moves on to Create New Job Step1: Client Information*/
	    System.out.println("moves on to Create New Job Step1: Client Information");
	    Thread.sleep(5000);
	    getObjectById("cNameId").sendKeys(data.get("cnameS1"));
	    getObjectByLinkText("watsonsClientLt").click();
	   
		getObjectByCss("cNextCss").click();
		
		Thread.sleep(5000);
		/*moves on to step 2 basic information*/
		explicitWaitId("biTitleId");
	    getObjectById("biTitleId").sendKeys(data.get("cTitleS2"));
	    String jobTitle = (data.get("cTitleS2"));
	    System.out.println("the new job title is " +jobTitle);
	    // getObjectById("biPurchaseId").sendKeys(data.get("cPurchaseS"));
	    getObject("biPurchaseX").sendKeys(data.get("cPurchaseS"));
	    getObjectById("biReqAvailbilityId").click();
	    Select month0 = new Select(getObject("biMonthX"));
	    month0.selectByVisibleText("Aug");
	    Select year0 = new Select(getObject("biYearX"));
	    year0.selectByValue("2015");
	    getObject("dateDatePicker21JobX").click();
	    //getObjectByLinkText("21").click();
	    Select weekPattern = new Select(getObject("biNumWeekPattern"));
	    weekPattern.selectByValue("1");
	    getObject("dateTime630Friday21X").click();
	    //dragDrop("dateTime12Friday21x", "dateTime12Friday21x");
	    System.out.println("drag and drop success");
	    //now click on reset to clear the values selected
	    //getObject("biResetCalX").click();
	   // getObject("biCalConfirmX").click();
	    driver.findElement(By.cssSelector("button.btn.btn-success")).click();
		
		/*moves on to step 2 basic information
		explicitWaitId("biTitleId");
	    getObjectById("biTitleId").sendKeys(data.get("cTitleS2"));
	    String jobTitle = (data.get("cTitleS2"));
	    System.out.println(jobTitle);
	    getObject("biPurchaseX").sendKeys(data.get("cPurchaseS"));
	    Thread.sleep(5000);
	    getObjectById("biReqAvailbilityId").click();
	    Select monthJob = new Select(getObject("biMonthX"));
	    monthJob.selectByVisibleText("Aug");
	    Select yearJob = new Select(getObject("biYearX"));
	    yearJob.selectByValue("2015");
	    getObject("jobDateAug18x").click();
	    Select weekPattern = new Select(getObject("biNumWeekPattern"));
	    weekPattern.selectByValue("1");
	    //dragDrop("biTime9X", "bitime5X");
	    dragDrop("jobTimes9X", "jobTimes4X");
	    System.out.println("drag and drop success");
	  //  waitForElement(10, "biCalConfirmX");
	    driver.findElement(By.xpath("html/body/div[8]/div/div/div[3]/button[1]")).click();*/
	    //driver.findElement(By.cssSelector("button.btn.btn-success")).click();
	    //getObject("biCalConfirmX").click();
	    /*brings back to the step 2 Basic information*/
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
	    
	    //movin on to  Step 3 billing information
	    getObjectById("billingTypeId").sendKeys(data.get("BillingTypeStep3"));
	    getObjectById("payRateId").click();
	    getObjectById("payRateId").clear();
	    getObjectById("payRateId").sendKeys(data.get("PayRateStep3"));
	    getObjectById("payRateLockId").click();
	    getObjectById("billRateLockId").click();
	    getObjectById("billRateId").clear();
	    getObjectById("billRateId").sendKeys(data.get("BillRateStep3"));
	    getObjectById("billRateLockId").click();
	    getObjectById("marginId").click();
	    getObjectById("marginLockId").click();
	    getObjectById("bookingFeeId").clear();
	    System.out.println("cleared");
	    getObjectById("feeFrequencyId").sendKeys(data.get("FeeFrequencyStep3"));
	    getObjectById("bookingFeeId").sendKeys(data.get("BookingFeeStep3"));
	    getObjectByCss("nextStep3Css").click();
	    /*moves on to the Step 4 Set Approvers*/
	    getObjectById("selectAgencyApproverId").click();
	    getObject("addAgencyApproverX").click();
	    getObjectById("selectClientContactId").click();
	    getObject("addClientContactX").click();
	    getObjectByCss("nextStep4cSS").click();
	    //moves on to criteria Step 5
	    System.out.println("moves on to Step 5 Criteria");
	    getObject("categoryX").click();
	    getObject("categoryEditFieldX").sendKeys(data.get("CategoryStep5"));
	    getObject("categoryEditFieldX").sendKeys(Keys.RETURN);
	    getObjectByCss("nextStep5Css").click();
	    
	    //moves on to Step 6 Match Jobs
	    System.out.println("moves on to Step 6 Match Jobs");
	    getObject("jobPayRateOverwriteX").click();
	    getObjectById("assignCandidateId").click();
	    getObjectById("candidateNameStep6").click();
	    getObjectById("candidateNameStep6").sendKeys(data.get("CandidateNameAssign"));
	    getObjectByLinkText("sherlockCandidateLt").click();
	    //44
	   // waitForElementCss(10, "finishCss");
	    Thread.sleep(3000);
	    getObjectByCss("finishCss").click();
	    Thread.sleep(10000);
		System.out.println("clicked on finish 1 in step 6");
		explicitWaitXpath("assignAutoConfirmClientYesX");
		getObject("assignAutoConfirmClientYesX").click();
		getObject("assignSendEmailClientYesX").click();
		getObject("assignAutoConfirmCandidateYesX").click();
		System.out.println("Auto confirmed client, worker and email sent is sent");
		getObjectByCss("assignCandidateYesCss").click();
		System.out.println("clicked on assign candidate yes");
		Thread.sleep(12000);
		getObjectByCss("assignSendCandidateCss").click();
		System.out.println("success");
		Thread.sleep(12000);
		
		/*SIGNING IN WORKERS PORTAL FOR CHECK IN AND CHECK OUT*/
		driver1 = new FirefoxDriver();
		System.out.println("signing into Workers portal for check in and check out");
		driver1.get("http://staging.tempbuddy.com");
		driver1.findElement(By.id("username")).sendKeys("sherlock-holmes");
		driver1.findElement(By.id("password")).sendKeys("qwerty1");
		driver1.findElement(By.id("_submit")).click();
		Thread.sleep(3000);
		String workerJob = driver1.findElement(By.xpath("//*[@id='title']/strong")).getText();
		System.out.println("job displayed on the workers dashboard is "+workerJob);
		Assert.assertEquals(jobTitle, workerJob);
		driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
		Thread.sleep(10000);
		driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
		Thread.sleep(5000);
		driver1.quit();
		
		Thread.sleep(3000);
		
		/*BACK ON RECRUITER PORTAL*/
		//now clicking on Pay&Bill-->TimeSheet and--> Generate New TimeSheet
		getObject("payBillX").click();
		getObject("timeSheetLinkX").click();
		getObject("timesheetGenerateX").click();
		//moves on to Step 1- Filtering By Assignment
		getObjectById("timesheetCandidateNameId").click();
		Thread.sleep(3000);
		getObjectById("timesheetCandidateNameId").sendKeys(data.get("TimeSheetCandidate"));
		getObjectByLinkText("sherlockCandidateLt").click();
		getObjectById("timesheetAssignmentTitleId").click();
		Thread.sleep(3000);
		getObjectById("timesheetAssignmentTitleId").sendKeys(data.get("Newjob"));
		getObjectByLinkText("watsonsJobsLt").click();
		getObject("timesheetByAssignmentNextX").click();
		//moves on to step 2-choose by date
		getObjectById("timesheetChooseDateRangeId").click();
		Select monthByAssignment = new Select(getObject("timesheetChooseMonthX"));
		monthByAssignment.selectByVisibleText("Aug");
	    Select yearByAssignment = new Select(getObject("timesheetChooseYearX"));
	    yearByAssignment.selectByValue("2015");
	    getObject("timesheetDateRangeDateX").click();
	    getObject("timesheetDateRangeFinishX").click();
	    Thread.sleep(10000);
	    
	    //now click on all time sheets
	    getObject("allTimeSheetsX").click();
	    getObject("timesheetFilterX").click();
	    getObject("timesheetFilterX").sendKeys(data.get("TimeSheetFilterJob"));
	    Thread.sleep(3000);
	    String jobName = getObject("firstSearchedJobX").getText();
	    System.out.println(jobName);
		//checking the searched job and appeared job are same and if same then the time sheet is generated successfully
	    Assert.assertEquals(jobName, jobTitle);
	    System.out.println("matched! timesheet is generated for "+jobName);
	    
	    }
}
