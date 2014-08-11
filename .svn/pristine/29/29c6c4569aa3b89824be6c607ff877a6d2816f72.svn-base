package ar.com.finit.owner.web.rest.client;

import javax.ws.rs.core.Cookie;

import org.jboss.resteasy.client.ClientResponse;

import ar.com.finit.dto.model.UserDTO;

/**
 * @author leo
 */
public interface HttpProtocolHelper {

	String getServiceUrl(RestElement path, RestElement restElement);

	ClientResponse<String> getJsonRequest(String url, Cookie cookie);

	ClientResponse<String> getJsonRequest(String url);

	String readHead(String key, ClientResponse<String> response);

	String readEntity(ClientResponse<String> response);

	ClientResponse<String> putJsonRequest(String url, Object o, Cookie cookie);

	ClientResponse<String> putJsonRequest(String url, Object o);

	ClientResponse<String> postJsonRequest(String url, Object o, Cookie cookie);

	ClientResponse<String> postJsonRequest(String url, Object o);

	ClientResponse<String> deleteJsonRequest(String url, Cookie cookie);

	ClientResponse<String> deleteJsonRequest(String url);
	
	Cookie getCookie();
	
	void setCookie(javax.servlet.http.Cookie cookie);

	UserDTO getUser();

	void setUser(UserDTO user);
	
	String getPath(RestElement restElement);

}
