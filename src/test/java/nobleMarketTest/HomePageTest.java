package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.noblemarket.LandingPage;
import common.BaseTest;

public class HomePageTest extends BaseTest {
	
	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("HomePageTest");
	}
	
	@Test
	public void verifyLogin_TC_Login_001() throws Exception
	{
		test=report.startTest("verifyLogin_TC_Login_001");
		parent.appendChild(test);
		Reporter.log("TC_Login_001");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().verifyUserLoggedInSuccessfully());
	}
}
