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

import cn.grad.grabing.common.PropertiesIO;
import cn.grad.grabing.common.ResourceGetter;
import cn.grad.grabing.entityutil.acfunc.VideoSection;
import cn.grad.grabing.support.acfunc.IndexAnalizer;

@RunWith(SpringJUnit4ClassRunner.class) // 使用Junit4进行测试
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml", })
public class IndexCase extends ResourceGetter {

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
		Document doc = getDocumentByUrl(getUrl(), null);
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
				.analizeCarouselSection(getElementByNameUsingUserAgent(ResourceGetter.CAROUSEL));
		print(carousels);
	}

	/**
	 * 猴子推荐
	 */
	@Test
	public void testMonkeyRecommendSectionAnalizer() {
		List<VideoSection> recommends = indexAnalizer
				.analizeMonkeySection(getElementByNameUsingUserAgent(ResourceGetter.MONKEYRECOMMEND));
		print(recommends);
	}

	/**
	 * 香蕉榜
	 */
	@Test
	public void testBananaSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeBananaSection(getElementByNameUsingUserAgent(ResourceGetter.BANANAANDARTICLE));
		print(results);
	}

	/**
	 * 娱乐版块
	 */
	@Test
	public void testEntertainmentSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeEntertainmentSection(getElementByNameUsingUserAgent(ResourceGetter.ENTERTAINMENT));
		print(results);
	}

	/**
	 * 番剧版块
	 */
	@Test
	public void testDramaSeriesSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeDramaSeriesSection(getElementByNameUsingUserAgent(ResourceGetter.DRAMASERIES));
		print(results);
	}

	/**
	 * 游戏
	 */
	@Test
	public void testGameSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeGameSection(getElementByNameUsingUserAgent(ResourceGetter.GAME));
		print(results);
	}

	/**
	 * 动画版块
	 */
	@Test
	public void testAnimationSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeAnimationSection(getElementByNameUsingUserAgent(ResourceGetter.ANIMATION));
		print(results);
	}

	/**
	 * 二次元版块
	 */
	@Test
	public void testSecondYuanSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeSecondYuanSection(getElementByNameUsingUserAgent(ResourceGetter.SCIENCEANDTECHNO));
		print(results);
	}

	/**
	 * 音乐版块
	 */
	@Test
	public void testMusicSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeMusicSection(getElementByNameUsingUserAgent(ResourceGetter.MUSIC));
		print(results);
	}

	/**
	 * 舞蹈*彼女版块
	 */
	@Test
	public void testDancerKanojoSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeDancerKanojoSection(getElementByNameUsingUserAgent(ResourceGetter.DANCERKANOJO));
		print(results);
	}

	/**
	 * 鱼塘版块
	 */
	@Test
	public void testFishesPoolSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeFishesPoolSection(getElementByNameUsingUserAgent(ResourceGetter.FISHESPOOL));
		print(results);
	}

	/**
	 * 科技版块
	 */
	@Test
	public void testScienceAndTechnoSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeScienceAndTechnoSection(getElementByNameUsingUserAgent(ResourceGetter.SCIENCEANDTECHNO));
		print(results);
	}

	/**
	 * 体育版块
	 */
	@Test
	public void testSportSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeSportSection(getElementByNameUsingUserAgent(ResourceGetter.SPORT));
		print(results);
	}

	/**
	 * 临时版块
	 */
	@Test
	public void testTemplateSectionAnalizer() {
		List<VideoSection> results = indexAnalizer
				.analizeTempSection(getElementByNameUsingUserAgent(ResourceGetter.TEMPLATE));
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
}
