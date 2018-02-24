package cn.grad.grabing.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础节点
 */
public class BaseVideoNode extends BaseEntity implements Serializable{
    private static final long serialVersionUID = -285784197902815307L;

    /**
     * 唯一标识id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * logo图片
     */
    private String logo;

    /**
     * 指向的链接
     */
    private String href;

    /**
     * 在页面中的位置
     */
    private String location;

    /**
     * 视频类型
     */
    private VideoStyle videoStyle;

    @Override
    public String toString() {
        return "BaseVideoNode{" +
                "videoId=" + id +
                ", title='" + title + '\'' +
                ", logo='" + logo + '\'' +
                ", href='" + href + '\'' +
                '}';
    }

    public BaseVideoNode() {
    }

    public VideoStyle getVideoStyle() {
        return videoStyle;
    }

    public void setVideoStyle(VideoStyle videoStyle) {
        this.videoStyle = videoStyle;
    }

    public BaseVideoNode(String title, String logo) {
        this.title = title;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
