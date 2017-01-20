package pages.noblemarket;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import pages.common.BasePage;
import utilities.PropertyDictionary;
import core.Browser;
import core.CommonAction;
import io.selendroid.server.common.exceptions.StaleElementReferenceException;

public class OrderBlotterPage extends BasePage<HomePage> {

	@FindBy(css = "span[data-bind='text:ui.label']")
	private WebElement pageHeader;

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(6)>a>span")
	private List<WebElement> account;

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(7)>span")
	private List<WebElement> orderType;

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(10)>div")
	private List<WebElement> price;

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(9)>div")
	private List<WebElement> quantity;

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(8)>a>span")
	private List<WebElement> instrument;

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover JColResizer'] > thead > tr > th:nth-child(2)")
	private WebElement OrderIdTableHeader;

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(3)")
	private List<WebElement> exchIdList;
	
	@FindBy(css="table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(13)>span")
	private WebElement statusNegAcceptOrder;

	@FindBy (css = "span[data-bind='text: offset() + 1']")
	private WebElement firstValueOnPage;
	
	@FindBy (css = "span[data-bind='text: Math.min(offset()+limit(),count())']")
	private WebElement lastValueOnPage;
	
	@FindBy (css = "span[data-bind='text: count']")
	private WebElement lastRecordOnBlotter;
	
	@FindBy (css = "ul[class='pagination']>li:nth-child(3)>a")
	private WebElement nextPage;
	
	@FindBy (css = "table[class='table table-bordered table-condensed table-hover JColResizer']>thead>tr>th:nth-child(3)")
	private WebElement exchIdHeader;
	
	@FindBy (css = "table[id^=JColResizer]>tbody>tr>td:nth-child(3)")
	private List<WebElement> allExchIds;
	
