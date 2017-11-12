package cn.grad.grabing.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 为网络页面抓取做准备
 */
public class PrepareWebGrabing implements Filter {

	public PrepareWebGrabing() {
		System.out.println("filter contrustor have been used@@!");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("filter destroy method have been used!!");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter doFilter have been used!!");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("filter init method have been used!");
	}

}
