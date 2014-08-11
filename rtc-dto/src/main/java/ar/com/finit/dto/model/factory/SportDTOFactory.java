package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.SportDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class SportDTOFactory extends AbstractDTOFactory<SportDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<SportDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<SportDTO>>(){}.getType();
	}
}