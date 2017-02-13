package TBR.Rough_Work;

import org.testng.annotations.Test;

import TBR.TestBase.TestBase;

public class IsElementPresentRough extends TestBase{
	
	@Test
	public void isElementPresentrough(){
		
		String a= "//*[@id='client-rows']/div/div/div/div[";

				String b = "]/table/thead/tr/td[1]";
				
				int i =1 ;
		while(isElementPresent(a+i+b)){
			
			
		}
	}

}
