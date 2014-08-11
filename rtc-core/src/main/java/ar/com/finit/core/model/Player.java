package ar.com.finit.core.model;


/**
 * @author leo
 */
public interface Player {

	public String getId();
	
	public String getFbId();

	public String getEmail();

	public String getUsername();

	public String getPassword();
	
	public boolean isEnabled();
	
	public boolean isEmailConfirmed();

}
