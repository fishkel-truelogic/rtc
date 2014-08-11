package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.StateDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class StateDTOFactory extends AbstractDTOFactory<StateDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<StateDTO>() {
		}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<StateDTO>>() {
		}.getType();
	}
}