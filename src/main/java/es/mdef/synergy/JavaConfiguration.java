package es.mdef.synergy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.mdef.synergy.rest.MixIns;
import synergy.api.AttachmentField;
import synergy.api.DateField;
import synergy.api.Process;

@Configuration
public class JavaConfiguration {
	
	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(DateField.class, MixIns.DateFieldMixIn.class);
		mapper.addMixIn(AttachmentField.class, MixIns.AttachmentFieldMixIn.class);
		mapper.addMixIn(Process.class, MixIns.ProcessMixIn.class);
		return mapper;
	}

}
