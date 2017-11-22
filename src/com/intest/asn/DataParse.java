package com.intest.asn;

import java.util.Map;
import java.util.Vector;

import org.asnlab.asndt.runtime.conv.EncodingRules;
import org.asnlab.asndt.runtime.type.Buffer;

import com.intest.util.ByteUtil;
import com.intest.util.TimeUtil;
import com.intest.util.ValitionValue;
import com.intest.zlib.ZlibUtils;

public class DataParse {

	public static void main(String[] args) {
		/*LogInOut logInOut = new LogInOut();
		logInOut.type = true;
		logInOut.rsp = new byte[]{2};
		Preamble preamble = new Preamble();
		preamble.logInOut = logInOut;
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataTime = new byte[]{0,0,0,0,0,0,0};
		basicInfo.dataSN = new byte[]{0,0,0,1};
		basicInfo.dataDirect = new byte[]{1};
		preamble.basicInfo = basicInfo;
		TerminalStat terminalStat = new TerminalStat();
		terminalStat.type = new byte[]{1};
		preamble.terminalStat = terminalStat;
		
		
		System.out.println("======== encode ========");
		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		Preamble.TYPE.encode(preamble, buffer, Preamble.CONV);
		byte[] bytes = buffer.array();
		System.out.println(preamble.TYPE.toHexString(bytes));
		
		Buffer buffer2 = Buffer.wrap(Preamble.TYPE.parseHexString("30 55 A0 12 80 01 01 81 04 00 00 00 09 82 07 17 08 09 14 38 15 00 A1 3F 80 01 FF A2 3A 80 11 A1 A2 A3 A4 A5 A6 A7 A8 A9 AA AB AC AD AE AF B0 B1 81 14 B1 B2 B3 B4 B5 B6 B7 B8 B9 BA BB BC BD BE BF C0 C1 C2 C3 C4 82 0F C1 C2 C3 C4 C5 C6 C7 C8 C9 CA CB CC CD CE CF".replaceAll(" ", "")), EncodingRules.DISTINGUISHED_ENCODING_RULES);
		Preamble p = (Preamble) Preamble.TYPE.decode(buffer2, Preamble.CONV);
		Preamble.TYPE.print(p, Preamble.CONV, System.out);
		
		
		System.out.println(Preamble.TYPE.toHexString(ZlibUtils.compress(encode())));*/
		//System.out.println("1".getBytes());
		
		//encode();
		//p.basicInfo
	
		/*Buffer buffer2 = Buffer.wrap(UserData.TYPE.parseHexString("	30 82 04 98 A0 0F 80 01 01 81 01 02 82 04 59 96 FF C0 83 01 3B A1 82 04 83 A2 82 04 7F A0 42 A0 40 80 04 32 34 2E 34 81 04 7F FF FF FF 82 01 40 A3 2F 80 01 FF 81 04 33 33 2E 33 82 01 FF 83 05 34 34 2E 34 34 84 05 35 35 2E 35 35 85 05 36 36 2E 36 36 86 05 36 36 2E 36 36 87 05 36 36 2E 36 36 A1 82 04 37 A0 82 04 33 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31 09 03 32 2E 31".replaceAll(" ", "")), EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData p = (UserData) UserData.TYPE.decode(buffer2, UserData.CONV);
		UserData.TYPE.print(p, UserData.CONV, System.out);*/
		/*Map<String, Object> map = new HashMap<String, Object>();
		IntRsp intRsp = new IntRsp();
		intRsp.dataInterval = 1;
		intRsp.heartInterval = 60;
		map.put("intSet", intRsp);
		System.out.println(UserData.TYPE.toHexString(paraSetReq(map)));
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("intReq", true);*/
		//System.out.println(UserData.TYPE.toHexString(rmtControl()));
		/*Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("img", true);
		System.out.println(UserData.TYPE.toHexString(fileTrans(map1)));*/
		TerminalData(1);
	}

