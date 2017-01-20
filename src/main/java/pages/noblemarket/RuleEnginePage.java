package pages.noblemarket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;
import pages.noblemarket.datamodal.AdminPageDataModel;
import utilities.PropertyDictionary;

public class RuleEnginePage extends BasePage<RuleEnginePage>  {

	@FindBy (css = "ul[class='nav navbar-nav']>li:nth-child(6)")
	private WebElement middleOffice;

	@FindBy (css = "li[class='dropdown open']>ul>li:nth-child(1)")
	private WebElement rulesEngine;

	@FindBy (css = "button[class='btn btn-warning pull-left']")
	private WebElement add;

	@FindBy (css = "select[class='form-control']")
	private WebElement ruleTypeDropdown;

	@FindBy (css = "select[class='form-control']>option")
	private WebElement ruleTypeOption;

	@FindBy (css = "button[class='btn btn-warning btn-sm pull-right']")
	private List<WebElement> addRule;

	@FindBy (css = "div[class='controls']>div[class='input-group input-group-sm ']>select")
	private WebElement attribute;

	@FindBy (css  = "input[type='radio']")
	private List<WebElement> conditionRadios;

	@FindBy (css = "input[class='form-control typeahead-box input-sm']")
	private WebElement valuefield;

	@FindBy (css = "div[class='max-enum-field']>div>select")
	private WebElement valuedropdown;

	@FindBy (css = "div[class='max-enum-field']>div>select>option")
	private WebElement valueOption;

	@FindBy (css = "table[class='table table-bordered table-condensed table-striped table-hover']>tbody>tr>td:nth-child(2)>div>select")
	private WebElement feeType;

	@FindBy (css = "table[class='table table-bordered table-condensed table-striped table-hover']>tbody>tr>td:nth-child(2)>div>select")
	private WebElement feeTypeOption;

	@FindBy (css = "input[data-bind*='value_num:']")
	private WebElement factor;

	@FindBy (css = "input[data-bind^='jqueryui']")
	private List<WebElement> currencyAndAccount;

	@FindBy (css = "button[title='Save changes']")
	private WebElement saveChanges;

	@FindBy (css = "button[class='btn btn-success']")
	private WebElement list;

	@FindBy(css="table[class='table table-bordered table-condensed table-hover']>tbody>tr>td:nth-child(2)>span")
	private List<WebElement> ruleEngineNames;

	@FindBy (css = "input[class='form-control']")
	private WebElement ruleName;

	@FindBy (css = "input[type='checkbox']")
	private WebElement isActive;

	@FindBy (css = "li[data-value='4:A1QA']")
	private WebElement valueList;

	@FindBy (css = "li[class='active']")
	private List<WebElement> valueListForAccount;
	
	@FindBy (css = "li[class='notactive']>ul>li>a")
	private WebElement addedTradeType;
	
	@FindBy (css = "button[title='edit item']")
	private WebElement editItem;

	public RuleEnginePage(Browser browser) throws Exception {
		super(browser);
		initDataModel(new AdminPageDataModel());
		// TODO Auto-generated constructor stub
	}

	public RuleEnginePage navigateToRuleEnginePage() throws Exception
	{
		Thread.sleep(5000);
		middleOffice.click();
		Thread.sleep(5000);
		rulesEngine.click();
		Thread.sleep(5000);
		return new RuleEnginePage(webBrowser);
	}

	public RuleEnginePage clickOnAdd() throws Exception
	{
		add.click();
		Thread.sleep(5000);
		return new RuleEnginePage(webBrowser);
	}

	public String addingTheNewRuleForTradeType(String attributeValue, String condition, String value, String feeTypeForAddFactor) throws Exception
	{
		String nameOfRule = attributeValue+condition+value+feeTypeForAddFactor;
		Thread.sleep(2000);
		commonAction.selectDropdown(ruleTypeDropdown, "Fee");
		addRule.get(0).click();
		ruleName.clear();
		ruleName.sendKeys(nameOfRule);
		Thread.sleep(2000);
		isActive.click();
		attribute.click();
		commonAction.selectDropdown(attribute, attributeValue);

		switch (condition.toLowerCase()) {
		case "equals":
			conditionRadios.get(0).click();
			break;
		case "not equal":
			conditionRadios.get(1).click();
			break;
		case "greater than":
			conditionRadios.get(2).click();
			break;
		case "less than":
			conditionRadios.get(3).click();
			break;
		}

		if(attributeValue.equalsIgnoreCase("trade type") || attributeValue.equalsIgnoreCase("instrument type") || attributeValue.equalsIgnoreCase("buy sell") ||
				attributeValue.equalsIgnoreCase("maturity") || attributeValue.equalsIgnoreCase("mirror trades"))
		{
			commonAction.selectDropdown(valuedropdown, value);
		}

		else
		{
			valuefield.sendKeys(value);
			valueList.click();
		}
		addRule.get(1).click();
		feeType.click();
		commonAction.selectDropdown(feeTypeOption, feeTypeForAddFactor);
		Thread.sleep(2000);
		factor.sendKeys(PropertyDictionary.map.get("FactorForAcquirerName"));
		saveChanges.click();
		return nameOfRule;
	}
	
	public String addingTheNewRule(String attributeValue, String condition, String value, String feeTypeForAddFactor) throws Exception
	{
		String nameOfRule = attributeValue+condition+value+feeTypeForAddFactor;
		Thread.sleep(2000);
		addedTradeType.click();
		editItem.click();
		//commonAction.selectDropdown(ruleTypeDropdown, "Fee");
		addRule.get(0).click();
		//ruleName.clear();
		//ruleName.sendKeys(nameOfRule);
		//Thread.sleep(2000);
		//isActive.click();
		attribute.click();
		commonAction.selectDropdown(attribute, attributeValue);

		switch (condition.toLowerCase()) {
		case "equals":
			conditionRadios.get(0).click();
			break;
		case "not equal":
			conditionRadios.get(1).click();
			break;
		case "greater than":
			conditionRadios.get(2).click();
			break;
		case "less than":
			conditionRadios.get(3).click();
			break;
		}

		if(attributeValue.equalsIgnoreCase("trade type") || attributeValue.equalsIgnoreCase("instrument type") || attributeValue.equalsIgnoreCase("buy sell") ||
				attributeValue.equalsIgnoreCase("maturity") || attributeValue.equalsIgnoreCase("mirror trades"))
		{
			commonAction.selectDropdown(valuedropdown, value);
		}

		else
		{
			valuefield.sendKeys(value);
			valueList.click();
		}
		addRule.get(1).click();
		feeType.click();
		commonAction.selectDropdown(feeTypeOption, feeTypeForAddFactor);
		Thread.sleep(2000);
		factor.sendKeys(PropertyDictionary.map.get("FactorForAcquirerName"));
		saveChanges.click();
		return nameOfRule;
	}

	public RuleEnginePage navigateToList() throws Exception
	{
		webBrowser.getWebDriver().navigate().refresh();
		Thread.sleep(12000);
		
		list.click();
		return new RuleEnginePage(webBrowser);
		
	}

	public boolean verifyRuleEngineName(String name) throws Exception
	{
		list.click();
		Thread.sleep(5000);
		List<String>ruleNames=new ArrayList<String>();
		
		for (WebElement element : ruleEngineNames) {
			ruleNames.add(element.getText());	

		}
		if(ruleNames.contains(name))
			return true;
		return false;

	}
	
	
/*	*/
}
