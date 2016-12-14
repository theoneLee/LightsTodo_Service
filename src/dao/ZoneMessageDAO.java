package dao;

import bean.User;
import bean.ZoneComment;
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


    /**
     *做关联操作（与ZoneComment）
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

    /**
     * 返回对应zid的comment　List
     * @param zid
     * @return
     */
    public List<ZoneComment> getZoneCommentListByZid(int zid) {
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        Query query=s.createQuery("select m.zoneComments from ZoneMessage m where m.zid=?");//查询时不需要关联查询，因为你要查的只有zoneComments，如果加入不会报错，但是输出的结果是有重复的
        query.setInteger(0,zid);
        List<ZoneComment> list= query.list();
        tx.commit();
        return list;
    }

    /**
     * 返回一个按时间从大到小的list
     * @param offset
     * @param count
     * @return
     */
    public List<ZoneMessage> getZoneMessageList(int offset, int count) {
        Session s= HibernateUtil.getSession();
        Transaction tx=s.beginTransaction();
        Query query=s.createQuery("from ZoneMessage order by date desc");
        query.setFirstResult(offset);
        query.setMaxResults(count);
        List<ZoneMessage> list=query.list();
        tx.commit();
        return list;
    }

    /**
     * 新增一个ZoneMessage
     * @param uid
     * @param zoneMessage
     * @return
     */
    public int appendZoneMessage(int uid, ZoneMessage zoneMessage) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(uid);
        user.getZoneMessages().add(zoneMessage);
        return userDAO.updateUser(user);
    }


    //下面写hql查询
}
