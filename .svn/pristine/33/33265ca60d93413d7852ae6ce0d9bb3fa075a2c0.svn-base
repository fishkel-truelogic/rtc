package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.AlbumDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class AlbumDTOFactory extends AbstractDTOFactory<AlbumDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<AlbumDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<AlbumDTO>>(){}.getType();
	}
}