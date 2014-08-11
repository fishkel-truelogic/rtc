package ar.com.finit.core.generator;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Utilizado desde los mapeos de hibernate para las clases que tienen un UUID
 * cómo ID.
 * 
 * @author leo
 */
public class UUIDGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		return UUID.randomUUID().toString();
	}
}