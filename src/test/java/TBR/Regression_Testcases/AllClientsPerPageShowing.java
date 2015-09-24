package TBR.Regression_Testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllClientsPerPageShowing extends RegressionSuiteBase{
	
	public static int perPageNum = 0;
	
	@Test
	public void clientsRough() throws IOException, InterruptedException{
    openBrowser();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get(CONFIG.getProperty("testSiteName"));
    login_Valid();
    getObject("clientsLinkx").click();
    getObject("allClientsX").click();
    //brings on to all clients list
    //String a10 = getObjectTextId("clientsPer10Id");
    //System.out.println(a10);
    Thread.sleep(3000);
    perPageNum = Integer.valueOf(getObjectTextId("clientsPerId").split(" ")[4]);
    System.out.println("on display is per "+perPageNum);
   
    Select perPage = new Select(getObject("clientShowPerX"));
    perPage.selectByValue("25");
    Thread.sleep(3000);
    perPageNum = Integer.valueOf(getObjectTextId("clientsPerId").split(" ")[4]);
    System.out.println("now displaying per "+perPageNum);
    
    Select perPage50 = new Select(getObject("clientShowPerX"));
    perPage50.selectByValue("50");
    Thread.sleep(3000);
    perPageNum = Integer.valueOf(getObjectTextId("clientsPerId").split(" ")[4]);
    System.out.println("now displaying per "+perPageNum);
    
    Select perPage100 = new Select(getObject("clientShowPerX"));
    perPage100.selectByValue("100");
    Thread.sleep(3000);
    perPageNum = Integer.valueOf(getObjectTextId("clientsPerId").split(" ")[4]);
    System.out.println("now displaying per "+perPageNum);
    
    Select perPage10 = new Select(getObject("clientShowPerX"));
    perPage10.selectByValue("10");
    Thread.sleep(2000);
    //waitForElementId(10, "clientsPerId");
    perPageNum = Integer.valueOf(getObjectTextId("clientsPerId").split(" ")[4]);
    System.out.println("now displaying per "+perPageNum);
    
    //now testing clients filter by client names and clicking on eye icon to see whether it is showing
    //the information of the searched client
    getObject("clientSearchFieldX").click();
    getObject("clientSearchFieldX").sendKeys("ClientTemp2");
    Thread.sleep(3000);
    String a = getObject("clientsFirstMatchX").getText();
    System.out.println("searched Client name is "+a);
    Assert.assertEquals(a, "ClientTemp2");
    System.out.println("Clients searched and matched");
    getObject("clientsEyeIconX").click();
    waitForElementId(10, "clientCompanyNameId");
    String b = getObjectByIdValue("clientCompanyNameId");
    System.out.println("company name is "+b);
    Assert.assertEquals(b, "ClientTemp2");
    System.out.println("Client name and company name matches");
    getObject("clientContactsLocationsX").click();
    scrollDownAction();
    scrollUpAction();
    getObject("clientJobsListX").click();
    scrollDownAction();
    scrollUpAction();
    getObject("clientBillingMethodX").click();
    scrollDownAction();
    scrollUpAction();
	}

}
