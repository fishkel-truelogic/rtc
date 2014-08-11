package ar.com.finit.dto.model;

/**
 * @author leo
 */
public class TpServiceDTO {

	private String id;

	private String name;

	private String userId;

	private AlbumDTO album;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AlbumDTO getAlbum() {
		if (album == null) {
			album = new AlbumDTO();
		}
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