	@FindBy (css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(2)>div")
	private List<WebElement> ordList;
	
	@FindBy (css = "table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(2)>div")
	private WebElement ordIdFirst;
	
	@FindBy (css = "table[class='table table-bordered table-condensed table-hover JColResizer']>thead>tr>th:nth-child(2)")
	private WebElement orderId;
	
	public static List<String> orderList = new ArrayList<String>();
	public static List<String> accountList = new ArrayList<String>();
	public static List<String> orderTypeList = new ArrayList<String>();
	public static List<String> instrumentList = new ArrayList<String>();
	public static List<String> quantityList = new ArrayList<String>();
	public static List<String> priceList = new ArrayList<String>();

	public OrderBlotterPage(Browser browser) throws Exception {
		super(browser);
	}

	public OrderBlotterPage setAllValue() throws Exception
	{
		Thread.sleep(2000);
		//webBrowser.getWebDriver().navigate().refresh();
		setOrderList();
		setAccountList();
		setOrderTypeList();
		setPriceList();
		setQuantityList();
		setInstrumentList();
		return new OrderBlotterPage(webBrowser);
	}

	public void setOrderList() {
		for (WebElement ordNo : ordList) {
			orderList.add(ordNo.getText());
		}
	}
	
	public void setAccountList() {
		for (WebElement accountnumber : account) {
			accountList.add(accountnumber.getText());
		}
	}

	public void setOrderTypeList() {
		for (WebElement orderTypetext : orderType) {
			orderTypeList.add(orderTypetext.getText());
		}
	}

	public void setPriceList() {
		for (WebElement pricetext : price) {
			priceList.add(pricetext.getText());
		}
	}

	public void setQuantityList() {
		for (WebElement quantitytext : quantity) {
			quantityList.add(quantitytext.getText());
		}
	}

	public void setInstrumentList() {
		for (WebElement instrumenttext : instrument) {
			instrumentList.add(instrumenttext.getText());
		}
	}

	public boolean verifyOrderEntryPageTitle() {
		if (pageHeader.getText().trim().equalsIgnoreCase("Order Entry"))
			return true;
		return false;
	}

	public boolean verifyOrderEntryDataGetMatchedSuccessfully(List<String> data) throws Exception {

		Reporter.log("TC_TrEntSimple_014");
		Thread.sleep(2000);
		if (!data.isEmpty()) {
			if (orderListVerify(data))
				return true;
			return false;
		}
		return false;
	}

	public boolean orderListVerify(List<String> orderlist) throws InterruptedException {		
		for (int count = 0; count < 3; count++) {
			if (verifyMorePagesAvailablility()) {
				Thread.sleep(3000);
				//webBrowser.getWebDriver().navigate().refresh();
				commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), ordIdFirst, 9);				
				//To get work around Stale element reference exception.
				 int i = 0;
				  //It will try 4 times to find same element using name.
				  while (i < 4) {
				   try {
					   for (WebElement ordNo : ordList) {
							orderList.add(ordNo.getText());
						}  
				   } catch (StaleElementReferenceException e) {
				    e.toString();
				    System.out.println("Trying to recover from a stale element:" + e.getMessage());
				    i = i + 1;
				   }
				   i = i + 4;
				  }			
			}
	 }		
		if (orderList.containsAll(orderlist))
			return true;
		return false;
	}

	public boolean verifyTestDataGetMatchedSuccessfully(String account,
			String ordertype, String instrument, String quantity, String price) {
		switch (ordertype) {
		case "1":
			ordertype = "Market";
			break;
		case "2":
			ordertype = "Limit";
			break;
		case "3":
			ordertype = "Stop";
			break;
		}
		if (//account(account) && 
				verifyOrderType(ordertype)
				&& verifyInstrument(instrument)
				)
			return true;
		return false;
	}

	public boolean account(String acct) {
		if (accountList.contains(acct))
			return true;
		return false;
	}

	public boolean verifyOrderType(String ordertyp) {

		if (orderTypeList.contains(ordertyp))
			return true;
		return false;
	}

	public boolean verifyInstrument(String instr) {

		if (instrumentList.contains(instr))
			return true;
		return false;
	}

	public boolean verifyQuantity(String qnty) {

		if (quantityList.contains(qnty))
			return true;
		return false;
	}

	public boolean verifyPrice(String prc) {

		if (priceList.contains("prc"))
			return true;
		return false;
	}

	public OrderBlotterPage arrangeOrdersDesc() throws Exception {
		CommonAction objaction = new CommonAction();
		objaction.waitElementToBeClickable(webBrowser.getWebDriver(), OrderIdTableHeader, 2);
		Thread.sleep(2000);
		OrderIdTableHeader.click();
		return new OrderBlotterPage(webBrowser);
	}

	public boolean verifyTestDataGetMatchedSuccessfullyRowwise(String clorId,
			String exchId, String account, String ordertype, String instrument,
			String quantity, String price, String buyorsell) throws InterruptedException {
		
		switch (ordertype) {
		case "1":
			ordertype = "Market";
			break;
		case "2":
			ordertype = "Limit";
			break;
		case "3":
			ordertype = "Stop";
			break;
		}
		
		switch (buyorsell) {
		case "1":
			Double temp1 = Double.parseDouble(quantity);
			DecimalFormat df1 = new DecimalFormat("#.00000");
			quantity = df1.format(temp1);
			break;
		case "2":
			Double temp2 = Double.parseDouble(quantity);
			DecimalFormat df2 = new DecimalFormat("-#.00000");
			quantity = df2.format(temp2);
			break;
		case "3":
			Double temp3 = Double.parseDouble(quantity);
			DecimalFormat df3 = new DecimalFormat("#.00000");
			quantity = df3.format(temp3);
			break;
		}

		List<String> testDataListParam = new ArrayList<String>();
		List<String> testDataListElementText = new ArrayList<String>();
		testDataListParam.add(exchId);
		testDataListParam.add(account);
		testDataListParam.add(ordertype);
		testDataListParam.add(instrument);
		testDataListParam.add(quantity);
		Double temp = Double.parseDouble(price);
		DecimalFormat df = new DecimalFormat("#.00000");
		String actualPrice = df.format(temp);
		testDataListParam.add(actualPrice);

		int count = 1;
		for (WebElement exchangeId : exchIdList) {
			if (exchangeId.getText().equalsIgnoreCase(exchId)) {
				testDataListElementText.add(exchId);
				testDataListElementText.add(dynamicCssCreatorForAccount(count)
						.getText());
				testDataListElementText
				.add(dynamicCssCreatorForOrderType(count).getText());
				testDataListElementText.add(dynamicCssCreatorForInstrument(
						count).getText());
				testDataListElementText.add(dynamicCssCreatorForQuantity(count)
						.getText());
				testDataListElementText.add(dynamicCssCreatorForPrice(count)
						.getText());
			}
			count++;
		}

		if (testDataListElementText.equals(testDataListParam))
			return true;
		return false;
	}

	public WebElement dynamicCssCreatorForAccount(int increment) {
		WebElement accountElement = webBrowser
				.getWebDriver()
				.findElement(
						By.cssSelector("table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child("
								+ increment + ")>td:nth-child(6)>a"));
		return accountElement;
	}

	public WebElement dynamicCssCreatorForOrderType(int increment) {
		WebElement orderTypeElement = webBrowser
				.getWebDriver()
				.findElement(
						By.cssSelector("table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child("
								+ increment + ")>td:nth-child(7)>span"));
		return orderTypeElement;
	}

	public WebElement dynamicCssCreatorForInstrument(int increment) {
		WebElement instrumentElement = webBrowser
				.getWebDriver()
				.findElement(
						By.cssSelector("table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child("
								+ increment + ")>td:nth-child(8)>a"));
		return instrumentElement;
	}

	public WebElement dynamicCssCreatorForQuantity(int increment) {
		WebElement quantityElement = webBrowser
				.getWebDriver()
				.findElement(
						By.cssSelector("table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child("
								+ increment + ")>td:nth-child(9)>div"));
		return quantityElement;
	}

	public WebElement dynamicCssCreatorForPrice(int increment) {
		WebElement priceElement = webBrowser
				.getWebDriver()
				.findElement(
						By.cssSelector("table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child("
								+ increment + ")>td:nth-child(10)>div"));
		return priceElement;
	}

	public boolean verifyStatusOfNegOrder() throws InterruptedException
	{
		Thread.sleep(1000);
		if(statusNegAcceptOrder.getText().trim().equalsIgnoreCase("Filled"))
			return true;
		return false;		
	}
	
	public boolean verifyCancelStatusNegOrder()
	{
		webBrowser.getWebDriver().navigate().refresh();
	
		if(statusNegAcceptOrder.getText().trim().equalsIgnoreCase("Canceled") || statusNegAcceptOrder.getText().trim().equalsIgnoreCase("PendingCanceled"))
			return true;
		return false;
	}
	public boolean startJenkinsBuild() throws Exception
	{
		if(commonAction.getResultStatusFromBuild())
		{
			String allRequest = commonAction.getRequestText();
			Writer requestWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PropertyDictionary.map.get("request")), "utf-8"));
			requestWriter.write(allRequest);
			requestWriter.close();
			
			String allResponse = commonAction.getResponseText();
			Writer responseWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PropertyDictionary.map.get("response")), "utf-8"));
			responseWriter.write(allResponse);
			responseWriter.close();
			return true;
		}
		else
			return false;
	}
	
	public boolean verifyMorePagesAvailablility()
	{
		int firstPageNumberOnPage = Integer.parseInt(firstValueOnPage.getText());
		int lastPageNumberOnPage = Integer.parseInt(lastValueOnPage.getText());
		int lastPageOfBlotter = Integer.parseInt(lastRecordOnBlotter.getText());
		if(lastPageNumberOnPage<lastPageOfBlotter && lastPageNumberOnPage != lastPageOfBlotter )
		{
			nextPage.click();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean clickOnExchIdForSortData() throws Exception
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(5000);
		exchIdHeader.click();
		Thread.sleep(8000);
		if(exchIdHeader.isDisplayed())
			return true;
		return false;
	}
	
	public boolean clickOnOrderIdForSortData() throws Exception
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(5000);
		orderId.click();
		Thread.sleep(8000);
		if(orderId.isDisplayed())
			return true;
		return false;
	}
	
	public Boolean searchAndVerifyForExchangeId(String clorId,
			String exchId, String account, String ordertype, String instrument,
			String quantity, String price, String buyorsell) throws Exception
	{
		Thread.sleep(2000);
		boolean flag = false;
		List<String> exchangeIdList = new ArrayList<String>();
		for(WebElement exchangeId : allExchIds)
		{
			exchangeIdList.add(exchangeId.getText());
		}
		
		if(exchangeIdList.contains(exchId))
		{
			flag =  verifyTestDataGetMatchedSuccessfullyRowwise(clorId,
					exchId,  account, ordertype,  instrument,
					quantity,  price, buyorsell);
		}
		
		else
		{
			if(verifyMorePagesAvailablility())
			{
				Thread.sleep(6000);
				flag =  searchAndVerifyForExchangeId(clorId,
						exchId,  account, ordertype,  instrument,
						quantity,  price, buyorsell);
			}
		}
		return flag;
	}

}
