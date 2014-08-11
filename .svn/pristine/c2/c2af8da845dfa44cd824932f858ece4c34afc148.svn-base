package ar.com.finit.owner.web.rest.client;

import javax.ws.rs.core.Cookie;

import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.dto.model.UserDTO;

/**
 * @author leo
 * esta clase tiene scope de session ya que para realizar request se necesita la cookie que identifica permisos
 * Aprovechando que tiene la información de los permisos, se agrega la información del usuario para mantenerlo en
 * scope de session
 */
public class HttpProtocolHelperImpl implements HttpProtocolHelper {

	private Cookie cookie;
	private UserDTO user;

	@Autowired
	private HttpProtocolHelper httpProtocolHelperGlobal;

	@Override
	public String getServiceUrl(RestElement path, RestElement restElement) {
		return this.httpProtocolHelperGlobal.getServiceUrl(path, restElement);
	}

	@Override
	public ClientResponse<String> getJsonRequest(String url, Cookie cookie) {
		return this.httpProtocolHelperGlobal.getJsonRequest(url, cookie);
	}

	@Override
	public String readHead(String key, ClientResponse<String> response) {
		return this.httpProtocolHelperGlobal.readHead(key, response);
	}

	@Override
	public String readEntity(ClientResponse<String> response) {
		return this.httpProtocolHelperGlobal.readEntity(response);
	}

	@Override
	public ClientResponse<String> putJsonRequest(String url, Object o, Cookie cookie) {
		return this.httpProtocolHelperGlobal.putJsonRequest(url, o, cookie);
	}

	@Override
	public ClientResponse<String> postJsonRequest(String url, Object o, Cookie cookie) {
		return this.httpProtocolHelperGlobal.postJsonRequest(url, o, cookie);
	}

	@Override
	public ClientResponse<String> deleteJsonRequest(String url, Cookie cookie) {
		return this.httpProtocolHelperGlobal.deleteJsonRequest(url, cookie);
	}

	@Override
	public ClientResponse<String> getJsonRequest(String url) {
		return this.httpProtocolHelperGlobal.getJsonRequest(url, this.getCookie());
	}

	@Override
	public ClientResponse<String> putJsonRequest(String url, Object o) {
		return this.httpProtocolHelperGlobal.putJsonRequest(url, o, this.getCookie());
	}

	@Override
	public ClientResponse<String> postJsonRequest(String url, Object o) {
		return this.httpProtocolHelperGlobal.postJsonRequest(url, o, this.getCookie());
	}

	@Override
	public ClientResponse<String> deleteJsonRequest(String url) {
		return this.httpProtocolHelperGlobal.deleteJsonRequest(url, this.getCookie());
	}


	@Override
	public void setCookie(javax.servlet.http.Cookie cookie) {
		this.cookie = new Cookie(cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getDomain(), cookie.getVersion());
		
	}
	
	@Override
	public Cookie getCookie() {
		return cookie;
	}

	@Override
	public UserDTO getUser() {
		return user;
	}

	@Override
	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String getPath(RestElement restElement) {
		return this.httpProtocolHelperGlobal.getPath(restElement);
	}
	
	

}
