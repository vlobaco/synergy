package es.mdef.synergy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import synergy.api.TextField;

@RepositoryRestResource(path = "text-fields", itemResourceRel = "text-field", collectionResourceRel = "text-fields")
public interface TextFieldDAO extends JpaRepository<TextField, Integer> {

}
