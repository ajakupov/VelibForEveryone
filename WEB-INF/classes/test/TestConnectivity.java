package test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import dao.FavoritesHome;
import dao.HibernateUtil;
import dao.StationHome;
import dao.VelibUserHome;
import model.Favorites;
import model.Station;
import model.VelibUser;

public class TestConnectivity {
	public static void main (String[] args) {
		/*VelibUser v = new VelibUser(2, "admin", "admin", "admin", 
				"admin@admin.com", 57.735893,47.527420);
		VelibUserHome vuh = new VelibUserHome();
		System.out.println("Saving to Velib_User...");
		vuh.saveOrUpdate(v);
		System.out.println("Success");
		
		System.out.println("Testing ");
		vuh.getVelibUsers().toString();
		System.out.println(vu.getEmail()+vu.getPrenom());*/
		
		/*StationHome sh =new StationHome();
		Station station = new Station(1, "testStation", 
				"Test street, test city", "Test street", 0.0001, 0.0001, 
				1, 0, new Date());
		System.out.println("Saving station...");
		sh.saveOrUpdate(station);
		System.out.println("Success");*/
		
		//StationHome sh =new StationHome();
		/*List<Station> clientResult = sh.getStationsByDepartment("ivry");
		
		for (Station station: clientResult) {
			System.out.println(station.getStName().toCharArray());
		}
		System.out.println(clientResult.size());*/
		
		//Station station = sh.getStation(new BigDecimal(42009));
		//System.out.println(station.getUpdateDate().toGMTString());
		
		VelibUserHome vuh = new VelibUserHome();
		StationHome sh = new StationHome();
		FavoritesHome fh = new FavoritesHome();
		
		VelibUser vu = vuh.getVelibUser(new BigDecimal(1));
		Station s = sh.getStation(new BigDecimal(31002));
		Favorites f = new Favorites(12, vu, s);
		fh.saveOrUpdate(f);
		System.out.println(fh.getFavorites().size());
		Favorites f2 = new Favorites(13, vu, s);
		fh.saveOrUpdate(f2);
		System.out.println(fh.getFavorites().size());
		System.out.println("success");
		
		//System.out.println(fh.getFavorites().toString());
		//System.out.println("Favorite stations");
		//System.out.println(fh.getFavoriteStations(1).toString());
	}
}
