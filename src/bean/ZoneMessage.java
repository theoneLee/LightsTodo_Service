package bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class ZoneMessage implements Serializable{
    private Integer zid;
    private String userName;
    private String content;
    private Date date;

    Set<ZoneComment> zoneComments=new HashSet<ZoneComment>();//一个朋友圈信息可以有0-n个评论

    User user;//多对一

    public ZoneMessage() {
    }

    public ZoneMessage( String content, Date date) {
        this.content = content;
        this.date = date;
    }

    public Integer getZid() {
        return zid;
    }

    public void setZid(Integer zid) {
        this.zid = zid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<ZoneComment> getZoneComments() {
        return zoneComments;
    }

    public void setZoneComments(Set<ZoneComment> zoneComments) {
        this.zoneComments = zoneComments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
