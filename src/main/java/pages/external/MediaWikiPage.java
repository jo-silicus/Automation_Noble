package pages.external;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;
import pages.noblemarket.datamodal.MediaWikiDataModel;

public class MediaWikiPage extends BasePage<MediaWikiPage>{
	
	@FindBy (id = "wpName1")
	private WebElement username;
	
	@FindBy (id = "wpPassword1")
	private WebElement password;
	
	@FindBy (id = "wpLoginAttempt")
	private WebElement login;
	
	@FindBy (css = "a[title='Documentation']")
	private WebElement documentation;
	
	@FindBy (css = "a[title='TestReportDocuments']")
	private WebElement testReportDocuments;
	
	@FindBy (css = "a[accesskey='e']")
	private WebElement edit;
	
	@FindBy (id = "wpTextbox1")
	private WebElement textbox;
	
	@FindBy (id = "wpSave")
	private WebElement saveChanges;

	public MediaWikiPage(Browser browser) throws Exception{
		super(browser);
		initDataModel(new MediaWikiDataModel());
	}
	
	public MediaWikiPage loginIntoMediaWiki()
	{
		username.sendKeys("Dilip");
		password.sendKeys("golf200");
		login.click();
		return this;
	}
	
	public void NavigateToDocumentationPage(List<String> txt) throws InterruptedException
	{
		int PassCount = Collections.frequency(txt, "||Pass");
		int failCount  = Collections.frequency(txt, "||Fail");
		int skippedCount = Collections.frequency(txt, "||Skipped");
		int totalTestCasesCount = PassCount + failCount + skippedCount;
		documentation.click();
		Thread.sleep(4000);
		testReportDocuments.click();
		Thread.sleep(4000);
		edit.click();
		Thread.sleep(4000);
		textbox.clear();
		StringBuffer testCountTable = new StringBuffer();
		testCountTable.append("'''Noble Markets Test Report run by Automation Testing'''").append("\n").append("{| class=\"wikitable\"").append("\n")
		.append("! Summary || ").append("\n").append("|-").append("\n").append("| Total test cases ||"+totalTestCasesCount).append("\n").append("|-")
		.append("\n").append("| Passed test cases ||"+PassCount).append("\n").append("|-").append("\n").append("| Failed test cases ||"+failCount)
		.append("\n").append("|-").append("\n").append("| Skipped test cases ||"+skippedCount).append("\n").append("|}").append("\n");
		//textbox.sendKeys("'''Noble Markets Test Report run by Automation Testing'''");
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("{| class=\"wikitable\"");
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("! Summary || ");
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("|-");
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("| Total test cases ||"+totalTestCasesCount);
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("|-");
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("| Passed test cases ||"+PassCount);
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("|-");
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("| Failed test cases ||"+failCount);
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("|-");
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("| Skipped test cases ||"+skippedCount);
		//textbox.sendKeys(Keys.ENTER);
		//textbox.sendKeys("|}");
		//textbox.sendKeys(Keys.ENTER);
		
		textbox.sendKeys("{| class=\"wikitable\"");
		textbox.sendKeys(Keys.ENTER);
		textbox.sendKeys("! Module || TestCase || Result");
		int count = 0;
		for(String text : txt)
		{
			Pattern.matches(" equals", "as");
		textbox.sendKeys(text);
		}
		textbox.sendKeys(Keys.ENTER);
		textbox.sendKeys("|}");
		Thread.sleep(5000);
		saveChanges.click();
	}

}
