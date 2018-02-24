package cn.grad.grabing.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 短视频作者
 */
public class Author extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7371313415742827003L;

    /**
     * 作者名称
     */
    private String name;
    /**
     * 首页地址
     */
    private String indexHref;
    /**
     * 签名
     */
    private String signature;
    /**
     * 拥有视频数量
     */
    private Integer videoCount;
    /**
     * 受关注度
     */
    private Integer attentionCount;
    /**
     * 粉丝数量
     */
    private Integer audienceCount;
    /**
     * 作者头像
     */
    private String logo;
    /**
     * 短视频
     */
    private List<MaskVideoNode> videoNodes;

    public Author( String name, String indexHref, String signature, Integer videoCount, Integer attentionCount, Integer audienceCount, String logo, List<MaskVideoNode> videoNodes) {
        this.name = name;
        this.indexHref = indexHref;
        this.signature = signature;
        this.videoCount = videoCount;
        this.attentionCount = attentionCount;
        this.audienceCount = audienceCount;
        this.logo = logo;
        this.videoNodes = videoNodes;
    }

    @Override
    public String toString() {
        return "VideoAuthor{" +
                ", name='" + name + '\'' +
                ", indexHref='" + indexHref + '\'' +
                ", signature='" + signature + '\'' +
                ", videoCount=" + videoCount +
                ", attentionCount=" + attentionCount +
                ", audienceCount=" + audienceCount +
                ", logo='" + logo + '\'' +
                ", videoNodes=" + videoNodes +
                '}';
    }

    public Author() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexHref() {
        return indexHref;
    }

    public void setIndexHref(String indexHref) {
        this.indexHref = indexHref;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<MaskVideoNode> getVideoNodes() {
        return videoNodes;
    }

    public void setVideoNodes(List<MaskVideoNode> videoNodes) {
        this.videoNodes = videoNodes;
    }
}
