package pages.noblemarket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem.Info;

import core.Browser;
import pages.common.BasePage;
import pages.noblemarket.datamodal.KYCDataModel;
import utilities.BoxApiServiceImpl;
import utilities.PropertyDictionary;

public class KYCPage extends BasePage<KYCPage>{
	
	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(className="login-heading")
	private WebElement loginHeading;
	
	@FindBy (css = "button[type='submit']")
	private WebElement nextButton;
	
	@FindBy (css = "button[type='submit']")
	private WebElement title;
	
	@FindBy (css = "select[data-ng-model='personalInfoModel.contact_information.title']")
	private WebElement salutation;
	
	@FindBy (name = "firstName")
	private WebElement firstName;
	
	@FindBy (name = "lastName")
	private WebElement lastName;
	
	@FindBy (css = "input[data-ng-model='personalInfoModel.contact_information.maiden_Name']")
	private WebElement maidenName;
	
	@FindBy (css = "span[class='input-group-addon']")
	private List<WebElement> calender;
	
	@FindBy (css = "h3[class='heading']")
	private List<WebElement> selectedHeader;
	
	@FindBy (css = "input[data-ng-model='personalInfoModel.nationalIdentifier']")
	private WebElement nationalidentifier;
	
	@FindBy (name = "citizenshipCountry")
	private WebElement citizenshipCountry;
	
	@FindBy (css = "input[name='homeAddress']")
	private WebElement homeAddress;
	
	@FindBy (css = "input[data-ng-model='personalInfoModel.contact_address.streetAddress2']")
	private WebElement streetAddress2;
	
	@FindBy (id = "country")
	private WebElement country;
	
	@FindBy (id = "state")
	private WebElement state;

	@FindBy (name = "city")
	private WebElement city;
	
	@FindBy (name = "zip")
	private WebElement zip;
	
	@FindBy (name = "businessPhone")
	private WebElement businessPhone;
	
	@FindBy (css = "input[data-ng-model='KYCModel.personalInfoModel.mobilePhone']")
	private WebElement mobilePhone;
	
	@FindBy (name = "email")
	private WebElement emailOnContactDetails;
	
	@FindBy (css = "select[name='documentType']")
	private WebElement documentType;
	
	@FindBy (css = "select[name='idType']")
	private WebElement idType;
	
	@FindBy (name = "idNo")
	private WebElement idNo;
	
	@FindBy (name = "idCountry")
	private WebElement idCountry;
	
	@FindBy (id = "mobilePhone")
	private WebElement mobileNo;
	
	@FindBy (css = "input[id='file']")
	private WebElement upload1;
	
	@FindBy (css = "input[id='entityFile']")
	private WebElement upload2;
	
	@FindBy (css = "input[id='eddFile']")
	private WebElement upload3;
	
	@FindBy (css = "button[class='btn btn-default marginr25 btn-save']")
	private List<WebElement> save;
	
	@FindBy (css = "button[class='btn btn-forgot p-next']")
	private WebElement next;
	
	// Entity Information Elements :
	
	@FindBy (name = "companyName")
	private WebElement companyName;
	
	@FindBy (name = "employerId")
	private WebElement employerId;
	
	@FindBy (name = "baseCurrency")
	private WebElement selectBaseCurrency;
	
	@FindBy (name = "typeOfBusiness")
	private WebElement selectTypeOfBusiness;
	
	@FindBy (name = "sourceOfFunds")
	private WebElement sourceOfFund;
	
	@FindBy (name = "hqAddress")
	private WebElement hqAddress;
	
	@FindBy (name = "hqCountry")
	private WebElement selectHqCountry;
	
	//div[class='ac-container']>ul>li>a>div>table>tbody>tr>td
	@FindBy (css = "div[class='ac-container']>ul>li>a>div>table>tbody>tr>td")
	private WebElement clickHqCountry;
	
	@FindBy (name = "hqCity")
	private WebElement hqCity;
	
	@FindBy (name = "hqZip")
	private WebElement hqZip;
	
