package geolocalisation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
/**
 * 
 * @author Jimmy
 * Determine les coordonnées GPS, le pays et la ville de localisation
 * avec le systeme de géolocalisation GéoIP.
 * Il est nécessaire d'avoir une adresse IP
 * 
 */
public class LocationByGeoIp {
	private final static Path localePath = Paths
			.get("GeoIP_ressources\\GeoLiteCity.dat");
	private String cityName, countryName;
	private double longitude, latitude;
	
	public LocationByGeoIp(String ipAdress) {
		// TODO Auto-generated constructor stub
		File file = new File(localePath.toString());
		LookupService lookup;
		try {
			lookup = new LookupService(file,
					LookupService.GEOIP_MEMORY_CACHE);
			Location locationServices = lookup.getLocation(ipAdress);
			this.getLocation(locationServices);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void getLocation(Location location) {
		try {
			this.cityName=location.city;
		}catch(NullPointerException e){
			this.cityName="NA";
		}
		try {
			this.countryName=location.countryName;
		}catch(NullPointerException e){
			this.countryName="NA";
		}
		try {
			this.longitude=location.longitude;
		}catch(NullPointerException e){
			this.longitude=0.0;
		}
		try {
			this.latitude=location.latitude;
		}catch(NullPointerException e){
			this.latitude=0.0;
		}
	}
}
