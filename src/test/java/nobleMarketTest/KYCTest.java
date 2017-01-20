package nobleMarketTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.noblemarket.KYCPage;
import common.BaseTest;

public class KYCTest extends BaseTest{
	
	@BeforeClass
	public void beforeClassTestLevel()
	{
		parent=report.startTest("KYCTest");
	}
	
	/*@Test
	public void verifyKYCPage_TC_KYC_001() throws Exception
	{
		test=report.startTest("verifyKYCPage_TC_KYC_001");
		parent.appendChild(test);
		Reporter.log("TC_KYC_001");
		KYCPage kycObject =new KYCPage(webBrowser);
		kycObject.goTo().enterCredentials();
	    Assert.assertTrue(kycObject.VerifyPersonalInformationTabSelectedDefault());		
	}*/
	
	@Test
	public void verifyFillInDetails_TC_KYC_002_To_TC_KYC_004_And_TC_KYC_006() throws Exception
	{
		test=report.startTest("verifyFillInDetails_TC_KYC_002_To_TC_KYC_004_And_TC_KYC_006");
		parent.appendChild(test);
		Reporter.log("TC_KYC_002 To TC_KYC_006");
		KYCPage kycObject =new KYCPage(webBrowser);
		String randomEmail=kycObject.goTo().enterCredentials();
		Assert.assertTrue(kycObject.fillPersonalInformationForm().fillEntityInformation().fillAccountInformation());	
	}
	
/*	@Test
	public void verifyEditableModeKycForm() throws Exception
	{
		test=report.startTest("verifyEditableModeKycForm");
		parent.appendChild(test);
		Reporter.log("TC_KYC_005");
		KYCPage kycObject =new KYCPage(webBrowser);
		String randomEmail=kycObject.goTo().enterCredentials();
		Assert.assertTrue(kycObject.fillPersonalInformationFormForEditableMode().verifyEditableUrl(randomEmail));		
	}*/
}
