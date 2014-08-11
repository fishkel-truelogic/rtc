package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.ImageDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class ImageDTOFactory extends AbstractDTOFactory<ImageDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<ImageDTO>(){}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<ImageDTO>>(){}.getType();
	}
}