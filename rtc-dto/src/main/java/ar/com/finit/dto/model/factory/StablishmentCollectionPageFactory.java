package ar.com.finit.dto.model.factory;

import java.lang.reflect.Type;
import java.util.Collection;

import ar.com.finit.dto.factory.AbstractDTOFactory;
import ar.com.finit.dto.model.page.StablishmentCollectionPage;

import com.google.gson.reflect.TypeToken;

/**
 * @author leo
 */
public class StablishmentCollectionPageFactory extends AbstractDTOFactory<StablishmentCollectionPage> {
	
	@Override
	protected Type getEntityType() {
		return new TypeToken<StablishmentCollectionPage>() {
		}.getType();
	}

	@Override
	protected Type getCollectionType() {
		return new TypeToken<Collection<StablishmentCollectionPage>>() {
		}.getType();
	}
}