package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.TpServiceDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class TpServiceDTOFactory extends AbstractDTOFactory<TpServiceDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<TpServiceDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<TpServiceDTO>>(){}.getType();
	}
}