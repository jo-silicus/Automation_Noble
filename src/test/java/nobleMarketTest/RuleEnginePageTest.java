package nobleMarketTest;

import java.io.FileReader;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.BaseTest;
import pages.noblemarket.AdminPage;
import pages.noblemarket.RuleEnginePage;
import pages.noblemarket.TradeSettlementPage;
import utilities.PropertyDictionary;

public class RuleEnginePageTest extends BaseTest {

	@BeforeClass
	public void beforeClassTestLevel() {
		parent = report.startTest("RuleEnginePageTest");
	}
	/*
	 * @DataProvider(name = "ruleEngineData") public static Object[][]
	 * ruleEngineData() { return new Object[][] {{"acquirerName1","true","100"},
	 * {"acquirerName2","False","200"}}; }
	 * 
	 * @Test public void navigateToRuleEnginePage() throws Exception { new
	 * AdminPage(webBrowser).goTo().logIns(); new
	 * RuleEnginePage(webBrowser).navigateToRuleEnginePage(); }
	 * 
	 * @Test(dataProvider="ruleEngineData",
	 * dependsOnMethods="navigateToRuleEnginePage") public void
	 * verifyRuleTypeAsAcquirerName(String attributeValue, String condition,
	 * String value) throws Exception { new
	 * AdminPage(webBrowser).goTo().logIns(); new
	 * RuleEnginePage(webBrowser).navigateToRuleEnginePage(); new
	 * RuleEnginePage(webBrowser).clickOnAdd().addingTheNewRule(attributeValue,
	 * condition, value); }
	 */

