package cn.grad.grabing.entityutil.acfun.index;

import java.util.List;

public class Nav {

	private String pageName;
	@Override
	public String toString() {
		return "Nav [pageName=" + pageName + ", els=" + els + "]";
	}

	private List<NavEl> els;

	public List<NavEl> getEls() {
		return els;
	}

	public void setEls(List<NavEl> els) {
		this.els = els;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

}
