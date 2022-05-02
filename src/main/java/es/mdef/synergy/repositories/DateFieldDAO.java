package es.mdef.synergy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import synergy.api.DateField;

@RepositoryRestResource(path = "date-fields", itemResourceRel = "date-field", collectionResourceRel = "date-fields")
public interface DateFieldDAO extends JpaRepository<DateField, Integer> {
}
