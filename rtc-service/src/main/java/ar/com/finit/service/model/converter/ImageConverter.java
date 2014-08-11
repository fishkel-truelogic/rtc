package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.core.model.Image;
import ar.com.finit.core.model.dao.ImageDao;
import ar.com.finit.core.model.impl.ImageImpl;
import ar.com.finit.dto.model.ImageDTO;

/**
 * @author leo
 */
public class ImageConverter {

	@Autowired
	private ImageDao imageDao;

	public List<ImageDTO> toDTO(Collection<Image> images) {
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();
		for (Image image : images) {
			dtos.add(toDTO(image));
		}
		return dtos;
	}

	public List<Image> toEntity(Collection<ImageDTO> images) {
		List<Image> entities = new ArrayList<Image>();
		for (ImageDTO imageDTO : images) {
			entities.add(toEntity(imageDTO));
		}
		return entities;
	}

	public ImageDTO toDTO(Image image) {
		if (image == null) {
			return null;
		}
		ImageDTO dto = new ImageDTO();
		dto.setId(image.getId());
		dto.setName(image.getName());
		dto.setDescription(image.getDescription());
		dto.setWidth(image.getWidth());
		dto.setHeight(image.getHeight());
		dto.setExtension(image.getExtension());
		dto.setEntity(image.getEntity());
		dto.setCover(image.isCover());
		dto.setDir("/images/" + image.getId() + "." + image.getExtension().split("/")[1]);

		return dto;
	}

	public Image toEntity(ImageDTO dto) {
		if (dto == null) {
			return null;
		}
		ImageImpl image = new ImageImpl();
		image.setId(dto.getId());
		image.setName(dto.getName());
		image.setDescription(dto.getDescription());
		image.setWidth(dto.getWidth());
		image.setHeight(dto.getHeight());
		image.setExtension(dto.getExtension());
		image.setEntity(dto.getEntity());
		image.setCover(dto.isCover());
		return image;
	}
}
