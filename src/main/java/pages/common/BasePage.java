package pages.common;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import core.Browser;
import core.CommonAction;

public abstract class BasePage<T extends BasePage<T>> {

	protected final Browser webBrowser;
	private BasePageDataModel<?> DataModel;
	protected String pageTitle;
	protected String pageUrl;
	protected final CommonAction commonAction = new CommonAction();

	public BasePage(Browser browser) {
		this.webBrowser = browser;
		PageFactory.initElements(webBrowser.getWebDriver(), this);
	}

	public String getStaticTitle() {
		return pageTitle;
	}

	public String getTitle() {
		String title = webBrowser.getWebDriver().getTitle().trim();
		return title;
	}

	public T goTo() {
		return goTo(pageUrl);
	}

	public T goTo(String url) {
		pageUrl = url;
		webBrowser.getWebDriver().get(url);
		return (T) this;
	}

	public boolean isAt() {
		boolean flag = webBrowser.getWebDriver().getTitle().trim().equalsIgnoreCase(pageTitle);
		return flag;
	}

	protected void initDataModel(BasePageDataModel<?> pageDataModel) throws Exception {
		DataModel = pageDataModel.getPageDataModel();
		pageTitle = DataModel.PAGE_TITLE;
		pageUrl = DataModel.PAGE_URL;
	}

}
