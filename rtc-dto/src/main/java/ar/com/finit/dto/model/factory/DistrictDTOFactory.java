package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.DistrictDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class DistrictDTOFactory extends AbstractDTOFactory<DistrictDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<DistrictDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<DistrictDTO>>(){}.getType();
	}
}