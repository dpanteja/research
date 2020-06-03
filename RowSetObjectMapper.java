package com.dandi.api.serilizers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class RowSetObjectMapper extends ObjectMapper {
	public RowSetObjectMapper() {
		registerModule(new RowSetModule());
		enable(SerializationFeature.INDENT_OUTPUT);
	}
}
