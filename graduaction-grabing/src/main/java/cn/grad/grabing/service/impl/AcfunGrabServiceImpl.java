package cn.grad.grabing.service.impl;

import java.util.Properties;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.grad.grabing.service.AcfunGrabService;
import cn.grad.grabing.support.acfunc.IndexAnalizer;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.PropertiesIO;
import cn.grad.grabing.util.ResourceGetter;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;

@Service("acfunGrabServiceImpl")
public class AcfunGrabServiceImpl extends BaseUtil implements AcfunGrabService {

	@Autowired
	private IndexAnalizer indexAnalizer;

	private Properties grabingConfiguations;

	private Document doc;

	private final String configuationPath = StrPropertiesMapper.CONFIGURATION_BASE_PATH + StrPropertiesMapper.ACFUN
			+ ".properties";

	private String seedUri;

	/**
	 * a站爬虫入口
	 */
	@Override
	public void beginAcfunGrabing() {
		
	}

	public void initBeforeGrabing(String targetUri) {
		this.seedUri = targetUri;
		PropertiesIO io = new PropertiesIO();
		grabingConfiguations = io.initConnector(configuationPath);
		ResourceGetter getter = new ResourceGetter();
		if (Validation.isObjNull(grabingConfiguations) || Validation.isPropertiesEmpty(grabingConfiguations.keySet())) {
			setDoc(getter.getDocumentByUrl(configuationPath, grabingConfiguations));
		} else {
			setDoc(getter.getDocumentByUrl(configuationPath));
		}
	}

	public String getSeedUri() {
		return seedUri;
	}

	public void setSeedUri(String seedUri) {
		this.seedUri = seedUri;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

}
