package ar.com.finit.core.model;

import java.util.List;

/**
 * @author leo
 */
public interface Stablishment {

	public String getId();

	public String getUserId();
	
	public String getName();
	
	public String getAddress();

	public String getEmail();

	public String getWeb();

	public String getOpenHour();

	public String getCloseHour();

	public String getOpenDay();
	
	public String getCloseDay();

	public District getDistrict();

	public Album getAlbum();
	
	public MapMarker getMapMarker();
	
	public List<Sport> getSports();
	
	public List<Field> getFields();
	
	public List<TpService> getTpServices();
	
	public Rate getRate();
}