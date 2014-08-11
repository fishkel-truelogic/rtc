package ar.com.finit.dto.factory.violation;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class ViolationDTOFactory extends AbstractDTOFactory<ViolationDTO> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<ViolationDTO>() {
		}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<ViolationDTO>>() {
		}.getType();
	}
}