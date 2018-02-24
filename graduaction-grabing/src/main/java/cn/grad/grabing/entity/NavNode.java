package cn.grad.grabing.entity;

import java.io.Serializable;

/**
 * 导航节点实体
 */
@SuppressWarnings("serial")
public class NavNode implements Serializable{
    private String name;
    private String ahref;

    public NavNode(String name, String ahref) {
        this.name = name;
        this.ahref = ahref;
    }

    public NavNode() {
    }

    @Override
    public String toString() {
        return "NavNode{" +
                "name='" + name + '\'' +
                ", ahref='" + ahref + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAhref() {
        return ahref;
    }

    public void setAhref(String ahref) {
        this.ahref = ahref;
    }
}
