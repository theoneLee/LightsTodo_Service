package bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class User implements Serializable{
    private Integer uid;
    private String userName;
    private String userPW;
    private String icon;//icon资源路径(id)

    //Todo机制相关
    private int totalDay;//总天数
    private int currentDay;//不间断天数，也是累计到现在的天数
    private int point;//积分

    //关联关系
    @JsonIgnore
    Set<TodoItem> todoItems=new HashSet<TodoItem>();//一个User可以有0-n个todoitem
    @JsonIgnore
    Set<ZoneMessage> zoneMessages=new HashSet<ZoneMessage>();//一个User可以有0-n个ZoneMessage

    public User() {
    }

    public User(String userName, String userPW, String icon, int totalDay, int currentDay, int point) {
        this.userName = userName;
        this.userPW = userPW;
        this.icon = icon;
        this.totalDay = totalDay;
        this.currentDay = currentDay;
        this.point = point;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(int totalDay) {
        this.totalDay = totalDay;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Set<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(Set<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public Set<ZoneMessage> getZoneMessages() {
        return zoneMessages;
    }

    public void setZoneMessages(Set<ZoneMessage> zoneMessages) {
        this.zoneMessages = zoneMessages;
    }
}
