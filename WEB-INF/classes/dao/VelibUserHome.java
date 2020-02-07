package dao;
// Generated Dec 24, 2016 1:12:12 PM by Hibernate Tools 3.5.0.Final

import java.math.BigDecimal;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import model.VelibUser;

/**
 * Home object for domain model class VelibUser.
 * @see dao.VelibUser
 * @author Hibernate Tools
 */
public class VelibUserHome {
	public VelibUserHome() {}
	
	public void saveOrUpdate (VelibUser velibUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(velibUser);
		session.getTransaction().commit();
		session.close();
	}
	
	public VelibUser getVelibUser (BigDecimal numInd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		VelibUser velibUser = (VelibUser) session.get(VelibUser.class, numInd);
		session.close();
		
		return velibUser;
	}
	
	public List<VelibUser> getVelibUsers () {
		List<VelibUser> results;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		results = (List<VelibUser>) session.createQuery("from VelibUser").list();		
		session.close();
		
		return results;
	}
	
	public VelibUser getVelibUser (String login, String password) {
		List<VelibUser> results;
		VelibUser velibUser = null;
		
		results = getVelibUsers();
		
		for (VelibUser item : results) {
			if (item.getNom().equals(login)
					&& item.getVuPassword().equals(password)) {
				velibUser = item;
			}
		}
		
		return velibUser;
	}
	
}
