package cn.grad.grabing.entityutil.acfun.index;

public class NavEl {

	private String name;
	private String link;
	private String flag;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "NavEl [name=" + name + ", link=" + link + ", flag=" + flag + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
