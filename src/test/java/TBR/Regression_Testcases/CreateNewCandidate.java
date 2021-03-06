package TBR.Regression_Testcases;

import java.util.Hashtable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;
/*Adding new candidate*/
public class CreateNewCandidate extends RegressionSuiteBase{
	
	@DataProvider
	public Object[][] getCreateNewCandidateData(){
	return TestUtil.getDataIntoHashTable(CandidateExcel, "AddNewCandidate");
	}
	
	@Test(dataProvider="getCreateNewCandidateData")
    public void createNewCandidate(Hashtable<String, String>data) throws InterruptedException {
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	login_Valid();
	//login_Kevin();
	getObject("candLinkX").click();
	getObject("addNewCandidateX").click();
	waitForElementClickableId(10, "candidateNameId");
	//waitForElementClickableId(10, "candidateNameId");
	
	//moves on to step 1 Basic Information
	//explicitWaitId("candidateNameId");
	getObjectById("candidateNameId").sendKeys(data.get("CandidateName"));
	Thread.sleep(5000);
	String candidateName = getObjectById("candidateNameId").getAttribute("value");
	System.out.println("name of the new candidate is= "+candidateName);
	getObjectById("candidateEmailId").sendKeys(data.get("CandidateEmail"));
	getObjectById("candidatePhoneNumberId").sendKeys(data.get("PhoneNumber"));
	/*getObject("candidateMonthX").sendKeys(data.get("DobMonth"));
	//getObjectById("candidateYearX").click();
	getObject("candidateYearX").sendKeys(data.get("DobYear"));
	getObject("candidateDayDateX").click();*/
	getObjectById("candidateDobId").click();
	Thread.sleep(5000);
	Select yearDob = new Select(getObject("candidateYearX"));
    yearDob.selectByValue("2009");
	Select monthDob = new Select(getObjectByCss("candidateMonthCSS"));
	monthDob.selectByVisibleText("Oct");
    getObject("candidateDayDateX").click();
	getObjectById("candidateAddressId").click();
    getObjectById("candidateAddressId").sendKeys(data.get("CandidateAddress"));
    Actions hover = new Actions(driver);
    WebElement elementToHover = getObject("candidateNextStep1X");
    hover.moveToElement(elementToHover);
    hover.build().perform();
    Thread.sleep(3000);
    getObject("candidateNextStep1X").click();
   
    //moving on to Step 2 LogIn Details
    getObjectById("candidateUsernameId").sendKeys(data.get("CandidateUsername"));
    getObjectById("candidatePasswordId").sendKeys(data.get("CandidatePassword"));
    Actions hove = new Actions(driver);
    WebElement elementToHov = getObject("candidateNextStep1X");
    hove.moveToElement(elementToHov);
    hove.build().perform();
    //getObjectByCss("candidateNextStepCss").click();
  
    Actions hover3 = new Actions(driver);
    WebElement elementToHover3 = getObjectByCss("candidateNextStepCss");
    hover3.moveToElement(elementToHover3);
    hover3.build().perform();
    Thread.sleep(4000);
    getObjectByCss("candidateNextStepCss").click();
    System.out.println("clicked on next in step 2");
    
    //moving on to Step 3 Employment Information
    waitForElementClickableId(10, "candidateNiNumberId");
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
    getObject("candidateCategoryX").click();
    getObject("candidateCategoryX").sendKeys(data.get("Category"));
    getObject("candidateCategoryX").sendKeys(Keys.RETURN);
    //Thread.sleep(3000);
    /*the below two lines are for live server*/
    //getObjectById("candidateLicenseId").click();
    //getObjectById("candidateLicenseId").sendKeys(data.get("driverlicense"));
    //getObjectById("candidateLicenseId").sendKeys(Keys.RETURN);
    /*end of live server code*/
    getObject("candidateCustomAttributeShowX").click();
    explicitWaitId("candidatePassportId");
    getObjectById("candidatePassportId").click();
    Select month = new Select(getObject("candidatePassportMonthX"));
    month.selectByVisibleText("Oct");
    Select year = new Select(getObject("candidatePassportYearX"));
    year.selectByValue("2020");
    getObject("candidatePassportDateX").click();
    Thread.sleep(5000);
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
    getObject("candidateFinishStep5X").click();
    Thread.sleep(10000);
    explicitWaitXpath("candLinkX");
    //back to dash board
    //getObject("candLinkX").click();
    //explicitWaitXpath("allCandidatesX");
    getObject("allCandidatesX").click();
    //moves on to candidate list wizard
    //explicitWaitXpath("searchCandidateX");
    getObject("searchCandidateX").click();
    //explicitWaitXpath("searchCandidateX");
    getObject("searchCandidateX").sendKeys(candidateName);
    
   
	//explicitWaitXpath("candidateMatchX");
    Thread.sleep(5000);
    String candidateNameAppeared = getObject("candidateMatchX").getText();
    System.out.println("candidate appeared on the screen is = "+candidateNameAppeared);
    Assert.assertEquals(candidateNameAppeared, candidateName);
    System.out.println("new candidate saved successfully");
    }
	
	@AfterMethod
	public void screenShot(){
		CaptureScreenShot.captureScreenShot(driver, "CreateNewCandidate");
	}
}
