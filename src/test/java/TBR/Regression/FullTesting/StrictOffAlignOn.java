package TBR.Regression.FullTesting;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.TestUtil;

public class StrictOffAlignOn extends FullTestingRegressionSuiteBase{
	public static WebDriver driver1 = null;
	@DataProvider
	public Object[][] getStrictOffAlignOnData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "StrictOffAlignOn");
    //return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAssignCandidate");
	}
	
	@Test(dataProvider="getStrictOffAlignOnData")
	public void strictOffAlignOn(Hashtable<String, String>data) throws InterruptedException{
		
	/*browserUrl() opens up a browser, goes to Staging url & performs login.*/
	browserUrl();
	
	waitForElement(5, "jobsLinkX");
	getObject("jobsLinkX").click();
	waitForElementClickable(10, "addNewJobX");
	getObject("addNewJobX").click();
	Thread.sleep(5000);

	step1ClientInformation(data);
	
	//Step 2
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
    //dragDrop("biTime9X", "bitime5X");
    //System.out.println("drag and drop success");
    //waitForElementClickable(10, "timeStartsWithIdX");
    // String editTime = getObjectText("timeStartsWithIdX");
    //System.out.println(editTime);
    //now click on reset to clear the values selected
    //getObject("biResetCalX").click();
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
    
    //Step 3
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
    
	step4SetApprovers(data);
	
	step5Criteria(data);

	/*moves on to Step 6 Match Jobs*/
	LOGS.debug("on Step6: Match Jobs");
    System.out.println("moves on to Step 6 Match Jobs");
    waitForElement(10, "jobPayRateOverwriteX");
    getObject("jobPayRateOverwriteX").click();
    getObjectById("assignCandidateId").click();
    getObjectById("candidateNameStep6").click();
    getObjectById("candidateNameStep6").sendKeys(data.get("CandidateName"));
    getObjectByLinkText("sherlockCandidateLt").click();
    Thread.sleep(5000);
    getObjectByCss("finishCss").click();
    LOGS.debug("end of Step6: Match Jobs");
    Thread.sleep(10000);
    
    getObject("assignAutoConfirmClientYesX").click();
    //getObject("assignSendEmailClientYesX").click();
    getObject("assignAutoConfirmCandidateYesX").click();
    //getObject("assignAutoConfirmCandidateNoX").click();
    
    
    /*confirm wizard and send wizard that comes after finishing Step6: Assign Job to Candidate*/
    ConfirmSendWizards();
    Thread.sleep(10000);
    /*SIGNING IN WORKERS PORTAL FOR CHECK IN AND CHECK OUT*/
	System.setProperty("webdriver.chrome.driver", "C:\\Workspaces\\eclipse\\TempBuddy\\TempBuddyRecruiter\\src\\test\\java\\TBR\\Properties\\chromedriver.exe");
	driver1 = new ChromeDriver();
	System.out.println("signing into Workers portal for check in and check out");
	driver1.manage().window().maximize();
	driver1.get("http://staging.tempbuddy.com");
	driver1.findElement(By.id("username")).sendKeys("sherlock-holmes");
	driver1.findElement(By.id("password")).sendKeys("qwerty1");
	driver1.findElement(By.id("_submit")).click();
    timeStamp();
    TimeUnit.MINUTES.sleep(3);
    timeStamp();
	/*Thread.sleep(3000);
	String workerJob = driver1.findElement(By.xpath("//*[@id='title']/strong")).getText();
	System.out.println("job displayed on the workers dashboard is "+workerJob);
	Assert.assertEquals(jobTitle, workerJob);
	driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
	TimeUnit.MINUTES.sleep(5);
	driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
	TimeUnit.MINUTES.sleep(2);
	driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
	TimeUnit.MINUTES.sleep(5);
	driver1.findElement(By.xpath("//*[@id='checkin-button']/div/span")).click();
	Thread.sleep(3000);*/
	driver1.close();
	
	/*BACK ON RECRUITER PORTAL*/
	//now clicking on Pay&Bill-->TimeSheet and--> Generate New TimeSheet
	getObject("payBillX").click();
	getObject("timeSheetLinkX").click();
	waitForElementClickable(10, "timesheetGenerateX");
	getObject("timesheetGenerateX").click();
	//moves on to Step 1- Filtering By Assignment
	waitForElementClickableId(10, "timesheetCandidateNameId");
	getObjectById("timesheetCandidateNameId").click();
	/*WebElement element = getObjectById("timesheetCandidateNameId");
	Actions actions = new Actions(driver);
	actions.moveToElement(element);
	actions.click();
	actions.sendKeys(data.get("TimeSheetCandidate"));*/
	getObjectById("timesheetCandidateNameId").sendKeys(data.get("TimeSheetCandidate"));
	getObjectByLinkText("sherlockCandidateLt").click();
	
	getObjectById("timesheetAssignmentTitleId").click();
	Thread.sleep(3000);
	getObjectById("timesheetAssignmentTitleId").sendKeys(data.get("Newjob"));
	getObjectByLinkText("jobLatestDecMornLt").click();
	getObject("timesheetByAssignmentNextX").click();
	//moves on to step 2-choose by date
	waitForElementClickableId(10, "timesheetChooseDateRangeId");
	getObjectById("timesheetChooseDateRangeId").click();
	Select monthByAssignment = new Select(getObject("timesheetChooseMonthX"));
	monthByAssignment.selectByVisibleText("Dec");
    Select yearByAssignment = new Select(getObject("timesheetChooseYearX"));
    yearByAssignment.selectByValue("2015");
    getObject("dec9X").click();
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
    
    waitForElementClickable(10, "eyeIconFirstSearchTimesheetX");
    getObject("eyeIconFirstSearchTimesheetX").click();
    waitForElement(12, "numOfMinutesX");
    String numOfMinutes = getObjectText("numOfMinutesX");
    System.out.println("no of minutes on the timesheet is: " +numOfMinutes);
    
    Assert.assertEquals(numOfMinutes, "0 hours, 36 minutes");
    System.out.println("Success! number of minutes on actual and expected timesheets matched");
    
    
   }
		
}

