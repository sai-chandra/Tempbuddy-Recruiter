package TBR.Regression_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AdvertisePoolCheckApplication extends RegressionSuiteBase {

@Test
public void advertisePoolCheckApplication(){

	waitForElement(5, "jobsLinkX");
	getObject("jobsLinkX").click();
    getObject("appLinkX").click();
    getObject("applicationFilterX").click();
    getObject("applicationFilterX").sendKeys("");
    String filteredJob = getObject("applicationListFilterFirstOptionX").getText();
    System.out.println(filteredJob);
    Assert.assertEquals(filteredJob, "");
    System.out.println("success jobs matched");
    //clicks on eye icon to view the information of the new application
    getObject("applicationEyeIconX").click();
    getObjectById("applicationAssignmentsId").click();
    getObject("applicationAssignHandIconFirstX").click();
    
    
}

}
