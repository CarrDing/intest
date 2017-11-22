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

import org.asnlab.asndt.runtime.conv.AnnotationCompositeConverter;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.IntegerConverter;
import org.asnlab.asndt.runtime.conv.OctetStringConverter;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class LinkRsp {

	@NotNull
	@Component(0)
	public byte[] commUrl;

	@NotNull
	@Min(0L) @Max(65535L)
	@Component(1)
	public Integer commPort;


	public boolean equals(Object obj) {
		if(!(obj instanceof LinkRsp)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static LinkRsp der_decode(InputStream in) throws IOException {
		return (LinkRsp)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65541);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(LinkRsp.class);
		AsnConverter commUrlConverter = OctetStringConverter.INSTANCE;
		AsnConverter commPortConverter = IntegerConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { commUrlConverter, commPortConverter });
	}


}