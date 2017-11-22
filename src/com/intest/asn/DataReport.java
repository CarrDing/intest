/*
 * Generated by ASN.1 Java Compiler (http://www.asnlab.org/)
 * From ASN.1 module "INTEST"
 */
package com.intest.asn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.asnlab.asndt.runtime.conv.AnnotationCompositeConverter;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class DataReport {

	@NotNull
	@Component(0)
	public TerminalStat terminalStat;

	@Null
	@Component(1)
	public VehiData vehiData;	/* OPTIONAL */


	public boolean equals(Object obj) {
		if(!(obj instanceof DataReport)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static DataReport der_decode(InputStream in) throws IOException {
		return (DataReport)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65563);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(DataReport.class);
		AsnConverter terminalStatConverter = TerminalStat.CONV;
		AsnConverter vehiDataConverter = VehiData.CONV;
		CONV.setComponentConverters(new AsnConverter[] { terminalStatConverter, vehiDataConverter });
	}


}
