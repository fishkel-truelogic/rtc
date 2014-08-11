package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.page.UserCollectionPage;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class UserCollectionPageFactory extends AbstractDTOFactory<UserCollectionPage> {

	@Override
	protected Type getEntityType() {
		return new TypeToken<UserCollectionPage>() {
		}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<UserCollectionPage>>() {
		}.getType();
	}

}
