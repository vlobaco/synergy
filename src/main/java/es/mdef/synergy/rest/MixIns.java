package es.mdef.synergy.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class MixIns {
	
	@JsonIgnoreProperties({"dateAsString"})
	public abstract class DateFieldMixIn{
		@JsonFormat(pattern="dd-MM-yyyy hh:mm")
		abstract Date getDate();
	}
	
	@JsonIgnoreProperties({"reader"})
	public abstract class AttachmentFieldMixIn {
	}
	
	@JsonIgnoreProperties({"fields"})
	public abstract class ProcessMixIn{
	}
}
