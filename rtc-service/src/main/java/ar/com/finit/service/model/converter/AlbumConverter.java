package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.core.model.Album;
import ar.com.finit.core.model.dao.ImageDao;
import ar.com.finit.core.model.impl.AlbumImpl;
import ar.com.finit.dto.model.AlbumDTO;

/**
 * @author leo
 */
public class AlbumConverter {

	@Autowired
	private ImageConverter imageConverter;
	@Autowired
	private ImageDao imageDao;
	
	public List<AlbumDTO> toDTO(Collection<Album> albums, boolean lazy) {
		List<AlbumDTO> albumsDTO = new ArrayList<AlbumDTO>();
		for (Album album : albums) {
			albumsDTO.add(this.toDTO(album, lazy));
		}
		return albumsDTO;
	}
	
	public List<Album> toEntity(Collection<AlbumDTO> albumsDTO) {
		List<Album> albums = new ArrayList<Album>();
		for (AlbumDTO album : albumsDTO) {
			albums.add(this.toEntity(album));
		}
		return albums;
	}

	public AlbumDTO toDTO(Album album, boolean lazy) {
		if (album == null) {
			return null;
		}
		AlbumDTO dto = new AlbumDTO();
		dto.setId(album.getId());
		dto.setEntity(album.getEntity());
		dto.setCover(imageConverter.toDTO(album.getCover()));
		if (!lazy) {
			dto.setImages(imageConverter.toDTO(imageDao.findByEntity(album.getId())));
		}		
		return dto;
	}
	
	public Album toEntity(AlbumDTO dto) {
		if (dto == null) {
			return null;
		}
		AlbumImpl album = new AlbumImpl();
		album.setId(dto.getId());
		album.setEntity(dto.getEntity());
		album.setCover(imageConverter.toEntity(dto.getCover()));
		album.setImages(imageConverter.toEntity(dto.getImages()));
		return album;
	}

}
