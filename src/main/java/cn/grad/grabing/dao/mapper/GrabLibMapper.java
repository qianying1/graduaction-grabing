package cn.grad.grabing.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.GrabLib;

@Repository("grabLibMapper")
public interface GrabLibMapper extends CommonMapper<GrabLib>{

	public void insert(int contentId, String webName, String ahref, String flag);

	public void inserts(int contentId, String webName, List<String> ahrefList, String flag);

	public boolean isGrabed(String url);

	public boolean selectGrabLibs(String url);
}
