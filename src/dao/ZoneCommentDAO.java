package dao;

import bean.ZoneComment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class ZoneCommentDAO {
    public int addZoneComment(ZoneComment comment) {
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.save(comment);
            int id=comment.getCid();
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

    public int deleteZoneComment(ZoneComment comment){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.delete(comment);
            int id=comment.getCid();//和add，update一样。都是在session.xxx()后就能够拿到id
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

    public int updateZoneComment(ZoneComment comment){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.update(comment);
            int id=comment.getCid();
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

    public ZoneComment getZoneCommentById(int id){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        ZoneComment comment=s.get(ZoneComment.class,id);
        tx.commit();
        return comment;
    }


    //下面写hql查询

}
