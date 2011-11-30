package com.wormdb.core.validation;

import java.util.Collection;

public class Validate 
{

    public static void fieldNotNull(final Object object, final String fieldName)
    {
        try
        {
            org.apache.commons.lang3.Validate.notNull(object);
        }
        catch (IllegalArgumentException e)
        {
            rethrowWithFieldName(fieldName, "null", e);
        }
    }
    
    public static void fieldNotBlank(final String object, final String fieldName)
    {
        try
        {
            org.apache.commons.lang3.Validate.notBlank(object);
        }
        catch (IllegalArgumentException e)
        {
            rethrowWithFieldName(fieldName, "blank", e);
        }
    }
    
    public static <T> void fieldNotNullWithNoNullElements(final Collection<T> collection, final String fieldName)
    {
        try
        {
            org.apache.commons.lang3.Validate.notNull(collection);
            for(T o : collection)
            {
                org.apache.commons.lang3.Validate.notNull(o);
            }
        }
        catch (IllegalArgumentException e)
        {
            rethrowWithFieldName(fieldName, "null or have null elements", e);
        }
    }
    
    public static <T> void fieldNotEmptyWithNoNullElements(final Collection<T> collection, final String fieldName)
    {
        try
        {
            org.apache.commons.lang3.Validate.notNull(collection);
            org.apache.commons.lang3.Validate.notEmpty(collection);
            for(T o : collection)
            {
                org.apache.commons.lang3.Validate.notNull(o);
            }
        }
        catch (IllegalArgumentException e)
        {
            rethrowWithFieldName(fieldName, "empty or have null elements", e);
        }
    }
    
    public static void rethrowWithFieldName(final String fieldName, final String exceptionType, final IllegalArgumentException e)
    {
        throw new IllegalArgumentException("Field " + fieldName + " cannot be " + exceptionType, e);
    }

    public static void isTrue(boolean b) 
    {
        org.apache.commons.lang3.Validate.isTrue(b);
    }
}
