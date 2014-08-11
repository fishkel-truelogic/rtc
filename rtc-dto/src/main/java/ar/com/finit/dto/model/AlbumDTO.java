package ar.com.finit.dto.model;

import java.util.ArrayList;
import java.util.List;

import ar.com.finit.dto.helper.EqualsHelper;

/**
 * @author leo
 */
public class AlbumDTO {

	private String id;

	private ImageDTO cover;

	private List<ImageDTO> images;

	private String entity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ImageDTO getCover() {
		return cover;
	}

	public void setCover(ImageDTO cover) {
		this.cover = cover;
	}

	public List<ImageDTO> getImages() {
		if (images == null) {
			images = new ArrayList<ImageDTO>();
		}
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public void addImage(ImageDTO image) {
		this.getImages().add(image);
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AlbumDTO)) {
			return false;
		}
		AlbumDTO other = (AlbumDTO) obj;
		return (EqualsHelper.equals(other.getId(), this.getId()) && EqualsHelper.equals(other.getCover(), this.getCover())
				&& EqualsHelper.equalsCollection(other.getImages(), this.getImages()) && EqualsHelper.equals(other.getEntity(), this.getEntity()));
	}

	public ImageDTO get(String name) {
		
		if(this.getCover() != null && this.getCover().getName().equals(name)) {
			return this.getCover();
		}
		if (this.getImages() != null) {
			for (ImageDTO image : this.getImages()) {
				if (image.getName().equals(name)) {
					return image;
				}
			}
		}
		return null;
	}

}
