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

public class ParaSetQuery {

	public static final int paraSetReqChosen = 0;
	public static final int paraSetRspChosen = 1;
	public static final int paraQueryReqChosen = 2;
	public static final int paraQueryRspChosen = 3;

	public final int choiceID;

	@Alternative(0)
	public final ParaSetReq paraSetReq;

	@Alternative(1)
	public final ParaSetRsp paraSetRsp;

	@Alternative(2)
	public final ParaQueryReq paraQueryReq;

	@Alternative(3)
	public final ParaQueryRsp paraQueryRsp;


	private ParaSetQuery(int choiceID, ParaSetReq paraSetReq, ParaSetRsp paraSetRsp, ParaQueryReq paraQueryReq, ParaQueryRsp paraQueryRsp) {
		this.choiceID = choiceID;
		this.paraSetReq = paraSetReq;
		this.paraSetRsp = paraSetRsp;
		this.paraQueryReq = paraQueryReq;
		this.paraQueryRsp = paraQueryRsp;
	}

	public static ParaSetQuery paraSetReq(ParaSetReq paraSetReq) {
		return new ParaSetQuery(paraSetReqChosen, paraSetReq, null, null, null);
	}

	public static ParaSetQuery paraSetRsp(ParaSetRsp paraSetRsp) {
		return new ParaSetQuery(paraSetRspChosen, null, paraSetRsp, null, null);
	}

	public static ParaSetQuery paraQueryReq(ParaQueryReq paraQueryReq) {
		return new ParaSetQuery(paraQueryReqChosen, null, null, paraQueryReq, null);
	}

	public static ParaSetQuery paraQueryRsp(ParaQueryRsp paraQueryRsp) {
		return new ParaSetQuery(paraQueryRspChosen, null, null, null, paraQueryRsp);
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof ParaSetQuery)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static ParaSetQuery der_decode(InputStream in) throws IOException {
		return (ParaSetQuery)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65558);

	public final static ChoiceConverter CONV;

	static {
		CONV = new AnnotationChoiceConverter(ParaSetQuery.class);
		AsnConverter paraSetReqConverter = ParaSetReq.CONV;
		AsnConverter paraSetRspConverter = ParaSetRsp.CONV;
		AsnConverter paraQueryReqConverter = ParaQueryReq.CONV;
		AsnConverter paraQueryRspConverter = ParaQueryRsp.CONV;
		CONV.setAlternativeConverters(new AsnConverter[] { paraSetReqConverter, paraSetRspConverter, paraQueryReqConverter, paraQueryRspConverter });
	}


}
