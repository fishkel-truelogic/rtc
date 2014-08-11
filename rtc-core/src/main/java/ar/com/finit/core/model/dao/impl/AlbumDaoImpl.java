package ar.com.finit.core.model.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.finit.core.dao.impl.AbstractDao;
import ar.com.finit.core.model.Album;
import ar.com.finit.core.model.Image;
import ar.com.finit.core.model.dao.AlbumDao;
import ar.com.finit.core.model.impl.AlbumImpl;

/**
 * @author leo
 */
public class AlbumDaoImpl extends AbstractDao<Serializable, Album> implements AlbumDao {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Album findByIdFull(String id) {
		AlbumImpl album = (AlbumImpl) this.findById(id);
		List<Image> images = new ArrayList<Image>();
		for (Image image : album.getImages()) {
			images.add(image);
		}
		album.setImages(images);
		return album;
	}

}
