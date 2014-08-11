package ar.com.finit.core.model;

/**
 * @author leo
 */
public interface Image {
	
	public String getId();

	public String getName();

	public String getDescription();
	
	public int getWidth();
	
	public int getHeight();
	
	public String getExtension();

	public String getEntity();

	public boolean isCover();
}
