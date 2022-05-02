package es.mdef.synergy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import synergy.api.AttachmentField;

@RepositoryRestResource(path = "attachment-fields", itemResourceRel = "attachment-field", collectionResourceRel = "attachment-fields")
public interface AttachmentFieldDAO extends JpaRepository<AttachmentField, Integer> {

}
