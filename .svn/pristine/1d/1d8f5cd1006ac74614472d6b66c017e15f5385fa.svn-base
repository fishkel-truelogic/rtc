package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.StablishmentDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class StablishmentDTOFactory extends AbstractDTOFactory<StablishmentDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<StablishmentDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<StablishmentDTO>>(){}.getType();
	}
}