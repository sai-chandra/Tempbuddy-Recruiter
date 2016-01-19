package TBR.TestUtil;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenShot {
	
	public static String captureScreenShot(WebDriver driver, String screenshotName){
		
		try
		{
			TakesScreenshot ts =(TakesScreenshot)driver;
			
			File source = ts.getScreenshotAs(OutputType.FILE);
			String mainPath = System.getProperty("user.dir");
			String path = mainPath+"./Screenshots/"+screenshotName+".png";
			FileUtils.copyFile(source, new File(path));
			System.out.println("Screenshot taken");
			return path;
		}
		catch(Exception e)
		{
			System.out.println("Exception while taking screenshot "+e.getMessage());
			return e.getMessage();
		}
		
	}

}
