package cn.grad.grabing.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.grad.grabing.domain.AnalizedMessage;

@Repository("analizedMessageMapper")
public interface AnalizedMessageMapper extends CommonMapper<AnalizedMessage> {

	public List<AnalizedMessage> listAll();

	public int addRecord(AnalizedMessage analizedMessage);

	public int saveOrUpdate(AnalizedMessage analizedMessage);

	public AnalizedMessage getDetail(String id);
}
