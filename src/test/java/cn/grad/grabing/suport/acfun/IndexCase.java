package cn.grad.grabing.suport.acfun;

import java.awt.image.RescaleOp;
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

	private Document getDocumentByUrl(String url) {
		if (Validation.isStringNull(url) || Validation.isStringEmpty(url))
			return null;
		Document doc = null;
		try {
			doc = Jsoup.connect(getUrl()).timeout(1000 * 60 * 6).userAgent(userAgent).get();
		} catch (IOException e) {
			log.error("Maybe network is too bad to connect the target or the connection is broken by some factors", e);
		}
		return doc;
	}

	private void print(List<VideoSection> datas) {
		if (datas == null || datas.size() <= 0)
			return;
		for (VideoSection data : datas) {
			System.out.println("video data: " + data);
		}
	}

	@Before
	public void before() {
		init();
	}

	@Test
	public void testHeaderAnalizer() {
		Document doc = getDocumentByUrl(getUrl());
		List<String> links = null;
		if (doc != null)
			links = indexAnalizer.analizeHeaderForUrls(doc);
		if (links != null && links.size() > 0)
			for (String url : links) {
				System.out.println("links: " + url);
			}
	}

	@Test
	public void testCarouselSectionAnalizer() {
		Document doc = getDocumentByUrl(getUrl());
		if (doc != null) {
			List<VideoSection> carousels = indexAnalizer
					.analizeCarouselSection(doc.getElementById("main").getElementsByTag("section").get(0));
			print(carousels);
		}
	}

	@Test
	public void testMonkeyRecommendSectionAnalizer() {
		Document doc = getDocumentByUrl(getUrl());
		// System.out.println("document: " +
		// doc.getElementById("main").getElementsByTag("section").get(1));
		if (doc != null) {
			List<VideoSection> recommends = indexAnalizer
					.analizeMonkeySection(doc.getElementById("main").getElementsByTag("section").get(1));
			print(recommends);
		}
	}
}
