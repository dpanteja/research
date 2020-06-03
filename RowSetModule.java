package com.dandi.api.serilizers;

import javax.sql.rowset.CachedRowSet;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RowSetModule extends SimpleModule{

	private static final String NAME = "RowSetModule";
	private static final VersionUtil VERSION_UTIL=new VersionUtil() {};
	
	public RowSetModule() {
		super(NAME, VERSION_UTIL.version());
		addSerializer(CachedRowSet.class, new RowSetSerializer());
	}
	
}
