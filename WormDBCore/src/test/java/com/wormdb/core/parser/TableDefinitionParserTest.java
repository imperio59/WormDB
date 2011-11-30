package com.wormdb.core.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wormdb.core.definition.ColumnDataType;
import com.wormdb.core.definition.TableDefinition;

public class TableDefinitionParserTest 
{
	@Test
	public void testSimpleParse() 
	{
		String rootDir = System.getProperty("user.dir");
		
		TableDefinition tableDef = 
				TableDefinitionParser.parseTableDefinitionFromJSONFile(rootDir + "/src/test/resources/ParserTests/TableDefinitionParserTestFiles/tableDefinitionTestCaseValid.json");
		
		assertEquals(tableDef.getName(), "test_table");

		assertTrue(tableDef.containsColumn("customer_id"));
		assertTrue(tableDef.containsColumn("first_name"));
		assertTrue(tableDef.containsColumn("last_name"));
		assertTrue(tableDef.containsColumn("membership_type"));
		assertTrue(tableDef.containsColumn("quantity"));
		assertTrue(tableDef.containsColumn("join_date"));
		
		assertTrue(tableDef.getColumn("customer_id").isPrimaryKey());
		
		assertTrue(tableDef.getColumn("customer_id").getType().equals(ColumnDataType.LONG));
		assertTrue(tableDef.getColumn("first_name").getType().equals(ColumnDataType.STRING));
		assertTrue(tableDef.getColumn("last_name").getType().equals(ColumnDataType.STRING));
		assertTrue(tableDef.getColumn("membership_type").getType().equals(ColumnDataType.STRING));
		assertTrue(tableDef.getColumn("quantity").getType().equals(ColumnDataType.INTEGER));
		assertTrue(tableDef.getColumn("join_date").getType().equals(ColumnDataType.TIMESTAMP));
	}
	
	   @Test
	    public void testSimpleParse2() 
	    {
	        String rootDir = System.getProperty("user.dir");
	        
	        TableDefinition tableDef = 
	                TableDefinitionParser.parseTableDefinitionFromJSONFile(rootDir + "/src/test/resources/ParserTests/TableDefinitionParserTestFiles/tableDefinitionTestCaseValidTwo.json");
	        
	        assertEquals(tableDef.getName(), "test_table");

	        assertTrue(tableDef.containsColumn("customer_id"));
	        assertTrue(tableDef.containsColumn("first_name"));
	        assertTrue(tableDef.containsColumn("last_name"));
	        assertTrue(tableDef.containsColumn("membership_type"));
	        assertTrue(tableDef.containsColumn("quantity"));
	        assertTrue(tableDef.containsColumn("join_date"));
	        
	        assertTrue(tableDef.getColumn("customer_id").isPrimaryKey());
	        
	        assertTrue(tableDef.getColumn("customer_id").getType().equals(ColumnDataType.LONG));
	        assertTrue(tableDef.getColumn("first_name").getType().equals(ColumnDataType.STRING));
	        assertTrue(tableDef.getColumn("last_name").getType().equals(ColumnDataType.STRING));
	        assertTrue(tableDef.getColumn("membership_type").getType().equals(ColumnDataType.STRING));
	        assertTrue(tableDef.getColumn("quantity").getType().equals(ColumnDataType.INTEGER));
	        assertTrue(tableDef.getColumn("join_date").getType().equals(ColumnDataType.TIMESTAMP));
	    }

}
