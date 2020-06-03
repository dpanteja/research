package com.dandi.api.serilizers;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class RowSetSerializer extends JsonSerializer<CachedRowSet> {

	@Override
	public void serialize(CachedRowSet value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartArray();
		
		
		try {
			ResultSetMetaData metaData = value.getMetaData();
			
			String[] columnNames = new String[metaData.getColumnCount()];
			
			for(int j=0; j <columnNames.length; j++) {
				columnNames[j] = metaData.getColumnName(j+1);
			}

			while(value.next()) {
			
				gen.writeStartObject();
				for(int j=0;j<columnNames.length;j++) {
					gen.writeStringField(columnNames[j], String.valueOf(value.getObject(j+1)));
				}
				gen.writeEndObject();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		gen.writeEndArray();
	}

}
