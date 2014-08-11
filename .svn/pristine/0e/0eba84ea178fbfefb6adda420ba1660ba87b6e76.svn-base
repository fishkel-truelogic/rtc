package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.MapMarkerDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class MapMarkerDTOFactory extends AbstractDTOFactory<MapMarkerDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<MapMarkerDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<MapMarkerDTO>>(){}.getType();
	}
}