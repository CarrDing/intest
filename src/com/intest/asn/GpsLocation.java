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
import org.asnlab.asndt.runtime.conv.BooleanConverter;
import org.asnlab.asndt.runtime.conv.CompositeConverter;
import org.asnlab.asndt.runtime.conv.DoubleConverter;
import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.conv.annotation.Component;
import org.asnlab.asndt.runtime.type.AsnType;

public class GpsLocation {

	@NotNull
	@Component(0)
	public Boolean isEast;

	@NotNull
	@Component(1)
	public Double longitude;

	@NotNull
	@Component(2)
	public Boolean isNorth;

	@NotNull
	@Component(3)
	public Double latitude;

	@NotNull
	@Component(4)
	public Double height;

	@NotNull
	@Component(5)
	public Double direction;

	@NotNull
	@Component(6)
	public Double speed;

	@NotNull
	@Component(7)
	public Double distance;


	public boolean equals(Object obj) {
		if(!(obj instanceof GpsLocation)){
			return false;
		}
		return TYPE.equals(this, obj, CONV);
	}

	public void der_encode(OutputStream out) throws IOException {
		TYPE.encode(this, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV, out);
	}

	public static GpsLocation der_decode(InputStream in) throws IOException {
		return (GpsLocation)TYPE.decode(in, EncodingRules.DISTINGUISHED_ENCODING_RULES, CONV);
	}


	public final static AsnType TYPE = INTEST.type(65559);

	public final static CompositeConverter CONV;

	static {
		CONV = new AnnotationCompositeConverter(GpsLocation.class);
		AsnConverter isEastConverter = BooleanConverter.INSTANCE;
		AsnConverter longitudeConverter = DoubleConverter.INSTANCE;
		AsnConverter isNorthConverter = BooleanConverter.INSTANCE;
		AsnConverter latitudeConverter = DoubleConverter.INSTANCE;
		AsnConverter heightConverter = DoubleConverter.INSTANCE;
		AsnConverter directionConverter = DoubleConverter.INSTANCE;
		AsnConverter speedConverter = DoubleConverter.INSTANCE;
		AsnConverter distanceConverter = DoubleConverter.INSTANCE;
		CONV.setComponentConverters(new AsnConverter[] { isEastConverter, longitudeConverter, isNorthConverter, latitudeConverter, heightConverter, directionConverter, speedConverter, distanceConverter });
	}


}
