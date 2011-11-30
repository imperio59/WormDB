package com.wormdb.core.definition;

public interface ParsableObject
{
	public void validateAfterParse();
	
	public void postParse();
}
