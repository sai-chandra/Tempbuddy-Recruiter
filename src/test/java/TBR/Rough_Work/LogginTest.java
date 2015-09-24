package TBR.Rough_Work;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;



public class LogginTest {
	
@Test
	public void logsTest(){
		Logger LOGS = Logger.getLogger("devpinoyLogger");
		LOGS.debug("success");
	}

}
