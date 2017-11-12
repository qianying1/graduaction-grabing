package cn.grad.grabing.dao.mapper;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.AnalizedMessage;
import cn.grad.grabing.domain.GrabMessage;

@Repository("grabMessageMapper")
public interface GrabMessageMapper extends CommonMapper<GrabMessage> {

	public int addRecord(AnalizedMessage analizedMessage);

	public int saveOrUpdate(AnalizedMessage analizedMessage);

	public AnalizedMessage getDetail(String id);

}
