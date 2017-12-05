package cn.grad.grabing.suport.acfun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.grad.grabing.entityutil.acfun.VideoSection;
import cn.grad.grabing.helper.DocumentInitailizer;
import cn.grad.grabing.support.acfunc.IndexAnalizer;
import cn.grad.grabing.util.PropertiesIO;
import cn.grad.grabing.util.Validation;

@RunWith(SpringJUnit4ClassRunner.class) // 使用Junit4进行测试
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml", })
public class IndexCase extends DocumentInitailizer {

	public final String CAROUSEL = "carousel";
	public final String MONKEYRECOMMEND = "monkeyrecommend";
	public final String BANANAANDARTICLE = "bananaandarticle";
	public final String ENTERTAINMENT = "entertainment";
	public final String DRAMASERIES = "dramaseries";
	public final String GAME = "game";
	public final String ANIMATION = "animation";
	public final String MUSIC = "music";
	public final String DANCERKANOJO = "dancerkanojo";
	public final String FISHESPOOL = "fishespool";
	public final String SCIENCEANDTECHNO = "scienceandtechno";
	public final String SPORT = "sport";
	public final String TEMPLATE = "template";
	public final String HEADER = "header";
	
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

	private Map<String, Object> getHeaderParams() {
		Map<String, Object> params = new HashMap<>();
		params.put("userAgent", userAgent);
		return params;
	}

	private Element getElementByNameUsingUserAgent(String sectionName) {
		return getDocumentSectionByName(sectionName, getUrl(), getHeaderParams());
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

	/**
	 * 首页头部
	 */
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

	/**
	 * 带轮播节点
	 */
	@Test
	public void testCarouselSectionAnalizer() {
		List<VideoSection> carousels = indexAnalizer
				.analizeCarouselSection(getElementByNameUsingUserAgent(CAROUSEL));
		print(carousels);
	}

	/**
	 * 猴子推荐
	 */
	@Test
	public void testMonkeyRecommendSectionAnalizer() {
		List<VideoSection> recommends = indexAnalizer
				.analizeMonkeySection(getElementByNameUsingUserAgent(MONKEYRECOMMEND));
		print(recommends);
	}

	/**
	 * 香蕉榜
	 */
	@Test
	public void testBananaSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeBananaSection(getElementByNameUsingUserAgent(BANANAANDARTICLE));
		print(results);
	}

	/**
	 * 娱乐版块
	 */
	@Test
	public void testEntertainmentSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeEntertainmentSection(getElementByNameUsingUserAgent(ENTERTAINMENT));
		print(results);
	}

	/**
	 * 番剧版块
	 */
	@Test
	public void testDramaSeriesSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeDramaSeriesSection(getElementByNameUsingUserAgent(DRAMASERIES));
		print(results);
	}

	/**
	 * 游戏
	 */
	@Test
	public void testGameSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeGameSection(getElementByNameUsingUserAgent(GAME));
		print(results);
	}

	/**
	 * 动画版块
	 */
	@Test
	public void testAnimationSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeAnimationSection(getElementByNameUsingUserAgent(ANIMATION));
		print(results);
	}

	/**
	 * 二次元版块
	 */
	@Test
	public void testSecondYuanSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeSecondYuanSection(getElementByNameUsingUserAgent(SCIENCEANDTECHNO));
		print(results);
	}

	/**
	 * 音乐版块
	 */
	@Test
	public void testMusicSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeMusicSection(getElementByNameUsingUserAgent(MUSIC));
		print(results);
	}

	/**
	 * 舞蹈*彼女版块
	 */
	@Test
	public void testDancerKanojoSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeDancerKanojoSection(getElementByNameUsingUserAgent(DANCERKANOJO));
		print(results);
	}

	/**
	 * 鱼塘版块
	 */
	@Test
	public void testFishesPoolSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeFishesPoolSection(getElementByNameUsingUserAgent(FISHESPOOL));
		print(results);
	}

	/**
	 * 科技版块
	 */
	@Test
	public void testScienceAndTechnoSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeScienceAndTechnoSection(getElementByNameUsingUserAgent(SCIENCEANDTECHNO));
		print(results);
	}

	/**
	 * 体育版块
	 */
	@Test
	public void testSportSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeSportSection(getElementByNameUsingUserAgent(SPORT));
		print(results);
	}

	/**
	 * 临时版块
	 */
	@Test
	public void testTemplateSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeTempSection(getElementByNameUsingUserAgent(TEMPLATE));
		print(results);
	}

	/**
	 * 整个Index页面
	 */
	@Test
	public void testIndexPageAnalizer() {
		List<VideoSection> results = indexAnalizer
				.ananlizePageForLinks(getDocumentByUrl(getUrl(), getHeaderParams()).getElementById("main"));
		print(results);
	}

	/**
	 * javaScript代码的抓取
	 */
	@Test
	public void testScriptGrabing() {
		Document document = getDocumentByUrl(getUrl(), getHeaderParams());
		System.out.println(document.getElementsByTag("script"));
	}
	
	private Element getDocumentSectionByName(String sectionName, String url, Map<String, Object> params) {
		int sectionNum = -1;
		Document doc = getDocumentByUrl(url, params);
		if (Validation.isNull(doc)) {
			log.error("can`t getting a page from uri: " + url);
			return null;
		}
		if (Validation.isStrIgnoreCaseEquals(HEADER, sectionName))
			return doc;
		switch (sectionName) {
		case CAROUSEL:
			sectionNum = 0;
			break;
		case MONKEYRECOMMEND:
			sectionNum = 1;
			break;
		case BANANAANDARTICLE:
			sectionNum = 2;
			break;
		case ENTERTAINMENT:
			sectionNum = 3;
			break;
		case DRAMASERIES:
			sectionNum = 4;
			break;
		case GAME:
			sectionNum = 5;
			break;
		case ANIMATION:
			sectionNum = 6;
			break;
		case DANCERKANOJO:
			sectionNum = 7;
			break;
		case FISHESPOOL:
			sectionNum = 8;
			break;
		case SCIENCEANDTECHNO:
			sectionNum = 9;
			break;
		case SPORT:
			sectionNum = 10;
			break;
		default:
			break;
		}
		Element result = doc.getElementById("main").getElementsByTag("section").get(sectionNum);
		if (Validation.isNull(result)) {
			log.error("can not to get " + sectionName + " section");
			return null;
		}
		return result;
	}
}
