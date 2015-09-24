package TBR.FunctionalTestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import TBR.TestUtil.ErrorUtil;

public class AssignmentsIcon extends FunctionalSuiteBase{
	
	 @Test
		public void assignmentsIcon() throws IOException, InterruptedException{
	      initialize();
	   	  openBrowser();
	   	  driver.get(CONFIG.getProperty("testSiteName"));
	   	  login_Valid();
	   try{	  
	   	hoverClickSvg("assignmentIconOpenBlueSvgX");
	    Thread.sleep(5000);
	    String assignmentsOpen = getObjectText("assignmentIconOpenTextSvgX");
	    System.out.println("no of assignments open are "+assignmentsOpen);
	    System.out.println("success for open Svg");
	   }catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			System.out.println("failed");
				}
	   try{
		hoverClickSvg("assignmentIconStartSoonSvgX");
	    Thread.sleep(5000);
	    String assignmentStartSoon = getObjectText("assignmentIconStartSoonTextSvgX");
	    System.out.println("no of assignments starts soon are "+assignmentStartSoon);
	    System.out.println("success for Start soon Svg");
	   }catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			System.out.println("failed");
				}
	   try{
		hoverClickSvg("assignmentIconEndSoonSvgX");
	    Thread.sleep(5000);
	    String assignmentEndSoon = getObjectText("assignmentIconEndSoonTextSvgX");
	    System.out.println("no of assignments end soon are "+assignmentEndSoon);
	    System.out.println("success for End Soon Svg");
	    }catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
			System.out.println("failed");
				}

}
}