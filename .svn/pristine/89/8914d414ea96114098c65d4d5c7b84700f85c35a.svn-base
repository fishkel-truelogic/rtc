package ar.com.finit.service.converter.violation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;

import ar.com.finit.dto.violation.ViolationDTO;

/**
 * @author leo
 */
public abstract class AbstractViolationConverter<T> {

	public Collection<ViolationDTO> toDTO(Set<ConstraintViolation<T>> violations) {
		Collection<ViolationDTO> dtos = new ArrayList<ViolationDTO>();
		for (ConstraintViolation<T> constraintViolation : violations) {
			dtos.add(toDTO(constraintViolation));
		}
		return dtos;
	}

	public ViolationDTO toDTO(ConstraintViolation<T> constraintViolation) {
		ViolationDTO dto = new ViolationDTO();
		dto.setInvalidValue(constraintViolation.getInvalidValue() == null ? "" : constraintViolation.getInvalidValue().toString());
		dto.setMessage(constraintViolation.getMessage());
		dto.setMessageTemplate(constraintViolation.getMessageTemplate());
		dto.setProperty(constraintViolation.getPropertyPath().toString());
		return dto;
	}
}