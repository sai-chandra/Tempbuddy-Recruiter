package TBR.Regression_Testcases;

import org.testng.annotations.Test;

public class CheckBranding extends RegressionSuiteBase{
	
	@Test
	public void checkBrading() throws InterruptedException{
		
		openBrowser();
		driver.get(CONFIG.getProperty("testSiteName"));
		login_Valid();
	
        getObject("settingLinkX").click();
        getObject("settingsBrandingX").click();
        getObject("brandingsTemplateX").click();
        /* getObjectById("candidateEmailTemplateAreaId").click();*/
    
        /*Now on Assign Candidate Template- Mandatory fields*/
        String AssignMandate = getObjectText("mandatoryAssignCandidateX");
        System.out.println("Mandatory fields for Assign Candidate Template are: "+AssignMandate);
        getObject("candNameTemplateX").click();
       
        /*Now checking whether the mandatory field is present in the text area or not*/
    	String b = getObjectById("AssignCandidateEmailTemplateAreaId").getText();
    	//System.out.println(b.contains("[[candName]]k"));
    	
    	if((b.contains("[[candName]]"))){
    		System.out.println("Successfull! mandatory tags are used properly");
    		LOGS.debug("Successfull! mandatory tags are used properly");
    	}
    	else{
    		System.out.println("not successfull! mandatory tags not matced and are not used properly");
    		LOGS.debug("not successfull! mandatory tags not matced and are not used properly");
    	}
    	//getObject("candNameTemplateX").click();
    	}
      
       
     /* String a = getObject("subpartCandidateEmailTemplateX").getText();
      System.out.println(a);
      */
     
      
    	   
       
        
		
	}


