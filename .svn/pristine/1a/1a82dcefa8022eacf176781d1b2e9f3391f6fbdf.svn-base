package ar.com.finit.owner.web.rest.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import ar.com.finit.dto.model.UserDTO;

/**
 * @author leo
 */
public class HttpProtocolHelperGlobalImpl implements HttpProtocolHelper {
	private static final Logger logger = Logger.getLogger(HttpProtocolHelperGlobalImpl.class);

	private Map<RestElement, String> services;

	@Override
	public String getServiceUrl(RestElement path, RestElement restElement) {
		if (restElement.equals(RestElement.ROOT_URL)) {
			return services.get(restElement);
		}
		if (path == null) {
			return services.get(RestElement.ROOT_URL) + services.get(restElement);
		}
		return services.get(RestElement.ROOT_URL) + services.get(path) + services.get(restElement);
	}

	@Override
	public ClientResponse<String> getJsonRequest(String url) {
		return this.getJsonRequest(url, null);
	}

	@Override
	public ClientResponse<String> getJsonRequest(String url, Cookie cookie) {
		ClientRequest request = new ClientRequest(url);
		if (cookie != null) {
			request.cookie(cookie);
		}

		request.accept(MediaType.APPLICATION_JSON_TYPE);
		ClientResponse<String> response;
		try {
			response = request.get(String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	@Override
	public String readHead(String key, ClientResponse<String> response) {
		logger.info("\n Headers");
		Map<String, List<String>> headers = response.getHeaders();
		for (String k : headers.keySet()) {
			List<String> head = headers.get(k);
			logger.info(k + ": " + head.toString());
			for (String value : head) {
				logger.info(" * * * " + value);
			}
		}
		List<String> head = headers.get(key);
		return head.get(0);
	}

	@Override
	public String readEntity(ClientResponse<String> response) {
		BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(response.getEntity().getBytes())));
		logger.info("\n*** Response from Server ***\n");
		String output = null;
		String result = "";
		try {
			while ((output = br.readLine()) != null) {
				logger.info(output);
				result += output;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public ClientResponse<String> putJsonRequest(String url, Object o, Cookie cookie) {
		ClientRequest request = new ClientRequest(url);
		request.accept(MediaType.APPLICATION_JSON);
		request.body(MediaType.APPLICATION_JSON, o);
		logger.info("** Realizando el put ** " + url);
		if (cookie != null) {
			request.cookie(cookie);
		}
		try {
			return request.put(String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ClientResponse<String> postJsonRequest(String url, Object o, Cookie cookie) {
		ClientRequest request = new ClientRequest(url);
		request.accept(MediaType.APPLICATION_JSON);
		request.body(MediaType.APPLICATION_JSON, o);
		logger.info("** Realizando el post ** " + url);
		if (cookie != null) {
			request.cookie(cookie);
		}
		try {
			return request.post(String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ClientResponse<String> deleteJsonRequest(String url, Cookie cookie) {
		ClientRequest request = new ClientRequest(url);
		request.accept(MediaType.APPLICATION_JSON);
		request.body(MediaType.APPLICATION_JSON_TYPE, "");
		logger.info("** Realizando el post ** " + url);
		if (cookie != null) {
			request.cookie(cookie);
		}
		try {
			return request.delete(String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Map<RestElement, String> getServices() {
		return services;
	}

	public void setServices(Map<RestElement, String> services) {
		this.services = services;
	}

	@Override
	public ClientResponse<String> putJsonRequest(String url, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientResponse<String> postJsonRequest(String url, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientResponse<String> deleteJsonRequest(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie getCookie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCookie(javax.servlet.http.Cookie cookie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDTO getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUser(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPath(RestElement restElement) {
		return this.services.get(restElement);
	}

}