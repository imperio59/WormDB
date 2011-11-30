package com.wormdb.core.definition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.wormdb.core.validation.Validate;

public final class TableDefinition implements ParsableObject
{
	private String name;
	private Set<ColumnDefinition> columns;
	private Set<IndexDefinition> indexes;
	
	private transient Map<String,ColumnDefinition> columnMap;
	
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @return the columns
	 */
	public final Set<ColumnDefinition> getColumns() 
	{
		return Collections.unmodifiableSet(columns);
	}

	/**
	 * @return the columnMap
	 */
	private Map<String,ColumnDefinition> getColumnMap() {
		return columnMap;
	}
	
	/**
	 * @return the indexes
	 */
	public Set<IndexDefinition> getIndexes() {
		return Collections.unmodifiableSet(indexes);
	}
	
	public final boolean containsColumn(final String columnName)
	{
		Validate.fieldNotNull(columnName, "columnName");
		
		return getColumn(columnName) != null;
	}
	
	public ColumnDefinition getColumn(final String columnName) 
	{
	    Validate.fieldNotBlank(columnName, "columnName");
	    
		return getColumnMap().get(columnName);
	}
	
	public void validateAfterParse() 
	{
		//Must have a name
		Validate.fieldNotBlank(getName(), "name");
		
		//Must have columns and they can't be null
		Validate.fieldNotEmptyWithNoNullElements(getColumns(), "columns");
		
		int numPrimaryKeysDefined = 0;
		
		for (ColumnDefinition def : getColumns())
		{
			if (def.isPrimaryKey())
			{
				numPrimaryKeysDefined++;
			}
		}
		
		//Can only have 1 PK
		Validate.isTrue(numPrimaryKeysDefined == 1);
		

		for (ColumnDefinition def : getColumns())
		{
			def.validateAfterParse();
		}
		
		//Check over indexes, make sure each column name for the indexes actually exists
		for(IndexDefinition def : getIndexes())
		{
			for(String idxColName : def.getColumnNames())
			{
			    Validate.isTrue(getColumns().contains(ColumnDefinition.valueOf(idxColName))); //Can't use containsColumn yet as that map gets initialized in postParse()
			}
		}
	}
	
	public void postParse() 
	{
		//Generate the map of column name to column to make our life easier:
		columnMap = new HashMap<String, ColumnDefinition>();
		
		for (ColumnDefinition def : getColumns())
		{
			columnMap.put(def.getName(), def);
		}
		
		for (ColumnDefinition def : getColumns())
		{
			def.postParse();
		}
	}
	
	
}
