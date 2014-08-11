package ar.com.finit.service.model.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.finit.core.model.Stablishment;
import ar.com.finit.core.model.dao.FieldDao;
import ar.com.finit.core.model.dao.TpServiceDao;
import ar.com.finit.core.model.impl.StablishmentImpl;
import ar.com.finit.dto.model.StablishmentDTO;

/**
 * @author leo
 */
public class StablishmentConverter {

	@Autowired
	private SportConverter sportConverter;
	@Autowired
	private RateConverter rateConverter;
	@Autowired
	private AlbumConverter albumConverter;
	@Autowired
	private FieldConverter fieldConverter;
	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private DistrictConverter districtConverter;
	@Autowired
	private MapMarkerConverter mapMarkerConverter;
	@Autowired
	private TpServiceConverter tpServiceConverter;
	@Autowired
	private TpServiceDao tpServiceDao;
	
	public List<StablishmentDTO> toDTO(Collection<Stablishment> stablishments, boolean lazy) {
		List<StablishmentDTO> stablishmentsDTO = new ArrayList<StablishmentDTO>();
		for (Stablishment stablishment : stablishments) {
			stablishmentsDTO.add(this.toDTO(stablishment, lazy));
		}
		return stablishmentsDTO;
	}
	
	public List<Stablishment> toEntity(Collection<StablishmentDTO> stablishmentsDTO) {
		List<Stablishment> stablishments = new ArrayList<Stablishment>();
		for (StablishmentDTO stablishment : stablishmentsDTO) {
			stablishments.add(this.toEntity(stablishment));
		}
		return stablishments;
	}

	public StablishmentDTO toDTO(Stablishment stablishment, Boolean lazy) {
		if (stablishment == null) return null;
		StablishmentDTO dto = new StablishmentDTO();
		dto.setId(stablishment.getId());
		dto.setName(stablishment.getName());
		dto.setUserId(stablishment.getUserId());
		dto.setAddress(stablishment.getAddress());
		dto.setWeb(stablishment.getWeb());
		dto.setEmail(stablishment.getEmail());
		dto.setOpenDay(stablishment.getOpenDay());
		dto.setOpenHour(stablishment.getOpenHour());
		dto.setCloseDay(stablishment.getCloseDay());
		dto.setCloseHour(stablishment.getCloseHour());
		dto.setSports(sportConverter.toDTO(stablishment.getSports()));
		dto.setMapMarker(mapMarkerConverter.toDTO(stablishment.getMapMarker()));
		dto.setDistrict(districtConverter.toDTO(stablishment.getDistrict()));
		dto.setRate(rateConverter.toDTO(stablishment.getRate()));
		dto.setAlbum(albumConverter.toDTO(stablishment.getAlbum(), true));
		if (!lazy) {
			dto.setTpServices(tpServiceConverter.toDTO(tpServiceDao.findByUserId(stablishment.getUserId()), true));
			dto.setFields(fieldConverter.toDTO(fieldDao.findByUserId(stablishment.getUserId()), true));
		}
		return dto;
	}
	
	public Stablishment toEntity(StablishmentDTO dto) {
		if (dto == null) return null;
		StablishmentImpl stablishment = new StablishmentImpl();
		stablishment.setId(dto.getId());
		stablishment.setName(dto.getName());
		stablishment.setUserId(dto.getUserId());
		stablishment.setAddress(dto.getAddress());
		stablishment.setWeb(dto.getWeb());
		stablishment.setEmail(dto.getEmail());
		stablishment.setOpenDay(dto.getOpenDay());
		stablishment.setOpenHour(dto.getOpenHour());
		stablishment.setCloseDay(dto.getCloseDay());
		stablishment.setCloseHour(dto.getCloseHour());
		stablishment.setTpServices(tpServiceConverter.toEntity(dto.getTpServices()));
		stablishment.setSports(sportConverter.toEntity(dto.getSports()));
		stablishment.setMapMarker(mapMarkerConverter.toEntity(dto.getMapMarker()));
		stablishment.setDistrict(districtConverter.toEntity(dto.getDistrict()));
		stablishment.setFields(fieldConverter.toEntity(dto.getFields()));
		stablishment.setAlbum(albumConverter.toEntity(dto.getAlbum()));
		stablishment.setRate(rateConverter.toEntity(dto.getRate()));
		return stablishment;
	}

}