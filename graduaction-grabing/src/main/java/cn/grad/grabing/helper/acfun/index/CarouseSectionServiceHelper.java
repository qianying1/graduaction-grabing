package cn.grad.grabing.helper.acfun.index;

import cn.grad.grabing.dao.helper.VideoDaoHelper;
import cn.grad.grabing.domain.Video;
import cn.grad.grabing.entity.BaseVideoNode;
import cn.grad.grabing.entity.CarouseSection;
import cn.grad.grabing.entity.MaskVideoNode;
import cn.grad.grabing.helper.acfun.SectionUtil;
import cn.grad.grabing.support.acfunc.IndexAnalizer;
import cn.grad.grabing.util.BaseUtil;
import cn.grad.grabing.util.Validation;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮播节点业务层辅助组件
 */
@Component
public class CarouseSectionServiceHelper extends BaseUtil{
    @Autowired
    private IndexAnalizer indexAnalizer;
    @Autowired
    private VideoDaoHelper videoDaoHelper;

    /**
     * 分析带轮播的节点
     */
    public void analizeCarouselSection(Document doc) {
        CarouseSection carouseSection=new CarouseSection();
        if(Validation.isObjNull(carouseSection)){
            carouseSection=new cn.grad.grabing.entity.CarouseSection();
        }
        indexAnalizer.analizeCarouselSection(SectionUtil.getSectionByName(SectionUtil.CAROUSEL,doc),carouseSection);
        if (Validation.isObjNull(carouseSection) ||Validation.isListEmpty(carouseSection.getMaskVideoNodes())) {
            log.error("can`t grabing the carouse section from index page");
            return;
        }
//        System.out.println("carouselSection size: "+carouseSection.getMaskVideoNodes().size()+" the location is: "+carouseSection.getMaskVideoNodes().get(0).getLocation());
        videoDaoHelper.insertVideos(carouseSection.getMaskVideoNodes(),false);
    }
}
