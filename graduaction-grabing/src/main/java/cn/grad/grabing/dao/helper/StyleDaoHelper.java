package cn.grad.grabing.dao.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.grad.grabing.dao.mapper.StyleMapper;
import cn.grad.grabing.domain.Style;
import cn.grad.grabing.util.Validation;

@Component
public class StyleDaoHelper {

	@Autowired
	private StyleMapper styleMapper;

	public void inertStyles(List<Style> styles) {
		if(Validation.isListEmpty(styles))
			return;
		for(Style style:styles){
			Style style2=styleMapper.selectOneByName(style.getStyleName());
			if(Validation.isObjNotNull(style2))
				continue;
			styleMapper.saveOrUpdate(style);
		}
	}
	
}
