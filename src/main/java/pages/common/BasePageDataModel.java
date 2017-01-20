package pages.common;

import java.util.Map;

import utilities.PropertyFile;

public abstract class BasePageDataModel<T extends BasePageDataModel<T>> {

	public String PAGE_TITLE;
	public String PAGE_URL;
	private Map<String, String> properties;
	private T instance;

	protected BasePageDataModel(String fileName) throws Exception {

		properties = new PropertyFile(fileName).getData();
		PAGE_TITLE = properties.get("PAGE_TITLE");
		PAGE_URL = properties.get("PAGE_URL");
	}

	public T getPageDataModel() throws Exception {
		if (instance == null) {
			instance = (T) this;
		}
		return instance;
	}

}

