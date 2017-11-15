package cn.grad.grabing.servlet;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;

import cn.grad.grabing.helper.GrabingThread;
import cn.grad.grabing.util.PropertiesIO;
import cn.grad.grabing.util.StrPropertiesMapper;
import cn.grad.grabing.util.Validation;

/**
 * Servlet implementation class ResourceLoader
 */
public class ResourceLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PropertiesIO pIo = new PropertiesIO();
	private final Log log = pIo.log;

	private Map<Object, Object> webSites;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResourceLoader() {
		super();
	}

	private void initGrabingWeaponWebSiteParams(Properties webSitesProperties) {
		this.webSites = webSitesProperties;
	}

	protected void initGrabingWeapon(Properties webSitesProperties) {
		if (Validation.isObjNull(webSites))
			webSites = new HashMap<>();
		initGrabingWeaponWebSiteParams(webSitesProperties);
	}

	protected Properties webSitesPropertiesLoader(ServletConfig config) {
		String webSitesPath = config.getInitParameter(StrPropertiesMapper.WEBSITESPROPERTIESFILE);
		if (Validation.isStringNull(webSitesPath) || Validation.isStringEmpty(webSitesPath)) {
			log.error("don`t have any webSitesPath have been config");
			return null;
		}
		webSitesPath = Validation.enValueConfigPath(webSitesPath);
		return pIo.initConnector(webSitesPath);
	}

	protected void prepareGrabings() {
		for (Object website : webSites.keySet()) {
			GrabingThread grabingThread = new GrabingThread();
			grabingThread.setTargetName((String) website);
			grabingThread.setTargetValue((String) webSites.get(website));
			new Thread(grabingThread).start();
		}
	}

}
