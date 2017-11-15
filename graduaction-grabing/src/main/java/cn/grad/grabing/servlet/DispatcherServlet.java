package cn.grad.grabing.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 为网站数据抓取做准备
 * @author qianhaibin
 *
 * 2017年11月14日
 */
public class DispatcherServlet extends ResourceLoader {
	private static final long serialVersionUID = 1L;

	private final Log log = LogFactory.getLog(DispatcherServlet.class);

	public void init(ServletConfig config) throws ServletException {
		Properties webSitesProperties = webSitesPropertiesLoader(config);
		initGrabingWeapon(webSitesProperties);
		prepareGrabings();
	}

	public void destroy() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
