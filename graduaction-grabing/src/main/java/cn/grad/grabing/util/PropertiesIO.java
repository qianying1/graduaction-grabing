package cn.grad.grabing.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

@Resource
public class PropertiesIO extends BaseUtil {

	public Properties initConnector(String propertiesUri) {
		Properties properties = new Properties();
		InputStream in = this.getClass().getResourceAsStream(propertiesUri);
		try {
			properties.load(in);
		} catch (IOException e) {
			log.error("fail to load properties contents from file: "+propertiesUri, e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return properties;
	}

	public String getPropertiesValue(String key, String propertiesUri) {
		Properties properties = initConnector(propertiesUri);
		if (Validation.isObjNull(properties))
			return null;
		return properties.getProperty(key);
	}

	public List<String> getPropertiesValues(String propertiesUri) {
		List<String> keys = new ArrayList<>();
		Properties properties = initConnector(propertiesUri);
		Iterator<String> itor = properties.stringPropertyNames().iterator();
		while (itor.hasNext()) {
			keys.add(properties.getProperty(itor.next()));
		}
		return keys;
	}

	public String[] getPropertiesKeys(String propertiesUri) {
		String[] keys = null;
		Properties properties = initConnector(propertiesUri);
		if (!Validation.isObjNull(properties))
			properties.stringPropertyNames().toArray(keys);
		return keys;
	}

	public List<String> getPropertiesValues(Properties headersProperties) {
		List<String> keys = new ArrayList<>();
		Iterator<String> itor = headersProperties.stringPropertyNames().iterator();
		while (itor.hasNext()) {
			keys.add(headersProperties.getProperty(itor.next()));
		}
		return keys;
	}

}
