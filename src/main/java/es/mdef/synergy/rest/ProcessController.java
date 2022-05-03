package es.mdef.synergy.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import synergy.api.Process;

@RepositoryRestController
public class ProcessController {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@GetMapping("/processes/search/with-compulsory-attachments") 
	@ResponseBody 
	public CollectionModel<PersistentEntityResource> getProcessesWithCompulsoryAttachments (PersistentEntityResourceAssembler assembler) { 
		String queryText = "select f.process "
						+ "from AttachmentField f "
						+ "where f.compulsory = true";
		List<Process> processes = entityManager.createQuery(queryText, Process.class).getResultList(); 
		return assembler.toCollectionModel(processes); 
	} 
}
