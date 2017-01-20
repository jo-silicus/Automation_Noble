package pages.noblemarket;


import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;
import pages.noblemarket.datamodal.AdminPageDataModel;
import utilities.PropertyDictionary;

public class AdminPage extends BasePage<AdminPage> {

	@FindBy(name = "email")
	private WebElement emailId;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(css = "button[type='submit']")
	private List<WebElement> signIn;

	@FindBy(css = "div[id='bs-example-navbar-collapse-1']>ul>li>a>span")
	private List<WebElement> adminMenu;

	@FindBy(css = "div[id='bs-example-navbar-collapse-1']>ul>li[class='dropdown open']>ul>li>a")
	private List<WebElement> clientMenu;

	@FindBy(css = "div[class='input-group input-group-sm']>input[id='appendedInputButtons']")
	private WebElement clientSearchBox;

	@FindBy(css = "div[class='input-group input-group-sm']>span[class='input-group-btn']>button")
	private List<WebElement> clientSearchButton;

	@FindBy(css = "div[class='col-xs-12 table-overflow']>button")
	private WebElement addClientPerson;

	@FindBy(css = "div[class='max-boolean-field']>input[data-bind='checked:do_a_full_registration_process']")
	private WebElement doFullRegisistration;

	@FindBy(id = "company_name_fld")
	private WebElement companyName;

	@FindBy(id = "firstname_fld")
	private WebElement firstName;

	@FindBy(id = "lastname_fld")
	private WebElement lastName;

	@FindBy(id = "email_fld")
	private WebElement emailFieldClient;

	@FindBy(css = "div[class='btn-group']>button[title='Save changes']")
	private WebElement saveChangesClientPerson;

	@FindBy(css = "div>button[title='edit item']")
	private WebElement edit;
	
	//@FindBy(css="div>table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child(1)>td:nth-child(1)")
	@FindBy(css = "div>table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr:nth-child(1)")
	private WebElement clientRow;

	@FindBy(css = "div>button[data-bind*='enable_client_person']")
	private WebElement enableClient;

	@FindBy(css = "div>button[data-bind*='approve_registration']")
	private WebElement approveRegistration;

	@FindBy(linkText = "Personal Information")
	private WebElement personalInfoTab;

	@FindBy(linkText = "Entity information")
	private WebElement entityInfoTab;

	@FindBy(linkText = "Account info")
	private WebElement accountInfoTab;

	@FindBy(linkText = "Registration Files")
	private WebElement registrationFilesTab;

	// personalInfoTab -----------------------------------

	@FindBy(id = "Business Phone_fld")
	private WebElement businessPhone;

	@FindBy(id = "Home Phone_fld")
	private WebElement homePhone;

	@FindBy(id = "Mobile Phone_fld")
	private WebElement mobilePhone;

	@FindBy(id = "Street Address 1_fld")
	private WebElement homeAddress;

	@FindBy(id = "Street Address 2_fld")
	private WebElement streetAddress;

	@FindBy(id = "Postcode_fld")
	private WebElement zipCode;

	@FindBy(id = "Email_fld")
	private WebElement emailfld;

	@FindBy(id = "DOB_fld")
	private WebElement dobField;

	@FindBy(id = "Country of Citizenship_fld")
	private WebElement country;

	@FindBy(id = "Country of Birth_fld")
	private WebElement birthCountry;

	@FindBy(id = "ID_1_no_fld")
	private WebElement id1fld;

	@FindBy(id = "ID_1_country_issue_fld")
	private WebElement id1Countryfld;

	@FindBy(id = "ID_2_no_fld")
	private WebElement id2fld;

	@FindBy(id = "ID_2_country_issue_fld")
	private WebElement id2Countryfld;

	@FindBy(id = "City_fld")
	private WebElement cityPersonalInfo;

	@FindBy(id = "Country_fld")
	private WebElement countryPersonalInfo;

	@FindBy(id = "Maiden Name_fld")
	private WebElement maidenName;

	// Entity Information --------------------------------------

