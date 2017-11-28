package cn.grad.grabing.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VideoAuthor implements Serializable {

	private Integer authorId;
	private String signature;
	private Integer videoCount;
	private Integer attentionCount;
	private Integer audienceCount;
	private String authorPageUrl;
	private String authorPic;
	private String authorName;

	public VideoAuthor() {
		super();
	}

	@Override
	public String toString() {
		return "VideoAuthor [authorId=" + authorId + ", signature=" + signature + ", videoCount=" + videoCount
				+ ", attentionCount=" + attentionCount + ", audienceCount=" + audienceCount + ", authorPageUrl="
				+ authorPageUrl + ", authorPic=" + authorPic + ", authorName=" + authorName + "]";
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}

	public Integer getAttentionCount() {
		return attentionCount;
	}

	public void setAttentionCount(Integer attentionCount) {
		this.attentionCount = attentionCount;
	}

	public Integer getAudienceCount() {
		return audienceCount;
	}

	public void setAudienceCount(Integer audienceCount) {
		this.audienceCount = audienceCount;
	}

	public String getAuthorPageUrl() {
		return authorPageUrl;
	}

	public void setAuthorPageUrl(String authorPageUrl) {
		this.authorPageUrl = authorPageUrl;
	}

	public String getAuthorPic() {
		return authorPic;
	}

	public void setAuthorPic(String authorPic) {
		this.authorPic = authorPic;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
