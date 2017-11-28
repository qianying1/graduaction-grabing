package cn.grad.grabing.helper.acfun;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.grad.grabing.dao.mapper.GrabLibMapper;
import cn.grad.grabing.dao.mapper.VideoMapper;
import cn.grad.grabing.domain.GrabLib;
import cn.grad.grabing.domain.Video;
import cn.grad.grabing.entityutil.acfunc.VideoSection;
import cn.grad.grabing.helper.DocumentInitailizer;
import cn.grad.grabing.support.acfunc.IndexAnalizer;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.PropertiesIO;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;

@Component
public class IndexHelper extends BaseUtil {

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

	@Autowired
	private IndexAnalizer indexAnalizer;
	@Autowired
	private GrabLibMapper grabLibMapper;
	@Autowired
	private VideoMapper videoMapper;

	private Document doc;

	private final String configuationPath = StrPropertiesMapper.CONFIGURATION_BASE_PATH + StrPropertiesMapper.ACFUN
			+ ".properties";

	private String seedUri;
	private Properties grabingConfiguations;

	public void beginGrabing() {
		System.out.println("begin grabing.....");
		if (Validation.isObjNull(this.doc)) {
			log.error("the document is empty for url: " + seedUri);
			// System.out.println("the document is empty for url: " + seedUri);
			return;
		}
		// 头部
		analizeHeaderForUrls();
		// 主体
		analizeBodyMain();
	}

	// 首页主体
	private void analizeBodyMain() {
		// Carousel，带轮播节点
		analizeCarouselSection();

	}

	private void analizeCarouselSection() {
		List<VideoSection> videos = indexAnalizer.analizeCarouselSection(getSectionByName(CAROUSEL));
		if (Validation.isObjNull(videos) || Validation.isListEmpty(videos)) {
			log.error("can`t grabing the carouse section from index page");
			return;
		}
		List<Video> vids = new ArrayList<>();
		for (VideoSection video : videos) {
			Video vid = dozer.map(video, Video.class);
			vids.add(vid);
		}
		videoMapper.inserts(vids);
	}

	private Element getSectionByName(String sectionName) {
		int sectionNum = -1;
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

	// 首页头部
	private void analizeHeaderForUrls() {
		List<String> headerUrls = indexAnalizer.analizeHeaderForUrls(this.doc);
		insertGrabLibs(headerUrls);
	}

	// 向grabLib添加数据
	private void insertGrabLibs(List<String> urls) {
		if (!Validation.isListEmpty(urls)) {
			List<GrabLib> libs = new ArrayList<>();
			for (String url : urls) {
				GrabLib lib = new GrabLib();
				lib.setWebSiteAddr(url);
				lib.setGrabedSign("N");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				lib.setAddTime(df.format(new Date()));
				lib.setWebsite(StrPropertiesMapper.ACFUN);
				libs.add(lib);
			}
			grabLibMapper.inserts(libs);
		}
	}

	public void initBeforeGrabing(String targetUri) {
		if (Validation.isStringNull(targetUri)) {
			log.error("the target url can`t be null!");
			return;
		}
		this.seedUri = targetUri;
		PropertiesIO io = new PropertiesIO();
		grabingConfiguations = io.initConnector(configuationPath);
		DocumentInitailizer initailizer = new DocumentInitailizer();
		Connection conn;
		if (Validation.isObjNull(grabingConfiguations) || Validation.isPropertiesEmpty(grabingConfiguations.keySet())) {
			conn = initailizer.initConnectionByUrlOnly(this.seedUri);
		} else {
			conn = initailizer.initConnectionByUrl(this.seedUri, grabingConfiguations);
		}
		if (!Validation.isObjNull(conn))
			this.doc = initailizer.getDocumentByConn(conn);
		else
			log.error("could`t gettig html document from url: " + this.seedUri);
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
