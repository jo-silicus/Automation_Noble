package common;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import utilities.PropertyFile;
import core.Browser;

public class BaseBlotterClass {

	public Browser webBrowser;

	@BeforeSuite
	@Parameters("propertyfileName")
	public void getParameters(String propertyfileName) throws Exception {

		String[] files = propertyfileName.split(",");
		for (String fileName : files) {
			if (fileName.contains("properties")) {
				new PropertyFile(fileName).getData();
			}
		}
	}
	@BeforeClass
	@Parameters({"browser", "environment"})
	public void setUp(String browser, String environment) throws Exception {
		webBrowser = new Browser(browser, environment);
	}

	@AfterClass
	public void tearDown() throws Exception {
		webBrowser.getWebDriver().close();
		webBrowser.getWebDriver().quit();
	}
}

