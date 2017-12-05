package cn.grad.grabing.support.acfunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.grabing.entityutil.acfun.VideoSection;
import cn.grad.grabing.helper.DocumentInitailizer;
import cn.grad.grabing.util.PropertiesIO;

public class ContentsPageAnalizer extends CommonAnalizer {

	@Autowired
	private DocumentInitailizer resourceGetter;
	@Autowired
	private PropertiesIO propertiesIO;

	public static List<VideoSection> getAllVideoSectionFromAWebPage(String url, Map<String, Object> params) {
		List<VideoSection> results = new ArrayList<>();

		return results;
	}
}
