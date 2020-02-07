package dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import model.Station;
import model.VelibUser;

/**
 * Home object for domain model class Station.
 * @see dao.Station
 * @author Hibernate Tools
 */
public class StationHome {
	
	public StationHome() {}
	
	public void saveOrUpdate (Station station) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(station);
		session.getTransaction().commit();
		session.close();
	}
	
	public Station getStation (BigDecimal numInd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Station station = (Station) session.get(Station.class, numInd);
		session.close();
		
		return station;
	}
	
	public List<Station> getStations () {
		List<Station> results;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		results = (List<Station>) session.createQuery("from Station").list();		
		session.close();
		
		return results;
	}
	
	public List<Station> getStationsByDistrict (String district) {
		List<Station> results;
		List<Station> temp = new ArrayList<Station>();
		
		results = getStations();
		
		for (Station item : results) {
			if (item.getAddress().trim().toLowerCase().contains(district)) {
				temp.add(item);
			}
		}
		
		return temp;
	}
	
	public List<Station> getStationsByDepartment (String department) {
		List<Station> results;
		List<Station> temp = new ArrayList<Station>();
		
		results = getStations();
		
		for (Station item : results) {
			if (item.getFullAddress().trim().toLowerCase().
					contains(department)) {
				temp.add(item);
			}
		}
		
		return temp;
	}
}
