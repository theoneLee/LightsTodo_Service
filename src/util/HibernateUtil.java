package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.service.ServiceRegistry;

/**
 * 单例模式
 * 返回一个可供数据库操作前session
 *
 * @author Lee
 *
 */
//todo 貌似在spring上可以有能够获取session的东西，但没测试过。
public class HibernateUtil {
	private static ServiceRegistry serviceRegistry;
	private static SessionFactory sessionFactory;

	private HibernateUtil(){}

	static {
		serviceRegistry = new StandardServiceRegistryBuilder().configure()
				.build();
		sessionFactory = new MetadataSources(serviceRegistry).buildMetadata()
				.buildSessionFactory();
	}


	public static Session getSession(){
		Session session=sessionFactory.getCurrentSession();
		return session;
	}
}
