package es.mdef.synergy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.mdef.synergy.rest.MixIns;
import es.mdef.synergy.rest.ProcessController;
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
	
	@Bean
	RepresentationModelProcessor<RepositorySearchesResource> addSearchLinks(RepositoryRestConfiguration config) {
		Map<Class<?>, Class<?>> controllers = new HashMap<>();

		controllers.put(Process.class, ProcessController.class);
		return new RepresentationModelProcessor<RepositorySearchesResource>() {

			@Override
			public RepositorySearchesResource process(RepositorySearchesResource searchResource) {
				if (controllers.containsKey(searchResource.getDomainType())) {
					Method[] metodos = 
						controllers.get(searchResource.getDomainType()).getDeclaredMethods();
					for (Method m : metodos) {
						if (!m.isAnnotationPresent(ResponseBody.class)) continue;
						try {
							Object[] pathVars = Stream.of(m.getParameters())
								.filter(p -> p.isAnnotationPresent(PathVariable.class))
								.map(p -> "(" + p.getName() + ")")
								.collect(Collectors.toList()).toArray();
							URI uri;
							if (pathVars.length==0) {
								uri = linkTo(m).toUri();
							} else {
								uri = linkTo(m, pathVars).toUri();
							}
							String path = new URI(
								uri.getScheme(), 
								uri.getUserInfo(), 
								uri.getHost(), 
								uri.getPort(),
								config.getBasePath() + uri.getPath(), 
								null, 
								null
								).toString();
							String requestParams = Stream.of(m.getParameters())
								.filter(p -> p.isAnnotationPresent(RequestParam.class))
								.map(Parameter::getName)
								.collect(Collectors.joining(","));
							searchResource.add(Link.of(path + "{?" + requestParams + "}", m.getName()));
						} catch (URISyntaxException e) { e.printStackTrace(); }
					}
				}
				return searchResource;
			}};
		}

}
