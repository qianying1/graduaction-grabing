package cn.grad.grabing.dao.helper;

import cn.grad.grabing.dao.mapper.VideoAuthorMapper;
import cn.grad.grabing.dao.mapper.VideoMapper;
import cn.grad.grabing.domain.Video;
import cn.grad.grabing.domain.VideoAuthor;
import cn.grad.grabing.entity.MaskVideoNode;
import cn.grad.grabing.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 短视频dao层辅助工具
 * @param <T>
 */
@Component
public class VideoDaoHelper<T> extends BaseUtil{

    @Autowired
    private VideoAuthorMapper videoAuthorMapper;

    @Autowired
    private VideoMapper videoMapper;
    public void insertVideos(List<MaskVideoNode> vids,Boolean isAuthorInsert){
        try{
            if(isAuthorInsert){
                VideoAuthor videoAuthor;
                Video video;
                for(MaskVideoNode maskNideo:vids){
                    video=dozer.map(maskNideo,Video.class);
                    if(video==null){
                        continue;
                    }
                    if(video.getAuthor()!=null) {
                        videoAuthor = video.getAuthor();
                        videoAuthorMapper.insert(videoAuthor);
                        video.setAuthor(videoAuthor);
                    }
                    videoMapper.insert(video);
                }
            }else {
                List<Video> inserts=new ArrayList<>();
                for (MaskVideoNode videoNode:vids){
                    inserts.add(dozer.map(videoNode,Video.class));
                }
                videoMapper.inserts(inserts);
            }
        }catch (Exception e){
            log.error("fail to insert data to video table!",e);
        }
    }
}
