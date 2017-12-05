package cn.grad.grabing.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Style implements Serializable {

	private Long styleId;
	private String name;
	private String link;
	private String flag;

	public Long getStyleId() {
		return styleId;
	}

	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}

	public String getName() {
		return name;
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
