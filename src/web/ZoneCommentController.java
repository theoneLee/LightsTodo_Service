package web;

import bean.ZoneComment;
import dao.ZoneCommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lee on 2016/12/13 0013.
 */
@RestController
@RequestMapping("/zoneComments")
public class ZoneCommentController {

    @Autowired
    private ZoneCommentDAO zoneCommentDAO;

    @RequestMapping(value = "/zoneMessage/{zid}",method = RequestMethod.POST,consumes = "application/json")
    public int saveZoneComment(@PathVariable int zid, @RequestBody ZoneComment zoneComment){
        return zoneCommentDAO.appendZoneComment(zid,zoneComment);//发表朋友圈信息的评论时,通过zid查询ZoneMessage，拿到zoneComment队列，然后往里面加一个传过来的ZoneComment，再更新ZoneMessage即可
    }

}
