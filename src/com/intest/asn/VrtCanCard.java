/*
 * Generated by ASN.1 Java Compiler (http://www.asnlab.org/)
 * From ASN.1 module "INTEST"
 */
package com.intest.asn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.asnlab.asndt.runtime.conv.AnnotationCompositeConverter;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.IntegerConverter;
import org.asnlab.asndt.runtime.conv.VectorConverter;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class VrtCanCard {

	@NotNull
	@Min(1L) @Max(255L)
	@Component(0)
	public Integer txSeq;

	@NotNull
	@Min(0L) @Max(65535L)
	@Component(1)
	public Integer timeOut;

	@NotNull
	@Min(1L) @Max(90L)
	@Component(2)
	public Integer recordNum;

	@NotNull
	@Component(3)
	public Vector<VCanDate> vCanData;


	public boolean equals(Object obj) {
		if(!(obj instanceof VrtCanCard)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static VrtCanCard der_decode(InputStream in) throws IOException {
		return (VrtCanCard)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65565);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(VrtCanCard.class);
		AsnConverter txSeqConverter = IntegerConverter.INSTANCE;
		AsnConverter timeOutConverter = IntegerConverter.INSTANCE;
		AsnConverter recordNumConverter = IntegerConverter.INSTANCE;
		AsnConverter vCanDataConverter = new VectorConverter(VCanDate.CONV);
		CONV.setComponentConverters(new AsnConverter[] { txSeqConverter, timeOutConverter, recordNumConverter, vCanDataConverter });
	}


}
