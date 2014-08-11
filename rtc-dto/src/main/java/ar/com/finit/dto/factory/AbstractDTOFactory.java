package ar.com.finit.dto.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * @author leo
 */
public abstract class AbstractDTOFactory<T> {
	private static final Logger logger = Logger.getLogger(AbstractDTOFactory.class);

	private Gson gson;

	protected abstract Type getEntityType();

	protected abstract Type getCollectionType();

	public T makeDTO(String json) {
		logger.debug("Make single DTO from: " + json);
		Type type = getEntityType();
		return gson.fromJson(json, type);
	}

	public Collection<T> makeDTOs(String json) {
		logger.debug("Make a DTO collection from: " + json);
		Type collectionType = getCollectionType();
		return gson.fromJson(json, collectionType);
	}

	public String toJson(T t) {
		String json = gson.toJson(t);
		logger.debug("just make: " + json);
		return json;
	}

	public String toJson(Collection<T> dtos) {
		String json = gson.toJson(dtos);
		logger.debug("just make: " + json);
		return json;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}
}