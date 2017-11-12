package cn.grad.grabing.common;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class LowerCaseWithUnderscoresObjectMapper extends ObjectMapper {
	public LowerCaseWithUnderscoresObjectMapper() {
		super();
		setSerializationInclusion(Inclusion.NON_NULL);//null值不打印
		setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);//小写规范
	}
}
