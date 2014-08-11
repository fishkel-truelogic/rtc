package ar.com.finit.dto.model.page;

import java.util.Collection;

import ar.com.finit.dto.model.FieldDTO;
import ar.com.finit.dto.page.AbstractCollectionPage;

/**
 * @author leo
 */
public class FieldCollectionPage extends AbstractCollectionPage<FieldDTO> {

	public FieldCollectionPage(long rowCount, int pageSize, int pageCant, int pageNumber, Collection<FieldDTO> field) {
		super(rowCount, pageSize, pageCant, pageNumber, field);
	}

	@Override
	protected boolean isSameInstant(Object obj) {
		return obj instanceof FieldCollectionPage;
	}
}