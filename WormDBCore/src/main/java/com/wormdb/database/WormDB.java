package com.wormdb.database;

import java.io.File;

import com.wormdb.core.definition.TableDefinition;
import com.wormdb.core.parser.TableDefinitionParser;
import com.wormdb.core.validation.Validate;

public final class WormDB 
{
    private File dataFile;
    private final TableDefinition tableDefinition;

    private WormDB(final TableDefinition tableDefinition, final File dataFile)
    {
        this(tableDefinition);
        
        Validate.fieldNotNull(dataFile, "dataFile");        
        this.dataFile = dataFile;        
    }
    
    private WormDB(final TableDefinition tableDefinition)
    {
        Validate.fieldNotNull(tableDefinition, "tableDefinition");
        this.tableDefinition = tableDefinition;
    }    
    
    public static WormDB newDB(final String tableDefinitionFileName)
    {
        TableDefinition tableDef = TableDefinitionParser.parseTableDefinitionFromJSONFile(tableDefinitionFileName);
        
        return new WormDB(tableDef);
    }
    
    public static WormDB loadDB(final String tableDefinitionFileName, final String tableDataFileName)
    {
        TableDefinition tableDef = TableDefinitionParser.parseTableDefinitionFromJSONFile(tableDefinitionFileName);
        File dataFile = new File(tableDataFileName).getAbsoluteFile();
        
        if (!dataFile.exists())
        {
            throw new IllegalArgumentException("File " + tableDataFileName + " could not be found.");
        }
        
        return new WormDB(tableDef, dataFile);
    }
    
    /**
     * @return the dataFile
     */
    public final File getDataFile() {
        return dataFile;
    }

    /**
     * @return the tableDefinition
     */
    public final TableDefinition getTableDefinition() {
        return tableDefinition;
    }

}
