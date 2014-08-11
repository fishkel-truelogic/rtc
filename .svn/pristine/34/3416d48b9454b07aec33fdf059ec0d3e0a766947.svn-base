package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.PlayerDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class PlayerDTOFactory extends AbstractDTOFactory<PlayerDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<PlayerDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<PlayerDTO>>(){}.getType();
	}
}