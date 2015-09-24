package TBR.FunctionalTestcases;

import org.testng.annotations.Test;

public class GeneralSearch extends FunctionalSuiteBase{
	
	@Test
	public void generalSearch(){
	openBrowser();
	driver.get(CONFIG.getProperty("testSiteName"));
	login_Valid();
	getObject("searchX").sendKeys("abcd");
	System.out.println("searched for abcd");
	assertTrue("searchMessInvalid", "There is no results matching the search");
	System.out.println("if messages match, I will be visible");
	getObject("searchX").clear();
	System.out.println("cleared search field");
	getObject("searchX").sendKeys("job");
	System.out.println("entered job");
	getObject("searchJPartialPopulate").click();
	System.out.println("clicked and should stay on jobs page");
	
	
	}

}
