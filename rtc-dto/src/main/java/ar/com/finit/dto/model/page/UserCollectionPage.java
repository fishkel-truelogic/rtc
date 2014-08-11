package ar.com.finit.dto.model.page;

import java.util.Collection;

import ar.com.finit.dto.model.UserDTO;
import ar.com.finit.dto.page.AbstractCollectionPage;

/**
 * @author leo
 */
public class UserCollectionPage extends AbstractCollectionPage<UserDTO> {

	public UserCollectionPage(long rowCount, int pageSize, int pageCant, int pageNumber, Collection<UserDTO> pageElements) {
		super(rowCount, pageSize, pageCant, pageNumber, pageElements);
	}

	@Override
	protected boolean isSameInstant(Object obj) {
		return obj instanceof UserCollectionPage;
	}

}