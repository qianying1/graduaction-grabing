package cn.grad.grabing.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.Video;

@Repository("videoMapper")
public interface VideoMapper extends CommonMapper<Video> {

	void inserts(List<Video> vids);

	void insert(Video video);
}
