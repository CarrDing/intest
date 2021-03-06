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
import org.asnlab.asndt.runtime.conv.StringConverter;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class Login {

	@NotNull
	@Component(0)
	public String vin;

	@NotNull
	@Component(1)
	public String iccid;

	@NotNull
	@Component(2)
	public String imei;


	public boolean equals(Object obj) {
		if(!(obj instanceof Login)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static Login der_decode(InputStream in) throws IOException {
		return (Login)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65538);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(Login.class);
		AsnConverter vinConverter = StringConverter.INSTANCE;
		AsnConverter iccidConverter = StringConverter.INSTANCE;
		AsnConverter imeiConverter = StringConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { vinConverter, iccidConverter, imeiConverter });
	}


}
