package cn.grad.grabing.service;

import java.io.IOException;
import java.util.List;

import cn.grad.grabing.domain.GrabingWeb;

public interface GrabingService{

	void grabWebInBF(List<GrabingWeb> grabingWebs) throws IOException;


}
