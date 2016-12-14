package dao;

import bean.TodoItem;
import bean.User;
import bean.ZoneMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class UserDAO {

    public int addUser(User user) {
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.save(user);
            int id=user.getUid();
            tx.commit();
            return id;
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            return -1;
        }

    }

    public int deleteUser(User user){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.delete(user);
            int id=user.getUid();//和add，update一样。都是在session.xxx()后就能够拿到id
            tx.commit();
            return id;
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            return -1;
        }

    }

    public int updateUser(User user){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.update(user);
            int id=user.getUid();
            tx.commit();
            return id;
        } catch (Exception e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
            return -1;
        }
    }

    public User getUserById(int id){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        User user=s.get(User.class,id);
        tx.commit();
        return user;
    }


    //下面做关联操作（与todoitem，zoneMessage）

    /**
     * 数据库已有User下
     *
     * @param userName 数据库早已经存了User信息，所以在通讯中只要传对应的userName即可，用来获取数据库的user实例
     * @param list 从客户端拿到的数据经过处理后
     */
    public void connectTodoItem(String userName, ArrayList<TodoItem> list){
        User user=getUserByName(userName);

        for (TodoItem item:list){
            user.getTodoItems().add(item);//对每一个TodoItem建立和user的关联关系(单向一对多)
        }

        updateUser(user);
    }
    public int connectTodoItem(int id, ArrayList<TodoItem> list){
        User user=getUserById(id);

        for (TodoItem item:list){
            user.getTodoItems().add(item);//对每一个TodoItem建立和user的关联关系(单向一对多)
        }

        return updateUser(user);
    }


    public void connectZoneMessage(String userName, ArrayList<ZoneMessage> list){
        User user=getUserByName(userName);
        for (ZoneMessage message:list){
            user.getZoneMessages().add(message);//对每一个ZoneMessage建立和user的关联关系(双向一对多)
            message.setUser(user);//设置多对一操作
        }

        updateUser(user);
    }




    //下面可以写hql的查询语句
    public User getUserByName(String userName) {
        Session session=HibernateUtil.getSession();
        Transaction tx=session.beginTransaction();
        Query query=session.createQuery("from User where userName=?");
        query.setString(0,userName);
        User user=(User) query.uniqueResult();
        tx.commit();
        return user;
    }


    public List<TodoItem> getTodoItemList(int uid) {
        Session session=HibernateUtil.getSession();
        Transaction tx=session.beginTransaction();
        Query query=session.createQuery("select u.todoItems from User u where u.uid=?");
        query.setInteger(0,uid);
        List<TodoItem> list= query.list();
        tx.commit();
        return list;
    }

    public List<ZoneMessage> getZoneMessageListById(int uid, int offset, int count) {
        Session session=HibernateUtil.getSession();
        Transaction tx=session.beginTransaction();
        Query query=session.createQuery("select u.zoneMessages from User u where u.uid=?");
        query.setInteger(0,uid);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<ZoneMessage> list=query.list();
        tx.commit();
        return list;
    }
}
