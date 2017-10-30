package cn.grad.grabing.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.WebSites;

@Repository("websitesMapper")
public interface WebsitesMapper extends CommonMapper<WebSites> {

	boolean acfunIsInserted(String webUrl);

}
