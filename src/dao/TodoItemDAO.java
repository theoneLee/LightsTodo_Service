package dao;

import bean.TodoItem;
import bean.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class TodoItemDAO {

    public int addTodoItem(TodoItem item) {
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.save(item);
            int id=item.getTid();
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

    public int deleteTodoItem(TodoItem item){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.delete(item);
            int id=item.getTid();//和add，update一样。都是在session.xxx()后就能够拿到id
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
    public void deleteTodoItemByUid(int uid){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        Query query=s.createQuery("from User u inner join fetch u.todoItems where u.uid=?");
        query.setInteger(0,uid);
        User user= (User) query.uniqueResult();
        List<TodoItem> list= (List<TodoItem>) user.getTodoItems();
        if (list.isEmpty())
            return ;
        for (TodoItem item:list){
            try{
                s.delete(item);
                tx.commit();
            }catch (Exception e){
                if (tx!=null)tx.rollback();
                e.printStackTrace();
            }
        }

    }

    public int updateTodoItem(TodoItem item){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.update(item);
            int id=item.getTid();
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

    public TodoItem getTodoItemById(int id){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        TodoItem item=s.get(TodoItem.class,id);
        tx.commit();//开启的Transaction要提交，不然会报『事务处于活跃的bug』
        return item;
    }



    //下面写hql查询

}
