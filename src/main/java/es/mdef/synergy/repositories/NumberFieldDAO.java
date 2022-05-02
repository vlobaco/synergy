package es.mdef.synergy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import synergy.api.NumberField;

@RepositoryRestResource(path = "number-fields", itemResourceRel = "number-field", collectionResourceRel = "number-fields")
public interface NumberFieldDAO extends JpaRepository<NumberField, Integer> {

}
