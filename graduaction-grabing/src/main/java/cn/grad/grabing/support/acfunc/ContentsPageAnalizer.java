package cn.grad.grabing.support.acfunc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.grad.grabing.common.PropertiesIO;
import cn.grad.grabing.common.ResourceGetter;
import cn.grad.grabing.entityutil.acfunc.VideoSection;

public class ContentsPageAnalizer extends CommonAnalizer {

	@Autowired
	private ResourceGetter resourceGetter;
	@Autowired
	private PropertiesIO propertiesIO;

	public static List<VideoSection> getAllVideoSectionFromAWebPage(String url, Map<String, Object> params) {
		List<VideoSection> results = new ArrayList<>();

		return results;
	}
}
