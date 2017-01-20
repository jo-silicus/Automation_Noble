package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.noblemarket.LandingPage;
import pages.noblemarket.UserRegistration;
import common.BaseTest;

public class LoginPageTest extends BaseTest {
	
	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("LoginPageTest");
	}
	
	@Test
	public void verifyLoginPage_TC_LoginPage_001() throws Exception {
		test=report.startTest("verifyLoginPage_TC_LoginPage_001");
		parent.appendChild(test);
		Reporter.log("TC_LoginPage_001");
		LandingPage landingpage = new LandingPage(webBrowser).goTo();
		Assert.assertTrue(landingpage.clickOnLoginButton().verifyPleaseSignInText());
	}
	
	@Test
	public void verifyLoginFunctionality_TC_LoginPage_002() throws InterruptedException, Exception
	{
		test=report.startTest("verifyLoginFunctionality_TC_LoginPage_002");
		parent.appendChild(test);
		Reporter.log("TC_LoginPage_002");
		LandingPage landingpage = new LandingPage(webBrowser).goTo();
		Assert.assertTrue(landingpage.clickOnLoginButton().enterLoginCredentials().verifydLoginFunctionality(null));
	}
	
	@Test
	public void InvalidLoginFunctionality_TC_LoginPage_003() throws Exception
	{
		test=report.startTest("InvalidLoginFunctionality_TC_LoginPage_003");
		parent.appendChild(test);
		Reporter.log("TC_LoginPage_003");
		LandingPage landingpage = new LandingPage(webBrowser).goTo();
		Assert.assertTrue(landingpage.clickOnLoginButton().verifyInvalidLoginFunctionality());		
	}

	@Test
	public void verifyForgetPasswordFunctionality_TC_LoginPage_004() throws Exception {
		test=report.startTest("verifyForgetPasswordFunctionality_TC_LoginPage_004");
		parent.appendChild(test);
		Reporter.log("TC_LoginPage_004");
		UserRegistration userregisterpage = new UserRegistration(webBrowser).goTo();
		String randomEmail = userregisterpage.fillRegistrationForm();
		userregisterpage.checkmailRegistration(randomEmail);
		LandingPage landingpage = new LandingPage(webBrowser).goTo();
		String newPwd=landingpage.clickOnLoginButton().clickOnForgetPassword(randomEmail).verifyNewPasswordLogIn(randomEmail);
		Assert.assertTrue(landingpage.clickOnLoginButton().enterCredentialsForgetPassword(randomEmail, newPwd).verifydLoginFunctionality(randomEmail));
	}

	@Test
	public void verifyinvalidLoginAttemptFunctionality_TC_LoginPage_005() throws Exception {
		test=report.startTest("verifyinvalidLoginAttemptFunctionality_TC_LoginPage_005");
		parent.appendChild(test);
		Reporter.log("TC_LoginPage_005");
		UserRegistration userregisterpage = new UserRegistration(webBrowser).goTo();
		String randomEmail = userregisterpage.fillRegistrationForm();
		Thread.sleep(1000);
		userregisterpage.checkmailRegistration(randomEmail);
		LandingPage landingpage = new LandingPage(webBrowser).goTo();
		Assert.assertTrue(landingpage.clickOnLoginButton().verifyInvalidAttemptFunctionality(randomEmail));

	}

}
