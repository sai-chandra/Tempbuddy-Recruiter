package TBR.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import TBR.TestUtil.ErrorUtil;
import TBR.TestUtil.Xls_Reader;

public class TestBase extends ParallelBaseClass {
	
	public static boolean isInitialized = false;
	public static boolean isBrowserOpened = false;
	public ExtentTest logger;
	public static Logger LOGS = null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static WebDriver driver = null;
	//public static WebDriver driver1 = null;
	public static Xls_Reader excel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\TBR\\Xlsdata_Files\\TestData.xlsx");
	public static Xls_Reader JobsExcel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\TBR\\Xlsdata_Files\\Jobs.xlsx");
	public static Xls_Reader ClientsExcel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\TBR\\Xlsdata_Files\\Clients.xlsx");
	public static Xls_Reader CandidateExcel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\TBR\\Xlsdata_Files\\Candidates.xlsx");
	public static Xls_Reader TimesheetsExcel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\test\\java\\TBR\\Xlsdata_Files\\Timesheets.xlsx");
	
	public void initialize() throws IOException{
		if(!isInitialized){
		//then initializing logs
			LOGS = Logger.getLogger("devpinoyLogger");
		
		//Now Initializing configuration properties file
		LOGS.debug("Loading property files");
		CONFIG = new Properties();
		FileInputStream fis = new FileInputStream(
		System.getProperty("user.dir")+ "//src//test//java//TBR//Properties//CONFIG.properties");
		CONFIG.load(fis);
		
		//Now Initializing or properties file
		OR = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//TBR//Properties//OR.properties");
		OR.load(fis);
		LOGS.debug("Loaded Property files successfully");
		
		isInitialized = true;
   }
}
	
