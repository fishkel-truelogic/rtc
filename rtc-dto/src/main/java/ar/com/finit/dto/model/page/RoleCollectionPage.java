package ar.com.finit.dto.model.page;

import java.util.Collection;

import ar.com.finit.dto.model.RoleDTO;
import ar.com.finit.dto.page.AbstractCollectionPage;

/**
 * @author leo
 */
public class RoleCollectionPage extends AbstractCollectionPage<RoleDTO> {

	public RoleCollectionPage(long rowCount, int pageSize, int pageCant, int pageNumber, Collection<RoleDTO> pageElements) {
		super(rowCount, pageSize, pageCant, pageNumber, pageElements);
	}

	@Override
	protected boolean isSameInstant(Object obj) {
		return obj instanceof RoleCollectionPage;
	}

}