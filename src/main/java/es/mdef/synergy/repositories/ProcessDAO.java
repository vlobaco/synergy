package es.mdef.synergy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import synergy.api.Process;

@RepositoryRestResource(path = "processes", itemResourceRel = "process", collectionResourceRel = "processes")
public interface ProcessDAO extends JpaRepository<Process, Integer> {

}
