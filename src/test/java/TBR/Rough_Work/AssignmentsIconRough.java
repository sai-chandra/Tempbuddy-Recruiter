package TBR.Rough_Work;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import TBR.TestBase.TestBase;
import TBR.TestUtil.ErrorUtil;

public class AssignmentsIconRough extends TestBase{
    @Test
	public void assignmentsIconRough() throws IOException, InterruptedException{
      initialize();
   	  openBrowser();
   	  driver.get(CONFIG.getProperty("testSiteName"));
   	  login_Valid();
   try{	  
   	hoverClickSvg("assignmentIconOpenBlueSvgX");
    Thread.sleep(5000);
    String a = getObjectText("assignmentIconOpenTextSvgX");
    System.out.println(a);
    System.out.println("success for open Svg");
   }catch(Throwable t){
		ErrorUtil.addVerificationFailure(t);
		System.out.println("failed");
			}
   try{
	hoverClickSvg("assignmentIconStartSoonSvgX");
    Thread.sleep(5000);
    String b = getObjectText("assignmentIconStartSoonTextSvg");
    System.out.println(b);
    System.out.println("success for Start soon Svg");
   }catch(Throwable t){
		ErrorUtil.addVerificationFailure(t);
		System.out.println("failed");
			}
   try{
	hoverClickSvg("assignmentIconEndSoonSvgX");
    Thread.sleep(5000);
    String c = getObjectText("assignmentIconEndSoonTextSvgX");
    System.out.println(c);
    System.out.println("success for End Soon Svg");
    }catch(Throwable t){
		ErrorUtil.addVerificationFailure(t);
		System.out.println("failed");
			}
    
    /* WebElement elementToHover = driver.findElement(By.xpath("//*[@id='donut-assig']/*[name()='svg']/*[name()='path'][2]"));
     Actions hover = new Actions(driver);
     hover.moveToElement(elementToHover);
     hover.build().perform();
     Thread.sleep(5000);
     driver.findElement(By.xpath("//*[@id='donut-assig']/*[name()='svg']/*[name()='path'][2]")).click();
     Thread.sleep(5000);
   	  WebElement svgObj = driver.findElement(By.xpath("//*[@id='donut-assig']/*[name()='svg']/*[name()='path'][4]"));
   	Actions builder = new Actions(driver);
   	builder.click(svgObj).build().perform();
    WebElement elementToHover1 = driver.findElement(By.xpath("//*[@id='donut-assig']/svg/path[4]"));
    hover1.moveToElement(elementToHover1);
    hover1.build().perform();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//*[@id='donut-assig']/*[name()='svg']/*[name()='path'][4]")).click();
    Thread.sleep(5000);
    WebElement elementToHover2 = driver.findElement(By.xpath("//*[@id='donut-assig']/*[name()='svg']/*[name()='path'][6]"));
    Actions hover2 = new Actions(driver);
    hover2.moveToElement(elementToHover2);
    hover2.build().perform();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//*[@id='donut-assig']/*[name()='svg']/*[name()='path'][6]")).click()*/;
   	
	}
}
