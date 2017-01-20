package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseTest;
import pages.noblemarket.LandingPage;
import pages.noblemarket.NegotiatedPage;

public class NegotiatedPageTest extends BaseTest {

	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("NegotiatedPageTest");
	}
	
	@Test
	public void verifyNegotiatedTradePage_TC_NegTrade_001() throws Exception {
		test=report.startTest("verifyNegotiatedTradePage_TC_NegTrade_001");
		parent.appendChild(test);
		Reporter.log("TC_NegTrade_001");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().verifyNegTradePage());
	}
	
	@Test
	public void verifyNegotiatedTradeBuyOrderPopulated_TC_NegTrade_002() throws Exception {
		test=report.startTest("verifyNegotiatedTradeBuyOrderPopulated_TC_NegTrade_002");
		parent.appendChild(test);
		Reporter.log("TC_NegTrade_002");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().verifyNegTradeOrderBuy());
	}
	
	@Test
	public void verifyNegotiatedTradeSellOrderPopulated_TC_NegTrade_003() throws Exception {
		test=report.startTest("verifyNegotiatedTradeSellOrderPopulated_TC_NegTrade_003");
		parent.appendChild(test);
		Reporter.log("TC_NegTrade_003");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().verifyNegTradeOrderSell());
	}

	@Test
	public void verifyCounterPartyOrderPopulated_TC_NegTrade_004() throws Exception
	{
		test=report.startTest("verifyCounterPartyOrderPopulated_TC_NegTrade_004");
		parent.appendChild(test);
		Reporter.log("TC_NegTrade_004");
		new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().createNegBuyOrder().logOut();
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentialsForCounterParty().navigateToNegTradeEntry().verifyCounterPartyOrder());	
	}
	
	@Test
	public void verifyCounterPartyDeclinedOrder_TC_NegTrade_005() throws InterruptedException, Exception
	{
		test=report.startTest("verifyCounterPartyDeclinedOrder_TC_NegTrade_005");
		parent.appendChild(test);
		Reporter.log("TC_NegTrade_005");
		String negOrdId=new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().createNegBuyOrder().getLatestNegOrderId();
		new NegotiatedPage(webBrowser).logOut();
		new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentialsForCounterParty().navigateToNegTradeEntry().clickOnDecline().logOut();		
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().verifyDeclinedOrderDeleted(negOrdId));
	}
	
	@Test
	public void verifyCounterPartyAcceptedOrder_TC_NegTrade_006() throws InterruptedException, Exception
	{
		test=report.startTest("verifyCounterPartyAcceptedOrder_TC_NegTrade_006");
		parent.appendChild(test);
		Reporter.log("TC_NegTrade_006");
		new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().createNegBuyOrder().logOut();
		new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentialsForCounterParty().navigateToNegTradeEntry().clickOnAccept().logOut();		
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToOrderBlotter().verifyStatusOfNegOrder());
	}
	
	@Test
	public void verifyInitiatorCancelsOrder_TC_NegTrade_007() throws InterruptedException, Exception
	{
		test=report.startTest("verifyInitiatorCancelsOrder_TC_NegTrade_007");
		parent.appendChild(test);
		Reporter.log("TC_NegTrade_007");
		new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToNegTradeEntry().createNegBuyOrder().cancelNegOrderByInitiator().logOut();
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToOrderBlotter().verifyCancelStatusNegOrder());
	}

}
