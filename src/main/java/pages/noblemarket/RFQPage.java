package pages.noblemarket;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;

public class RFQPage extends BasePage<RFQPage> {

	@FindBy(css = "span[data-bind='text:ui.label']")
	private WebElement rfqPageTitle;
	
	@FindBy(id="price_fld")
	private WebElement price;
	
	@FindBy(id="min_amount_fld")
	private WebElement minAmount;
	
	@FindBy(css="div[class='input-group input-group-lg']>input[placeholder='amount']")
	private WebElement amount;
	
	@FindBy(css="button")
	private List<WebElement>buttons;
	
	@FindBy(css="div[data-bind='with:table_your_rfqs']>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td")
	private List<WebElement> yourRFQ;
	
	@FindBy(css="div[data-bind='with:table_your_rfqs']>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(7)>span")
	private WebElement yourRFQStatus;
	
	@FindBy(css="div[data-bind='with:table_your_rfqs']>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(4)>span")
	private WebElement yourRFQInstrument;
	
	@FindBy(css="div[data-bind='with:table_your_rfqs']>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(1)>span")
	private WebElement yourRFQRefNo;
	
	@FindBy(css="div[class='input-group spinner']>input[data-bind*='seconds_to_expiry']")
	private WebElement requestTime;
	
	@FindBy(css="div[data-bind='with:table_your_rfqs']>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(9)>span")
	private WebElement statusOfTimeLeft;
	
	@FindBy(css="div[data-bind='with:table_your_rfqs']>div>table[class='table table-bordered table-vcondensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(10)>button")
	private WebElement trash;
	
	@FindBy(css="div[class='modal-footer']>button[class='btn btn-small btn-danger']")
	private WebElement confirmCancel;
	
	@FindBy(css="div[data-bind='with:table_your_rfqs']>div>p:nth-child(1)>span")
	private WebElement yourRFQTableText;
	
	@FindBy(css="div[data-bind='with:table_market_rfqs']>div>p:nth-child(1)>span")
	private WebElement rfqFromMarketTableText;
	
	@FindBy(css="div[data-bind*='get_trades_from_rfqs']>div>p:nth-child(1)>span")
	private WebElement tradeFromRfqTableText;
	
	@FindBy(css="div[data-bind*='get_open_current_quotes']>div>p:nth-child(1)>span")
	private WebElement openQuotesTableText;
	
	@FindBy(css="div[data-bind='with:table_market_rfqs']>div>table>tbody>tr:nth-child(1)>td:nth-child(2)>div")
	private WebElement qtyRfqFromMarket;
	

	@FindBy(css="div[data-bind='with:table_market_rfqs']>div>table>tbody>tr:nth-child(1)>td:nth-child(5)>div")
	private WebElement qtyMinRfqFromMarket;
	
	@FindBy(css="div[data-bind='with:table_market_rfqs']>div>table>tbody>tr:nth-child(1)>td:nth-child(7)>button")
	private WebElement quote;
	
	public RFQPage(Browser browser) {
		super(browser);
	}

	public boolean verifyRFQTradePage() {
		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(),
				rfqPageTitle, 2);	
		if (rfqPageTitle.getText().equalsIgnoreCase("RFQ Trade Entry")
				&& (yourRFQTableText.getText().equalsIgnoreCase("Your RFQ's"))
				&& (rfqFromMarketTableText.getText().equalsIgnoreCase("RFQ's From Market"))
				&& (tradeFromRfqTableText.getText().equalsIgnoreCase("Trades From RFQs and Quotes"))
				&& (openQuotesTableText.getText().equalsIgnoreCase("Open Quotes")))
			return true;
		else {
			return false;
		}		
	}
	
	public RFQPage createRfqOrder() throws InterruptedException
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(2000);
		price.sendKeys("300");	
		minAmount.sendKeys("1");		
		amount.sendKeys("5");				
		Thread.sleep(1000);	
		return new RFQPage(webBrowser);
	}
	
	public RFQPage requestTimeLeft() throws InterruptedException
	{
		requestTime.clear();
		requestTime.sendKeys("2");
		Thread.sleep(2000);
		return new RFQPage(webBrowser);
	}
	
	public RFQPage clickOnRfqBuy()
	{
		buttons.get(3).click();	
		return new RFQPage(webBrowser);
	}
		
	public boolean verifyYourRfqPopulated() throws InterruptedException
	{	
		if(yourRFQInstrument.getText().trim().equalsIgnoreCase("XBT/USD") && yourRFQStatus.getText().trim().equalsIgnoreCase("New"))		
			return true;
		return false;		
	}
		
	public boolean verifyCancelledRfq()
	{
		String yourRfqRefNo=yourRFQRefNo.getText().trim().toString();
		
		trash.click();
		confirmCancel.click();
		
		if (yourRFQRefNo.getText().trim().toString()!=yourRfqRefNo)			
			return true;
		return false;	
	}
	
	public boolean verifyAlreadyExpiredRfqStatus()
	{
		if(statusOfTimeLeft.getText().trim().equalsIgnoreCase("Already Expired"))
			return true;
		return false;
	}
	

	public boolean verifyRfqFromMarket()
	{
		if(qtyRfqFromMarket.getText().trim().equals("5.00") && qtyMinRfqFromMarket.getText().trim().equals("1.00") && quote.isDisplayed())
		return true;
		return false;
	}
	
	
}
