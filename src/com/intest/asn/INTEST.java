/*
 * Generated by ASN.1 Java Compiler (http://www.asnlab.org/)
 * From ASN.1 module "INTEST"
 */
package com.intest.asn;

import java.util.Vector;

import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.type.AsnModule;
import org.asnlab.asndt.runtime.type.AsnType;


public class INTEST extends AsnModule {

	public final static INTEST instance = new INTEST();


	/**
	/* Creates the ASN.1 module.
	/* The ASN.1 module instance is created automatically, clients must not call.
	/* A metadata file named INTEST.meta must exist in the same package of this class.
	 **/
	private INTEST() {
		super(INTEST.class); 
	}


	public static AsnType type(int id) {
		return instance.getType(id);
	}

	public static Object value(int valueId, AsnConverter converter) {
		return instance.getValue(valueId, converter);
	}

	public static Object object(int objectId, AsnConverter converter) {
		return instance.getObject(objectId, converter);
	}

	@SuppressWarnings("rawtypes")
	public static Vector objectSet(int objectSetId, AsnConverter converter) {
		return instance.getObjectSet(objectSetId, converter);
	}


}
