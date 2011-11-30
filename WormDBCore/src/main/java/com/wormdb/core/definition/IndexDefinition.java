package com.wormdb.core.definition;

import java.util.Collections;
import java.util.Set;

import com.wormdb.core.validation.Validate;


/**
 * Class represents a DB Index.
 * @author Florian Laplantif
 *
 */
public final class IndexDefinition implements ParsableObject
{

	private String name;
	private IndexType type = IndexType.BTREE; //Default index type is Binary Tree
	private Set<String> columnNames;
	
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @return the type
	 */
	public final IndexType getType() {
		return type;
	}
	/**
	 * @return the columnNames
	 */
	public final Set<String> getColumnNames() {
		return Collections.unmodifiableSet(columnNames);
	}
	
	public void validateAfterParse() 
	{
		Validate.fieldNotBlank(getName(), "name");
		Validate.fieldNotEmptyWithNoNullElements(columnNames, "columnNames");
	}
	public void postParse() {
		// TODO Auto-generated method stub
		
	}
	
}
