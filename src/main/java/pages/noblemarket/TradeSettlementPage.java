package pages.noblemarket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;

public class TradeSettlementPage extends BasePage<TradeSettlementPage> {

	
	@FindBy(id="trade_types_fld")
	private WebElement tradeType;
	
	@FindBy(css="div>input[data-bind*='fx_ass_currency.name']")
	private WebElement asset;
	
	@FindBy(css="div>input[id='xaq']")
	private WebElement quantity1;
	
	@FindBy(css="div>input[id='xr']")
	private WebElement rate;
	
	@FindBy(css="div>input[data-bind*='fx_num_currency.name']")
	private WebElement numerator;
	
	@FindBy(css="div>input[id='xnq']")
	private WebElement quantity2;
	
	@FindBy(css="div>button[data-bind*='editFXCashFlows']")
	private WebElement calPL;
	
	@FindBy(css="div>input[id='entity_fld']")
	private WebElement acquirer;
	
	@FindBy(css="div>ul>li[data-value='1:USD']")
	private WebElement assetSelect;
	
	@FindBy(css="div>ul>li[data-value='7:Acme Investment Inc']")
	private WebElement acquirerSelect;
	
	@FindBy(css="div>input[id='counterparty_fld']")
	private WebElement counterParty;
	
	@FindBy(css="div>ul>li[data-value='2:JP Morgan']")
	private WebElement counterPartySelect;
	
	@FindBy(css="div>input[id='entity_account_fld']")
	private WebElement account1;
	
	@FindBy(css="div>ul>li[data-value='12:Acme Investment Inc_Settlement']")
	private WebElement account1Select;
	
	@FindBy(css="div>input[id='counterparty_account_fld']")
	private WebElement account2;
	
	@FindBy(css="div>input[id='portfolio_fld']")
	private WebElement portfolio;
	
	@FindBy(css="div>ul>li[data-value='1:Default']")
	private WebElement portfolioSelect;
	
	@FindBy(css="div>input[id='deal_fld']")
	private WebElement deal;
	
	@FindBy(css="div>input[id='trader_fld']")
	private WebElement trader;
	
	@FindBy(css="div>input[id='mark_fld']")
	private WebElement mark;
		
	@FindBy(css="div>input[id='strategy_fld']")
	private WebElement stargegy;
	
	@FindBy(css="div>input[id='sales_trader_fld']")
	private WebElement salesPerson;
		
	@FindBy(css="button[title='Save changes']")
	private WebElement saveChanges;
	
	@FindBy (css = "button[class='btn btn-xs btn-info']")
	private WebElement newItems;
	
	@FindBy (css = "ul[class='nav navbar-nav']>li:nth-child(2)")
	private WebElement tradeAndSettlement;
	
	@FindBy (css = "li[class='dropdown open']>ul>li:nth-child(1)")
	private WebElement tradeBlotter;
	
	@FindBy (css = "button[class='btn btn-warning pull-left']")
	private WebElement addButton;
	
	@FindBy (css = "button[class='btn btn-success']")
	private WebElement editButton;
	
	@FindBy (css = "span[data-bind^='text_date: {theDate:raw ||']")
	private List<WebElement> tradeTime;
	
	@FindBy (css = "span[data-bind^='text_date:{theDate']")
	private List<WebElement> dateList;
	
	@FindBy (css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody")
	private WebElement tradeItems;

	public TradeSettlementPage(Browser browser) {
		super(browser);
		// TODO Auto-generated constructor stub
	}
	
	public String AddFXTrade() throws InterruptedException
	{
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), tradeAndSettlement, 6);
		tradeAndSettlement.click();
		tradeBlotter.click();
		webBrowser.getWebDriver().navigate().refresh();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), tradeItems,15);
		addButton.click();
		commonAction.selectDropdown(tradeType, "FXTrade");
		asset.sendKeys("USD",Keys.ENTER);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), assetSelect, 3);
		assetSelect.click();
		
		quantity1.sendKeys("10000",Keys.TAB);
		quantity2.sendKeys("600000",Keys.TAB);
		calPL.click();
		acquirer.sendKeys("Acme Investment Inc");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), acquirerSelect, 3);
		acquirerSelect.click();
		
		counterParty.sendKeys("JP Morgan");
		Thread.sleep(2000);// counterPartySelect
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), counterPartySelect, 3);
		counterPartySelect.click();
		
		Thread.sleep(2000);
		account1.sendKeys("Acme Investment Inc_Settlement");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), account1Select, 3);
		account1Select.click();
		Thread.sleep(2000);
		portfolio.sendKeys("Default"); //portfolioSelect
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), portfolioSelect, 3);
		portfolioSelect.click();
		Thread.sleep(2000);
		saveChanges.click();
		String tradtm = tradeTime.get(0).getText();
		editButton.click();
		return tradtm;
		
	}
	
	public boolean verifyRecentlyAddItemIsOnList(String timeOfTrade) throws InterruptedException
	{
		List<String> listOfDate = new ArrayList<String>();
		newItems.click();
		Thread.sleep(4000);
		for(WebElement element : dateList)
		{
			listOfDate.add(element.getText());
		}
		if(listOfDate.contains(timeOfTrade))
			return true;
		return false;
	}
}
