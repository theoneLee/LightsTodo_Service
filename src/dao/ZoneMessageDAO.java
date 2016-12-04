package dao;

import bean.ZoneComment;
import bean.ZoneMessage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Created by Lee on 2016/11/29 0029.
 */
@Component
public class ZoneMessageDAO {

    public int addZoneMessage(ZoneMessage message) {
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.save(message);
            int id=message.getZid();
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

    public int deleteZoneMessage(ZoneMessage message){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.delete(message);
            int id=message.getZid();//和add，update一样。都是在session.xxx()后就能够拿到id
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

    public int updateZoneMessage(ZoneMessage message){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        try {
            s.update(message);
            int id=message.getZid();
            //System.out.println(id);
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

    public ZoneMessage getZoneMessageById(int id){
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        ZoneMessage message=s.get(ZoneMessage.class,id);
        tx.commit();
        return message;
    }

    //做关联操作（与ZoneComment）

    /**
     *
     * @param zoneMessageId
     * @param list
     */
    public void connectZoneComment(int zoneMessageId, ArrayList<ZoneComment> list){
        ZoneMessage message=getZoneMessageById(zoneMessageId);

        for (ZoneComment comment:list){
            message.getZoneComments().add(comment);//单向一对多
        }

        updateZoneMessage(message);
    }







    //下面写hql查询
}
