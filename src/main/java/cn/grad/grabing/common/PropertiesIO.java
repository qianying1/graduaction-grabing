package cn.grad.grabing.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PropertiesIO extends BaseUtil {

	private Properties initConnector() {
		Properties properties = new Properties();
		FileInputStream fIn = null;
		InputStream in = null;
		try {
			if (fIn != null)
				fIn = new FileInputStream("classpath:websites.properties");
		} catch (FileNotFoundException e) {
			log.error("fail to load properties file", e);
			try {
				fIn.close();
			} catch (IOException e1) {
			}
		}
		in = new BufferedInputStream(fIn);
		try {
			properties.load(in);
		} catch (IOException e) {
			log.error("fail to load properties contents", e);
		} finally {
			try {
				in.close();
				fIn.close();
			} catch (IOException e) {
			}
		}
		return properties;
	}

	public String getPropertiesValue(String key) {
		Properties properties = initConnector();
		if (Validation.isObjNull(properties))
			return null;
		return properties.getProperty(key);
	}

	public List<String> getPropertiesValues() {
		List<String> keys = new ArrayList<>();
		Properties properties = initConnector();
		Iterator<String> itor = properties.stringPropertyNames().iterator();
		while (itor.hasNext()) {
			keys.add(properties.getProperty(itor.next()));
		}
		return keys;
	}

	public String[] getPropertiesKeys() {
		String[] keys = null;
		Properties properties = initConnector();
		if (!Validation.isObjNull(properties))
			properties.stringPropertyNames().toArray(keys);
		return keys;
	}

}
