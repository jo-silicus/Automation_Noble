package nobleMarketTest;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.external.JenkinsPage;
import pages.noblemarket.LandingPage;
import pages.noblemarket.OrderBlotterPage;
import pages.noblemarket.OrderEntryPage;
import common.BaseBlotterClass;
import common.BaseTest;
import core.CommonAction;


public class OrderBlotterTest extends BaseBlotterClass {
	
	@DataProvider
	public static Object[][] OrderDetails() throws IOException
	{
		CommonAction commonAction = new CommonAction();
		commonAction.getAllRecordsFromResponse();
		 Object[][] iteration = new Object [commonAction.getClorId()][8];
		 int j = 0;
		for(int i=0; i<commonAction.getClorId(); i++)
		{
			iteration[i][0] = commonAction.getAllRecordsFromResponse().get(j);
			iteration[i][1] = commonAction.getAllRecordsFromResponse().get(j+1);
			iteration[i][2] = commonAction.getAllRecordsFromResponse().get(j+2);
			iteration[i][3] = commonAction.getAllRecordsFromResponse().get(j+3);
			iteration[i][4] = commonAction.getAllRecordsFromResponse().get(j+4);
			iteration[i][5] = commonAction.getAllRecordsFromResponse().get(j+5);
			iteration[i][6] = commonAction.getAllRecordsFromResponse().get(j+6);
			iteration[i][7] = commonAction.getAllRecordsFromResponse().get(j+7);
			j+=8;
		}
		return iteration;
	}
	
	@Test
	public void verifyOrderBlotter_TC_OrderBlotter_001() throws Exception
	{
		Reporter.log("TC_OrderBlotter_001");
		Assert.assertTrue(new OrderBlotterPage(webBrowser).startJenkinsBuild());
		//new JenkinsPage(webBrowser).goTo().enterCredentials().startLoadSimulator().navigateToWorkspaceOfJob().navigateToRequest().readHTMLRequestAndSaveIntoTextFile().pageBack().navigateToResponse().readHTMLResponseAndSaveIntoTextFile();
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToOrderBlotter().clickOnOrderIdForSortData());
	}
	
	@Test(dataProvider="OrderDetails",dependsOnMethods = {"verifyOrderBlotter_TC_OrderBlotter_001"})
	public void verifyOrderBlotterPage_TC_OrderBlotter_002(String ClorId, String ExchId, String account, String ordertype, String instrument, String quantity, String price, String buyorsell) throws Exception
	{
		Reporter.log("TC_OrderBlotter_002");
		Assert.assertTrue(new OrderBlotterPage(webBrowser).setAllValue().searchAndVerifyForExchangeId(ClorId, ExchId, account,ordertype, instrument, quantity, price, buyorsell));
	}
	
	/*@Test(dataProvider="OrderDetails",dependsOnMethods = {"verifyOrderBlotter"})
	public void verifyOrderBlotterPage(String ClorId, String ExchId, String account, String ordertype, String instrument, String quantity, String price, String buyorsell) throws Exception
	{
		Reporter.log("TC_OrderBlotter_002");
		System.out.println(ClorId);
		Assert.assertTrue(new OrderBlotterPage(webBrowser).setAllValue().searchAndVerifyForExchangeId(ClorId, ExchId, account,ordertype, instrument, quantity, price, buyorsell));
	}*/
	
}
