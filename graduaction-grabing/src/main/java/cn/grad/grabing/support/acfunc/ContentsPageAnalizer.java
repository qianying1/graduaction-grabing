package cn.grad.grabing.support.acfunc;

import cn.grad.grabing.helper.DocumentInitailizer;
import cn.grad.grabing.util.PropertiesIO;
import org.springframework.beans.factory.annotation.Autowired;

public class ContentsPageAnalizer extends CommonAnalizer {

	@Autowired
	private DocumentInitailizer resourceGetter;
	@Autowired
	private PropertiesIO propertiesIO;

	/*public static List<VideoSection> getAllVideoSectionFromAWebPage(String url, Map<String, Object> params) {
		List<VideoSection> results = new ArrayList<>();

		return results;
	}*/
}
