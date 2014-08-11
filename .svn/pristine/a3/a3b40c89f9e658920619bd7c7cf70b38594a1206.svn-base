package ar.com.finit.owner.web.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.ClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.model.factory.UserDTOFactory;
import ar.com.finit.owner.web.rest.client.HttpProtocolHelper;
import ar.com.finit.owner.web.rest.client.RestElement;

@Component
public class UserSessionHelper {

	@Autowired
	private HttpProtocolHelper httpProtocolHelper;
	@Autowired
	private UserDTOFactory userDTOFactory;

	public UserDTO getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (this.httpProtocolHelper.getUser() != null) {
			return this.httpProtocolHelper.getUser();
		}
		
		if (this.httpProtocolHelper.getCookie() == null) {
			if (request == null) throw new RuntimeException();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("JSESSIONIDSSO")) {
						httpProtocolHelper.setCookie(cookie);
					}
				}
			}
			if (httpProtocolHelper.getCookie() == null) {
				throw new RuntimeException();
//				request.setAttribute("path", request.getPathInfo());
//				request.getContextPath();
//				request.getRequestDispatcher("/").forward(request, response);
			}
			
		}
		UserDTO user;
		String url = httpProtocolHelper.getServiceUrl(RestElement.PRIV, RestElement.USER) + request.getUserPrincipal().getName();
		ClientResponse<String> clientResponse = httpProtocolHelper.getJsonRequest(url);
		
		if (clientResponse.getStatus() != Status.OK.getStatusCode()) {
			user = null;
		} else {
			user = userDTOFactory.makeDTO(clientResponse.getEntity());
		}
		
		this.httpProtocolHelper.setUser(user);
		
		return user;
	}
	
}
