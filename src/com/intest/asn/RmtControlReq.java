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
import javax.validation.constraints.Null;

import org.asnlab.asndt.runtime.conv.AnnotationCompositeConverter;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.BooleanConverter;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.IntegerConverter;
import org.asnlab.asndt.runtime.conv.LongConverter;
import org.asnlab.asndt.runtime.conv.OctetStringConverter;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class RmtControlReq {

	@Null
	@Component(0)
	public Boolean reportReq;	/* OPTIONAL */

	@Null
	@Component(1)
	public Boolean rebootReq;	/* OPTIONAL */

	@Null
	@Component(2)
	public Boolean resetDistReq;	/* OPTIONAL */

	@Null
	@Min(0L) @Max(4294967295L)
	@Component(3)
	public Long calibrateReq;	/* OPTIONAL */

	@Null
	@Component(4)
	public VrtCanCard vCanCardReq;	/* OPTIONAL */

	@Null
	@Min(0L) @Max(255L)
	@Component(5)
	public Integer accumVarReq;	/* OPTIONAL */

	@Null
	@Component(6)
	public byte[] delFileReq;	/* OPTIONAL */


	public boolean equals(Object obj) {
		if(!(obj instanceof RmtControlReq)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static RmtControlReq der_decode(InputStream in) throws IOException {
		return (RmtControlReq)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65566);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(RmtControlReq.class);
		AsnConverter reportReqConverter = BooleanConverter.INSTANCE;
		AsnConverter rebootReqConverter = BooleanConverter.INSTANCE;
		AsnConverter resetDistReqConverter = BooleanConverter.INSTANCE;
		AsnConverter calibrateReqConverter = LongConverter.INSTANCE;
		AsnConverter vCanCardReqConverter = VrtCanCard.CONV;
		AsnConverter accumVarReqConverter = IntegerConverter.INSTANCE;
		AsnConverter delFileReqConverter = OctetStringConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { reportReqConverter, rebootReqConverter, resetDistReqConverter, calibrateReqConverter, vCanCardReqConverter, accumVarReqConverter, delFileReqConverter });
	}


}
