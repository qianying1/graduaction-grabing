package cn.grad.grabing.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GrabLib implements Serializable {
	private Integer grabId;
	private String website;
	private String webSiteAddr;
	private String grabedSign;
	private String addTime;

	public GrabLib() {
		super();
	}

	@Override
	public String toString() {
		return "GrabLib [grabId=" + grabId + ", website=" + website + ", webSiteAddr=" + webSiteAddr + ", grabedSign="
				+ grabedSign + ", addTime=" + addTime + "]";
	}

	public Integer getGrabId() {
		return grabId;
	}

	public void setGrabId(Integer grabId) {
		this.grabId = grabId;
	}

	public String getWebSiteAddr() {
		return webSiteAddr;
	}

	public void setWebSiteAddr(String webSiteAddr) {
		this.webSiteAddr = webSiteAddr;
	}

	public String getGrabedSign() {
		return grabedSign;
	}

	public void setGrabedSign(String grabedSign) {
		this.grabedSign = grabedSign;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
