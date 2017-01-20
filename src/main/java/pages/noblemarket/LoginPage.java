package pages.noblemarket;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import core.CommonAction;
import pages.common.BasePage;
import pages.noblemarket.datamodal.HomePageDataModel;
import utilities.PropertyDictionary;

public class LoginPage extends BasePage<LandingPage> {

	@FindBy(css = "h2[style='margin-top:0px']")
	private WebElement pleaseSignIn;

	@FindBy(id = "email_fld")
	private WebElement username;

	@FindBy(id = "password_fld")
	private WebElement password;

	@FindBy(css = "button[class='btn btn-sm btn-primary pull-right']")
	private WebElement signIn;

	@FindBy(css = "button[class='btn btn-sm btn-info']")
	private WebElement forgetPassword;

	@FindBy(css = "input[id='forgot_email_fld']")
	private WebElement forgetEmailId;

	@FindBy(css = "button[class='btn btn-sm btn-info pull-right']")
	private WebElement btnContinue;

	@FindBy(css = "h4[data-bind='text:message']")
	private WebElement errorMessage;

	@FindBy(css = "h4[data-bind='text:error']")
	private WebElement invalidAttemptMsg;

	public LoginPage(Browser browser) throws Exception {
		super(browser);
		initDataModel(new HomePageDataModel());
	}

	public HomePage enterLoginCredentials() throws Exception {		
		username.sendKeys(PropertyDictionary.map.get("username"));
		password.sendKeys(PropertyDictionary.map.get("password"));
		commonAction.Wait(1000);
		signIn.click();
		commonAction.Wait(1000);
		return new HomePage(webBrowser);
	}
	
	public HomePage enterCredentialsForgetPassword(String useremail , String pwd) throws Exception
	{
		username.sendKeys(useremail);
		password.sendKeys(pwd);
		signIn.click();
		commonAction.Wait(1000);
		return new HomePage(webBrowser);
	}
	
	public HomePage enterCredentialsforNewUser(String useremail) throws Exception
	{
		username.sendKeys(useremail);
		password.sendKeys("1234567890");
		signIn.click();
		commonAction.Wait(1000);
		return new HomePage(webBrowser);
	}
	
	public HomePage enterLoginCredentialsForCounterParty() throws Exception {
		username.sendKeys(PropertyDictionary.map.get("username_counterparty"));
		password.sendKeys(PropertyDictionary.map.get("password_counterparty"));
		signIn.click();
		return new HomePage(webBrowser);
	}

	public boolean verifyPleaseSignInText() throws InterruptedException {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), pleaseSignIn, 4);
		if (pleaseSignIn.getText().equalsIgnoreCase("Please sign in"))
			return true;
		return false;
	}

	public UserRegistration clickOnForgetPassword(String userEmail) throws Exception {
		Thread.sleep(1000);
		forgetPassword.click();
		forgetEmailId.sendKeys(userEmail);
		btnContinue.click();
		Thread.sleep(1000);
		return new UserRegistration(webBrowser);
	}

	public boolean verifyInvalidAttemptFunctionality(String randomEmail) throws InterruptedException {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), pleaseSignIn, 4);
		commonAction.Wait(1000);
		username.sendKeys(randomEmail);
		commonAction.Wait(1000);
		password.sendKeys("123456789");
		for (int i = 0; i < 3; i++) {
			Thread.sleep(1000);
			signIn.click();
		}
		commonAction.Wait(1000);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), invalidAttemptMsg, 2000);
		commonAction.Wait(1000);
		if (invalidAttemptMsg.getText().trim().equalsIgnoreCase("maximum login attempts exceeded, login disabled!"))
			return true;
		return false;
	}

	public boolean verifyInvalidLoginFunctionality() throws InterruptedException {		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), pleaseSignIn, 4);
		username.sendKeys(PropertyDictionary.map.get("username"));
		password.sendKeys("1234567$9");
		signIn.click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), invalidAttemptMsg, 2000);
		if (invalidAttemptMsg.getText().trim().equalsIgnoreCase("Email or password incorrect!"))
			return true;
		return false;	
	}
}