	@FindBy (css = "div>input[type='checkbox'][data-ng-model='sameasAbove']")
	private WebElement sameAsAbove;
	
	@FindBy (name = "phone")
	private WebElement phone;
	
	@FindBy (name = "alternatePhone")
	private WebElement alternatePhone;
	
	@FindBy (name = "transferCallbackPhone")
	private WebElement alternatecallPhone;
	
	@FindBy (name = "callbackEmail")
	private WebElement callBackEmail;
	
	@FindBy (name = "entityDocUpload")
	private WebElement selectEntityDocUpload;
	
	@FindBy (name = "enhancedDueDiligence")
	private WebElement selectEnhancedDueDiligence;
	
	@FindBy (css = "div[class='form-group margint16']>span[data-ng-model='entityInfoModel.file']")
	private WebElement btnEntityDocUpload;
	
	@FindBy (css = "div[class='form-group margint16 lbl-space']>span[class='btn btn-upload btn-file']")
	private WebElement btnEnhancedDueDiligence;
	
	@FindBy (css = "button[class='btn btn-forgot e-next']")
	private WebElement entityNext;
	
	// Account Information ;
	
	@FindBy (css = "div[class='form-group']>select[data-ng-model='accountInfoModel.account_type']")
	private WebElement selectAccountPurpose;
	
	@FindBy (name = "accountName")
	private WebElement accountName;
	
	@FindBy (name = "paymentType")
	private WebElement selectPaymentType;
	
	@FindBy (name = "bankCode")
	private WebElement bankCode;
	
	@FindBy (name = "bank")
	private WebElement bank;
	
	@FindBy (name = "address1")
	private WebElement accountAddress;
	
	@FindBy (name = "city")
	private List<WebElement> accountcity;
	
	@FindBy (name = "zip")
	private List<WebElement> accountZip;
	
	@FindBy (css="a[class='ng-binding']>div")
	private List<WebElement> countryList;
	
	@FindBy (name = "corrBankCode")
	private WebElement corrBankCode;
	
	@FindBy (name = "corrPaymentType")
	private WebElement selectCorrPaymentType;
	
	@FindBy (name = "corrBank")
	private WebElement corrBank;
	
	@FindBy (css = "button[class='btn btn-save']")
	private List<WebElement> accountSaveBtn;
	
	@FindBy (id = "btYes")
	private WebElement btYes;
	
	@FindBy (css = "div[class='modal-footer']>button[data-ng-click='Logout()']")
	private WebElement btOK;
	
	@FindBy (css = "table[class='table table-responsive']>tbody>tr>td")
	private WebElement uploadedDocGrid;
	
	@FindBy (css = "table[class='table table-responsive']>tbody[ng-repeat='doc in docEntityArray']>tr>td")
	private WebElement uploadedDocGridEntity;
	
	@FindBy (css = "table[class='table table-responsive']>tbody[ng-repeat='doc in docEDDArray']>tr>td")
	private WebElement uploadedDocGridEdd;
	
	@FindBy (id = "loaderDiv")
	private WebElement loaderDiv;

	//private boolean fasle;
	
	
	public KYCPage(Browser browser) throws Exception {
		super(browser);
		initDataModel(new KYCDataModel());
	}
	
	public String enterCredentials() throws Exception
	{
		String randomEmail=commonAction.randomEmailGenerate();
		email.sendKeys(randomEmail);
		nextButton.click();
		Thread.sleep(5000);
		return randomEmail;
	}
	
	public boolean VerifyPersonalInformationTabSelectedDefault()
	{
		if(selectedHeader.get(0).getText().equals("Personal Details"))
			return true;
		return false;
	}
	
