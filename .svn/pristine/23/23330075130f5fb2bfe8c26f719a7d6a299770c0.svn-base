package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.page.RoleCollectionPage;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class RoleCollectionPageFactory extends AbstractDTOFactory<RoleCollectionPage> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<RoleCollectionPage>() {
		}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<RoleCollectionPage>>() {
		}.getType();
	}

}
