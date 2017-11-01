package cn.grad.grabing.entityutil.acfunc;

public class VideoSection {
	private String link;
	private String image;

	public VideoSection(String link, String image) {
		super();
		this.link = link;
		this.image = image;
	}

	public VideoSection() {
		super();
	}

	@Override
	public String toString() {
		return "Carousel [link=" + link + ", image=" + image + "]";
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
