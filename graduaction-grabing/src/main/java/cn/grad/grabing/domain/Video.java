package cn.grad.grabing.domain;

import java.io.Serializable;
@SuppressWarnings("serial")
public class Video implements Serializable {
	private Long videoId;
	private String videoName;
	private String link;
	private String image;

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoName=" + videoName + ", link=" + link + ", image=" + image + "]";
	}

	public Video() {
		super();
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