	@SuppressWarnings("static-access")
	public static UserData decode(String bs) {
		Buffer buffer2 = Buffer.wrap(UserData.TYPE.parseHexString(bs.replaceAll(" ", "")), EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData p = (UserData) UserData.TYPE.decode(buffer2, UserData.CONV);
		return p;
		//Preamble.TYPE.print(p, Preamble.CONV, System.out);
	}
	
	public static UserData decode(byte[] bs) {
		Buffer buffer2 = Buffer.wrap(bs, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData p = (UserData) UserData.TYPE.decode(buffer2, UserData.CONV);
		return p;
		//Preamble.TYPE.print(p, Preamble.CONV, System.out);
	}
	//发送登录请求回应
	public static byte[] loginOut() {
		
		
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataDirect = 1;
		basicInfo.dataSN = 1l;
		basicInfo.timeMsec = 1;
		basicInfo.timeSec = 100l;
		
		
		
		UserData userData = new UserData();
		userData.basicInfo = basicInfo;

		ChoiceLogInOut choiceLogInOut = ChoiceLogInOut.loginRsp(1L);
		
		
		userData.chioceDate = ChoiceData.logInOut(choiceLogInOut);
		
		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData.TYPE.encode(userData, buffer, UserData.CONV);
		byte[] bytes = buffer.array();
		byte[] asn1 = ZlibUtils.compress(bytes);
		
		//获得长度
		byte[] asn1Length = ByteUtil.int2Bytes(asn1.length);
		//校验值
		byte valition = ValitionValue.valitionValue(asn1);
		//固定头部
		byte[] filename = new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};
		byte[] md5 = new byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		byte[] zlib = new byte[]{1};
		byte[] encode = new byte[]{1}; 
		byte[] compressLength = asn1Length; 
		byte[] valitionValue = new byte[]{valition};
		//获取真实时间
		byte[] time = new byte[]{(byte) TimeUtil.getTime()[0],(byte) TimeUtil.getTime()[1],(byte) TimeUtil.getTime()[2],(byte) TimeUtil.getTime()[3]}; 
		
		byte[] data = add(add(add(add(add(add(filename,md5),zlib),encode),compressLength),valitionValue), time);
		
		//固定头部 + asn.1
		byte[] payload = add(data, asn1);
		
		
		return payload;
	}
	
	//发送参数设置请求
	public static byte[] paraSetReq(Map<String, Object> map) {

		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataDirect = 1;
		basicInfo.dataSN = 1l;
		basicInfo.timeMsec = 1;
		basicInfo.timeSec = 100l;

		UserData userData = new UserData();
		userData.basicInfo = basicInfo;
		ParaSetReq paraSetReq = new ParaSetReq();
		//switch 参数设置请求
		for (String key : map.keySet()) {
			switch(key) {
				case "intSet":{paraSetReq.intSet = (IntRsp) map.get(key);} break;//7E协议的F1，终端通信数据间隔参数设置
				case "link1Set":{paraSetReq.link1Set = (LinkRsp) map.get(key);} break;//7E协议的F2，主站IP地址和端口设置
				case "link2Set":{paraSetReq.link2Set = (LinkRsp) map.get(key);} break;//第二链路IP地址和端口设置
				case "link2EnSet":{paraSetReq.link2EnSet = (Boolean) map.get(key);} break;//设置第二个链接的开关(国标直连)，true：开启，false：关闭 
				case "vinSet":{paraSetReq.vinSet = (byte[]) map.get(key);} break;//7E协议的F68，设置终端存储VIN码，17byte
				case "destSet":{paraSetReq.destSet = (DestRsp) map.get(key);} break;//7E协议的F3，目的地设置
				case "boundSet":{paraSetReq.boundSet = (BoundData) map.get(key);} break;//7E协议的F4，越界参数设置
				case "dbcNameSet":{paraSetReq.dbcNameSet = (byte[]) map.get(key);} break;//7E协议的F5，DBC文件名设置
				case "sysSet":{paraSetReq.sysSet = (Long) map.get(key);} break;//7E协议的F8，系统休眠模式设置，0：正常，1：本地唤醒，2：CAN唤醒，4：GPRS唤醒 
				case "sdHzSet":{paraSetReq.sdHzSet = (Integer) map.get(key);} break;//7E协议的F13，SD卡数据存储频率设置 
				case "authKeySet":{paraSetReq.authKeySet = (byte[]) map.get(key);} break;//7E协议的F14，终端密码设置
				case "commIntSet":{paraSetReq.commIntSet = (CommInt) map.get(key);} break;//7E协议的F16，快速上报周期设置
				case "canlogModeSet":{paraSetReq.canlogModeSet = (Integer) map.get(key);} break;//7E协议的F18，设置SD卡数据存储模式，0：inx:，1：iwdz
				case "canlogTimeSet":{paraSetReq.canlogTimeSet = (Long) map.get(key);} break;//7E协议的F19，设置故障触发原始CAN报文存储时长
			}
		}
		ParaSetQuery paraSetQuery = ParaSetQuery.paraSetReq(paraSetReq);
		userData.chioceDate = ChoiceData.paraSetQuery(paraSetQuery);
		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData.TYPE.encode(userData, buffer, UserData.CONV);
		byte[] bytes = buffer.array();
		byte[] asn1 = ZlibUtils.compress(bytes);

		// 获得长度
		byte[] asn1Length = ByteUtil.int2Bytes(asn1.length);
		// 校验值
		byte valition = ValitionValue.valitionValue(asn1);
		// 固定头部
		byte[] filename = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
		byte[] md5 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		byte[] zlib = new byte[] { 1 };
		byte[] encode = new byte[] { 1 };
		byte[] compressLength = asn1Length;
		byte[] valitionValue = new byte[] { valition };
		// 获取真实时间
		byte[] time = new byte[] { (byte) TimeUtil.getTime()[0], (byte) TimeUtil.getTime()[1],
				(byte) TimeUtil.getTime()[2], (byte) TimeUtil.getTime()[3] };

		byte[] data = add(add(add(add(add(add(filename, md5), zlib), encode), compressLength), valitionValue), time);

		// 固定头部 + asn.1
		byte[] payload = add(data, asn1);

		return payload;
	}
	
	//发送参数查询请求
	public static byte[] paraQueryReq(Map<String, Object> map) {
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataDirect = 1;
		basicInfo.dataSN = 1l;
		basicInfo.timeMsec = 1;
		basicInfo.timeSec = 100l;

		UserData userData = new UserData();
		userData.basicInfo = basicInfo;

		ParaQueryReq paraQueryReq = new ParaQueryReq();
		for (String key : map.keySet()) {
			switch(key) {
				case "intReq":paraQueryReq.intReq = (Boolean)map.get(key); break;
				case "link1Req":paraQueryReq.link1Req = (Boolean)map.get(key); break;
				case "link2Req":paraQueryReq.link2Req = (Boolean)map.get(key); break;
				case "link2EnReq":paraQueryReq.link2EnReq = (Boolean)map.get(key); break;
				case "vinReq":paraQueryReq.vinReq = (Boolean)map.get(key); break;
				case "destReq":paraQueryReq.destReq = (Boolean)map.get(key); break;
				case "boundReq":paraQueryReq.boundReq = (Boolean)map.get(key); break;
				case "dbcNameReq":paraQueryReq.dbcNameReq = (Boolean)map.get(key); break;
				case "sysReq":paraQueryReq.sysReq = (Boolean)map.get(key); break;
				case "sdHzReq":paraQueryReq.sdHzReq = (Boolean)map.get(key); break;
				case "authKeyReq":paraQueryReq.authKeyReq = (Boolean)map.get(key); break;
				case "commIntReq":paraQueryReq.commIntReq = (Boolean)map.get(key); break;
				case "canlogModeReq":paraQueryReq.canlogModeReq = (Boolean)map.get(key); break;
				case "canlogTimeReq":paraQueryReq.canlogTimeReq = (Boolean)map.get(key); break;
				case "custNameReq":paraQueryReq.custNameReq = (CustNameReq)map.get(key); break;
				case "sdCapReq":paraQueryReq.sdCapReq = (Boolean)map.get(key); break;
				case "fileDirReq":paraQueryReq.fileDirReq = (DirFileNo)map.get(key); break;
				case "upFileReq":paraQueryReq.upFileReq = (UploadFile)map.get(key); break;
			}
		}
		
		ParaSetQuery paraSetQuery = ParaSetQuery.paraQueryReq(paraQueryReq);

		userData.chioceDate = ChoiceData.paraSetQuery(paraSetQuery);

		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData.TYPE.encode(userData, buffer, UserData.CONV);
		byte[] bytes = buffer.array();
		byte[] asn1 = ZlibUtils.compress(bytes);

		// 获得长度
		byte[] asn1Length = ByteUtil.int2Bytes(asn1.length);
		// 校验值
		byte valition = ValitionValue.valitionValue(asn1);
		// 固定头部
		byte[] filename = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
		byte[] md5 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		byte[] zlib = new byte[] { 1 };
		byte[] encode = new byte[] { 1 };
		byte[] compressLength = asn1Length;
		byte[] valitionValue = new byte[] { valition };
		// 获取真实时间
		byte[] time = new byte[] { (byte) TimeUtil.getTime()[0], (byte) TimeUtil.getTime()[1],
				(byte) TimeUtil.getTime()[2], (byte) TimeUtil.getTime()[3] };

		byte[] data = add(add(add(add(add(add(filename, md5), zlib), encode), compressLength), valitionValue), time);

		// 固定头部 + asn.1
		byte[] payload = add(data, asn1);

		return payload;
	}
	
	//远程控制
	public static byte[] rmtControl(Map<String, Object> map) {

		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataDirect = 1;
		basicInfo.dataSN = 1l;
		basicInfo.timeMsec = 1;
		basicInfo.timeSec = 100l;

		UserData userData = new UserData();
		userData.basicInfo = basicInfo;
		RmtControlReq rmtControlReq = new RmtControlReq();
		
		for (String key : map.keySet()) {
			switch (key) {
			case "reportReq":{rmtControlReq.reportReq = true;}break;
			case "rebootReq":{rmtControlReq.rebootReq = true;}break;
			case "resetDistReq":{rmtControlReq.resetDistReq = true;}break;
			case "calibrateReq":{rmtControlReq.calibrateReq = (Long) map.get(key);}break;
			case "vCanCardReq":{
				VrtCanCard vrtCanCard = new VrtCanCard();
				vrtCanCard.recordNum = 1;
				vrtCanCard.timeOut = 12;
				vrtCanCard.txSeq = 3;
				VCanDate vCanDate = new VCanDate();
				vCanDate.dataID = 1l;
				vCanDate.dataLen = 10;
				vCanDate.dataPort = 1;
				vCanDate.dataType = "a";
				vCanDate.dataValue = new byte[]{1};
				Vector<VCanDate> vector = new Vector<VCanDate>();
				vector.add(vCanDate);
				vrtCanCard.vCanData = vector;
				rmtControlReq.vCanCardReq = vrtCanCard;
			}break;
			case "accumVarReq":{rmtControlReq.accumVarReq = (Integer) map.get(key);}break;
			case "delFileReq":{rmtControlReq.delFileReq = (byte[]) map.get(key);}break;
			default:
				break;
			}
		}
		
		//rmtControlReq.accumVarReq = 1;
		//rmtControlReq.calibrateReq = 1506787200l;
		//rmtControlReq.delFileReq = new byte[]{'/','t','e','s','t','(','3','0',')','.','l','o','g'};
		//rmtControlReq.rebootReq = true;
		//rmtControlReq.reportReq = true;
		//rmtControlReq.resetDistReq = true;
		
		/*VrtCanCard vrtCanCard = new VrtCanCard();
		vrtCanCard.recordNum = 1;
		vrtCanCard.timeOut = 12;
		vrtCanCard.txSeq = 3;
		VCanDate vCanDate = new VCanDate();
		vCanDate.dataID = 1l;
		vCanDate.dataLen = 10;
		vCanDate.dataPort = 1;
		vCanDate.dataType = "a";
		vCanDate.dataValue = new byte[]{1};
		Vector<VCanDate> vector = new Vector<VCanDate>();
		vector.add(vCanDate);
		vrtCanCard.vCanData = vector;
		rmtControlReq.vCanCardReq = vrtCanCard;*/
		
		
		RmtControl rmtControl = RmtControl.rmtControlReq(rmtControlReq);
		userData.chioceDate = ChoiceData.rmtControl(rmtControl);
		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData.TYPE.encode(userData, buffer, UserData.CONV);
		byte[] bytes = buffer.array();
		byte[] asn1 = ZlibUtils.compress(bytes);

		// 获得长度
		byte[] asn1Length = ByteUtil.int2Bytes(asn1.length);
		// 校验值
		byte valition = ValitionValue.valitionValue(asn1);
		// 固定头部
		byte[] filename = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
		byte[] md5 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		byte[] zlib = new byte[] { 1 };
		byte[] encode = new byte[] { 1 };
		byte[] compressLength = asn1Length;
		byte[] valitionValue = new byte[] { valition };
		// 获取真实时间
		byte[] time = new byte[] { (byte) TimeUtil.getTime()[0], (byte) TimeUtil.getTime()[1],
				(byte) TimeUtil.getTime()[2], (byte) TimeUtil.getTime()[3] };

		byte[] data = add(add(add(add(add(add(filename, md5), zlib), encode), compressLength), valitionValue), time);

		// 固定头部 + asn.1
		byte[] payload = add(data, asn1);

		return payload;
	}
	
	//事件上报
	public static byte[] eventReport(Map<String, Object> map) {
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataDirect = 1;
		basicInfo.dataSN = 1l;
		basicInfo.timeMsec = 1;
		basicInfo.timeSec = 100l;

		UserData userData = new UserData();
		userData.basicInfo = basicInfo;
		FaultStruct faultStruct = null;
		for (String key : map.keySet()) {
			switch (key) {
			case "state":faultStruct = FaultStruct.vfState(1);break;
			case "J1939":{
				Vector<FaultData> vector = new Vector<FaultData>();
				FaultData faultData = new FaultData();
				faultData.vfCode = new byte[]{1};
				faultData.vfEndSec = 1l;
				faultData.vfFlag = 1;
				faultData.vfStartSec = 1l;
				vector.add(faultData);
				faultStruct = FaultStruct.vf1939(vector);
			}break;
			case "vfCustm":{
				Vector<FaultData> vector = new Vector<FaultData>();
				FaultData faultData = new FaultData();
				faultData.vfCode = new byte[]{1};
				faultData.vfEndSec = 1l;
				faultData.vfFlag = 1;
				faultData.vfStartSec = 1l;
				vector.add(faultData);
				faultStruct = FaultStruct.vfCustm(vector);
			}break;
			default:break;
			}
		}
		
		
		
		EventReport eventReport = EventReport.faultReport(faultStruct);
		userData.chioceDate = ChoiceData.eventReport(eventReport);

		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData.TYPE.encode(userData, buffer, UserData.CONV);
		byte[] bytes = buffer.array();
		byte[] asn1 = ZlibUtils.compress(bytes);

		// 获得长度
		byte[] asn1Length = ByteUtil.int2Bytes(asn1.length);
		// 校验值
		byte valition = ValitionValue.valitionValue(asn1);
		// 固定头部
		byte[] filename = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
		byte[] md5 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		byte[] zlib = new byte[] { 1 };
		byte[] encode = new byte[] { 1 };
		byte[] compressLength = asn1Length;
		byte[] valitionValue = new byte[] { valition };
		// 获取真实时间
		byte[] time = new byte[] { (byte) TimeUtil.getTime()[0], (byte) TimeUtil.getTime()[1],
				(byte) TimeUtil.getTime()[2], (byte) TimeUtil.getTime()[3] };

		byte[] data = add(add(add(add(add(add(filename, md5), zlib), encode), compressLength), valitionValue), time);

		// 固定头部 + asn.1
		byte[] payload = add(data, asn1);

		return payload;
	}
	
	//文件传输
	public static byte[] fileTrans(Map<String, Object> map) {
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataDirect = 1;
		basicInfo.dataSN = 1l;
		basicInfo.timeMsec = 1;
		basicInfo.timeSec = 100l;

		UserData userData = new UserData();
		userData.basicInfo = basicInfo;
		
		FileTrans fileTrans = null;
		for (String key : map.keySet()) {
			switch (key) {
			case "img":
				ImgDownReq imgDownReq = (ImgDownReq) map.get("img");
				ImgDownload imgDownload = ImgDownload.imgDownReq(imgDownReq);
				fileTrans = FileTrans.imgDownload(imgDownload);
				break;
			case "dbc":
				DBCDownReq dbcDownReq = new DBCDownReq();
				dbcDownReq.data = new byte[]{1};
				dbcDownReq.index = 1;
				dbcDownReq.length = 10;
				dbcDownReq.name = new byte[]{1};
				dbcDownReq.total = 11;
				DBCDownload dbcDownload = DBCDownload.dbcDownReq(dbcDownReq);
				fileTrans = FileTrans.dbcDownload(dbcDownload);
				break;
			default:
				break;
			}
		}
		userData.chioceDate = ChoiceData.fileTrans(fileTrans);

		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		UserData.TYPE.encode(userData, buffer, UserData.CONV);
		byte[] bytes = buffer.array();
		byte[] asn1 = ZlibUtils.compress(bytes);

		// 获得长度
		byte[] asn1Length = ByteUtil.int2Bytes(asn1.length);
		// 校验值
		byte valition = ValitionValue.valitionValue(asn1);
		// 固定头部
		byte[] filename = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
		byte[] md5 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		byte[] zlib = new byte[] { 1 };
		byte[] encode = new byte[] { 1 };
		byte[] compressLength = asn1Length;
		byte[] valitionValue = new byte[] { valition };
		// 获取真实时间
		byte[] time = new byte[] { (byte) TimeUtil.getTime()[0], (byte) TimeUtil.getTime()[1],
				(byte) TimeUtil.getTime()[2], (byte) TimeUtil.getTime()[3] };

		byte[] data = add(add(add(add(add(add(filename, md5), zlib), encode), compressLength), valitionValue), time);

		// 固定头部 + asn.1
		byte[] payload = add(data, asn1);

		return payload;
	}
	
	public static byte[] TerminalData(int i) {

		BasicInfo basicInfo = new BasicInfo();
		basicInfo.dataDirect = 1;
		basicInfo.dataSN = 1l;
		basicInfo.timeMsec = (int) (System.currentTimeMillis()%1000);
		basicInfo.timeSec = System.currentTimeMillis()/1000;

		UserData userData = new UserData();
		userData.basicInfo = basicInfo;

		DataReport dataReport = new DataReport();
		
		GpsLocation gpsLocation = new GpsLocation();
		gpsLocation.direction = 0.3;
		gpsLocation.distance = 12.0;
		gpsLocation.height = 10.0;
		gpsLocation.latitude = 78.9;
		gpsLocation.longitude = 9778.0;
		gpsLocation.speed = 54.0;
		gpsLocation.isEast = true;
		gpsLocation.isNorth = true;
		
		TerminalReport terminalReport = new TerminalReport();
		terminalReport.ant = 100;
		terminalReport.voltage = 334.0;
		terminalReport.workStatus = 12l;
		terminalReport.rebootCnt = 1l;
		terminalReport.sysRunTime = 100l;
		terminalReport.gprsFlowCnt = 1l;
		terminalReport.gpsLocation = gpsLocation;
		
		TerminalStat terminalStat = TerminalStat.terminalReport(terminalReport);
		VehiData vehiData = new VehiData();
		vehiData.dbcMD5 = new byte[]{1,1};
		Vector<Double> vector = new Vector<Double>();
		//vector.add(12.00);
		vehiData.vehiReport = vector;
		dataReport.terminalStat = terminalStat;
		dataReport.vehiData = vehiData;

		userData.chioceDate = ChoiceData.dataReport(dataReport);

		Buffer buffer = Buffer.allocate(1024, EncodingRules.DISTINGUISHED_ENCODING_RULES);
		
		UserData.TYPE.encode(userData, buffer, UserData.CONV);
		byte[] bytes = buffer.array();
		byte[] asn1 = ZlibUtils.compress(bytes);

		// 获得长度
		byte[] asn1Length = ByteUtil.int2Bytes(asn1.length);
		// 校验值
		byte valition = ValitionValue.valitionValue(asn1);
		// 固定头部
		byte[] filename = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
		byte[] md5 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		byte[] zlib = new byte[] { 1 };
		byte[] encode = new byte[] { 1 };
		byte[] compressLength = asn1Length;
		byte[] valitionValue = new byte[] { valition };
		// 获取真实时间
		byte[] time = new byte[] { (byte) TimeUtil.getTime()[0], (byte) TimeUtil.getTime()[1],
				(byte) TimeUtil.getTime()[2], (byte) TimeUtil.getTime()[3] };

		byte[] data = add(add(add(add(add(add(filename, md5), zlib), encode), compressLength), valitionValue), time);

		// 固定头部 + asn.1
		byte[] payload = add(data, asn1);

		return payload;
	}
	
	public static byte[] add(byte[] b1, byte[] b2) {
		byte[] data3 = new byte[b1.length + b2.length];
		System.arraycopy(b1, 0, data3, 0, b1.length);
		System.arraycopy(b2, 0, data3, b1.length, b2.length);
		return data3;
	}
}
