/*
 * Generated by ASN.1 Java Compiler (http://www.asnlab.org/)
 * From ASN.1 module "INTEST"
 */
package com.intest.asn;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.asnlab.asndt.runtime.conv.AnnotationChoiceConverter;
import org.asnlab.asndt.runtime.conv.AsnConverter;
import org.asnlab.asndt.runtime.conv.ChoiceConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.annotation.Alternative;
import org.asnlab.asndt.runtime.type.AsnType;

public class RmtControl {

	public static final int rmtControlReqChosen = 0;
	public static final int rmtControlRspChosen = 1;

	public final int choiceID;

	@Alternative(0)
	public final RmtControlReq rmtControlReq;

	@Alternative(1)
	public final RmtControlRsp rmtControlRsp;


	private RmtControl(int choiceID, RmtControlReq rmtControlReq, RmtControlRsp rmtControlRsp) {
		this.choiceID = choiceID;
		this.rmtControlReq = rmtControlReq;
		this.rmtControlRsp = rmtControlRsp;
	}

	public static RmtControl rmtControlReq(RmtControlReq rmtControlReq) {
		return new RmtControl(rmtControlReqChosen, rmtControlReq, null);
	}

	public static RmtControl rmtControlRsp(RmtControlRsp rmtControlRsp) {
		return new RmtControl(rmtControlRspChosen, null, rmtControlRsp);
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof RmtControl)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static RmtControl der_decode(InputStream in) throws IOException {
		return (RmtControl)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65568);

	public final static ChoiceConverter CONV;

	static {
		CONV = new AnnotationChoiceConverter(RmtControl.class);
		AsnConverter rmtControlReqConverter = RmtControlReq.CONV;
		AsnConverter rmtControlRspConverter = RmtControlRsp.CONV;
		CONV.setAlternativeConverters(new AsnConverter[] { rmtControlReqConverter, rmtControlRspConverter });
	}


}
