/*
 * Generated by ASN.1 Java Compiler (http://www.asnlab.org/)
 * From ASN.1 module "INTEST"
 */
package com.intest.asn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.asnlab.asndt.runtime.conv.AnnotationCompositeConverter;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.IntegerConverter;
import org.asnlab.asndt.runtime.conv.OctetStringConverter;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class DBCDownReq {

	@Null
	@Component(0)
	public byte[] name;	/* OPTIONAL */

	@NotNull
	@Min(1L) @Max(65535L)
	@Component(1)
	public Integer total;

	@NotNull
	@Min(1L) @Max(65535L)
	@Component(2)
	public Integer index;

	@NotNull
	@Min(1L) @Max(65535L)
	@Component(3)
	public Integer length;

	@NotNull
	@Component(4)
	public byte[] data;


	public boolean equals(Object obj) {
		if(!(obj instanceof DBCDownReq)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static DBCDownReq der_decode(InputStream in) throws IOException {
		return (DBCDownReq)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65574);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(DBCDownReq.class);
		AsnConverter nameConverter = OctetStringConverter.INSTANCE;
		AsnConverter totalConverter = IntegerConverter.INSTANCE;
		AsnConverter indexConverter = IntegerConverter.INSTANCE;
		AsnConverter lengthConverter = IntegerConverter.INSTANCE;
		AsnConverter dataConverter = OctetStringConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { nameConverter, totalConverter, indexConverter, lengthConverter, dataConverter });
	}


}
