package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertyFile implements IDataSource {
	Properties properties = new Properties();

	public PropertyFile(String fileName) throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(fileName));
	}

	public Map<String, String> getData() throws Exception {
		
		for (String key : properties.stringPropertyNames()) {
			PropertyDictionary.map.put(key, properties.getProperty(key));
		}
		return PropertyDictionary.map;
	}
}