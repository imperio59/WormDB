package com.wormdb.core.definition;

import com.wormdb.core.validation.Validate;


public final class ColumnDefinition implements Comparable<ColumnDefinition>, ParsableObject
{
	private String name;
	private ColumnDataType type;
	private boolean primaryKey = false;
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @return the type
	 */
	public final ColumnDataType getType() {
		return type;
	}
	/**
	 * @return the primary_key
	 */
	public final boolean isPrimaryKey() {
		return primaryKey;
	}
	
	public int compareTo(ColumnDefinition o) 
	{
		if (o == null)
		{
			return 1;
		}
		
		return getName().compareTo(o.getName());
	}
	
	@Override
	public boolean equals(final Object other) 
	{
		if (this == other) 
		{
			return true;
		}
		if (other instanceof ColumnDefinition)
		{
			return getName().equals(((ColumnDefinition) other).getName());
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return getName().hashCode();
	}
		
	protected static ColumnDefinition valueOf(final String columnName) 
	{
		
		ColumnDefinition columnDef = new ColumnDefinition();
		columnDef.name = columnName;
		return columnDef;
	}
	public void validateAfterParse() 
	{
		Validate.fieldNotBlank(getName(), "name");
		Validate.fieldNotNull(getType(), "type");
		
	}
	public void postParse() {
		// TODO Auto-generated method stub
		
	}
}
