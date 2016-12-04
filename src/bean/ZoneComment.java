package bean;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class ZoneComment {
    private Integer cid;
    private String userName;
    private String content;
    private Date date;



    public ZoneComment() {
    }

    public ZoneComment(String userName, String content, Date date) {
        this.userName = userName;
        this.content = content;
        this.date = date;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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


}
