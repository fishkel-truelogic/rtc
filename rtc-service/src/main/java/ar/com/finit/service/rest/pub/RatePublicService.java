package ar.com.finit.service.rest.pub;

import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.core.model.Field;
import ar.com.finit.core.model.Rate;
import ar.com.finit.core.model.Stablishment;
import ar.com.finit.core.model.dao.FieldDao;
import ar.com.finit.core.model.dao.RateDao;
import ar.com.finit.core.model.dao.StablishmentDao;
import ar.com.finit.core.model.impl.RateImpl;
import ar.com.finit.dto.model.factory.RateDTOFactory;
import ar.com.finit.dto.violation.ViolationDTO;
import ar.com.finit.service.model.converter.RateConverter;
import ar.com.finit.service.model.converter.violation.RateViolationConverter;
import ar.com.finit.service.rest.utils.EntityPath;
import ar.com.finit.service.rest.utils.VariantsFactory;

/**
 * @author leo
 */

@Configurable
@Path(EntityPath.PUBLIC + EntityPath.RATES)
public class RatePublicService {

	@Autowired
	private RateDao rateDao;
	@Autowired
	private FieldDao fieldDao;
	@Autowired
	private StablishmentDao stablishmentDao;
	@Autowired
	private RateConverter rateConverter;
	@Autowired
	private RateDTOFactory rateDTOFactory;
	@Autowired
	private RateViolationConverter rateViolationConverter;
	@Autowired
	private Validator validator;

	@GET
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("id") String id) {
		Rate rate = this.rateDao.findById(id);
		return Response.ok(rateConverter.toDTO(rate)).build();
	}
	
	@POST
	@Path("/{id:(([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}){1}}")
	public Response update(@PathParam("id") String id, @QueryParam("vote") double vote) {
		RateImpl rate = (RateImpl) rateDao.findById(id);
		double average = rate.getAverage() * rate.getVotes();
		average += vote;
		rate.setVotes(rate.getVotes() + 1);
		rate.setAverage(average/rate.getVotes());
		Response response = validate(rate);
		if (response != null) return response;
		rateDao.update(rate);
		
		Field field = fieldDao.findById(rate.getEntity());
		Stablishment stablishment = stablishmentDao.findByUserId(field.getUserId());
		RateImpl stablishmentRate = (RateImpl) stablishment.getRate();
		average = stablishmentRate.getAverage() * stablishmentRate.getVotes();
		average += vote;
		stablishmentRate.setVotes(stablishmentRate.getVotes() + 1);
		stablishmentRate.setAverage(average/stablishmentRate.getVotes());
		rateDao.update(stablishmentRate);
		
		return Response.ok().build();
	}
	
	private Response validate(Rate rate) {
		Set<ConstraintViolation<Rate>> violations = validator.validate(rate);
		if (!violations.isEmpty()) {
			Collection<ViolationDTO> dtos = rateViolationConverter.toDTO(violations);
			return Response.notAcceptable(VariantsFactory.variants()).entity(dtos).build();
		}
		return null;
	}
	
	
	
	

}
