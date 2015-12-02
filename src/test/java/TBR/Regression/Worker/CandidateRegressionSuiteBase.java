package TBR.Regression.Worker;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import TBR.TestBase.TestBase;

public class CandidateRegressionSuiteBase extends TestBase {
	
	@BeforeSuite
	public void clientSuiteBase() throws IOException{
	initialize();
	}
	
	@AfterSuite
	public void tearDown() throws InterruptedException{
	//Thread.sleep(6000);
	//driver.quit();
	}
	
	/*Advertise Job Step 1 Select Job - Candidate pools*/
	public void advertiseJobStep1SelectJob(){
    LOGS.debug("on Advertise Job, Step1: Select Job - Candidate pools");
	System.out.println("on Advertise Job, Step1: Select Job - Candidate pools");		
	waitForElementClickableId(10, "advertiseJobEnterJobId");
    getObjectById("advertiseJobEnterJobId").sendKeys("watsonOld");
    waitForElementClickableLinkText(10, "watsonOldJobLt");
    getObjectByLinkText("watsonOldJobLt").click();
    getObjectByCss("advertiseJobNextCss").click();
	}
	
    /*Advertise Job Step 2 Choose Candidates - Candidate pools*/
    public void advertiseJobStep2ChooseCandidates(){
    LOGS.debug("on Advertise Job, Step2: Choose Candidates - Candidate pools");
    System.out.println("on Advertise Job, Step2: Choose Candidates - Candidate pools");
    waitForElementClickableId(10, "candidateSelectAllButtonLeftId");
    getObjectById("candidateSelectAllButtonLeftId").click();
    waitForElementClickable(10, "candidateListFirstOptionX");
    dragDropClickMoveRelease("candidateListOptionX", "selectedCandidatesAreaId");
    getObjectByCss("chooseCandidatesStep2NextCss").click();
    }
    
    /*Advertise Job Step 3 Schedule Advertisement - Candidate pools*/
    public void advertiseJobStep3ScheduleAdvertisement(){
    LOGS.debug("on Advertise Job, Step3: Schedule Advertisement - Candidate pools");
    System.out.println("on Advertise Job, Step3: Schedule Advertisement - Candidate pools");
    waitForElementClickableId(10, "NoOfCandidatesPerPositionId");
    populateFieldById("NoOfCandidatesPerPositionId", "1");
    getObjectById("timeIntervalBetweenAdvertsId").clear();
    populateFieldById("timeIntervalBetweenAdvertsId", "1");
    selectOptionById("actionWhenCandidateAcceptsId", "Application");
    populateFieldById("maxApplicationsPerPositionId", "1");
    getObjectByCss("scheduleAdvertisementStep3nextCss").click();
    }
    
    /*Advertise Job Step-4 Set Message*/
    public void advertiseJobStep4SetMessage(){
    LOGS.debug("on Advertise Job, Step4: Set Message - Candidate pools");
    System.out.println("on Advertise Job, Step4: Set Message - Candidate pools");
    waitForElementClickableId(10, "enterMessageAdvertieJobId");
    getObjectById("enterMessageAdvertieJobId").sendKeys("EnterMessage");
    getObjectByCss("setMessageStep4FinishX").click();
    System.out.println("here");
    }
    
}