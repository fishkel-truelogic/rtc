package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.RateDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class RateDTOFactory extends AbstractDTOFactory<RateDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<RateDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<RateDTO>>(){}.getType();
	}
}