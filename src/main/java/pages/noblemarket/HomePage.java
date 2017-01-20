package pages.noblemarket;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;
import pages.noblemarket.datamodal.HomePageDataModel;
import utilities.PropertyDictionary;

public class HomePage extends BasePage<HomePage> {

	@FindBy(css = "span[data-bind='text:appl.ui.client_name']")
	private WebElement clientName;

	@FindBy(linkText = "Risk Management")
	private WebElement riskManagementMainMenu;

	@FindBy(linkText = "Order Blotter")
	private WebElement orderBlotter;
	
	@FindBy(linkText = "Account Managment")
	private WebElement accountManagement;
	
	@FindBy(linkText = "Entity Files")
	private WebElement entityFiles;

	@FindBy(linkText = "Order Entry")
	private WebElement orderEntry;
	
	@FindBy(linkText = "Negotiated Trade Entry")
	private WebElement negTradeEntry;
	
	@FindBy(linkText = "RFQ Trade Entry")
	private WebElement RFQ;
	
	@FindBy(linkText = "Reporting")
	private WebElement reporting;
	
	@FindBy(linkText = "General Reports")
	private WebElement generalReports;

	@FindBy(css = "#bs-example-navbar-collapse-1 > ul:nth-child(1) > li.dropdown.open > ul > li:nth-child(1) > a")
	private WebElement orderEntrySubMenu;
	
	@FindBy(linkText="Support/Help")
	private WebElement supportHelp;
	
	@FindBy(css="div[id='bs-example-navbar-collapse-1']>ul[class='nav navbar-nav navbar-right']>li>a[class='dropdown-toggle']")
	private WebElement clientNameTitle;

	public HomePage(Browser browser) throws Exception {
		super(browser);
		initDataModel(new HomePageDataModel());
	}

	public boolean verifyUserLoggedInSuccessfully() throws InterruptedException {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), clientName, 8);
		if (clientName.getText().length() != 0)
			return true;
		return false;
	}

	public OrderBlotterPage navigateToOrderBlotter() throws Exception {
		//commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), riskManagementMainMenu, 8);
		Thread.sleep(8000);
		riskManagementMainMenu.click();
		orderBlotter.click();
		return new OrderBlotterPage(webBrowser);
	}

	public OrderEntryPage navigateToOrderEntry() throws Exception {
		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), riskManagementMainMenu, 8);
		riskManagementMainMenu.click();
		
		orderEntry.click();
		
		return new OrderEntryPage(webBrowser);
	}
	
	public NegotiatedPage navigateToNegTradeEntry() throws Exception {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), riskManagementMainMenu, 8);
		riskManagementMainMenu.click();
		negTradeEntry.click();
		
		return new NegotiatedPage(webBrowser);
	}
	
	public RFQPage navigateToRFQ() throws InterruptedException
	{
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), riskManagementMainMenu, 8);
		riskManagementMainMenu.click();
		RFQ.click();				
		return new RFQPage(webBrowser);		
	}
	
	public ClientReportPage navigateToClientReport()
	{
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), reporting, 8);
		reporting.click();
		generalReports.click();
		return new ClientReportPage(webBrowser);
		
	}
	
	public EntityFilePage navigateToEntityFile()
	{
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), accountManagement, 5);
		accountManagement.click();
		entityFiles.click();
		return new EntityFilePage(webBrowser);
	}
	
	public boolean verifydLoginFunctionality(String mail) throws InterruptedException {
		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), supportHelp,2);
		supportHelp.click();		
		String clientEmail=clientNameTitle.getAttribute("title");
		if (mail==null) {
			if (clientEmail.equalsIgnoreCase(PropertyDictionary.map.get("username"))) 
				return true;
			return false;	
		}else
		{
			if (clientEmail.equalsIgnoreCase(mail)) 
				return true;
			return false;
		}
	}
}
