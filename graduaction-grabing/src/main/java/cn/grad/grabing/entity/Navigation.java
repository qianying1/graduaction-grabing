package cn.grad.grabing.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 导航实体
 */
@SuppressWarnings("serial")
public class Navigation implements Serializable{

    private List<NavNode> navNodeList;
    private location location;

    public Navigation() {
    }

    @Override
    public String toString() {
        return "Navigation{" +
                "navNodeList=" + navNodeList +
                ", location=" + location +
                '}';
    }

    public Navigation(List<NavNode> navNodeList, Navigation.location location) {
        this.navNodeList = navNodeList;
        this.location = location;
    }

    public Navigation.location getLocation() {
        return location;
    }

    public void setLocation(Navigation.location location) {
        this.location = location;
    }

    public enum  location{
       top,
        middle,
        bottom
    };

    public List<NavNode> getNavNodeList() {
        if(navNodeList==null){
            navNodeList=new ArrayList<>();
        }
        return navNodeList;
    }

    public void setNavNodeList(List<NavNode> navNodeList) {
        this.navNodeList = navNodeList;
    }
}
