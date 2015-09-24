package TBR.TestUtil;

import java.util.Hashtable;

import org.testng.SkipException;

public class TestUtil {
	
	
	
	   /*to check whether the Test suite is runnable or not*/
	   /*
	   * @param xls
	   * @param name
	   * return isExecutable*/
	    public static boolean isSuiteRunnable(Xls_Reader xls, String name){
		boolean isExecutable = false;
		for(int i=2;i<=xls.getRowCount("");i++){
			if(xls.getCellData("sheetName", "colName", i).equalsIgnoreCase(name)){
				if(xls.getCellData("sheetName", "colName", i).equalsIgnoreCase("Y")){
					isExecutable = true;
				}
				else {
					isExecutable = false;
				}
			}
		}
	    return isExecutable; 
	    }
	    
	    /*to check the run mode of a test case is set to yes or no*/
		/*
		 * @param xls
		 * @param name
		 * @return isExecutable*/
			
		public static boolean isTestcaseRunnable(Xls_Reader xls, String name){
				boolean isExecutable = false;
				for(int i=2;i<=xls.getRowCount("sheetName");i++){
					if(xls.getCellData("sheetname", "TCID", i).equalsIgnoreCase(name)){
						if(xls.getCellData("sheetName", "Runmode", i).equalsIgnoreCase("Y")){
							isExecutable = true;
						}
						else{
						isExecutable = false;	
						}
					}
				}
		return isExecutable;	
		}
		
		/*get testdata from excel sheet in to two dimensional array*/
		/*
		 * @param xls
		 * @param testCaseName
		 * @return
		 */
		public static Object[][] getData(Xls_Reader xls, String testName){
			// return test data;
			// read test data from xls
			if(xls == null){
				// load the TestData sheet
				xls = new Xls_Reader(System.getProperty("user.dir")+"src//test//java//com//trial//xlsFile//TestTrialData.xlsx");
				}
			int rows=xls.getRowCount(testName)-1;
			if(rows <=0){
				Object[][] testData =new Object[1][0];
				return testData;
			 }
		    rows = xls.getRowCount(testName);  // 3
			int cols = xls.getColumnCount(testName);
			System.out.println("Test Name -- "+testName);
			System.out.println("total rows -- "+ rows);
			System.out.println("total cols -- "+cols);
			Object data[][] = new Object[rows-1][cols];
			
			for(int rowNum = 2 ; rowNum <= rows ; rowNum++){
				for(int colNum=0 ; colNum< cols; colNum++){
					data[rowNum-2][colNum]=xls.getCellData(testName, colNum, rowNum);
				}
		}
			
		return data;
			
		}
		

	    /*get the test data into Hash table*/
	    public static Object[][] getDataIntoHashTable(Xls_Reader xls, String testCaseName)
	  	{
	  		if (!xls.isSheetExist(testCaseName)) {
	  			xls = null;
	  			return new Object[1][0];
	  		}
	  		int rows = xls.getRowCount(testCaseName);
	  		System.out.println(rows);
	  		int cols = xls.getColumnCount(testCaseName);
	  		System.out.println(cols);

	  		Object[][] data = new Object[rows - 1][1];
	  		Hashtable<String, String> table = null;

	  		for (int rowNum = 2; rowNum <= rows; rowNum++) {
	  			table = new Hashtable<String, String>();
	  			for (int colNum = 0; colNum < cols - 1; colNum++) {
	  				System.out.println(xls.getCellData(testCaseName, colNum, 1)+"--"+xls.getCellData(testCaseName, colNum, rowNum));
	  				table.put(xls.getCellData(testCaseName, colNum, 1),
	  						xls.getCellData(testCaseName, colNum, rowNum));
	  	}
	  	data[rowNum - 2][0] = table;
	  			// System.out.println();
	  	}
	  	return data;
	  	}
	    
	    /*to update the result column in the datasheet*/
	     /**
	 	 * @param xls
	 	 * @param testCaseName
	 	 * @param rowNum
	 	 * @param result
	 	 */
	 	public static void reportDataSetResult(Xls_Reader xls, String testCaseName,
	 			int rowNum, String result) {
	 		xls.setCellData(testCaseName, "Results", rowNum, result);
	 	}
        
	 	/**
	 	 * @param xls
	 	 * @param testCaseName
	 	 */
	 	public static void skippingTheTest(Xls_Reader xls, String testCaseName) {
	 	TestUtil.reportDataSetResult(xls, "Test Cases",TestUtil.getTestCaseRowNum(xls, testCaseName), "SKIP");
	 	throw new SkipException("Skipping the test case " + testCaseName+ " as Runmode is set to NO");

	 	}
	 	
	 	 /**
		 * @param xls
		 * @param testCaseName
		 * @return
		 */
		public static int getTestCaseRowNum(Xls_Reader xls, String testCaseName) {
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
				String tcid = xls.getCellData("Test Cases", "TCID", i);
				if (tcid.equalsIgnoreCase(testCaseName)) {
					xls = null;
					return i;
				}
		}
		return -1;
		}

} 
