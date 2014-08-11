package ar.com.finit.dto.model.page;

import java.util.Collection;

import ar.com.finit.dto.model.ImageDTO;
import ar.com.finit.dto.page.AbstractCollectionPage;

/**
 * @author leo
 */
public class ImageCollectionPage extends AbstractCollectionPage<ImageDTO> {

	public ImageCollectionPage(long rowCount, int pageSize, int pageCant, int pageNumber, Collection<ImageDTO> image) {
		super(rowCount, pageSize, pageCant, pageNumber, image);
	}

	@Override
	protected boolean isSameInstant(Object obj) {
		return obj instanceof ImageCollectionPage;
	}
}