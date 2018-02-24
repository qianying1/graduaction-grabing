package cn.grad.grabing.dao.helper;

import cn.grad.grabing.dao.mapper.GrabLibMapper;
import cn.grad.grabing.domain.GrabLib;
import cn.grad.grabing.domain.Style;
import cn.grad.grabing.entity.HrefLib;
import cn.grad.grabing.entity.NavNode;
import cn.grad.grabing.entity.Navigation;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
public class GrabLibDaoHelper extends BaseUtil{

	@Autowired
	private GrabLibMapper grabLibMapper;
	@Autowired
	private StyleDaoHelper styleHelper;
	
	public void insertGrabLibs(Navigation navigation) {
		if(Validation.isObjNull(navigation)||Validation.isObjNull(navigation.getNavNodeList())
				||Validation.isListEmpty(navigation.getNavNodeList())){
			return;
		}
		if (Validation.isObjNotNull(navigation)) {
			List<GrabLib> libs = new ArrayList<>();
			List<Style> styles=new ArrayList<>();
			for (NavNode el : navigation.getNavNodeList()) {
				Style style=new Style();
				style.setStyleName(el.getName());
				styles.add(style);
				
				GrabLib lib = new GrabLib();
				lib.setWebSiteAddr(el.getAhref());
				lib.setIsGrabbed(false);
				lib.setCreateDate(new Date());
				lib.setWebsite(StrPropertiesMapper.ACFUN);
				lib.setName(el.getName());
				libs.add(lib);
			}
			styleHelper.inertStyles(styles);
			grabLibMapper.inserts(libs);
		}
	}

	public void inserts(List<HrefLib> hrefLibs){
		if(ArrayUtils.isEmpty(hrefLibs.toArray())){
			return;
		}
		List<GrabLib> grabLibs=new ArrayList<>();
		for(HrefLib hrefLib:hrefLibs){
			if(ObjectUtils.equals(hrefLib,null)){
				continue;
			}
			grabLibs.add(dozer.map(hrefLib,GrabLib.class));
		}
		grabLibMapper.inserts(grabLibs);
	}

}
