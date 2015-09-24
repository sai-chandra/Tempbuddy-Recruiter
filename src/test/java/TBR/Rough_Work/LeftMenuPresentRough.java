package TBR.Rough_Work;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TBR.FunctionalTestcases.FunctionalSuiteBase;

public class LeftMenuPresentRough extends FunctionalSuiteBase{
	
	@Test
	public void leftSideMenu() throws InterruptedException{
    openBrowser();
    driver.get(CONFIG.getProperty("testSiteName"));
	//login_valid allows us to login with valid credentials
    login_Valid();
   /* match("dashBoardTextX", "Dashboard1");
    System.out.println("success db");
    match("jobsTextX", "Jobs");
    System.out.println("gasfgfsg");*/
    
  
    //String x =  getObject("searchX").getAttribute("placeholder");
   //String x = getObject("searchX").getAttribute("placeholder");
    
  //String a = x.getAttribute("placeholder");
   // System.out.println(x);
   
   
 /*  Assert.assertTrue(getObject("searchX").getText().equals("Search..."));
   System.out.println("True");
   Assert.assertTrue(getObject("dashBoardTextX").getText().equals("Dashboard"));
   System.out.println("True");
   Assert.assertTrue(getObject("rosterViewLinkX").getText().equals("Roster View"));
   System.out.println("True");
   Assert.assertTrue(getObject("jobsLinkX").getText().equals("Jobs"));
   System.out.println("True");
   Assert.assertTrue(getObject("clientsTextX").getText().equals("Clients"));
   System.out.println("True");
   Assert.assertTrue(getObject("candTextX").getText().equals("Candidates"));
   System.out.println("True");
   Assert.assertTrue(getObject("appTextX").getText().equals("Applications"));
   System.out.println("True");
   Assert.assertTrue(getObject("assignTextX").getText().equals("Assignments"));
   System.out.println("True");
   Assert.assertTrue(getObject("timeSheetTextX").getText().equals("TimeSheet"));
   System.out.println("True");
   Assert.assertTrue(getObject("invocieTextX").getText().equals("Invoice"));
   System.out.println("True");
   Assert.assertTrue(getObject("payslipTextX").getText().equals("PaySlip"));
   System.out.println("True");
   Assert.assertTrue(getObject("reportsTextX").getText().equals("Reports"));
   System.out.println("True");
   Assert.assertTrue(getObject("settingTextX").getText().equals("Settings"));
   System.out.println("True");*/
   /* }
   if(driver.findElement(By.xpath("dashBoardTextX")).isDisplayed()){
	   System.out.println();*/
	   
	   
   }
}