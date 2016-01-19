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
/*Adding new Template*/
public class AddSelectNewTemplate extends FullTestingRegressionSuiteBase{
	
	@DataProvider
	public Object[][] getAddSelectNewTemplateData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "AddSelectNewTemplate");
	}
    
	@Test(dataProvider="getAddSelectNewTemplateData")
	public void addSelectNewTemplate(Hashtable<String, String> data) throws InterruptedException{
		
		logger =report.startTest("AddSelectNewTemplate");   
		
		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		logger.log(LogStatus.INFO, "Browser started");
		String path = logger.addScreenCapture(CaptureScreenShot.captureScreenShot(driver, "AddSelectNewTemplate"));
		logger.log(LogStatus.PASS, path);
		
		/*count number of unassigned jobs is stored before creation of job*/
		String countUnassignJobsNumBefore = getObjectById("unassignedJobsCountId").getText();
		LOGS.debug("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		System.out.println("unassignedjobs number before saving a job is "+countUnassignJobsNumBefore);
		
		/*for storing the count of total jobs in a string before the job is created*/
		getObject("jobsLinkX").click();
		getObject("allJobsX").click();
		Thread.sleep(15000);
		
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
		
		requiredAvailability(data);
		
		/*Add New Job-Step3: Billing Information*/
	    LOGS.debug("on Step3: Billing Information");
	    /*Add New Template*/
	    waitForElement(10, "newTemplateX");
	    getObject("newTemplateX").click();
	    getObjectById("templateNameId").sendKeys(data.get("NewTemplateName"));
	    //getObjectById("templateBillingTypeId").click();
	    getSelectedByText("templateBillingTypeId", "Per hour");
	    getObjectById("templateNewTemplateBasicInfoPayRateId").click();
	    getObjectById("templateNewTemplateBasicInfoPayRateId").clear();
	    getObjectById("templateNewTemplateBasicInfoPayRateId").sendKeys(data.get("NewPayRate"));
	    String templatePayRate = getObjectById("templateNewTemplateBasicInfoPayRateId").getAttribute("value");;
	    LOGS.debug(templatePayRate);
	    System.out.println("template pay rate is "+templatePayRate);
	    getObject("templatePayRateLockX").click();
	    getObjectById("templateNewTemplateBasicInfoBillRateId").click();
	    getObjectById("templateNewTemplateBasicInfoBillRateId").clear();
	    getObjectById("templateNewTemplateBasicInfoBillRateId").sendKeys(data.get("NewBillRate"));
	    String NewTemplateBillRate = getObjectById("templateNewTemplateBasicInfoBillRateId").getAttribute("value");
	    LOGS.debug(NewTemplateBillRate);
	    System.out.println("new template bill rate is" +NewTemplateBillRate);
	    getObject("templateBillRateLockX").click();
	    //getObjectById("templateCostId").sendKeys(data.get("NewTemplateCost"));
	    //getObjectById("templateCostArrowUpdaterId").click();
	    //getObjectById("templateMarkupId").click();
	    //getObject("templateMarkupLockX").click();
	    getSelectedByText("templateCurrencyId", "€");
	   /* waitForElementClickableCss(10, "templateAddNewTemplateCss");
	    getObjectByCss("templateAddNewTemplateCss").click();*/
	    getObject("templateAddNewTemplateX").click();
	    
	    Thread.sleep(4000);
	    waitForElementCss(10, "addNewTemplateSuccessMessageCss");
	    String SuccessMessage = getObjectCssText("addNewTemplateSuccessMessageCss");
	    System.out.println(SuccessMessage);
	    
	    Assert.assertEquals(SuccessMessage, "Add New Template\nAdded");
	    System.out.println("success message matched and therefore Job is created successfully");
	    
	    getObjectById("templateNameOnStep3Id").sendKeys(data.get("CreatedTemplateName"));
	    getObjectByLinkText("templateNameLt").click();
	    
	    getObjectById("billingTypeId").sendKeys(data.get("BillingTypeStep3"));
	   
	    getObjectById("payRateId").clear();
	    getObjectById("payRateId").sendKeys(data.get("PayRateStep3"));
	    getObjectById("payRateLockId").click();
	    System.out.println("total id after the pay rate is entered and locked, auto generated value is = "+getObjectById("totalId").getAttribute("value"));
	    String BillRate = getObjectTextId("billRateLockId");
	    //getObjectById("billRateId").clear();
	    //getObjectById("billRateId").sendKeys(data.get("BillRateStep3"));
	    //getObjectById("billRateLockId").click();
	    //following is the value of pay rate
	    System.out.println("payrate value entered is = "+getObjectByIdValue("payRateId"));
	    //following is the value of bill rate
	    System.out.println("billrate value entered is = "+getObjectByIdValue("billRateId"));
	    
	    String billRate = getObjectById("billRateId").getAttribute("value");
	    LOGS.debug(billRate);
	    System.out.println(billRate);
	    Assert.assertEquals(billRate, NewTemplateBillRate);
	    LOGS.debug("success matched bill rates");
	    String payRate = getObjectById("payRateId").getAttribute("value");
	    LOGS.debug(payRate);
	    System.out.println(payRate);
	    Assert.assertEquals(payRate, templatePayRate);
	    LOGS.debug("success matched pay rates");
	    
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
		
		step6MatchJob();
		
		/*This method is to check job operation success message*/
	    jobOperationSuccess();
	    
	    String countUnassignJobsNumAfter = getObjectById("unassignedJobsCountId").getText();
		System.out.println("unassigned job number after saving a job is "+countUnassignJobsNumAfter);
		
		//checks if the unassigned jobs number is not equal to the after value
		checkUnassignedJobIncrement(countUnassignJobsNumBefore, countUnassignJobsNumAfter, Integer.valueOf(data.get("cNumPos")));
		
	    /*on Dashboard=>Jobs=>All Jobs*/
		LOGS.debug("click on All Jobs");
		getObject("allJobsX").click();
		Thread.sleep(15000);
		
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
				String screenshot_path= CaptureScreenShot.captureScreenShot(driver, "AddSelectNewTemplate");
				String image = logger.addScreenCapture(screenshot_path);
				logger.log(LogStatus.FAIL, "AddSelectNewTemplate", image);
			}
			report.endTest(logger);
			report.flush();
    }
}