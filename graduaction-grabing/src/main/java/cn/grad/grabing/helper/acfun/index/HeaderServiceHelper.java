package cn.grad.grabing.helper.acfun.index;

import cn.grad.grabing.dao.helper.GrabLibDaoHelper;
import cn.grad.grabing.entity.Navigation;
import cn.grad.grabing.support.acfunc.IndexAnalizer;
import cn.grad.grabing.util.Validation;
import com.gargoylesoftware.htmlunit.Page;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeaderServiceHelper {

    @Autowired
    private GrabLibDaoHelper grabLibDaoHelper;
    @Autowired
    private IndexAnalizer indexAnalizer;

    /**
     * 爬取首页头部导航栏
     */
    public void headerGrabbing(Document doc){
        Navigation navigation = new Navigation();
        indexAnalizer.analizeHeaderForUrls(doc, navigation);
        if (Validation.isObjNotNull(navigation) && Validation.isListNotEmpty(navigation.getNavNodeList())) {
            grabLibDaoHelper.insertGrabLibs(navigation);
        }
    }

}
