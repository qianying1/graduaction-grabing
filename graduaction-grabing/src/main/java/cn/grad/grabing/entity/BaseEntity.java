package cn.grad.grabing.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 基类
 */
public class BaseEntity implements Serializable{
    private static final long serialVersionUID = 120310937238886244L;

    /**
     * 唯一主键id
     */
    private Long id;
    /**
     * 产生时间
     */
    private Date createDate=new Date();

    /**
     * 获取创建时间
     *
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取唯一主键id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一主键id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
