package pages.noblemarket;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;
import pages.noblemarket.datamodal.HomePageDataModel;

public class LandingPage extends BasePage<LandingPage> {
	
	@FindBy(linkText = "Login")
	private WebElement login;
	
	@FindBy(css = "img[class='noble_logo_small']")
	private WebElement nobleMarketLogo;
	
	@FindBy(css = "p > a[href='/pub-registration']")
	private WebElement register;
	
	public LandingPage(Browser browser) throws Exception {
		super(browser);
		initDataModel(new HomePageDataModel());
	}
	
	public LoginPage clickOnLoginButton() throws Exception
	{
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), login, 3);
		login.click();
		return new LoginPage(webBrowser);
	}
	
	public UserRegistration clickOnRegisterButton() throws Exception
	{
		register.click();
		return new UserRegistration(webBrowser);
	}
	
	public boolean verifyNobleMarketLogo() throws InterruptedException
	{
		if(nobleMarketLogo.isDisplayed())
			return true;
		return false;
	}
}

