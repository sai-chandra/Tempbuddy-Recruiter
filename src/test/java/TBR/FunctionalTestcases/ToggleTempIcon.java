package TBR.FunctionalTestcases;

import org.testng.annotations.Test;

public class ToggleTempIcon extends FunctionalSuiteBase {
	
	@Test
	public void toggleTempIcon(){
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	login_Valid();
	String toggleside1 = getObjectById("toggleId").getAttribute("data-placement");
	System.out.println(toggleside1);
    getObjectById("toggleId").click();
    String toggleside2 = getObjectById("toggleRightId").getAttribute("data-placement");
    System.out.println(toggleside2);
    System.out.println("click on tempbuddyicon on dashboard");
    getObject("tempBuddyIconX").click();
    
    //on message Icon
    getObject("messageIconX").click();
    getObjectById("newMessagesId").click();
    getObjectById("allMessagesId").click();
    getObjectById("sentMessagesId").click();
    
    //on account
    getObject("accountX").click();
    getObject("accountInX").click();
    getObject("signOutX").click();
    
    
    
	}
	

	
}
