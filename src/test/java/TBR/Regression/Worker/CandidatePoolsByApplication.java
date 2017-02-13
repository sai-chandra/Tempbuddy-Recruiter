package TBR.Regression.Worker;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CandidatePoolsByApplication extends CandidateRegressionSuiteBase{
	//Candidate Pools By Applications...
	@Test
	public void candidatesPoolByApplication() throws IOException, InterruptedException, ClassNotFoundException, SQLException{
	browserUrl();
	
	waitForElement(5, "candLinkX");
	getObject("candLinkX").click();
	waitForElement(10, "candidatePoolsX");
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
    advertiseJobStep3ScheduleAdvertisement();
    
    //moves on to Advertise Job Step-4 Set Message
    advertiseJobStep4SetMessage();

}
}
