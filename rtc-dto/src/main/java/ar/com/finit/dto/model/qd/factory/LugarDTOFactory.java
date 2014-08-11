package ar.com.finit.dto.model.qd.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.qd.LugarDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class LugarDTOFactory extends AbstractDTOFactory<LugarDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<LugarDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<LugarDTO>>(){}.getType();
	}
}