package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseTest;
import pages.noblemarket.LandingPage;

public class RFQPageTest extends BaseTest {
	
	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("RFQPageTest");
	}
	
	@Test
	public void verifyRfqPageTC_RFQ_001() throws InterruptedException, Exception
	{
		test=report.startTest("verifyRfqPageTC_RFQ_001");
		parent.appendChild(test);
		Reporter.log("TC_RFQ_001");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToRFQ().verifyRFQTradePage());
	}

	@Test
	public void verifyRfqCancelled_TC_RFQ_002() throws InterruptedException, Exception
	{
		test=report.startTest("verifyRfqCancelled_TC_RFQ_002");
		parent.appendChild(test);
		Reporter.log("TC_RFQ_002");
	    Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToRFQ().createRfqOrder().clickOnRfqBuy().verifyCancelledRfq());		
	}
	
	@Test
	public void verifyYourRfqPopulated_TC_RFQ_003() throws InterruptedException, Exception
	{
		test=report.startTest("verifyYourRfqPopulated_TC_RFQ_003");
		parent.appendChild(test);
		Reporter.log("TC_RFQ_003");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToRFQ().createRfqOrder().clickOnRfqBuy().verifyYourRfqPopulated());	
	}	
	
	@Test
	public void verifyRfqExpiredStatus_TC_RFQ_004() throws InterruptedException, Exception
	{
		test=report.startTest("verifyRfqExpiredStatus_TC_RFQ_004");
		parent.appendChild(test);
		Reporter.log("TC_RFQ_004");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToRFQ().createRfqOrder().requestTimeLeft().clickOnRfqBuy().verifyAlreadyExpiredRfqStatus());
	}
	
	@Test
	public void verifyRfqFromMarketLoadedTC_RFQ_005() throws InterruptedException, Exception
	{
		test=report.startTest("verifyRfqFromMarketLoadedTC_RFQ_005");
		parent.appendChild(test);
		Reporter.log("TC_RFQ_005");		
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToRFQ().createRfqOrder().clickOnRfqBuy().verifyRfqFromMarket());
		
	}
	
}
