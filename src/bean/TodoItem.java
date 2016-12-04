package bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class TodoItem implements Serializable{
    private Integer tid;
    private String title;
    private boolean completed;
    private boolean reminded;
    private String date;



    public TodoItem() {
    }

    public TodoItem(String title, boolean isCompleted, boolean isReminded, String date) {
        this.title = title;
        this.completed = isCompleted;
        this.reminded = isReminded;
        this.date = date;
    }


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        completed = completed;
    }

    public boolean isReminded() {
        return reminded;
    }

    public void setReminded(boolean reminded) {
        reminded = reminded;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
