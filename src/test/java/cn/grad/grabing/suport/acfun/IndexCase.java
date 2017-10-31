package cn.grad.grabing.suport.acfun;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import cn.grad.grabing.support.acfunc.IndexAnalizer;

@RunWith(SpringJUnit4ClassRunner.class) // 使用Junit4进行测试
@ContextConfiguration(locations = { "classpath:spring/spring-mybatis.xml", })
public class IndexCase extends BaseUtil {

	@Autowired
	IndexAnalizer indexAnalizer;
	List<String> urls = null;
	PropertiesIO io = null;

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public void init() {
		System.out.println("initualing.....");
		urls = new ArrayList<>();
		io = new PropertiesIO();
		setUrls(io.getPropertiesValues());
		System.out.println("initual end......");
	}

	@Before
	public void before() {
		System.out.println("before initual");
		init();
		System.out.println("after initual");
	}

	@Test
	public void testHeaderAnalizer() {
		for (String url : getUrls()) {
			Document doc = null;
			try {
				doc = Jsoup.parse(new URL(url), 20 * 60 * 60);
			} catch (MalformedURLException e) {
				log.error("the url is valide: " + url, e);
			} catch (IOException e) {
				log.error("connect to " + url + " error", e);
			}
			List<String> links = indexAnalizer.analizeHeaderForUrls(doc);
			System.out.println(links);
		}
	}

}
