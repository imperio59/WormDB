package com.wormdb.database;

import java.io.File;
import java.util.Map;

public final class TableStorageInformation 
{
    private File dataFile;
    
    private Map<String, File> indexFiles;
}
