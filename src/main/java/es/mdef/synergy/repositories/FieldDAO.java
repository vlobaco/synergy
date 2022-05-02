package es.mdef.synergy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import synergy.api.Field;

@RepositoryRestResource(path = "fields", itemResourceRel = "field", collectionResourceRel = "fields")
public interface FieldDAO extends JpaRepository<Field, Integer> {

}
