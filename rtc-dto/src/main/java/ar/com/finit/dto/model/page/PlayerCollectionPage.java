package ar.com.finit.dto.model.page;

import java.util.Collection;

import ar.com.finit.dto.model.PlayerDTO;
import ar.com.finit.dto.page.AbstractCollectionPage;

/**
 * @author leo
 */
public class PlayerCollectionPage extends AbstractCollectionPage<PlayerDTO> {

	public PlayerCollectionPage(long rowCount, int pageSize, int pageCant, int pageNumber, Collection<PlayerDTO> player) {
		super(rowCount, pageSize, pageCant, pageNumber, player);
	}

	@Override
	protected boolean isSameInstant(Object obj) {
		return obj instanceof PlayerCollectionPage;
	}
}