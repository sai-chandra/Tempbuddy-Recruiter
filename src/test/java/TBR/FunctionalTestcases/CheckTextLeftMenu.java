package TBR.FunctionalTestcases;

import org.testng.annotations.Test;

public class CheckTextLeftMenu extends FunctionalSuiteBase {
	
	@Test
	public void checkTextLeftMenu(){
    openBrowser();
    driver.get(CONFIG.getProperty("testSiteName"));
    login_Valid();
    assertTrue("dashBoardLinkX", "Dashboard1");
    System.out.println("True for DashBoard");
    assertTrue("rosterViewLinkX", "Roster View");
    System.out.println("True for Roster View");
    assertTrue("jobsLinkX", "Jobs");
    System.out.println("True for Jobs");
    assertTrue("clientsTextX", "Clients");
    System.out.println("True for Clients");
    assertTrue("candTextX", "Candidates");
    System.out.println("True for Candidates");
    assertTrue("appTextX", "Applications");
    System.out.println("True for Applications");
    assertTrue("assignTextX", "Assignments");
    System.out.println("True for Assignments");
    assertTrue("timeSheetTextX", "TimeSheet");
    System.out.println("True for TimeSheet");
    assertTrue("invocieTextX", "Invoice");
    System.out.println("True for Invoice");
    assertTrue("payslipTextX", "PaySlip");
    System.out.println("True for Payslip");
    assertTrue("reportsLinkX","Reports");
    System.out.println("true for Reports");
    assertTrue("settingTextX", "Settings");
    System.out.println("True for settings");
  
	}
}
