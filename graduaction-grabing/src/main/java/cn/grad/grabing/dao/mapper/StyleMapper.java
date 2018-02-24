package cn.grad.grabing.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.Style;

@Repository
public interface StyleMapper {

	public List<Style> listAll();

	public void inserts(List<Style> styles);

	public void saveOrUpdate(Style style);

	public Style selectOneByName(String styleName);
}
