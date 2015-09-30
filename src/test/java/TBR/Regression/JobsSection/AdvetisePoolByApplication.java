package TBR.Regression.JobsSection;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TBR.TestUtil.CaptureScreenShot;
import TBR.TestUtil.TestUtil;

public class AdvetisePoolByApplication extends JobsRegressionSuiteBase{
	
	@DataProvider
	public Object[][] getAdvertiseJobPoolByApplicationData(){
    return TestUtil.getDataIntoHashTable(JobsExcel, "JobsFlowAdvertisePool");
	}
	
	@Test(dataProvider="getAdvertiseJobPoolByApplicationData")
	public void jobFlowByPoolRough(Hashtable<String, String> data) throws InterruptedException, IOException{

		/*browserUrl() opens up a browser, goes to Staging url & performs login*/
		browserUrl();
		
		waitForElement(5, "jobsLinkX");
		getObject("jobsLinkX").click();
		getObject("addNewJobX").click();
		Thread.sleep(5000);
		
        step1ClientInformation(data);
		
		step2Step3AddNewJob(data);
		   
		step4SetApprovers(data);
		
		step5Criteria(data);
		
		/*Add New Job-Step6: Match Jobs for Advertise Pool*/
		step6AdvertisePool();
	    Thread.sleep(5000);
	    /*moves on to Advertise Job Step-1 Select Job*/
	    LOGS.debug("on Advertise Job Step-1 Select Job");
	    step1AdvertisePoolSelectJob();
	    
	    /*moves on to Advertise Job Step-3 Schedule Advertisement*/
	    LOGS.debug("on Advertise Job Step-3 Schedule Advertisement");
	    waitForElementClickableId(10, "NoOfCandidatesPerPositionId");
	    populateFieldById("NoOfCandidatesPerPositionId", data.get("NoOfCandidatesPerPosition"));
	    getObjectById("timeIntervalBetweenAdvertsId").clear();
	    populateFieldById("timeIntervalBetweenAdvertsId", data.get("TimeIntervalBetweenAdverts"));
	    selectOptionById("actionWhenCandidateAcceptsId", data.get("ActionCandidateAccepts"));
	    populateFieldById("maxApplicationsPerPositionId", data.get("MaxApplicationperPosition"));
	    getObjectByCss("scheduleAdvertisementStep3nextCss").click();
	    
	    /*Advertise Job Step-4 Set Message*/
	    step4AdvertiseJobSetMessage(data);
	    }
	
	    @AfterMethod
        public void screenShot(){
        CaptureScreenShot.captureScreenShot(driver, "AdvetisePoolByApplication");
        }
}