package TBR.TestBase;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.testng.annotations.BeforeClass;

import TBR.TestUtil.ExtentManager;

import com.relevantcodes.extentreports.ExtentReports;

public abstract class ParallelBaseClass 
{
    protected ExtentReports report;
    protected final String filePath = "C:\\reports\\TestAutomationReport.html";
    
    @BeforeClass
    public void beforeClass() {
    	report = ExtentManager.getReporter(filePath);
    }
    
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}