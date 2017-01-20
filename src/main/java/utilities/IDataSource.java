package utilities;

import java.util.Map;

public interface IDataSource {
	public <K, V> Map<K, V> getData() throws Exception;
}