	//for opening a browser
	public void openBrowser(){
		LOGS.debug("opening a browser");
		LOGS.debug(CONFIG.getProperty("browserName"));
		if(CONFIG.getProperty("browserName").equalsIgnoreCase("Mozilla")){
			FirefoxProfile profile = new FirefoxProfile();
			driver = new FirefoxDriver(profile);
			//String path = "C:\\MyFiles\\downloadsPdf";
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream;application/pdf");
			//profile.setPreference("browser.helperApps.alwaysAsk.force",false);
			//profile.setPreference("browser.download.manager.showWhenStarting",false);
			profile.setPreference("browser.download.folderList", 0);
			//profile.setPreference("browser.download.dir", path);
			/*profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("pref.downloads.disable_button.edit_actions", true);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/octet-stream,application/pdf");
			profile.setPreference("browser.download.manager.showWhenStarting",false);
			driver = new FirefoxDriver(profile);*/
			
		}
		else if(CONFIG.getProperty("browserName").equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Workspaces\\eclipse\\TempBuddy\\TempBuddyRecruiter\\src\\test\\java\\TBR\\Properties\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(CONFIG.getProperty("browserName").equalsIgnoreCase("IE")){
			driver = new InternetExplorerDriver();
		}
		isBrowserOpened = true;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		}
	
	
	//Opening a browser, takes to url and Logins
	public void browserUrl(){
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
	}
	
	     
	     //gets by class name
			public WebElement getObjectByClass(String className){
			return driver.findElement(By.className(OR.getProperty(className)));
			}
			
	     //gets xpath of the web element
			public WebElement getObject(String xpathKey){
			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
			}
			
		//gets Id of the element	
			public WebElement getObjectById(String elementId){
			return driver.findElement(By.id(OR.getProperty(elementId)));	
			}
			
		//gets the value by of Id	
			public String getObjectByIdValue(String elementId){
			String b = driver.findElement(By.id(OR.getProperty(elementId))).getAttribute("value");
			return b;
			}	
		//gets Css of the web element	
			public WebElement getObjectByCss(String Csskey){
			return driver.findElement(By.cssSelector(OR.getProperty(Csskey)));
			}
			
		//gets the name of the web element	
			public WebElement getObjectByName(String elementName){
			return driver.findElement(By.name(OR.getProperty(elementName)));
			}
			
		//gets the value of the web element	
			public String getObjectValue(String xpathKey){
			return driver.findElement(By.xpath(OR.getProperty(xpathKey))).getAttribute("value");
			}
		
			//gets the text of the web element	
			public String getObjectCssText(String csspath){
			return driver.findElement(By.cssSelector(OR.getProperty(csspath))).getText();
			}
			
		//gets the text of the web element	
			public String getObjectText(String xpathKey){
			return driver.findElement(By.xpath(OR.getProperty(xpathKey))).getText();
			}
	    
		//gets the text of the web element	
			public String getObjectTextId(String idKey){
			return driver.findElement(By.id(OR.getProperty(idKey))).getText();
			}			
	    //gets the value of the web element
			public String getObjectValueById(String elementId){
			return driver.findElement(By.id(OR.getProperty(elementId))).getAttribute("value");
			}
			
		//gets the name of the web element
			public String getObjectValueByName(String elementName) {
			return driver.findElement(By.name(OR.getProperty(elementName))).getAttribute("value");
			}
			
		//gets the value of the web element
			public String getObjectValueByXpath(String elementXpath) {
			return driver.findElement(By.xpath(OR.getProperty("elementXpath"))).getAttribute("value");
			}
			
		//gets the LinkText of the web element
			public WebElement getObjectByLinkText(String linkText){
			return driver.findElement(By.linkText(OR.getProperty(linkText)));	
			}
			
			
		
		//selects the option is a drop down by Id
			public String getSelectedOptionById(String elementId) {
				Select s = new Select(getObjectById(elementId));
				return s.getFirstSelectedOption().getText();
				}
			
			//selects by text xpath
			public void getSelectedByTextXpath(String elementXpath, String option){
				Select text = new Select(getObject(elementXpath));
				text.selectByVisibleText(option);
				//return text;
				}
			
			//selects by value xpath
			public void getSelectedByValueXpath(String elementXpath, String option){
				Select value = new Select(getObjectById(elementXpath));
				value.selectByValue(option);
				//return text;
				}
			
		//selects by text id
			public void getSelectedByText(String elementId, String option){
				Select text = new Select(getObjectById(elementId));
				text.selectByVisibleText(option);
				//return text;
				}
			
			//selects by value
			public void getSelectedByValue(String elementId, String option){
				Select value = new Select(getObjectById(elementId));
				value.selectByValue(option);
				//return text;
				}
			
			public String getSelectedDropdownValueByName(String nameKey) {
				WebElement element = driver.findElement(By.name(OR.getProperty(nameKey)));
				return new Select(element).getFirstSelectedOption().getText();
				}
				
				public void assertElementPresence(String xpathKey, String errMessage) {
				Assert.assertTrue(isElementPresent(xpathKey), errMessage);
				Assert.assertTrue(isElementDisplayed(xpathKey), errMessage);
				}
				
				//checks whether the element is present or not	
				public boolean isElementPresent1(String xpathKey){
				int count = driver.findElements(By.xpath((xpathKey))).size();
				if(count > 0){
				return true;
				}else {
				return false;
				}
				}
				
			//checks the expected and actual values are equal or not	
				public void assertValuesAreEqual(String actualXpathKey, String expectedValue) {
				Assert.assertEquals(getObjectText(actualXpathKey),OR.getProperty(expectedValue));
				}
			//checks whether the element is present or not	
				public boolean isElementPresent(String xpathKey){
				int count = driver.findElements(By.xpath(OR.getProperty(xpathKey))).size();
				if(count > 0){
				return true;
				}else {
				return false;
				}
				}
			//checks whether the element is displayed or not	
				public boolean isElementDisplayed(String xpathKey){
				return driver.findElement(By.xpath(OR.getProperty(xpathKey))).isDisplayed();
				}
			//clears the populate field and sends the given value	
				public void populateField(String xpathkey, String value){
					getObject(xpathkey).clear();
					getObject(xpathkey).sendKeys(value);
				}
				
		    //clears the populate field and sends the given value	
				public void populateFieldById(String idKey, String value){
					getObjectById(idKey).clear();
					getObjectById(idKey).sendKeys(value);
				}
				
				 //recruiter login with valid credentials
			    public void login_Kevin(){
			   // driver.get(CONFIG.getProperty("testSiteName"));
			    getObjectById("usernameId").sendKeys("kevintest");
			    getObjectById("passwordId").sendKeys("kevintest1");
			    getObjectById("logInId").click();
			    }
					
		    //recruiter login with valid credentials
			    public void login_Valid(){
			   // driver.get(CONFIG.getProperty("testSiteName"));
			    getObjectById("usernameId").sendKeys("Sai_C");
			    getObjectById("passwordId").sendKeys("qwerty");
			    getObjectById("logInId").click();
			    }
			    
			//recruiter login with valid credentials
			    public void login_Candidate(){
			    // driver.get(CONFIG.getProperty("testSiteName"));
			    getObjectById("usernameId").sendKeys("temp-buddy");
			    getObjectById("passwordId").sendKeys("qwerty1");
			    getObjectById("logInId").click();
			    }			
			    
			//compare two strings
			    public boolean compareStrings(String expectedVal, String actualVal){
			    try{
			    Assert.assertEquals(actualVal, expectedVal);
			    }catch (Throwable t){
			    ErrorUtil.addVerificationFailure(t);
			    LOGS.debug("String values does not match");
			    return false;
			    }
			    return true;
			    }
			    
			//drag and drop function build.dragAndDrop
			    public WebElement dragDrop(String drag, String drop){
			    	WebElement point1 = getObject(drag);
			    	WebElement point2 = getObject(drop);
			    	Actions build = new Actions(driver);
			    	build.dragAndDrop(point1, point2).perform();
					return null;
			    }
			//drag and drop function using clickAndHold, moveToElement & release with Lint Text
				  public WebElement dragDropClickMoveReleaseLinkText(String from, String to){
					  WebElement point1 = getObjectByLinkText(from);
					  WebElement point2 = getObjectById(to);
					  Actions build = new Actions(driver);
					  build.clickAndHold(point1).build().perform();
					  build.moveToElement(point2).build().perform();
					  build.release(point2).perform();
					  return null;
				  }
			    
			//drag and drop function using clickAndHold, moveToElement & release
			  public WebElement dragDropClickMoveRelease(String from, String to){
				  WebElement point1 = getObject(from);
				  WebElement point2 = getObjectById(to);
				  Actions build = new Actions(driver);
				  build.clickAndHold(point1).build().perform();
				  build.moveToElement(point2).build().perform();
				  build.release(point2).perform();
				  return null;
			  }
			
			//webdriver wait by xpath  
			  public void waitForElement(long waitTime, String elementXpath) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.visibilityOfElementLocated(By
									.xpath(OR.getProperty(elementXpath))));
				}
			 
			//webdriver wait by xpath till element to be clickable 
			  public void waitForElementClickable(long waitTime, String elementXpath) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.elementToBeClickable(By
									.xpath(OR.getProperty(elementXpath))));
				}
			 
			//webdriver wait by id till element to be clickable 
			  public void waitForElementClickableId(long waitTime, String elementId) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.elementToBeClickable(By
									.id(OR.getProperty(elementId))));
				}

			//webdriver wait by id 
			  public void waitForElementId(long waitTime, String elementId) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.visibilityOfElementLocated(By
									.id(OR.getProperty(elementId))));
				}
			  
			//webdriver wait by Css
			  public void waitForElementCss(long waitTime, String elementCss) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.visibilityOfElementLocated(By
									.cssSelector(OR.getProperty(elementCss))));
				}
			  
			//webdriver wait by Css till element to be clickable 
			  public void waitForElementClickableCss(long waitTime, String elementCss) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.elementToBeClickable(By
									.cssSelector(OR.getProperty(elementCss))));
				}
			
			  //webdriver wait by LinkText Clickable till element to be clickable 
			  public void waitForElementClickableLinkText(long waitTime, String elementLt) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.elementToBeClickable(By
									.linkText(OR.getProperty(elementLt))));
				}
			  
			  //webdriver wait by Lt till element to be clickable 
			  public void waitForElementClickableLinkTextVisible(long waitTime, String elementLt) {
					new WebDriverWait(driver, waitTime)
							.withTimeout(waitTime, TimeUnit.SECONDS)
							.pollingEvery(2, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class)
							.until(ExpectedConditions.visibilityOfElementLocated(By
									.linkText(OR.getProperty(elementLt))));
				}
			//explicit wait by linkText
		    public void explicitWait(String xpath){
			(new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(By.linkText(xpath)));
				}
		    //explicit wait by css
		    public void explicitWaitCss(String csspath){
		    (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(getObjectByCss(csspath)));
		    }
		    
		    //explicit wait by id
		    public void explicitWaitId(String id){
			(new WebDriverWait(driver, 40)).until(ExpectedConditions.elementToBeClickable(getObjectById(id)));
					}
		   
		    
		    //explicit wait by xpath
		    public void explicitWaitXpath(String xpath){
		    (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(getObject(xpath)));
		    }
		    
		    //scrolling down using Actions class
		    public void scrollDownAction(){
		    Actions action = new Actions(driver);
		    action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		    }
		    
		    //scrolling up using Actions class
		    public void scrollUpAction(){
		    Actions action = new Actions(driver);
		    action.keyDown(Keys.CONTROL).sendKeys(Keys.UP).perform();
		    }
		    

		   /* //explicit wait by xpath
		    public void explicitWaitXpathPageLoad(){
		    (new WebDriverWait(driver, 20)).until(ExpectedConditions.(getObject(xpath)));
		    }*/
		    
		        
		    
		     
		    	/*try{
		    	Assert.assertEquals(text, gettext);
		    	}catch(Throwable t){
		    	ErrorUtil.addVerificationFailure(t);
		    	System.out.println("the text does not match each other");
		    	}*/
		    	
			    
			//  comp  
			    public boolean match(String xpath, String message){
			    WebElement element = getObject(xpath);
			    String element1 = element.getText();
			    try{
			    Assert.assertEquals(message, element1);
			    }catch(Throwable t){
			    	LOGS.debug("not matching");
			    	System.out.println("not matching");
			    	return false;
			    }
			    return false;
				}
			    
			    //Assert.assertTrue will match the text/link and returns false if the test case is failed. 
				//it will run through all the assertions and returns false even if one assert is failed.
				public void assertTrue(String object, String text){
					try{
						Assert.assertTrue(getObject(object).getText().equals(text));
					}catch(Throwable t){
				ErrorUtil.addVerificationFailure(t);
				System.out.println("failed");
					}
					return;
					}
					
				
			    //waits until the visibility of the css selector element
				public void WebWait(String css){
				WebElement abc = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(css))));
				}
				
				
				//waits until the visibility of the Id selector element
				public void WebWaitId(String id){
				WebElement waitId = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(getObjectById(id)));
				}
			    
			    //To verify whether text is present or not
			    public boolean isTextPresent(String textTobeVerified){
			    	{
			    		try
			    		{
			    			if(getObject(textTobeVerified)!=null){
			    			return true;
			    				}
			    			}catch(Exception t)
			    			{
			    				return false;
			    			}
			    			return false;
			    				}
			    	}
		
	            //to get the attribute value by id
				public String hoursFixed(String idElement){
					String a = driver.findElement(By.id(OR.getProperty(idElement))).getAttribute("value");
					//System.out.println(a);
					return a;
					}
				
				//getTotal() method used for multiplying hours fixed and Billrate
				public String getTotal(String hours, String Bill){
				//the next line converts hours value to double which of String
				double pay = Double.parseDouble(hours);
				DecimalFormat df =new DecimalFormat("0.00");
				//sets the decimal value to 2 that is takes two digits after decimal point
				df.setMaximumFractionDigits(2);
				//the next line converts hours value to double which of String
				double pay1 = Double.parseDouble(Bill);
				double total = pay*pay1;
				// coverts the double value back to string and returns the value 
				return df.format(total).toString();
				}
				
				// Get Margin Method
				public String getMargin(String payRates, String billRates) {
					double pr = Double.parseDouble(payRates);
					double br = Double.parseDouble(billRates);
					DecimalFormat df = new DecimalFormat("0.00");
					df.setMaximumFractionDigits(2);
					//System.out.println(df.format(pr));
					//System.out.println(df.format(br));
					double profit = br-pr;
					//System.out.println(profit);
					//System.out.println(df.format(profit));
					double profitpercent = profit/pr*100;
					//System.out.println(df.format(profitpercent));
					return df.format(profitpercent).toString();
				}
				
				//method for checking the unassigned job before number +1 is equal to unassigned job after
				public void checkUnassignedJobIncrement(String unassignedJobsNumberBefore,String unassignedJobNumberAfter,int assignedJob){
				
					Integer n = Integer.valueOf(unassignedJobsNumberBefore);
					Integer add = n+assignedJob;//countUnassignJobsNumBefore+1;
					//System.out.println("the int value of unassignedJobNumberBefore added with 1 is = "+add);
					String after = String.valueOf(add);
					//System.out.println("converting Int to String, value of unassignedJobNumberBefore added with 1 is = "+add);
					System.out.println(after);
					/*if(!after.equals(unassignedJobNumbeforeAdd1)){
					ErrorUtil.addVerificationFailure(null);
				    System.out.println("fail");
					}
					else{
						System.out.println("success");
					}
				}		*/
				try{
					Assert.assertEquals(unassignedJobNumberAfter, after);
				}catch(Throwable t){
					ErrorUtil.addVerificationFailure(t);
					System.out.println("fail");
					}
				    System.out.println("success, unassigned job number is increased");
				
                 }
				
				//for String Split 
				public String stringSplit(String textMessage){
				String[] a;
			    a =textMessage.split("\\s+");
				for(int i =0;i<a.length;i++){
				System.out.println(a[i]);
				}
				//System.out.println(a[7]);
				return a[7];
				}
				
				//for addition of assignmentTotalValue +1 for checking whether the assignment is added or not successfully
				public void checkAssignmentIncrement(String assignmentNumBefore, String assignmentNumAfter){
				Integer n = Integer.valueOf(assignmentNumBefore);
				Integer add = n+1;
				System.out.println("the int value of assignmentNumBefore is added with 1 is" +add);
				String after = String.valueOf(add);
				System.out.println("converting Int to String, value of assignmentNumBefore added with 1 is = "+add);
				System.out.println(after);
				Assert.assertEquals(assignmentNumAfter, after);
				System.out.println("success");
				}
				
				//using select class
				public void selectOptionById(String idElement, String optionValue){
				//getObjectById(idElement).click();
				Select select = new Select(getObjectById(idElement));
				select.selectByVisibleText(optionValue);
				
				}
				
				//for hover and click using svg xpath element
				public void hoverClickSvg(String xpath) throws InterruptedException{
					WebElement svgObject = getObject(xpath);
					Actions hover = new Actions(driver);
                    hover.moveToElement(svgObject).build().perform();
                    Thread.sleep(4000);
                    getObject(xpath).click();				
                    }
				
			    //checks the Javascripts notification Job Operation Success
				public void JobOperationSuccess(){
			    waitForElement(10, "JobOperationSuccessSaveJobX");
			    String SuccessMessage = getObjectText("JobOperationSuccessSaveJobX");
			    System.out.println(SuccessMessage);
			    
			    Assert.assertEquals(SuccessMessage, "job\nOperation success");
			    System.out.println("success message matched and therefore Job is created successfully");
			    }
				
				//goes to the url
				public void goToUrl(String url){
					driver.navigate().to(url);
					}
				
				/*Match Candidate: looks for "ALL POSITIONS ARE NOW FILLED" success message and assertsEquals*/
				public void matchCandidateSuccessMessage(){
					 waitForElement(25, "matchCandidateSuccessMessageX");
					 String successMessage = getObjectText("matchCandidateSuccessMessageX");
					 LOGS.debug(successMessage);
					 System.out.println(successMessage);
					 Assert.assertEquals(successMessage, "ALL POSITIONS ARE NOW FILLED");
					 LOGS.debug("a candidate has been matched successfully");		
				}
				
				public void timeStamp(){
				DateFormat df = new SimpleDateFormat("yyyy_MMM_dd HH_mm_ss");
				Date d = new Date();
				String time = df.format(d);
				System.out.println(time);
				}
				
				public void addTimeStamps(){
				//int totalSeconds = ((hours*60)+ minutes) * 60 + seconds;
				}
				
				//Click xpath element using Actions class
				public void clickAction(String xpath){
				WebElement element = getObject(xpath);
				Actions action = new Actions(driver);
				action.moveToElement(element).click().perform();
				}
				
				//Click Id element using Actions class
				public void clickActionId(String Id){
				WebElement elementId = getObjectById(Id);
				Actions action = new Actions(driver);
				action.moveToElement(elementId).click().perform();
				}
				
				//Click Id element using Actions class
				public void clickActionCss(String css){
				WebElement elementCss = getObjectByCss(css);
				Actions action = new Actions(driver);
				action.moveToElement(elementCss).click().perform();
				}

			}