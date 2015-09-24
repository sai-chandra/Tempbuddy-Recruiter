package TBR.Regression.CandidatesSection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

public class CandidatePoolsByApplication extends CandidateRegressionSuiteBase{
	
	@Test
	public void candidatesPoolByApplication() throws IOException, InterruptedException{
    openBrowser();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(CONFIG.getProperty("testSiteName"));
    login_Valid();
    getObject("candLinkX").click();
    getObject("candidatePoolsX").click();
    getObject("candidateFirstPoolNameMessageIconX").click();
    //Advertise Job Step 1 Select Job
    waitForElementClickableId(10, "advertiseJobEnterJobId");
    getObjectById("advertiseJobEnterJobId").sendKeys("watsonOld");
    waitForElementClickableLinkText(10, "watsonOldJobLt");
    getObjectByLinkText("watsonOldJobLt").click();
    getObjectByCss("advertiseJobNextCss").click();
    
    //Advertise Job Step 2 Choose Candidates
    waitForElementClickableId(10, "candidateSelectAllButtonLeftId");
    getObjectById("candidateSelectAllButtonLeftId").click();
    waitForElementClickable(10, "candidateListFirstOptionX");
    dragDropClickMoveRelease("candidateListOptionX", "selectedCandidatesAreaId");
    getObjectByCss("chooseCandidatesStep2NextCss").click();
   /* waitForElementClickableLinkTextVisible(10, "johnDoyleLt");
    dragDropClickMoveReleaseLinkText("johnDoyleLt", "selectedCandidatesAreaId");
    getObjectByCss("chooseCandidatesStep2NextCss").click();*/
    
    //Advertise Job Step 3 Schedule Advertisement
    waitForElementClickableId(10, "NoOfCandidatesPerPositionId");
    populateFieldById("NoOfCandidatesPerPositionId", "1");
    getObjectById("timeIntervalBetweenAdvertsId").clear();
    populateFieldById("timeIntervalBetweenAdvertsId", "1");
    selectOptionById("actionWhenCandidateAcceptsId", "Application");
    populateFieldById("maxApplicationsPerPositionId", "1");
    getObjectByCss("scheduleAdvertisementStep3nextCss").click();
    
    //moves on to Advertise Job Step-4 Set Message
    waitForElementClickableId(10, "enterMessageAdvertieJobId");
    getObjectById("enterMessageAdvertieJobId").sendKeys("EnterMessage");
    getObjectByCss("setMessageStep4FinishX").click();
    System.out.println("here");
    

}
}
