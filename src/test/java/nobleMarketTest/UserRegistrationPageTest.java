package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import common.BaseTest;
import pages.noblemarket.LandingPage;
import pages.noblemarket.UserRegistration;

public class UserRegistrationPageTest extends BaseTest {

	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("UserRegistrationPageTest");
	}
	
	@Test
	public void verifyUserRegistrationPage_TC_UserReg_001() throws Exception
	{
		test=report.startTest("verifyUserRegistrationPage_TC_UserReg_001");
		parent.appendChild(test);
		Reporter.log("TC_UserReg_001");
		Assert.assertTrue(new UserRegistration(webBrowser).goTo().verifyUserRegistrationPage());		
	}
	
	@Test
	public void verifyUserRegistration_TC_UserReg_002() throws Exception
	{
		test=report.startTest("verifyUserRegistration_TC_UserReg_002");
		parent.appendChild(test);
		Reporter.log("TC_UserReg_002");
		UserRegistration register = new UserRegistration(webBrowser);
		register.goTo().fillRegistrationForm();	
		Assert.assertTrue(register.verifyConfirmEmailSentMsg());
	}
	
	@Test
	public void verifyConfirmationEmailLink_TC_UserReg_003() throws Exception
	{
		test=report.startTest("verifyConfirmationEmailLink_TC_UserReg_003");
		parent.appendChild(test);
		Reporter.log("TC_UserReg_003");
		UserRegistration register = new UserRegistration(webBrowser);
		String randomEmail=register.goTo().fillRegistrationForm();				
		Assert.assertTrue(register.checkLinkReceived(randomEmail));
	}
	
	@Test
	public void verifyNewUserCanLogIn_TC_UserReg_004() throws Exception
	{
		test=report.startTest("verifyNewUserCanLogIn_TC_UserReg_004");
		parent.appendChild(test);
		Reporter.log("TC_UserReg_004");
		UserRegistration register = new UserRegistration(webBrowser);
		String randomEmail=register.goTo().fillRegistrationForm();		
		register.checkmailRegistration(randomEmail);
		LandingPage landingpage = new LandingPage(webBrowser).goTo();
		Assert.assertTrue(landingpage.clickOnLoginButton().enterCredentialsforNewUser(randomEmail).verifydLoginFunctionality(randomEmail));
	}
}
