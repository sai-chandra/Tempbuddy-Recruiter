package TBR.Regression.Worker;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

public class CandidatePoolsByAssignment extends CandidateRegressionSuiteBase{
	//Candidate pools by Assignment
	@Test
	public void candidatesPoolByAssignment() throws IOException, InterruptedException{
	browserUrl();
    
	getObject("candLinkX").click();
    getObject("candidatePoolsX").click();
    getObject("candidateFirstPoolNameMessageIconX").click();
    
    //Advertise Job Step 1 Select Job
    advertiseJobStep1SelectJob();
    
    //Advertise Job Step 2 Choose Candidates
    advertiseJobStep2ChooseCandidates();
   /* waitForElementClickableLinkTextVisible(10, "johnDoyleLt");
    dragDropClickMoveReleaseLinkText("johnDoyleLt", "selectedCandidatesAreaId");
    getObjectByCss("chooseCandidatesStep2NextCss").click();*/
    
    //Advertise Job Step 3 Schedule Advertisement
    waitForElementClickableId(10, "NoOfCandidatesPerPositionId");
    populateFieldById("NoOfCandidatesPerPositionId", "1");
    getObjectById("timeIntervalBetweenAdvertsId").clear();
    populateFieldById("timeIntervalBetweenAdvertsId", "1");
    selectOptionById("actionWhenCandidateAcceptsId", "Assignment");
    getObjectByCss("scheduleAdvertisementStep3nextCss").click();
    
    //moves on to Advertise Job Step-4 Set Message
    advertiseJobStep4SetMessage();

    
}
}