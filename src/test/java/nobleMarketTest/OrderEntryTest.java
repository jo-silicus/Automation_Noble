package nobleMarketTest;

import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.noblemarket.LandingPage;
import pages.noblemarket.OrderEntryPage;
import common.BaseTest;

public class OrderEntryTest extends BaseTest {
	
	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("OrderEntryTest");
	}
	
	@Test
	public void verifyOrderBlotterBuyDatafromOrderEntry_TC_TrEntSimple_001_To_TC_TrEntSimple_014() throws Exception {
		test=report.startTest("verifyOrderBlotterBuyDatafromOrderEntry_TC_TrEntSimple_001_To_TC_TrEntSimple_014");
		parent.appendChild(test);
		Reporter.log("TC_TrEntSimple_001");
		Reporter.log("TC_TrEntSimple_002");
		Reporter.log("TC_TrEntSimple_003");
		Reporter.log("TC_TrEntSimple_004");
		List<String> orderIdData = new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToOrderEntry().fillPriceAndAmountlimit();		
		Assert.assertTrue(new OrderEntryPage(webBrowser).navigateOrderBlotter().arrangeOrdersDesc().setAllValue().verifyOrderEntryDataGetMatchedSuccessfully(orderIdData));
	}

}
