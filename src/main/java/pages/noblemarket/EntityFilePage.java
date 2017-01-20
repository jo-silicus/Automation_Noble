package pages.noblemarket;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Browser;
import pages.common.BasePage;

public class EntityFilePage extends BasePage<EntityFilePage> {

	@FindBy(css="table[class='table table-bordered table-condensed table-hover']>tbody>tr>td:nth-child(2)")
	private List<WebElement> fileGrid;
	
	public EntityFilePage(Browser browser) {
		super(browser);
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyEntityFile(String fileName)
	{
		List<String> files = new ArrayList<String>();
		for (WebElement file : fileGrid) {
			files.add(file.getText());
		}
		if (files.contains(fileName))
			return true;
		return false;
	}

}
