package com.wormdb.core.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wormdb.core.definition.TableDefinition;
import com.wormdb.core.validation.Validate;

public final class TableDefinitionParser 
{
	public static TableDefinition parseTableDefinitionFromJSONFile(final String filename) 
	{
		Validate.fieldNotBlank(filename, "filename");
		
		BufferedReader fileReader;
		try {
			fileReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Could not open table definition file \"" + filename + "\".", e);
		}
		
		GsonBuilder gsonBuilder = new GsonBuilder()
										.serializeNulls()
										.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		
		Gson gsonParser = gsonBuilder.create();
		
		TableDefinition parseDefinition = gsonParser.fromJson(fileReader, TableDefinition.class);
		
		parseDefinition.validateAfterParse();
		parseDefinition.postParse();
		
		return parseDefinition;
	}
}
