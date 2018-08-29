package employee.conf;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConf {

	private static SessionFactory sessionFactory;
	
	private HibernateConf(){};
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory==null){
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();//configures settings from hibernate.cfg.xml
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		return sessionFactory;
	}
}
