package cn.grad.grabing.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("/testUtil")
public class TestUtil {

	@Value("${acfun}")
	private String acfunUri;

	public void printUri() {
		System.out.println("acfun: " + acfunUri);
	}
}
