package ar.com.finit.core.model;

import java.util.Set;

/**
 * @author leo
 */
public interface User {

	public String getId();

	public String getUsername();

	public String getPassword();

	public boolean isEnabled();

	public Set<Role> getRoles();
}
