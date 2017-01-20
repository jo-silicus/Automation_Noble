package common;

import java.io.File;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

import core.Browser;
import utilities.MediaWikiTest;
import utilities.PropertyFile;


public class BaseTest {

	 public Browser webBrowser;
	 protected ExtentReports report;
	 public  ExtentTest parent,test ;
	    
	 final String filePath = ".\\target\\Extent.html";

	@BeforeSuite
	@Parameters("propertyfileName")
	public void getParameters(String propertyfileName) throws Exception 
	{

		String[] files = propertyfileName.split(",");
		for (String fileName : files) {
			if (fileName.contains("properties")) {
				new PropertyFile(fileName).getData();
			}
		}	
		
		 File f = new File((System.getProperty("user.dir")+"/target/Extent.html"));
		  if(f.exists()){
		   f.delete();
		  }
	}
	
	@BeforeClass
	public void BeforeclassMethod()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"/target/Extent.html",false,NetworkMode.ONLINE);
	}
	
	
	@BeforeMethod
	@Parameters({"browser", "environment"})
	public void setUp(String browser, String environment) throws Exception {
		webBrowser = new Browser(browser, environment);
		
	}
	

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, "Test Failed ::"  + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "Test skipped ::" + result.getThrowable());
        } else {
            test.log(LogStatus.PASS, "Test passed !!");
        }
		test.getTest().setStartedTime(new Date(result.getStartMillis()));
		 test.getTest().setEndedTime(new Date(result.getEndMillis()));
		webBrowser.getWebDriver().close();
		webBrowser.getWebDriver().quit();
		report.endTest(test);
	}
	
	
	
	@AfterClass
	public void afterClassMethod()
	{		
		  report.endTest(parent);
		  report.flush();
		  report.close();
	}
	
	@AfterSuite
	@Parameters({"browser", "environment"})
	public void updateResultOnWiki(String browser, String environment) throws Exception
	{
		//new MediaWikiTest().resultUpdate();
		/*setUp(browser, environment);
		Listener listener = new Listener();
		System.out.println(listener.getResultList());
		MediaWikiPage mediaWiki = new MediaWikiPage(webBrowser);
		mediaWiki.goTo().loginIntoMediaWiki().NavigateToDocumentationPage(listener.getResultList());
		tearDown();*/
	}
}
