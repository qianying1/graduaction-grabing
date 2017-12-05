package cn.grad.grabing.dao.mapper;

import java.util.List;

import cn.grad.grabing.domain.Style;

public interface StyleMapper {
	public List<Style> listAll();

	public void inserts(List<Style> styles);

	public void saveOrUpdate(Style style);
}
