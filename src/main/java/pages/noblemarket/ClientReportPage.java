package pages.noblemarket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;

public class ClientReportPage extends BasePage<ClientReportPage> {

	@FindBy(css="nav>div[class='navbar-header']>span")
	private WebElement headerText;
	
	@FindBy(css="div[class='input-group input-group-sm']>select")
	private WebElement selectReportType;
	
	@FindBy(css="div[class='max-scalar-field']>input")
	private WebElement accountNo;
	
	@FindBy(css="div[class='max-date-field input-group input-group-sm']>input[placeholder='from_date']")
	private WebElement fromDate;
	
	@FindBy(css="div[class='max-date-field input-group input-group-sm']>input[placeholder='to_date']")
	private WebElement toDate;
	
	@FindBy(css="div[class='max-scalar-field']>button[data-bind*='load_fee_report']")
	private WebElement loadReports;
	
	@FindBy(css="table[class='table table-bordered table-condensed table-hover JColResizer']>tbody>tr>td:nth-child(6)>span")
	private List<WebElement> tradeDate;
	
	@FindBy(css="div[class='input-group input-group-sm']>select[data-bind*='Select File Type']")
	private WebElement fileTypes;
	
	@FindBy(css="div>button[data-bind*='save_report_in_archive']")
	private WebElement saveFileToArchive;
	
	@FindBy(css="div[class='alert in fade alert-warning']")
	private WebElement reportAlert;
	
	@FindBy(css="div[class='alert in fade alert-warning']>a")
	private WebElement closeAlert;
	
	@FindBy(css="div>button[data-bind*='clear_report']")
	private WebElement clearReport;
	
	
	
	
	public ClientReportPage(Browser browser) {
		super(browser);
		// TODO Auto-generated constructor stub
	}

	
	public boolean verifyClientReportPage()
	{
		if(headerText.getText().trim().equalsIgnoreCase("General Reports"))
			return true;	
		return false;
	}
	
	public ClientReportPage generateClientReport()
	{	
		commonAction.selectDropdown(selectReportType, "commission_report");
		accountNo.sendKeys("P05A000001");
		fromDate.sendKeys("25 Aug 2016");
		toDate.sendKeys("26 Aug 2016");
		loadReports.click();	
		return new ClientReportPage(webBrowser);
	}
	
	public boolean verifyTradeDate()
	{
		List<String> booleans = new ArrayList<>();
		for (WebElement elementDate : tradeDate) {
			if(elementDate.getText().equalsIgnoreCase("25 Aug 2016") || elementDate.getText().equalsIgnoreCase("26 Aug 2016"))
				booleans.add("True");
			else
			return false;
		}
			return true;		
	}
	
    public ClientReportPage selectFileTypeAndSaveToArchive(String type)
    {
    	commonAction.waitElementToBeClickable(webBrowser.getWebDriver(),fileTypes, 8);
       commonAction.selectDropdown(fileTypes, type);      
       saveFileToArchive.click();     
       return new ClientReportPage(webBrowser);
    }
    
    public boolean verifyAlert()
    {
    	commonAction.waitElementToBeClickable(webBrowser.getWebDriver(),reportAlert, 5);
    	if (reportAlert.getText().trim().contains("commission_report"))
    	{
    		closeAlert.click();
    		return true;
         }
    	else{
    		return false;
    	}   	
    }
    
	public boolean verifyReportsCleared() {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(),clearReport, 6);
		clearReport.click();
		if (tradeDate.isEmpty())
			return true;
		return false;
	}
    
	public String getFileNameFromAlert() {
		commonAction.waitElementToBeClickable(webBrowser.getWebDriver(),reportAlert, 5);
		String fileName = reportAlert.getText().trim();
		closeAlert.click();
		return fileName;
	}

}