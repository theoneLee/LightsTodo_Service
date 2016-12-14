package web;

import bean.ZoneComment;
import bean.ZoneMessage;
import dao.UserDAO;
import dao.ZoneMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lee on 2016/12/9 0009.
 */
@RestController
@RequestMapping("/zoneMessages")
public class ZoneMessageController {
    @Autowired
    private ZoneMessageDAO zoneMessageDAO;

    @Autowired
    private UserDAO userDAO;

    /**
     * 获取当前所有动态。给个前标和一个数量，返回对应数量的message
     * 处理/zoneMessages?offset=1&count=1
     * @param offset
     * @param count
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<ZoneMessage> getCurrentZoneMessageList(
            @RequestParam(value = "offset",defaultValue = "0")int offset,@RequestParam(value = "count",defaultValue = "10")int count){
        return zoneMessageDAO.getZoneMessageList(offset,count);
    }

    /**
     * 获取某个用户的所有动态，给个前标和一个数量和uid
     * 处理/zoneMessages/user/1?offset=1&count=1  不加后面参数就用默认值
     * @param uid
     * @param offset
     * @param count
     * @return
     */
    @RequestMapping(value = "/user/{uid}",method = RequestMethod.GET)
    public List<ZoneMessage> getZoneMessageListByUid(@PathVariable int uid,@RequestParam(value = "offset",defaultValue = "0")int offset,@RequestParam(value = "count",defaultValue = "10")int count){
        if(uid==-1)
            return null;
        return userDAO.getZoneMessageListById(uid,offset,count);
    }

    /**
     *
     * 要点进去消息详情才有评论（即进去之后才关联查询）
     * 获取某条message的Comment
     * 处理/zoneMessages/1的get
     * @param zid
     * @return
     */
    @RequestMapping(value = "/{zid}",method = RequestMethod.GET)
    public List<ZoneComment> getZoneCommentList(@PathVariable int zid){
        if(zid==-1)
            return null;
        return zoneMessageDAO.getZoneCommentListByZid(zid);
    }

    /**
     * 处理/zoneMessages/user/1的post
     * @param zoneMessage 要求json要能直接转化为ZoneMessage对象
     * @param uid
     * @return
     */
    @RequestMapping(value = "/user/{uid}",method = RequestMethod.POST,consumes = "application/json")
    public int saveZoneMessage(@RequestBody ZoneMessage zoneMessage,@PathVariable int uid){
        return zoneMessageDAO.appendZoneMessage(uid,zoneMessage);//发表朋友圈信息时,通过uid查询User，拿到zoneMessage队列，然后往里面加一个传过来的ZoneMessage
    }
}
