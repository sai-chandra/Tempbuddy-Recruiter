package TBR.FunctionalTestcases;

import org.testng.annotations.Test;

public class LeftSideMenu extends FunctionalSuiteBase{
	
	@Test
	public void leftSideMenu() throws InterruptedException{
    openBrowser();
	//driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
    driver.get(CONFIG.getProperty("testSiteName"));
	//login_valid allows us to login with valid credentials
    login_Valid();
	getObject("dashBoardLinkX").click();
	System.out.println("clicked on dashboard");
	getObject("rosterViewLinkX").click();
	System.out.println("clicked on RosterView");
	getObject("jobsLinkX").click();
	System.out.println("clicked on Jobs");
	//Clients
	getObject("clientsLinkx").click();
	System.out.println("clicked on Clients");
	//Candidates
	getObject("candLinkX").click();
	System.out.println("clicked on Candidates");
	//Applications
    getObject("appLinkX").click();
    System.out.println("clicked on Applications");
	//Assignments
	getObject("assignLinKX").click();
	System.out.println("clicked on Assignments");
	//TimeSheet
	getObject("timeSheetLinkX").click();
	System.out.println("clicked on TimeSheet");
	//Invoice
	getObject("invoiceLinkX").click();
	System.out.println("clicked on Invoice");
	//Payslips
	getObject("payslipLinkX").click();
	System.out.println("clicked on Payslip");
	//Reports
	getObject("reportsLinkX").click();
	System.out.println("clicked on Reports");
	//Settings
	getObject("settingLinkX").click();
	System.out.println("clicked on Settings");
	System.out.println("success");
	}

}
