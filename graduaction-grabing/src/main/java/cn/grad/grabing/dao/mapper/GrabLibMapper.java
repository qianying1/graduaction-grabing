package cn.grad.grabing.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.GrabLib;

@Repository("grabLibMapper")
public interface GrabLibMapper extends CommonMapper<GrabLib>{

	public void inserts(List<GrabLib> libs);
}
