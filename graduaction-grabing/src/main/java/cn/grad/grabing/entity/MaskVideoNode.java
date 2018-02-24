package cn.grad.grabing.entity;

import java.io.Serializable;

/**
 * 用于展示的小视频
 */
public class MaskVideoNode extends BaseVideoNode implements Serializable{
    private static final long serialVersionUID = -7772270424778035525L;

    /**
     * up主名称
     */
    private String upMan;
    /**
     * 播放次数
     */
    private Long views;
    /**
     * 弹幕数量
     */
    private Long masks;

    /**
     * 视频长度
     */
    private String times;

    /**
     * 评论数量
     */
    private Long comments;

    /**
     * 投蕉数
     */
    private Long bananas;
    /**
     * 视频作者
     */
    private Author author;

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public MaskVideoNode(String upMan, Long views, Long masks, String times, Long bananas) {
        this.upMan = upMan;
        this.views = views;
        this.masks = masks;
        this.times = times;
        this.bananas = bananas;
    }

    public MaskVideoNode(String title, String logo, String upMan, Long views, Long masks, String times, Long bananas) {
        super(title, logo);
        this.upMan = upMan;
        this.views = views;
        this.masks = masks;
        this.times = times;
        this.bananas = bananas;
    }

    public MaskVideoNode() {
    }

    public String getUpMan() {
        return upMan;
    }

    public void setUpMan(String upMan) {
        this.upMan = upMan;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getMasks() {
        return masks;
    }

    public void setMasks(Long masks) {
        this.masks = masks;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Long getBananas() {
        return bananas;
    }

    public void setBananas(Long bananas) {
        this.bananas = bananas;
    }
}
