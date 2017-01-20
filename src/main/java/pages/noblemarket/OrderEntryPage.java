package pages.noblemarket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import core.Browser;
import core.CommonAction;
import pages.common.BasePage;

public class OrderEntryPage extends BasePage<OrderEntryPage> {

	@FindBy(css = "input[id='price_fld']")
	private WebElement price;

	@FindBy(css = "input[placeholder='amount']")
	private WebElement amount;

	@FindBy(css = "button[class='btn btn-lg btn-danger']")
	private WebElement buy;

	@FindBy(css = "button[class='btn btn-lg btn-success']")
	private WebElement sell;

	@FindBy(linkText = "Risk Management")
	private WebElement riskManagementTab;

	@FindBy(linkText = "Order Blotter")
	private WebElement orderBlotterTab;

	@FindBy(css = "select[id='order_type_flde']")
	private WebElement type;

	@FindBy(css = "select[id='time_in_force_flde']")
	private WebElement timeInForce;

	@FindBy(css = "input[id='expiry_date_fld']")
	private WebElement expiryDate;

	@FindBy(css = "input[id='expiry_time_fld']")
	private WebElement expiryTime;

	@FindBy(css = "table[class='table table-bordered table-vcondensed table-hover JColResizer'] > tbody > tr > td:nth-child(1) > div")
	private List<WebElement> orderId;

	@FindBy(css = "table[class='table table-bordered table-vcondensed table-hover JColResizer'] > tbody > tr > td:nth-child(6) > div")
	private WebElement quantity;

	@FindBy(css = "table[class='table table-bordered table-vcondensed table-hover JColResizer'] > tbody > tr > td:nth-child(7) > div")
	private WebElement priced;

	@FindBy(css = "table[class='table table-bordered table-vcondensed table-hover JColResizer'] > tbody > tr > td:nth-child(8) > div")
	private WebElement matched;

	public OrderEntryPage(Browser browser) {
		super(browser);
		// TODO Auto-generated constructor stub
	}

