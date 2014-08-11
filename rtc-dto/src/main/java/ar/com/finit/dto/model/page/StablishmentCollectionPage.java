package ar.com.finit.dto.model.page;

import java.util.Collection;

import ar.com.finit.dto.model.StablishmentDTO;
import ar.com.finit.dto.page.AbstractCollectionPage;

/**
 * @author leo
 */
public class StablishmentCollectionPage extends AbstractCollectionPage<StablishmentDTO> {

	public StablishmentCollectionPage(long rowCount, int pageSize, int pageCant, int pageNumber, Collection<StablishmentDTO> stablishment) {
		super(rowCount, pageSize, pageCant, pageNumber, stablishment);
	}

	@Override
	protected boolean isSameInstant(Object obj) {
		return obj instanceof StablishmentCollectionPage;
	}
}