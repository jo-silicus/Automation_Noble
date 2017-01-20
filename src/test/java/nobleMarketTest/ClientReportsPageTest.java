package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.BaseTest;
import pages.noblemarket.HomePage;
import pages.noblemarket.LandingPage;

public class ClientReportsPageTest extends BaseTest {
	
	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("ClientReportsPageTest");
	}
	
	@DataProvider(name = "type")
	   public static Object[][] primeNumbers() {
	      return new Object[][] {{0,"html"},{1,"json"},{2,"txt"},{3,"csv"},{4,"xlsx"}};
	   }
	
	@Test
	public void verifyClientReportPage_TC_ClientReports_001() throws Exception
	{
		test=report.startTest("verifyClientReportPage");
		parent.appendChild(test);
		Reporter.log("TC_ClientReports_001");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToClientReport().verifyClientReportPage());	
	}
	
	@Test
	public void verifyReportsLoadedAsPerDate_TC_ClientReports_002() throws Exception
	{
		test=report.startTest("verifyReportsLoadedAsPerDate");
		parent.appendChild(test);
		Reporter.log("TC_ClientReports_002");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToClientReport().generateClientReport().verifyTradeDate());
	}
	
	@Test(dataProvider="type")
	public void verifyClientReportAlert_TC_ClientReports_003_To_TC_ClientReports_007(int a, String type) throws Exception
	{
		test=report.startTest("verifyClientReportAlert For Type :"+type);
		parent.appendChild(test);
		Reporter.log("TC_ClientReports_003_To_TC_ClientReports_007");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToClientReport().generateClientReport().selectFileTypeAndSaveToArchive(type).verifyAlert());
	}
	
	@Test
	public void verifyClearReports_TC_ClientReports_008() throws Exception
	{
		test=report.startTest("verifyClearReports");
		parent.appendChild(test);
		Reporter.log("TC_ClientReports_008");
		Assert.assertTrue(new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToClientReport().generateClientReport().verifyReportsCleared());
	}
	
	@Test(dataProvider="type")
	public void verifyEntityFiles_TC_ClientReports_009_To_TC_ClientReports_013(int a, String type) throws Exception
	{
		test=report.startTest("verifyEntityFiles For Type :"+type);
		parent.appendChild(test);
		Reporter.log("TC_ClientReports_009_To_TC_ClientReports_013");
		String fileName= new LandingPage(webBrowser).goTo().clickOnLoginButton().enterLoginCredentials().navigateToClientReport().generateClientReport().selectFileTypeAndSaveToArchive(type).getFileNameFromAlert();
		new HomePage(webBrowser).navigateToEntityFile().verifyEntityFile(fileName);	
	}

}
