package nobleMarketTest;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import common.BaseTest;
import pages.noblemarket.LandingPage;

public class LandingPageTest extends BaseTest {
	
	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("LandingPageTest");
	}

	@Test
	public void verifyLandingPage_TC_LandingPage_001() throws Exception
	{
		test=report.startTest("verifyLandingPage_TC_LandingPage_001");
		parent.appendChild(test);	
		LandingPage landingpage = new LandingPage(webBrowser).goTo();
		Assert.assertTrue(landingpage.verifyNobleMarketLogo());	
	}
}
