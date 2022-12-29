package com.api.algafood.api.model;

import java.util.List;

import com.api.algafood.domain.model.Kitchen;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@Data
//@JsonRootName("kitchens")
@JacksonXmlRootElement(localName = "kitchens")
public class KitchensXmlWrapper {
	
	@JacksonXmlProperty(localName = "kitchen") // Or @JsonProperty
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull
	private List<Kitchen> kitchens;
}
