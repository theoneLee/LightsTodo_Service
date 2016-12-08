package test;

import bean.TodoItem;
import bean.User;
import bean.ZoneComment;
import bean.ZoneMessage;
import dao.TodoItemDAO;
import dao.UserDAO;
import dao.ZoneMessageDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lee on 2016/11/29 0029.
 */

public class test {
    private User user;

    public static void main(String[] args) {
        test t=new test();
//        t.testTodoItem();
//        t.testZoneMessage();
//        t.testZoneComment();
//        t.testDelete();
        t.testSpring();
    }



    test(){

    }

    @Autowired
    private void setUser(User user){
        this.user=user;
    }

    private void testSpring() {
        System.out.println(user);
    }



    public void testTodoItem(){
        UserDAO userDAO=new UserDAO();
        TodoItemDAO todoItemDAO=new TodoItemDAO();

        User user=new User("lee","1234","",0,0,0);
        userDAO.addUser(user);

        TodoItem item1=new TodoItem("1",false,false,new Date().toString());
        TodoItem item2=new TodoItem("2",false,false,new Date().toString());
        ArrayList<TodoItem> todoItems=new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);

        userDAO.connectTodoItem("lee",todoItems);//这个函数里面已经会使用update将User提交到数据库
    }

    private void testZoneMessage() {
        UserDAO userDAO=new UserDAO();

        ZoneMessage zoneMessage1=new ZoneMessage("zone meg1",new Date());
        ZoneMessage zoneMessage2=new ZoneMessage("zone meg2",new Date());
        ArrayList<ZoneMessage> items=new ArrayList<ZoneMessage>();
        items.add(zoneMessage1);
        items.add(zoneMessage2);

        userDAO.connectZoneMessage("lee",items);
    }

    private void testZoneComment() {
        ZoneMessageDAO dao=new ZoneMessageDAO();

        ZoneComment comment1=new ZoneComment("z1","content1",new Date());
        ZoneComment comment2=new ZoneComment("z2","content2",new Date());
        ArrayList<ZoneComment> items=new ArrayList<ZoneComment>();
        items.add(comment1);
        items.add(comment2);

        dao.connectZoneComment(1,items);
    }

    private void testDelete(){//测试delete是否能够返回id--》可以
        ZoneMessageDAO dao=new ZoneMessageDAO();
        ZoneMessage message=dao.getZoneMessageById(1);
        int id=dao.deleteZoneMessage(message);
        System.out.println(id);
    }


}
