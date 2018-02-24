package cn.grad.grabing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 轮播节点
 */
@SuppressWarnings("serial")
public class CarouseSection implements Serializable{

    /**
     * 拥有详细信息的视频
     */
    private List<MaskVideoNode> maskVideoNodes;

    public CarouseSection( List<MaskVideoNode> maskVideoNodes) {
        this.maskVideoNodes = maskVideoNodes;
    }

    public List<MaskVideoNode> getMaskVideoNodes() {
        if(maskVideoNodes==null){
            maskVideoNodes=new ArrayList<>();
        }
        return maskVideoNodes;
    }

    public void setMaskVideoNodes(List<MaskVideoNode> maskVideoNodes) {
        this.maskVideoNodes = maskVideoNodes;
    }

    public CarouseSection() {
    }

    @Override
    public String toString() {
        return "CarouseSection{" +
                ", maskVideoNodes=" + maskVideoNodes +
                '}';
    }
}