	@FindBy(id = "Fax_fld")
	private WebElement fax;

	@FindBy(id = "Phone_fld")
	private WebElement phonefld;

	@FindBy(id = "Alt Phone_fld")
	private WebElement altPhonefld;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Street Address 1_fld']")
	private WebElement postalAddress;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Postcode_fld']")
	private WebElement zipPostal;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Country_fld']")
	private WebElement countryEntity;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Jurisdiction_fld']")
	private WebElement jusrisdiction;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Employer Identification Number_fld']")
	private WebElement employerId;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Trademark Name_fld']")
	private WebElement tradeMarkName;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Transfer Call back Phone_fld']")
	private WebElement transferCallback;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Transfer Call back Email_fld']")
	private WebElement transferEmail;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Registered Address 1_fld']")
	private WebElement regdAddressfld;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Registered City_fld']")
	private WebElement regdCity;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Registered Postcode_fld']")
	private WebElement regdPostalCode;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='Registered Country_fld']")
	private WebElement regdCountry;

	@FindBy(css = "div>button[data-bind*='create_entity_gateway_id']")
	private WebElement createGatewayId;

	@FindBy(css = "div[class='notifications top-left']>div>a")
	private WebElement notificationMsgClose;

	@FindBy(css = "div[class='form-group has-error']>div>div>input[id='City_fld']")
	private WebElement cityEntity;

	@FindBy(css = "div[class='col-sm-offset-5 controls']>div[class='max-text-field']>textarea")
	private WebElement sourceOfFund;

	// Account Information
	@FindBy(id = "name_fld")
	private WebElement accountName;

	@FindBy(id = "account_no_fld")
	private WebElement accountId;

	@FindBy(id = "payment_code_one_ref_fld")
	private WebElement code1;

	@FindBy(id = "addrLine1_fld")
	private WebElement address1;

	@FindBy(id = "addrCity_fld")
	private WebElement addressCity;

	@FindBy(id = "addrPostCode_fld")
	private WebElement postalZip;

	@FindBy(id = "accountCountry_fld")
	private WebElement accountCountry;

	@FindBy(id = "account_no_fld")
	private WebElement walletAddress;

	// Registration Files

	@FindBy(css = "table[class='table table-bordered table-condensed table-hover']>tbody>tr")
	private List<WebElement> registrationFile;

	@FindBy(css = "table[class='table table-bordered table-condensed table-striped']>tbody>tr>td>input[type='checkbox']")
	private WebElement approvedCheckbox;

	@FindBy(css = "div>button[data-bind='click:save_row']")
	private WebElement update;

	// Approval buttons

	@FindBy(css = "button[data-bind*='tech_pack_sent']")
	private WebElement techPackSent;

	@FindBy(css = "button[data-bind*='approve_kyc_modal']")
	private WebElement approveKYC;

	@FindBy(css = "button[data-bind*='approve_kyc'][data-bind*='true']")
	private WebElement proceedWithAccountSetUP;

	@FindBy(css = "button[data-bind*='account_completed']")
	private WebElement accountCompleted;

	@FindBy(css = "button[data-bind*='approve_registration']")
	private WebElement approveRegd;

	public AdminPage(Browser browser) throws Exception {
		super(browser);
		initDataModel(new AdminPageDataModel());
		// TODO Auto-generated constructor stub
	}

	public AdminPage logIns() throws Exception {
		emailId.sendKeys("admin");
		password.sendKeys("admin");
		signIn.get(0).click();
		return new AdminPage(webBrowser);

	}

	public AdminPage navigateToClientKYC() throws Exception {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), adminMenu.get(3), 5);
		adminMenu.get(3).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), clientMenu.get(0), 5);
		clientMenu.get(0).click();
		return new AdminPage(webBrowser);

	}

	public AdminPage navigateToClientPerson() throws Exception {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), adminMenu.get(2), 5);
		adminMenu.get(2).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), clientMenu.get(8), 5);
		clientMenu.get(8).click();
		return new AdminPage(webBrowser);
	}
	
	
	public String fillClientPersonInfo() throws Exception {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), addClientPerson, 5);
		addClientPerson.click();
		JavascriptExecutor jse= (JavascriptExecutor)webBrowser.getWebDriver();
		jse.executeScript("window.scrollBy(0,-250)","");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), doFullRegisistration,5);
		doFullRegisistration.click();
		String randomEmail = commonAction.randomEmailGenerate();
		String company = randomEmail.split("@")[0].trim();
		companyName.sendKeys(company);
		firstName.sendKeys(PropertyDictionary.map.get("FirstName"));
		lastName.sendKeys(PropertyDictionary.map.get("LastName"));
		emailFieldClient.sendKeys(randomEmail);
		//Scroll Up for Linux execution SaveChanges Button to be visible
		//For Linux execution Set Window dimension.
		Dimension ds = new Dimension(720,634);
		webBrowser.getWebDriver().manage().window().setSize(ds);
		//JavascriptExecutor jse= (JavascriptExecutor)webBrowser.getWebDriver();
		jse.executeScript("window.scrollBy(0,-250)","");
		Thread.sleep(4000);
		saveChangesClientPerson.click();
		Thread.sleep(4000);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), edit,15);
		return randomEmail;
	}

	
	public AdminPage searchClientPersonAndEnable(String mailID) throws Exception
	{
		//For Linux execution Set Window dimension.
		Dimension ds = new Dimension(752,640);
		webBrowser.getWebDriver().manage().window().setSize(ds);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), clientSearchBox,5);
		clientSearchBox.sendKeys(mailID);
		Thread.sleep(1000);
		clientSearchButton.get(0).click();
		Thread.sleep(3000);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), clientRow,5);
		Thread.sleep(3000);
		clientRow.click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), enableClient, 5);
		enableClient.click();
		return new AdminPage(webBrowser);
	}

	// personal information data :
	public boolean fillPersonalInformation(String mailfield) throws Exception {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), approveRegistration, 5);
		personalInfoTab.click();
		edit.click();

		businessPhone.sendKeys(commonAction.randomPhoneNumberGenerate());
		homePhone.sendKeys(commonAction.randomPhoneNumberGenerate());
		mobilePhone.sendKeys(commonAction.randomPhoneNumberGenerate());
		homeAddress.sendKeys(PropertyDictionary.map.get("HomeAddress"));
		streetAddress.sendKeys(PropertyDictionary.map.get("HomeAddress"));
		zipCode.sendKeys("123456");
		emailfld.sendKeys(mailfield);
		dobField.sendKeys("11 AUG 1981");
		country.sendKeys("India");
		birthCountry.sendKeys("India");
		id1Countryfld.sendKeys("9111");
		id1fld.sendKeys("111");
		id2fld.sendKeys("1212");
		id2Countryfld.sendKeys("1111");
		cityPersonalInfo.sendKeys("Pune");
		countryPersonalInfo.sendKeys("India");
		maidenName.sendKeys(PropertyDictionary.map.get("MaidenName"));
		JavascriptExecutor jse = (JavascriptExecutor) webBrowser.getWebDriver();
		jse.executeScript("scroll(0, -250);");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), saveChangesClientPerson, 20);
		Thread.sleep(3000);
		saveChangesClientPerson.click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), edit, 15);
		if (edit.isDisplayed())
			return true;
		return false;
	}

	// Entity information data:

	public boolean fillEntityInformation() throws Exception {
		webBrowser.getWebDriver().navigate().refresh();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), entityInfoTab, 10);
		entityInfoTab.click();

		edit.click();
		Thread.sleep(3000);

		fax.sendKeys(commonAction.randomPhoneNumberGenerate());
		phonefld.sendKeys(commonAction.randomPhoneNumberGenerate());
		altPhonefld.sendKeys(commonAction.randomPhoneNumberGenerate());
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), postalAddress, 3);
		postalAddress.sendKeys(PropertyDictionary.map.get("HomeAddress"));
		zipPostal.sendKeys("123456");
		countryEntity.sendKeys("India");
		jusrisdiction.sendKeys("Pune");
		employerId.sendKeys("420786");
		tradeMarkName.sendKeys("SilicusTradeMark");
		transferCallback.sendKeys(commonAction.randomPhoneNumberGenerate());
		transferEmail.sendKeys(commonAction.randomEmailGenerate());
		regdAddressfld.sendKeys(PropertyDictionary.map.get("HomeAddress"));
		regdCity.sendKeys("Pune");
		regdPostalCode.sendKeys("123456");
		regdCountry.sendKeys("India");
		cityEntity.sendKeys("Pune");
		sourceOfFund.sendKeys("SourceOfFund");
		//Scroll Up for Linux execution SaveChanges Button to be visible
		JavascriptExecutor jse = (JavascriptExecutor)webBrowser.getWebDriver();		
		jse.executeScript("scroll(0, -250);");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), saveChangesClientPerson, 9);
		saveChangesClientPerson.click();
		((JavascriptExecutor) webBrowser.getWebDriver()).executeScript("window.onbeforeunload = function(e){};");

		webBrowser.getWebDriver().navigate().refresh();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), edit, 15);
		createGatewayId.click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), notificationMsgClose, 3);
		notificationMsgClose.click();
		if (edit.isDisplayed())
			return true;
		return false;
	}

	public boolean fillAccountInformation() throws Exception {
		accountInfoTab.click();
		edit.click();
		// accountName.sendKeys(PropertyDictionary.map.get("FirstName"));
		accountId.sendKeys("786786786");
		code1.sendKeys("021502011");
		address1.sendKeys(PropertyDictionary.map.get("HomeAddress"));
		addressCity.sendKeys("Pune");
		postalZip.sendKeys("121212");
		accountCountry.sendKeys("India");
		walletAddress.sendKeys("2NDPdDRzd21e6E89Mpzwq5t3bkdCky4Q6YB");
		//For Linux execution Set Window dimension.
		Dimension ds = new Dimension(720,634);
		webBrowser.getWebDriver().manage().window().setSize(ds);
		//Scroll Up for Linux execution SaveChanges Button to be visible
		JavascriptExecutor jse = (JavascriptExecutor)webBrowser.getWebDriver();
		jse.executeScript("scroll(0, -250);");
		saveChangesClientPerson.click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), edit, 4);
		if (edit.isDisplayed())
			return true;
		return false;
	}

	public boolean approveRegistrationFiles() throws Exception {
		webBrowser.getWebDriver().navigate().refresh();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), registrationFilesTab, 10);
		registrationFilesTab.click();
		edit.click();
		Thread.sleep(2000);		
		for(int i=0;i<11;i++)
		{
			Thread.sleep(2000);
			registrationFile.get(i).click();
			commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), approvedCheckbox, 5);
			approvedCheckbox.click();
			commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), update, 4);
			update.click();
		}
		Thread.sleep(2000);
		//Scroll Up for Linux execution SaveChanges Button to be visible
		JavascriptExecutor jse = (JavascriptExecutor)webBrowser.getWebDriver();
		jse.executeScript("scroll(0, -250);");
		saveChangesClientPerson.click();

		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), edit, 4);
		if (edit.isDisplayed())
			return true;
		return false;
	}

	public boolean approveAllKYC() throws Exception {
		webBrowser.getWebDriver().navigate().refresh();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), techPackSent, 10);
		techPackSent.click();
		webBrowser.getWebDriver().navigate().refresh();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), approveKYC, 10);
		approveKYC.click();
		Thread.sleep(1000);
		proceedWithAccountSetUP.click();
		webBrowser.getWebDriver().navigate().refresh();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), accountCompleted, 25);
		accountCompleted.click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), approveRegistration, 10);
		approveRegd.click();
		Thread.sleep(2000);
		if (enableClient.getText().trim().equalsIgnoreCase("Disable Client")) 
			return true;		
		return false;	
	}

}
