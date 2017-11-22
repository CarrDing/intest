package com.intest.test;

import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.type.Buffer;



public class ExTest {

	public static void main(String[] args) {
	
		//encode
		com.intest.test.ex.Preamble preamble = new com.intest.test.ex.Preamble();
		preamble.name = 1l;
		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		com.intest.test.ex.Preamble.TYPE.encode(preamble, buffer, com.intest.test.ex.Preamble.CONV);
		byte[] bytes = buffer.array();
		
		
		//decode
		Buffer buffer2 = Buffer.wrap(bytes, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		com.intest.test.ex1.Preamble p = (com.intest.test.ex1.Preamble) com.intest.test.ex1.Preamble.TYPE.decode(buffer2, com.intest.test.ex1.Preamble.CONV);
		com.intest.test.ex1.Preamble.TYPE.print(p, com.intest.test.ex1.Preamble.CONV, System.out);
		
		//decode
		Buffer buffer3 = Buffer.wrap(bytes, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		com.intest.test.ex2.Preamble p1 = (com.intest.test.ex2.Preamble) com.intest.test.ex2.Preamble.TYPE.decode(buffer3, com.intest.test.ex2.Preamble.CONV);
		com.intest.test.ex2.Preamble.TYPE.print(p1, com.intest.test.ex2.Preamble.CONV, System.out);
	}
	
	
}
