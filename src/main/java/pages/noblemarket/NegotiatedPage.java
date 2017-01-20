package pages.noblemarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;
import utilities.PropertyDictionary;

public class NegotiatedPage extends BasePage<NegotiatedPage> {

	@FindBy(css = "span[data-bind='text:ui.label']")
	private WebElement negPageTitle;

	@FindBy(css = "select[id='order_type_flde'] > option")
	private WebElement typeOfOrder;
	
	@FindBy(css = "input[id='price_fld']")
	private WebElement priceforNegOrder;
	
	@FindBy(css = "input[id='negotiated_counterparty_fld']")
	private WebElement counterparty;
	
	@FindBy(css = "input[placeholder='amount']")
	private WebElement amount;
	
	@FindBy(css = "button[class='btn btn-lg btn-danger']")
	private WebElement buy;

	@FindBy(css = "button[class='btn btn-lg btn-success']")
	private WebElement sell;
	
	@FindBy(css = "div[data-bind=\"with:queries_dict['load_open_neg_cpty_trades']\"]>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td>button[class='btn btn-success btn-xs']")
	private WebElement Accept;
	
	@FindBy(css = "div[data-bind=\"with:queries_dict['load_open_neg_cpty_trades']\"]>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td>button[class='btn btn-danger btn-xs']")
	private WebElement Decline;
	
	@FindBy(css="div[data-bind=\"with:queries_dict['load_client_open_orders']\"]>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td>span")
	private List<WebElement> negInitiatorOrderInfo;
	
	@FindBy(css="div[data-bind=\"with:queries_dict['load_open_neg_cpty_trades']\"]>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td>span")
	private List<WebElement> negCounterPartyOrderInfo;
	
	@FindBy(css="div[data-bind=\"with:queries_dict['load_client_open_orders']\"]>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr>td:nth-child(1)>div")
	private List<WebElement>numericInitiatorValues;
	
	@FindBy(css="div[data-bind=\"with:queries_dict['load_client_open_orders']\"]>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td>button")
	private WebElement trashIconBtn;
	
	@FindBy(css="button[class='btn btn-small btn-danger']")
	private WebElement confirmCancel;
	
	@FindBy(linkText="Support/Help")
	private WebElement supportHelp;
	
	@FindBy(linkText="Logout")
	private WebElement logOut;
	
	

	public NegotiatedPage(Browser browser) {
		super(browser);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyNegTradePage() {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(),
				negPageTitle, 2);
		if (negPageTitle.getText().equalsIgnoreCase("Negotiated Trade Entry")
				&& typeOfOrder.getText().equalsIgnoreCase("Negotiated"))
			return true;
		return false;
	}
	
	public NegotiatedPage cancelNegOrderByInitiator() throws InterruptedException
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(3000);
		trashIconBtn.click();
		Thread.sleep(2000);
		confirmCancel.click();
		return new NegotiatedPage(webBrowser);
	}

	
	public boolean verifyNegTradeOrderBuy() throws InterruptedException
	{
		createNegBuyOrder();
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(4000);
		if (negInitiatorOrderInfo.get(2).getText().equalsIgnoreCase("Negotiated"))
			return true;
		return false;

	}
	
	public NegotiatedPage createNegBuyOrder() throws InterruptedException
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(1000);
		priceforNegOrder.sendKeys("100");
		counterparty.sendKeys(PropertyDictionary.map.get("counterparty_Id"));
		amount.sendKeys("2");		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 3);
		buy.click();
		Thread.sleep(2000);
		return new NegotiatedPage(webBrowser);
	}
	
	public boolean verifyDeclinedOrderDeleted(String ordIdDeclined)
	{
		List<String> orderId=new ArrayList<String>();
	   for (WebElement element : numericInitiatorValues) {		
		orderId.add(element.getText().trim().toString());
	}
		if(!orderId.contains(ordIdDeclined))		
			return true;
		return false;
	}
	
	public String getLatestNegOrderId()
	{		
		return(numericInitiatorValues.get(0).getText().trim().toString());		
	}
	
	public LandingPage logOut() throws Exception
	{
		Thread.sleep(2000);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), supportHelp,5);
		supportHelp.click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), logOut, 6);
		logOut.click();
		return new LandingPage(webBrowser);
	}
	
	public boolean verifyNegTradeOrderSell() throws InterruptedException
	{
		priceforNegOrder.sendKeys("100");
		counterparty.sendKeys(PropertyDictionary.map.get("counterparty_Id"));
		amount.sendKeys("2");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), sell, 2);
		sell.click();
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(4000);
		if (negInitiatorOrderInfo.get(2).getText().equalsIgnoreCase("Negotiated"))
			return true;
		return false;
		
	}
	
	public boolean verifyCounterPartyOrder()
	{
		if (!negCounterPartyOrderInfo.isEmpty()) {
			if (negCounterPartyOrderInfo.get(5).getText().equalsIgnoreCase("Counterparty")&& negCounterPartyOrderInfo.get(2).getText().equalsIgnoreCase("Negotiated") && Accept.isDisplayed() && Decline.isDisplayed())
				return true;
			return false;
		}
		else
		{
			return false;
		}
		
	}
	
	public NegotiatedPage clickOnDecline() throws InterruptedException
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(2000);
		Decline.click();
		return new NegotiatedPage(webBrowser);
	}
	
	public NegotiatedPage clickOnAccept() throws InterruptedException
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(2000);
		Accept.click();
		return new NegotiatedPage(webBrowser);
	}

}
