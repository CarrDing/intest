/*
 * Generated by ASN.1 Java Compiler (http://www.asnlab.org/)
 * From ASN.1 module "INTEST"
 */
package com.intest.asn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.validation.constraints.NotNull;

import org.asnlab.asndt.runtime.conv.AnnotationCompositeConverter;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.LongConverter;
import org.asnlab.asndt.runtime.conv.OctetStringConverter;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class UploadFile {

	@NotNull
	@Component(0)
	public byte[] fileName;

	@NotNull
	@Component(1)
	public Long startNo;

	@NotNull
	@Component(2)
	public Long endNo;


	public boolean equals(Object obj) {
		if(!(obj instanceof UploadFile)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static UploadFile der_decode(InputStream in) throws IOException {
		return (UploadFile)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65550);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(UploadFile.class);
		AsnConverter fileNameConverter = OctetStringConverter.INSTANCE;
		AsnConverter startNoConverter = LongConverter.INSTANCE;
		AsnConverter endNoConverter = LongConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { fileNameConverter, startNoConverter, endNoConverter });
	}


}
