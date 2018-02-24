package cn.grad.grabing.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.VideoAuthor;
@Repository("videoAuthorMapper")
public interface VideoAuthorMapper extends CommonMapper<VideoAuthor> {

	Long insert(VideoAuthor videoAuthor);

	Long selects(String authorPageUrl);

}
