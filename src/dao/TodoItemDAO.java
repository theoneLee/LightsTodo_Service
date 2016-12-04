package dao;

import bean.TodoItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

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