	public KYCPage fillPersonalInformationForm() throws Exception
	{
		Reporter.log("TC_KYC_002");
		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), salutation, 5000);
		commonAction.selectDropdown(salutation, "Mr");
		firstName.sendKeys(PropertyDictionary.map.get("FirstName"));
		lastName.sendKeys(PropertyDictionary.map.get("LastName"));
		maidenName.sendKeys(PropertyDictionary.map.get("MaidenName"));
		calender.get(0).click();		
		//calender
		nationalidentifier.sendKeys(PropertyDictionary.map.get("NationalIden"));
	/*	CharSequence[] Country={"I","n","d","i","a"};
		for(int i=0;i<Country.length;i++)
		{
			commonAction.Wait(500);
			citizenshipCountry.sendKeys(Country[i]);
		}*/
		citizenshipCountry.sendKeys("Ind");
		citizenshipCountry.sendKeys("i");
		commonAction.Wait(1000);
		citizenshipCountry.sendKeys("a");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), countryList.get(0), 30000);
		countryList.get(0).click();
		homeAddress.sendKeys(PropertyDictionary.map.get("HomeAddress"));
		streetAddress2.sendKeys("testname");
		/*for(int i=0;i<Country.length;i++)
		{
			commonAction.Wait(500);
			country.sendKeys(Country[i]);
		}	*/
		country.sendKeys("Ind");
		country.sendKeys("i");
		commonAction.Wait(1000);
		country.sendKeys("a");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), countryList.get(0), 30000);
		countryList.get(0).click();
		state.sendKeys("Maharashtra");
		city.sendKeys("testname");
		zip.sendKeys("123456");
		businessPhone.sendKeys("12234567890");
		emailOnContactDetails.sendKeys("testname@test.com");
		commonAction.selectDropdown(documentType, "Proof of ID 1");
		commonAction.selectDropdown(idType, PropertyDictionary.map.get("Passport")); //Passport
		idNo.sendKeys("123456");	
		idCountry.sendKeys("Ind");
		idCountry.sendKeys("i");
		commonAction.Wait(1000);
		idCountry.sendKeys("a");
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), countryList.get(0), 30000);
		countryList.get(0).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), calender.get(1), 9000);	
		calender.get(1).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), calender.get(2), 9000);
		calender.get(2).click();	
		String filePath = System.getProperty("user.dir")+PropertyDictionary.map.get("UploadPath");
		System.out.println(filePath);
		upload1.sendKeys(filePath);	
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), uploadedDocGrid, 9000);
		save.get(1).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), next, 9000);
		next.click();		
		return new KYCPage(webBrowser);
	}
	
	
	public KYCPage fillPersonalInformationFormForEditableMode() throws Exception
	{
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), salutation, 5000);
		commonAction.selectDropdown(salutation, "Mr");
		firstName.sendKeys(PropertyDictionary.map.get("FirstName"));
		lastName.sendKeys(PropertyDictionary.map.get("LastName"));
		maidenName.sendKeys(PropertyDictionary.map.get("MaidenName"));
		calender.get(0).click();		
		//calender
		nationalidentifier.sendKeys(PropertyDictionary.map.get("NationalIden"));
		citizenshipCountry.sendKeys("India");
		homeAddress.sendKeys(PropertyDictionary.map.get("HomeAddress"));
		streetAddress2.sendKeys("testname");
		country.sendKeys("India");		
		state.sendKeys("Maharashtra");
		city.sendKeys("testname");
		zip.sendKeys("123456");
		businessPhone.sendKeys("12234567890");
		emailOnContactDetails.sendKeys("testname@test.com");
		commonAction.selectDropdown(documentType, "Proof of ID 1");
		commonAction.selectDropdown(idType, PropertyDictionary.map.get("Passport")); //Passport
		idNo.sendKeys("123456");	
		idCountry.sendKeys("India");		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), calender.get(1), 9000);	
		calender.get(1).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), calender.get(2), 9000);
		calender.get(2).click();	
		upload1.sendKeys(PropertyDictionary.map.get("UploadPath"));	
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), uploadedDocGrid, 9000);
		save.get(1).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), next, 9000);
		next.click();		
		return new KYCPage(webBrowser);
	}
	
	
	public boolean verifyEditableUrl( String randomEmail)
	{
		String encryptedUrl = commonAction.getEncryptedUrl(randomEmail);		
		webBrowser.getWebDriver().navigate().to(encryptedUrl);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), uploadedDocGrid, 9);
		mobileNo.sendKeys("786786786");
		save.get(1).click();
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), next, 9);
		if (next.isEnabled())
			return true;
		return false;
	}
	
	
	public KYCPage fillEntityInformation()throws Exception

	{
		Reporter.log("TC_KYC_003");
		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), companyName, 9000);
		companyName.sendKeys(PropertyDictionary.map.get("Company"));
		
		employerId.sendKeys("420420");
			
		commonAction.selectDropdown(selectBaseCurrency, "string:USD");
			
		commonAction.selectDropdown(selectTypeOfBusiness, PropertyDictionary.map.get("TypeOfBusiness"));
		Thread.sleep(1000);
		
		sourceOfFund.sendKeys("Silicussource");
		Thread.sleep(1000);
		
		hqAddress.sendKeys("SilicusTestAddress");
		Thread.sleep(1000);
		
		//commonAction.selectDropdown(selectHqCountry, "object:140");
		selectHqCountry.sendKeys("Ind");
		selectHqCountry.sendKeys("i");
		commonAction.Wait(1000);
		selectHqCountry.sendKeys("a");
		//commonAction.Wait(2000);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), countryList.get(0), 30000);
		countryList.get(0).click();
		//clickHqCountry.click();
		hqCity.sendKeys("Pune");
		
		hqZip.sendKeys("456123");
		Thread.sleep(1000);
		
		sameAsAbove.click();
		
		phone.sendKeys("1234567890");
		Thread.sleep(1000);
		
		alternatePhone.sendKeys("9874651230");
			
		alternatecallPhone.sendKeys("32012467890");
		
		callBackEmail.sendKeys("silicusTest@silicus.com");		
		String filePath = System.getProperty("user.dir")+PropertyDictionary.map.get("UploadPath");
		System.out.println(filePath);
		commonAction.selectDropdown(selectEntityDocUpload, "Ownership Entity Legal Doc");
		upload2.sendKeys(filePath);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), uploadedDocGridEntity, 19000);
		commonAction.selectDropdown(selectEnhancedDueDiligence,"AML Questionnaire");			
		upload3.sendKeys(filePath);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), uploadedDocGridEdd, 19000);	
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), save.get(3), 10000);	
		commonAction.Wait(2000);
		save.get(3).click();
		commonAction.Wait(2000);
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), entityNext, 19000);	
		entityNext.click();
				
		return new KYCPage(webBrowser);
	}


	public boolean fillAccountInformation()throws InterruptedException, IOException
	{
		Reporter.log("TC_KYC_004");
				
		commonAction.selectDropdown(selectAccountPurpose, PropertyDictionary.map.get("AccountPurpose"));
		
		accountName.sendKeys("SilicusTest");
				
		commonAction.selectDropdown(selectPaymentType, PropertyDictionary.map.get("PaymentType"));
		
		bankCode.sendKeys("1234");
		
		bank.sendKeys(PropertyDictionary.map.get("Bank"));
				
		accountAddress.sendKeys("SilicusAddressTest");
				
		accountcity.get(1).sendKeys("Pune");
		
		accountZip.get(1).sendKeys("123456");
			
		commonAction.selectDropdown(selectCorrPaymentType, "IBAN");
		
		corrBankCode.sendKeys("123456");
		
		corrBank.sendKeys(PropertyDictionary.map.get("CorrBank"));
		
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), accountSaveBtn.get(0), 9000);	
		accountSaveBtn.get(0).click();   // Save
	
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), accountSaveBtn.get(1), 9000);	
		accountSaveBtn.get(1).click();  // Submit Information	
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), btYes, 9000);	
		btYes.click();	
		WebDriverWait wait = new WebDriverWait(webBrowser.getWebDriver(), 9000);
		wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(loaderDiv)));	
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(), btOK, 9000);
		btOK.click();
		
		if(loginHeading.getText().equalsIgnoreCase("SIGN IN"))
			return true;
		return false;
	}
	
	public boolean verifyPersonalAccountEntityInfoFromBox(String randomEmail) throws Exception
	{
		Reporter.log("TC_KYC_006");
		//Adding the data in the list from property files :
		
		//PersonalInfo
		List<String>propDataPersonalInfo = new ArrayList<String>();
		propDataPersonalInfo.add(PropertyDictionary.map.get("FirstName"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("LastName"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("MaidenName"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("NationalIden"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("HomeAddress"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("Country"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("State"));
		//propDataPersonalInfo.add(PropertyDictionary.map.get("City"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("Zip"));
		propDataPersonalInfo.add(PropertyDictionary.map.get("Passport"));
		
		
		//EntityInfo
		List<String>propDataEntityInfo = new ArrayList<String>();
		propDataEntityInfo.add(PropertyDictionary.map.get("Company"));
		propDataEntityInfo.add(PropertyDictionary.map.get("Currency"));
		propDataEntityInfo.add(PropertyDictionary.map.get("TypeOfBusiness"));
		
		//AccountInfo
		List<String>propDataAccountInfo = new ArrayList<String>();
		propDataAccountInfo.add(PropertyDictionary.map.get("AccountPurpose"));
		//propDataAccountInfo.add(PropertyDictionary.map.get("AccountName"));
		propDataAccountInfo.add(PropertyDictionary.map.get("PaymentType"));		
		propDataAccountInfo.add(PropertyDictionary.map.get("Bank"));
		propDataAccountInfo.add(PropertyDictionary.map.get("CorrBank"));
		
		
		// Create Box connection and get the data from json into the list. 
		
		
		
		BoxApiServiceImpl boxAPIServices = new BoxApiServiceImpl();		
		BoxFolder a = boxAPIServices.getBoxFolder(randomEmail+"_inProgress");
		List<String> al = new ArrayList<String>();
		al.add("AccountInfo");
		al.add("EntityInfo");
		al.add("PersonalInfo");
		for(Info b : a.getChildren())
		{
			System.out.println(b.getName());
			if(b.getName().contains(".txt"))
			{
				boxAPIServices.downloadFolderFromBox(randomEmail+"_inProgress", al, ".\\input-data", "");
			}
		}
		List<String> listDataForPersonalInfo=new ArrayList<String>();
		List<String> listDataForAccountInfo=new ArrayList<String>();
		List<String> listDataForEntityInfo=new ArrayList<String>();
		
		 listDataForPersonalInfo = boxAPIServices.jsonreaderForPersonalInfo(".\\input-data\\"+randomEmail+"_inProgress\\PersonalInfo_"+randomEmail+".txt");
		 listDataForAccountInfo = boxAPIServices.jsonreaderForAccountInfo(".\\input-data\\"+randomEmail+"_inProgress\\AccountInfo_"+randomEmail+".txt");
		 listDataForEntityInfo = boxAPIServices.jsonreaderForEntityInfo(".\\input-data\\"+randomEmail+"_inProgress\\EntityInfo_"+randomEmail+".txt");
		
		List<String> flag=new ArrayList<String>();
		for (String DataPersonalInfo : propDataPersonalInfo) {			
			if(listDataForPersonalInfo.contains(DataPersonalInfo))
			{
				flag.add("true");
			}
			else{
			return false;
			}
		}
		
		
		for(String DataEntityInfo :propDataEntityInfo ){
			if(listDataForEntityInfo.contains(DataEntityInfo))
			{
				flag.add("true");
			}
			else{
				return false;
				}
		}
		
		
		for(String DataAccountInfo :propDataAccountInfo ){
			if(listDataForAccountInfo.contains(DataAccountInfo))
			{
				flag.add("true");
			}
			else{
				return false;
				}
		}		
		return true;
		/*//Verify the data from box with property files data.
		if(listDataForPersonalInfo.contains(propDataPersonalInfo) && listDataForEntityInfo.contains(propDataEntityInfo) && listDataForAccountInfo.contains(propDataAccountInfo))
			return true;	
		return false;*/
	}
}