	@DataProvider(name = "dataProviderForAcquirerName")
	public static Object[][] dataProviderForAcquirerName() {
		return new Object[][] { { "equals", "A1QA", "Quantity Fee" }, { "equals", "A1QA", "Value Fee" },
				{ "equals", "A1QA", "Flat Fee" }, { "not equal", "A1QA", "Quantity Fee" },
				{ "not equal", "A1QA", "Value Fee" }, { "not equal", "A1QA", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForAcquirerName")
	public void verifyRuleTypeAsAcquirerName(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsAcquirerName");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRuleForTradeType("trade type",
				"equals", "FXTrade", "Quantity Fee");
		ruleEnginePage.addingTheNewRule("acquirer name", condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForCounterParty")
	public static Object[][] dataProviderForCounterParty() {
		return new Object[][] { { "equals", "Jose Created Test Company", "Quantity Fee" },
				{ "equals", "Jose Created Test Company", "Value Fee" },
				{ "equals", "Jose Created Test Company", "Flat Fee" },
				{ "not equal", "Jose Created Test Company", "Quantity Fee" },
				{ "not equal", "Jose Created Test Company", "Value Fee" },
				{ "not equal", "Jose Created Test Company", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForCounterParty")
	public void verifyRuleTypeAsCounterParty(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsCounterParty");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("counterparty name",
				condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForCountry")
	public static Object[][] dataProviderForCountry() {
		return new Object[][] { { "equals", "United States of America", "Quantity Fee" },
				{ "equals", "United States of America", "Value Fee" },
				{ "equals", "United States of America", "Flat Fee" },
				{ "not equal", "United States of America", "Quantity Fee" },
				{ "not equal", "United States of America", "Value Fee" },
				{ "not equal", "United States of America", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForCountry")
	public void verifyRuleTypeAsCountry(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsCountry");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("country", condition,
				value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForTradeType")
	public static Object[][] dataProviderForTradeType() {
		return new Object[][] { { "equals", "FXTrade", "Quantity Fee" }, { "equals", "FXTrade", "Value Fee" },
				{ "equals", "FXTrade", "Flat Fee" }, { "not equal", "FXTrade", "Quantity Fee" },
				{ "not equal", "FXTrade", "Value Fee" }, { "not equal", "FXTrade", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForTradeType")
	public void verifyRuleTypeAsTradeType(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsTradeType");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRuleForTradeType("trade type",
				condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.verifyRuleEngineName(param));

	}

	@DataProvider(name = "dataProviderForCurrancyPair")
	public static Object[][] dataProviderForCurrancyPair() {
		return new Object[][] { { "equals", "USD", "Quantity Fee" }, { "equals", "USD", "Value Fee" },
				{ "equals", "USD", "Flat Fee" }, { "not equal", "USD", "Quantity Fee" },
				{ "not equal", "USD", "Value Fee" }, { "not equal", "USD", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForCurrancyPair")
	public void verifyRuleTypeAsCurrancyPair(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsCurrancyPair");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("currency pair",
				condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForUnderlying")
	public static Object[][] dataProviderForUnderlying() {
		return new Object[][] { { "equals", "XBT/USD", "Quantity Fee" }, { "equals", "XBT/USD", "Value Fee" },
				{ "equals", "XBT/USD", "Flat Fee" }, { "not equal", "XBT/USD", "Quantity Fee" },
				{ "not equal", "XBT/USD", "Value Fee" }, { "not equal", "XBT/USD", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForUnderlying")
	public void verifyRuleTypeAsUnderlying(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsUnderlying");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("underlying", condition,
				value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForMarketName")
	public static Object[][] dataProviderForMarketName() {
		return new Object[][] { { "equals", "Ace Market", "Quantity Fee" }, { "equals", "Ace Market", "Value Fee" },
				{ "equals", "Ace Market", "Flat Fee" }, { "not equal", "Ace Market", "Quantity Fee" },
				{ "not equal", "Ace Market", "Value Fee" }, { "not equal", "Ace Market", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForMarketName")
	public void verifyRuleTypeAsMarketName(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsMarketName");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("market name", condition,
				value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForSalesperson")
	public static Object[][] dataProviderForSalesperson() {
		return new Object[][] { { "equals", "Andy Admin", "Quantity Fee" }, { "equals", "Andy Admin", "Value Fee" },
				{ "equals", "Andy Admin", "Flat Fee" }, { "not equal", "Andy Admin", "Quantity Fee" },
				{ "not equal", "Andy Admin", "Value Fee" }, { "not equal", "Andy Admin", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForSalesperson")
	public void verifyRuleTypeAsSalesperson(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsSalesperson");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("sales person",
				condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForPortfolioName")
	public static Object[][] dataProviderForPortfolioName() {
		return new Object[][] { { "equals", "Test_Portfolio", "Quantity Fee" },
				{ "equals", "Test_Portfolio", "Value Fee" }, { "equals", "Test_Portfolio", "Flat Fee" },
				{ "not equal", "Test_Portfolio", "Quantity Fee" }, { "not equal", "Test_Portfolio", "Value Fee" },
				{ "not equal", "Test_Portfolio", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForPortfolioName")
	public void verifyRuleTypeAsPortfolioName(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsPortfolioName");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("portfolio name",
				condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForBuySell")
	public static Object[][] dataProviderForBuySell() {
		return new Object[][] { { "equals", "Buy", "Quantity Fee" }, { "equals", "Buy", "Value Fee" },
				{ "equals", "Buy", "Flat Fee" }, { "not equal", "Buy", "Quantity Fee" },
				{ "not equal", "Buy", "Value Fee" }, { "not equal", "Buy", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForBuySell")
	public void verifyRuleTypeAsBuySell(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsBuySell");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("buy sell", condition,
				value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForPXFactorFuturesOnly")
	public static Object[][] dataProviderForPXFactorFuturesOnly() {
		return new Object[][] { { "equals", "100", "Quantity Fee" }, { "equals", "100", "Value Fee" },
				{ "equals", "100", "Flat Fee" }, { "not equal", "100", "Quantity Fee" },
				{ "not equal", "100", "Value Fee" }, { "not equal", "100", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForPXFactorFuturesOnly")
	public void verifyRuleTypeAsPXFactorFuturesOnly(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsPXFactorFuturesOnly");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd()
				.addingTheNewRule("px factor (futures only)", condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForAccount")
	public static Object[][] dataProviderForAccount() {
		return new Object[][] { { "equals", "A1QA_Settlement", "Quantity Fee" },
				{ "equals", "A1QA_Settlement", "Value Fee" }, { "equals", "A1QA_Settlement", "Flat Fee" },
				{ "not equal", "A1QA_Settlement", "Quantity Fee" }, { "not equal", "A1QA_Settlement", "Value Fee" },
				{ "not equal", "A1QA_Settlement", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForAccount")
	public void verifyRuleTypeAsAccount(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsAccount");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("account", condition,
				value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForSource")
	public static Object[][] dataProviderForSource() {
		return new Object[][] { { "equals", "Test_Source", "Quantity Fee" }, { "equals", "Test_Source", "Value Fee" },
				{ "equals", "Test_Source", "Flat Fee" }, { "not equal", "Test_Source", "Quantity Fee" },
				{ "not equal", "Test_Source", "Value Fee" }, { "not equal", "Test_Source", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForSource")
	public void verifyRuleTypeAsSource(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsSource");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("source", condition,
				value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForStrategy")
	public static Object[][] dataProviderForStrategy() {
		return new Object[][] { { "equals", "Test_Strategy", "Quantity Fee" },
				{ "equals", "Test_Strategy", "Value Fee" }, { "equals", "Test_Strategy", "Flat Fee" },
				{ "not equal", "Test_Strategy", "Quantity Fee" }, { "not equal", "Test_Strategy", "Value Fee" },
				{ "not equal", "Test_Strategy", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForStrategy")
	public void verifyRuleTypeAsStrategy(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsStrategy");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("strategy", condition,
				value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@DataProvider(name = "dataProviderForMirrorTrades")
	public static Object[][] dataProviderForMirrorTrades() {
		return new Object[][] { { "equals", "Test_Strategy", "Quantity Fee" },
				{ "equals", "Test_Strategy", "Value Fee" }, { "equals", "Test_Strategy", "Flat Fee" },
				{ "not equal", "Test_Strategy", "Quantity Fee" }, { "not equal", "Test_Strategy", "Value Fee" },
				{ "not equal", "Test_Strategy", "Flat Fee" } };
	}

	@Test(dataProvider = "dataProviderForMirrorTrades")
	public void verifyRuleTypeAsMirrorTrades(String condition, String value, String feeType) throws Exception {
		test = report.startTest("verifyRuleTypeAsMirrorTrades");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		RuleEnginePage ruleEnginePage = new RuleEnginePage(webBrowser);
		String param = ruleEnginePage.navigateToRuleEnginePage().clickOnAdd().addingTheNewRule("mirror trades",
				condition, value, feeType);
		Assert.assertTrue(ruleEnginePage.navigateToList().verifyRuleEngineName(param));
	}

	@Test
	public void verifyTradeAndSettlement() throws Exception {
		test = report.startTest("verifyTradeAndSettlement");
		parent.appendChild(test);
		new AdminPage(webBrowser).goTo().logIns();
		TradeSettlementPage tradeSettlementPage = new TradeSettlementPage(webBrowser);
		tradeSettlementPage.verifyRecentlyAddItemIsOnList(tradeSettlementPage.AddFXTrade());
	}

	/* */
}
