package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.EventDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class EventDTOFactory extends AbstractDTOFactory<EventDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<EventDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<EventDTO>>(){}.getType();
	}
}