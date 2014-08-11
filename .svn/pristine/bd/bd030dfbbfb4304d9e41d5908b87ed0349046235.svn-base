package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.GroundDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class GroundDTOFactory extends AbstractDTOFactory<GroundDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<GroundDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<GroundDTO>>(){}.getType();
	}
}