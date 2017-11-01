package cn.grad.grabing.suport.acfun;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.grad.grabing.common.BaseUtil;
import cn.grad.grabing.common.PropertiesIO;
import cn.grad.grabing.common.Validation;
import cn.grad.grabing.entityutil.acfunc.VideoSection;
import cn.grad.grabing.support.acfunc.IndexAnalizer;

@RunWith(SpringJUnit4ClassRunner.class) // 使用Junit4进行测试
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml", })
public class IndexCase extends BaseUtil {

	private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/56.0";
	@Autowired
	IndexAnalizer indexAnalizer;
	String url = null;
	private String grabType = "acfun";
	private String propertiesUri = "/properties/websites.properties";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	PropertiesIO io = null;

	private void init() {
		io = new PropertiesIO();
		setUrl(io.getPropertiesValue(grabType, propertiesUri));
	}

	private Document getDocumentByUrl(String url) throws IOException {
		if (Validation.isStringNull(url) || Validation.isStringEmpty(url))
			return null;
		return Jsoup.connect(getUrl()).timeout(20 * 60 * 60).userAgent(userAgent).get();
	}

	@Before
	public void before() {
		init();
	}

	@Test
	public void testHeaderAnalizer() {
		Document doc = null;
		try {
			doc = getDocumentByUrl(getUrl());
			log.debug("running exam......");
		} catch (MalformedURLException e) {
			log.error("the url is valide: " + url, e);
		} catch (IOException e) {
			log.error("connect to " + url + " error", e);
		}
		List<String> links = indexAnalizer.analizeHeaderForUrls(doc);
		for (String url : links) {
			System.out.println("links: " + url);
		}
	}

	@Test
	public void testCarouselSectionAnalizer() {
		Document doc = null;
		try {
			doc = getDocumentByUrl(getUrl());
			log.debug("running exam......");
		} catch (MalformedURLException e) {
			log.error("the url is valide: " + url, e);
		} catch (IOException e) {
			log.error("connect to " + url + " error", e);
		}
		List<VideoSection> carousels = indexAnalizer
				.analizeCarouselSection(doc.getElementById("main").getElementsByTag("section").get(0));
		for (VideoSection carousel : carousels) {
			System.out.println("carousel: " + carousel);
		}
	}

}