	public List<String> fillPriceAndAmountlimit() throws InterruptedException {
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(4000);

		// 1. Limit - Day - Buy
		Reporter.log("TC_TrEntSimple_005");
		CommonAction objaction = new CommonAction();
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), price, 2);
		commonAction.Wait(1000);
		clearPriceText();
		commonAction.Wait(1000);
		price.sendKeys("100");
		commonAction.Wait(1000);
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("10");
		commonAction.Wait(1000);
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 2);
		buy.click();
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), riskManagementTab, 2);

		// 2. Limit - Day - Sell
		Reporter.log("TC_TrEntSimple_006");
		Thread.sleep(1000);
		clearPriceText();
		price.sendKeys("100");
		commonAction.Wait(1000);
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("1");
		commonAction.Wait(1000);
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 2);
		sell.click();

		// 10. Market - Day - Buy
		Reporter.log("TC_TrEntSimple_07");
		Thread.sleep(1000);
		objaction.selectDropdown(type, "Market");
		commonAction.Wait(1000);
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("10");
		commonAction.Wait(1000);
		buy.click();

		// 11. Market - GTC - Buy
		Reporter.log("TC_TrEntSimple_08");
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "GTC");
		commonAction.Wait(1000);
		clearAmount();
		amount.sendKeys("10");
		commonAction.Wait(1000);
		buy.click();

		// 3. Limit - GTC - Buy
		Reporter.log("TC_TrEntSimple_09");
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "GTC");
		commonAction.Wait(1000);
		objaction.selectDropdown(type, "Limit");
		commonAction.Wait(1000);
		clearPriceText();
		commonAction.Wait(1000);
		price.sendKeys("100");
		clearAmount();
		amount.sendKeys("1");
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 2);
		buy.click();

		// 4. Limit - GTC - Sell
		
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "GTC");
		commonAction.Wait(1000);
		clearPriceText();
		price.sendKeys("100");
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("1");
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), sell, 2);
		sell.click();

		// 5. Limit - IOC - Buy
		Reporter.log("TC_TrEntSimple_010");
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "IOC");
		commonAction.Wait(2000);
		clearPriceText();
		commonAction.Wait(1000);
		price.sendKeys("100");
		commonAction.Wait(1000);
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("1");
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 2);
		commonAction.Wait(1000);
		buy.click();

		// 6. Limit - FOK - Buy
		Reporter.log("TC_TrEntSimple_011");
		Thread.sleep(1000);
		commonAction.Wait(1000);
		objaction.selectDropdown(timeInForce, "FOK");
		clearPriceText();
		commonAction.Wait(1000);
		price.sendKeys("100");
		commonAction.Wait(1000);
		clearAmount();
		amount.sendKeys("1");
		commonAction.Wait(1000);
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 2);
		buy.click();

		// 7. Limit - FOK - Sell
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "FOK");
		clearPriceText();
		commonAction.Wait(1000);
		price.sendKeys("100");
		commonAction.Wait(1000);
		clearAmount();
		amount.sendKeys("1");
		commonAction.Wait(1000);
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 2);
		sell.click();

		// 8. Limit - GTD - Buy
		Reporter.log("TC_TrEntSimple_012");
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "GTD");
		Thread.sleep(1000);
		commonAction.Wait(1000);
		clearExpiryDate();
		commonAction.Wait(1000);
		expiryDate.sendKeys("29 Jul 2016");
		clearPriceText();
		commonAction.Wait(1000);
		price.sendKeys("100");
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("1");
		commonAction.Wait(1000);
		Thread.sleep(1000);
		clearExpiryTime();
		commonAction.Wait(1000);
		expiryTime.sendKeys("14:00:00");

		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), buy, 2);
		commonAction.Wait(1000);
		buy.click();

		// 9. Limit - GTD - Sell
		Thread.sleep(1000);
		sell.click();
		
		// 12. Market - GTC - Sell
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "GTC");
		commonAction.Wait(1000);
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("10");
		clearPriceText();
		commonAction.Wait(1000);
		price.sendKeys("100");
		commonAction.Wait(1000);
		sell.click();

		// 13. Market - IOC - Buy
		objaction.selectDropdown(type, "Market");
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "IOC");
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("10");
		commonAction.Wait(1000);
		buy.click();

		// 14. Market - IOC - Sell
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "IOC");
		clearAmount();
		amount.sendKeys("10");
		commonAction.Wait(1000);
		sell.click();

		// 15. Market - FOK - Buy
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "FOK");
		clearAmount();
		amount.sendKeys("10");
		commonAction.Wait(1000);
		buy.click();

		// 16. Market - FOK - Sell
		Reporter.log("TC_TrEntSimple_013");
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "FOK");
		clearAmount();
		amount.sendKeys("10");
		commonAction.Wait(1000);
		sell.click();

		// 17. Market - GTD - Buy		
		Thread.sleep(1000);
		objaction.selectDropdown(timeInForce, "GTD");
		Thread.sleep(1000);
		clearExpiryDate();
		commonAction.Wait(1000);
		expiryDate.sendKeys("29 Jul 2016");
		clearExpiryTime();
		commonAction.Wait(1000);
		expiryTime.sendKeys("14:00:00");
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("10");
		commonAction.Wait(1000);
		buy.click();

		// 18. Market - GTD - Sell
		Thread.sleep(1000);
		clearExpiryDate();
		commonAction.Wait(1000);
		expiryDate.sendKeys("29 Jul 2016");
		clearExpiryTime();
		commonAction.Wait(1000);
		expiryTime.sendKeys("14:00:00");
		clearAmount();
		commonAction.Wait(1000);
		amount.sendKeys("10");
		commonAction.Wait(2000);
		sell.click();

		commonAction.Wait(6000);
		List<String> data = new ArrayList<String>();
		for (WebElement Id : orderId) {
			data.add(Id.getText());
		}
		return data;
	}

	private void clearExpiryTime() {
		// TODO Auto-generated method stub
		expiryTime.clear();

	}

	private void clearExpiryDate() {
		// TODO Auto-generated method stub
		expiryDate.clear();

	}

	private void clearAmount() {
		// TODO Auto-generated method stub
		amount.clear();

	}

	private void clearPriceText() {
		// TODO Auto-generated method stub
		price.clear();

	}

	public OrderBlotterPage navigateOrderBlotter() throws Exception {
		commonAction.Wait(1000);
		riskManagementTab.click();
		commonAction.Wait(1000);
		orderBlotterTab.click();
		commonAction.Wait(1000);
		return new OrderBlotterPage(webBrowser);
	}

}
