package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseTest;
import pages.noblemarket.AdminPage;

public class AdminPageTest extends BaseTest {

	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("AdminPageTest");
	}
	
	@Test
	public void navigateAdminPortal_TC_AccountSetUp_001_006() throws Exception {
		test=report.startTest("navigateAdminPortal");
		parent.appendChild(test);
		Reporter.log("TC_AccountSetUp_001_To_AccountSetUp_006");
		AdminPage objAdmin = new AdminPage(webBrowser);
		String randomEmail = objAdmin.goTo().logIns().navigateToClientPerson().fillClientPersonInfo();
		// Assert.assertTrue(objAdmin.navigateToClientKYC().searchClientPersonAndEnable(randomEmail).fillPersonalInformation(randomEmail).fillEntityInformation().fillAccountInformation().approveRegistrationFiles().approveAllKYC());
		objAdmin.navigateToClientKYC().searchClientPersonAndEnable(randomEmail);
		Assert.assertTrue(objAdmin.fillPersonalInformation(randomEmail));
		Assert.assertTrue(objAdmin.fillEntityInformation());
		Assert.assertTrue(objAdmin.fillAccountInformation());
		Assert.assertTrue(objAdmin.approveRegistrationFiles());
		Assert.assertTrue(objAdmin.approveAllKYC());		
	}

}
