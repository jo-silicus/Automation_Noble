package pages.noblemarket;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import core.CommonAction;
import pages.common.BasePage;
import pages.noblemarket.datamodal.UserRegisterPageDataModel;


public class UserRegistration extends BasePage<UserRegistration> {

	@FindBy(css = "input[type='checkbox']")
	private WebElement checkboxAutorize;

	@FindBy(css = "input[id='firstname_fld']")
	private WebElement firstName;

	@FindBy(css = "input[id='lastname_fld']")
	private WebElement lastName;

	@FindBy(css = "select[id='gender_flde']")
	private WebElement selectGender;

	@FindBy(css = "input[id='email_fld']")
	private WebElement email;
	
	@FindBy(css = "input[id='emailConfirm_fld']")
	private WebElement confirmEmail;

	@FindBy(css = "input[id='password_fld']")
	private WebElement password;

	@FindBy(css = "input[id='passwordConfirm_fld']")
	private WebElement confirmPassword;

	@FindBy(css = "input[id='phone_fld']")
	private WebElement phone;

	@FindBy(css = "input[data-bind='value:corp_name']")
	private WebElement companyName;

	@FindBy(css = "button[class='btn btn-success btn-sm']")
	private WebElement registerbtn;

	@FindBy(css = "div[class='alert in fade alert-danger']")
	private WebElement successregistration;

	@FindBy(css = "body")
	private WebElement body;

	@FindBy(css = "input[id='inboxfield']")
	private WebElement inputEmail;

	@FindBy(css = "button[class='btn btn-dark']")
	private WebElement goButton;

	@FindBy(css = "body a[target='_other'][rel='nofollow']")
	private WebElement linkEmail;

	@FindBy(css = "div[class='row ng-scope oddrow_public']")
	private WebElement mailElement;

	@FindBy(css = "div[class='col-md-12 col-sm-12']>h2[class='page-header']")
	private WebElement pageHeader;

	@FindBy(css = "div[class='notifications reg-notify col-sm-6']>div[class='alert in fade alert-danger']")
	private WebElement pageAlertMsg;
	
	@FindBy(linkText = "Continue")
	private WebElement continueLink;

	public UserRegistration(Browser browser) throws Exception {
		super(browser);
		initDataModel(new UserRegisterPageDataModel());
	}

	public boolean verifyUserRegistrationPage() {
		if (pageHeader.getText().trim().equalsIgnoreCase("User Registration"))
			return true;
		return false;
	}

	public boolean verifyConfirmEmailSentMsg() {
		if (pageAlertMsg.getText().trim().equalsIgnoreCase("×\nYou should receive an email shortly"))
			return true;
		return false;

	}

	public String fillRegistrationForm() throws Exception {
		//chnaged code as the "register" button was not working on linux mahcine.
				//resize the window and scol-up, again rest to maxium size			
				checkboxAutorize.click();
				firstName.sendKeys("TestSilicusFirstName");
				lastName.sendKeys("TestSilicusLastName");
				CommonAction action = new CommonAction();
				action.selectDropdown(selectGender, "Male");
				String randomEmail = action.randomEmailGenerate();
				email.sendKeys(randomEmail);
				confirmEmail.sendKeys(randomEmail);
				password.sendKeys("1234567890");
				confirmPassword.sendKeys("1234567890");
				phone.sendKeys("123456789");;
				companyName.sendKeys(randomEmail);
				commonAction.Wait(1000);
				Dimension d = new Dimension(420,600);
				//Resize the current window to the given dimension
				webBrowser.getWebDriver().manage().window().setSize(d);
				JavascriptExecutor jse = (JavascriptExecutor)webBrowser.getWebDriver();
				jse.executeScript("scroll(0, -250);");
				commonAction.Wait(2000);
				action.waitElementToBeClickable(webBrowser.getWebDriver(), registerbtn, 10);
				Actions action1 = new Actions(webBrowser.getWebDriver());
				action1.moveToElement(registerbtn).click().build().perform();
				commonAction.Wait(3000);
				webBrowser.getWebDriver().manage().window().maximize();
				return randomEmail;
	}

	public boolean validateSuccessRegistration() {
		if (successregistration.getText().trim().equalsIgnoreCase("You should receive an email shortly"))
			return true;
		return false;
	}
	
	public String verifyNewPasswordLogIn(String randomEmail) throws Exception
	{
		navigateToMailinator(randomEmail);
		webBrowser.getWebDriver().switchTo().frame("publicshowmaildivcontent");		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), body, 8);
		String allText=body.getText().trim();
		String newPassword = null;
		if(allText.contains("Password Reset"))
		{
		String[] mailBody = allText.split("==============================");
		newPassword=mailBody[1].trim();
	
		Thread.sleep(5000);
		webBrowser.getWebDriver().navigate().to(linkEmail.getAttribute("href"));			
		continueLink.click();
		}
		else
		{
			System.out.println("Password NULL returned!!!");
		}
		return newPassword;
	}
	
	public void checkmailRegistration(String randomEmail) throws InterruptedException {
		//commented Ravi's code
		/*Thread.sleep(2000);
		body.sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tab = new ArrayList<String>(webBrowser.getWebDriver().getWindowHandles());
		String windowHandle = ((String) tab.toArray()[1]);
		webBrowser.getWebDriver().switchTo().window(windowHandle);
		webBrowser.getWebDriver().navigate().to("http://maildrop.cc/");
		String[] p  = randomEmail.split("@");
		maildropsearchField.sendKeys(p[0]);
		Thread.sleep(2000);
		submitButton.click();
		Thread.sleep(10000);
		mailElement.click();
		webBrowser.getWebDriver().switchTo().frame("publicshowmaildivcontent");
		webBrowser.getWebDriver().navigate().to(linkEmail.getAttribute("href"));
		Thread.sleep(2000);*/
		navigateToMailinator(randomEmail);
		webBrowser.getWebDriver().switchTo().frame("publicshowmaildivcontent");
		Thread.sleep(5000);
		webBrowser.getWebDriver().navigate().to(linkEmail.getAttribute("href"));
	}

	public boolean checkLinkReceived(String randomEmail) throws InterruptedException, IOException {
		navigateToMailinator(randomEmail);
		//mailElement.click();
		webBrowser.getWebDriver().switchTo().frame("publicshowmaildivcontent");
		Thread.sleep(5000);
		if (linkEmail.isDisplayed())
			return true;
		return false;
	}
	
	
	public void navigateToMailinator(String randomEmail) throws InterruptedException
	{
		body.sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tab = new ArrayList<String>(webBrowser.getWebDriver().getWindowHandles());
		String windowHandle = ((String) tab.toArray()[1]);
		webBrowser.getWebDriver().switchTo().window(windowHandle);
		webBrowser.getWebDriver().navigate().to("https://www.mailinator.com/");
		inputEmail.sendKeys(randomEmail);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), goButton, 10);
		goButton.click();
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(8000);
		do
		{
			webBrowser.getWebDriver().navigate().refresh();
			Thread.sleep(3000);
			System.out.println(mailElement.getSize());
		}
		while(!(mailElement.isDisplayed()));
		Actions actions = new Actions(webBrowser.getWebDriver());
		actions.moveToElement(mailElement).click().perform();
	}

	public LoginPage gotoLoginPage() throws Exception {
		LoginPage loginPage = new LoginPage(webBrowser);
		loginPage.goTo();
		return new LoginPage(webBrowser);
	}
}
