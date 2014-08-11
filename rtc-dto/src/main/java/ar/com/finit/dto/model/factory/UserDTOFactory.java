package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.UserDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class UserDTOFactory extends AbstractDTOFactory<UserDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<UserDTO>() {
		}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<UserDTO>>() {
		}.getType();
	}

}
