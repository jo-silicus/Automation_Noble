package pages.external;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;
import pages.noblemarket.HomePage;
import pages.noblemarket.datamodal.HomePageDataModel;
import pages.noblemarket.datamodal.JenkinsPageDataModel;

public class JenkinsPage extends BasePage<JenkinsPage> {
	
	@FindBy (css = "div[class='login']")
	private WebElement loginlink;
	
	@FindBy (id = "j_username")
	private WebElement username;
	
	@FindBy (name = "j_password")
	private WebElement password;
	
	@FindBy (id = "yui-gen1-button")
	private WebElement login;
	
	@FindBy (css = "a[href='requests.txt']")
	private WebElement requests;
	
	@FindBy (css = "a[href='responses.txt']")
	private WebElement response;
	
	@FindBy (css = "pre[style='word-wrap: break-word; white-space: pre-wrap;']")
	private WebElement htmlTextRequest;
	
	@FindBy (css = "pre[style='word-wrap: break-word; white-space: pre-wrap;']")
	private WebElement htmlTextResponse;
	
	@FindBy (css = "a[href='job/Load%20Simulator/']")
	private WebElement loadSimulator;
	
	@FindBy (css = "a[href='job/job_for_silicus/build?delay=0sec']")
	private WebElement jobForSilicus;

	private BufferedWriter out;
	

	public JenkinsPage(Browser browser) throws Exception {
		super(browser);
		initDataModel(new JenkinsPageDataModel());
	}
	
	public JenkinsPage enterCredentials() throws Exception
	{
		loginlink.click();
		username.sendKeys("dilip");
		password.sendKeys("dilip");
		login.click();
		return new JenkinsPage(webBrowser);
	}	
	
	public JenkinsPage startLoadSimulator() throws Exception
	{
		loadSimulator.click();
		Thread.sleep(3000);
		jobForSilicus.click();
		Thread.sleep(30000);
		return new JenkinsPage(webBrowser);
	}
	
	public JenkinsPage navigateToWorkspaceOfJob() throws Exception
	{
		webBrowser.getWebDriver().get("https://jenkins.noblemarkets.net/job/Load%20Simulator/job/job_for_silicus/ws/output/iteration1/");
		return new JenkinsPage(webBrowser);
	}
	
	public JenkinsPage readHTMLRequestAndSaveIntoTextFile() throws Exception
	{
		String s = htmlTextRequest.getText();
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//input-data//request.txt"), "utf-8"));
        Thread.sleep(3000);
		writer.write(s);
		Thread.sleep(4000);
		writer.close();
		return new JenkinsPage(webBrowser);
	}
	
	public JenkinsPage navigateToRequest() throws Exception
	{
		requests.click();
		return new JenkinsPage(webBrowser);
	}
	
	public JenkinsPage navigateToResponse() throws Exception
	{
		response.click();
		return new JenkinsPage(webBrowser);
	}

	public void readHTMLResponseAndSaveIntoTextFile() throws IOException, InterruptedException
	{
		String s = htmlTextResponse.getText();
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//input-data//response.txt"), "utf-8"));
        Thread.sleep(3000);
		writer.write(s);
		Thread.sleep(4000);
		writer.close();
	}
	
	public JenkinsPage pageBack() throws Exception
	{
		webBrowser.getWebDriver().navigate().back();
		return new JenkinsPage(webBrowser);
	}
}
