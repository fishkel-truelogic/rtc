package ar.com.finit.service.rest.pub;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Configurable;

import ar.com.finit.service.rest.utils.EntityPath;

/**
 * @author leo
 */
@Configurable
@Path(EntityPath.PUBLIC + "jbpm")
public class JbpmTestPublicService {

	@GET
	@Path("/")
	public String test() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("/jbpm/hellojbpm.bpmn", JbpmTestPublicService.class), ResourceType.BPMN2);
		KnowledgeBase kbase = kbuilder.newKnowledgeBase();
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		ksession.startProcess("IntermediateCatchEvent");
		ksession.dispose();
		return "ok";
	}
}
