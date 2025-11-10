/*
 * SerializableString.java
 *
 * Copyright 2006 Maan Sarovar Software Pvt. Ltd.
 */

package com.maan.common.util;
import java.io.Serializable;


/**
 * Utilities for manipulations of strings
 *
 * @todo REMOVE ALL REFERENCES TO THIS CLASS!
 */
public class SerializableString
        implements Serializable {

    private static final String thisClass = SerializableString.class.getName();
    private String myValue = null;

    /**
     * Constructor
     */
    public SerializableString() {
        super();
    } /* SerializableString() */

    /**
     *
     *
     * @param   s the String to initalize with
     */
    public SerializableString(String s) {
        super();
        myValue = s;
    } /* SerializableString(String) */

    /**
     *
     *
     * @return java.lang.String, the nested value.
     */
    public String toString() {
        return myValue;
    } /* toString() */

}
/* SerializableString */