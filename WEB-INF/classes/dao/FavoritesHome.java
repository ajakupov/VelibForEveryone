package dao;
// Generated Dec 29, 2016 10:50:19 AM by Hibernate Tools 3.5.0.Final

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

import model.Favorites;
import model.Station;

/**
 * Home object for domain model class Favorites.
 * @see dao.Favorites
 * @author Hibernate Tools
 */
public class FavoritesHome {
	public FavoritesHome() {}
	
	public void saveOrUpdate (Favorites favorites) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(favorites);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Favorites> getFavorites () {
		List<Favorites> results;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		results = (List<Favorites>) session.createQuery("from Favorites").list();		
		session.close();
		
		return results;
	}
	
	public List<Station> getFavoriteStations (int clientID) {
		List<Station> results = new ArrayList<Station>();
		List<Station> allStations;
		List<Favorites> favorites;
		
		StationHome stationDAO = new StationHome();
		
		allStations = stationDAO.getStations();
		favorites = getFavorites();
		
		for (Favorites favorite : favorites) {
			for (Station station : allStations) {
				if (favorite.getVelibUser().getNumInd().equals(new BigDecimal(clientID)) 
						&& favorite.getStation().getStationId().
						equals(station.getStationId())) {
					results.add(station);
				}
			}
		}
		
		return results;
	}
	
}
