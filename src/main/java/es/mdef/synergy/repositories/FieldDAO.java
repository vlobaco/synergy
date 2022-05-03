package es.mdef.synergy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import synergy.api.Field;
import synergy.api.Process;

@RepositoryRestResource(path = "fields", itemResourceRel = "field", collectionResourceRel = "fields")
public interface FieldDAO extends JpaRepository<Field, Integer> {
	List<Field> findByProcess(Process process);
}
