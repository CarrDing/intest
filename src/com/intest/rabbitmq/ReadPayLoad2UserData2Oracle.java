package com.intest.rabbitmq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.intest.asn.DataParse;
import com.intest.asn.UserData;
import com.intest.bean.DataWareHouse;
import com.intest.bean.DataWareHouseV1;
import com.intest.bean.IncomRealTimeEntity;
import com.intest.impl.DataWareHouseImpl;
import com.intest.impl.IncomRealTimeEntityImpl;
import com.intest.util.BuildSeqId;

public class ReadPayLoad2UserData2Oracle {
	
	public static List<IncomRealTimeEntity> incomRealTimeEntitys = new ArrayList<IncomRealTimeEntity>();
	public static List<DataWareHouse> dataWareHouses = new ArrayList<DataWareHouse>();
	public static List<DataWareHouseV1> dataWareHouseV1s = new ArrayList<DataWareHouseV1>();
	
	public void getMessageFromRmq2Oracle(String message) {
		if(!message.equals("null")) {
			try {
				UserData userData = DataParse.decode(message);

        		// 更新实时库
    			//System.out.println(message);
    			//UserData.TYPE.print(userData, UserData.CONV, System.out);
    			// UserData userData = UserDataQueen.take();
    			/**
    			 * put mq
    			 */
    			// UserData.TYPE.print(userData, UserData.CONV, System.out);
    			// System.out.println(Thread.currentThread().getName() +
    			// "读写UserData队列线程开启后，队列里数据量的大小:" + UserDataQueen.pool.size() +
    			// "......");
    			IncomRealTimeEntity incomRealTimeEntity = new IncomRealTimeEntity();
    			incomRealTimeEntity.setTc(201704221);
    			incomRealTimeEntity.setTe(new Date());
    			incomRealTimeEntity
    					.setLongitude(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.longitude);
    			incomRealTimeEntity
    					.setLatitude(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.latitude);
    			incomRealTimeEntity
    					.setAltitude(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.height);
    			incomRealTimeEntity.setSpeed(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.speed);
    			incomRealTimeEntity
    					.setMileage(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.distance);
    			System.out.println("里程:" + userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.distance);
    			incomRealTimeEntity
    					.setDirection(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.direction);
    			incomRealTimeEntity.setIsPosition(1);
    			incomRealTimeEntity.setIsDriver(1);
    			incomRealTimeEntitys.add(incomRealTimeEntity);
    			// 批量入库
    			if (incomRealTimeEntitys.size() > 9) {
    				long start = System.currentTimeMillis();
    				new IncomRealTimeEntityImpl().saveOrUpdate(incomRealTimeEntity);
    				long end = System.currentTimeMillis();
    				System.out.println("更新实时表用时:" + (end - start) / 1000);
    				incomRealTimeEntitys.clear();
    			}
    			// 更新历史库
    			if (userData.chioceDate.dataReport.vehiData.vehiReport != null) {
    				List<Double> canValue = userData.chioceDate.dataReport.vehiData.vehiReport;
    				Double[] temp = new Double[700];
    				for (int i = 0; i < 700; i++) {
    					if (i < canValue.size()) {
    						temp[i] = canValue.get(i);
    					} else {
    						temp[i] = Double.MAX_VALUE;
    					}
    				}
    				DataWareHouse dataWareHouse = new DataWareHouse();
    				dataWareHouse.setTc(201704221);
    				dataWareHouse.setSeqId(
    						BuildSeqId.getSeqId(new Date(userData.basicInfo.timeSec * 1000 + userData.basicInfo.timeMsec)));
    				dataWareHouse.setMd5("1F15AB7B9BCBEA5D54883863A2FFAD8C");
    				dataWareHouse.setTe(new Date(userData.basicInfo.timeMsec + userData.basicInfo.timeSec * 1000));
    				dataWareHouse.setLongitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.longitude.toString());
    				dataWareHouse.setLatitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.latitude.toString());
    				dataWareHouse.setAltitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.height.toString());
    				dataWareHouse.setSpeed(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.speed.toString());
    				dataWareHouse.setMileage(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.distance * 1.0 + "");
    				dataWareHouse.setDirection(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.direction.toString());
    				if (temp[0] != Double.MAX_VALUE) {
    					dataWareHouse.setData001(canValue.get(0));
    				}
    				if (temp[1] != Double.MAX_VALUE) {
    					dataWareHouse.setData002(canValue.get(1));
    				}
    				if (temp[2] != Double.MAX_VALUE) {
    					dataWareHouse.setData003(canValue.get(2));
    				}
    				if (temp[3] != Double.MAX_VALUE) {
    					dataWareHouse.setData004(canValue.get(3));
    				}
    				if (temp[4] != Double.MAX_VALUE) {
    					dataWareHouse.setData005(canValue.get(4));
    				}
    				if (temp[5] != Double.MAX_VALUE) {
    					dataWareHouse.setData006(canValue.get(5));
    				}
    				if (temp[6] != Double.MAX_VALUE) {
    					dataWareHouse.setData007(canValue.get(6));
    				}
    				if (temp[7] != Double.MAX_VALUE) {
    					dataWareHouse.setData008(canValue.get(7));
    				}
    				if (temp[8] != Double.MAX_VALUE) {
    					dataWareHouse.setData009(canValue.get(8));
    				}
    				if (temp[9] != Double.MAX_VALUE) {
    					dataWareHouse.setData010(canValue.get(9));
    				}
    				if (temp[10] != Double.MAX_VALUE) {
    					dataWareHouse.setData011(canValue.get(10));
    				}
    				if (temp[11] != Double.MAX_VALUE) {
    					dataWareHouse.setData012(canValue.get(11));
    				}
    				if (temp[12] != Double.MAX_VALUE) {
    					dataWareHouse.setData013(canValue.get(12));
    				}
    				if (temp[13] != Double.MAX_VALUE) {
    					dataWareHouse.setData014(canValue.get(13));
    				}
    				if (temp[14] != Double.MAX_VALUE) {
    					dataWareHouse.setData015(canValue.get(14));
    				}
    				if (temp[15] != Double.MAX_VALUE) {
    					dataWareHouse.setData016(canValue.get(15));
    				}
    				if (temp[16] != Double.MAX_VALUE) {
    					dataWareHouse.setData017(canValue.get(16));
    				}
    				if (temp[17] != Double.MAX_VALUE) {
    					dataWareHouse.setData018(canValue.get(17));
    				}
    				if (temp[18] != Double.MAX_VALUE) {
    					dataWareHouse.setData019(canValue.get(18));
    				}
    				if (temp[19] != Double.MAX_VALUE) {
    					dataWareHouse.setData020(canValue.get(19));
    				}
    				if (temp[20] != Double.MAX_VALUE) {
    					dataWareHouse.setData021(canValue.get(20));
    				}
    				if (temp[21] != Double.MAX_VALUE) {
    					dataWareHouse.setData022(canValue.get(21));
    				}
    				if (temp[22] != Double.MAX_VALUE) {
    					dataWareHouse.setData023(canValue.get(22));
    				}
    				if (temp[23] != Double.MAX_VALUE) {
    					dataWareHouse.setData024(canValue.get(23));
    				}
    				if (temp[24] != Double.MAX_VALUE) {
    					dataWareHouse.setData025(canValue.get(24));
    				}
    				if (temp[25] != Double.MAX_VALUE) {
    					dataWareHouse.setData026(canValue.get(25));
    				}
    				if (temp[26] != Double.MAX_VALUE) {
    					dataWareHouse.setData027(canValue.get(26));
    				}
    				if (temp[27] != Double.MAX_VALUE) {
    					dataWareHouse.setData028(canValue.get(27));
    				}
    				if (temp[28] != Double.MAX_VALUE) {
    					dataWareHouse.setData029(canValue.get(28));
    				}
    				if (temp[29] != Double.MAX_VALUE) {
    					dataWareHouse.setData030(canValue.get(29));
    				}
    				if (temp[30] != Double.MAX_VALUE) {
    					dataWareHouse.setData031(canValue.get(30));
    				}
    				if (temp[31] != Double.MAX_VALUE) {
    					dataWareHouse.setData032(canValue.get(31));
    				}
    				if (temp[32] != Double.MAX_VALUE) {
    					dataWareHouse.setData033(canValue.get(32));
    				}
    				if (temp[33] != Double.MAX_VALUE) {
    					dataWareHouse.setData034(canValue.get(33));
    				}
    				if (temp[34] != Double.MAX_VALUE) {
    					dataWareHouse.setData035(canValue.get(34));
    				}
    				if (temp[35] != Double.MAX_VALUE) {
    					dataWareHouse.setData036(canValue.get(35));
    				}
    				if (temp[36] != Double.MAX_VALUE) {
    					dataWareHouse.setData037(canValue.get(36));
    				}
    				if (temp[37] != Double.MAX_VALUE) {
    					dataWareHouse.setData038(canValue.get(37));
    				}
    				if (temp[38] != Double.MAX_VALUE) {
    					dataWareHouse.setData039(canValue.get(38));
    				}
    				if (temp[39] != Double.MAX_VALUE) {
    					dataWareHouse.setData040(canValue.get(39));
    				}
    				if (temp[40] != Double.MAX_VALUE) {
    					dataWareHouse.setData041(canValue.get(40));
    				}
    				if (temp[41] != Double.MAX_VALUE) {
    					dataWareHouse.setData042(canValue.get(41));
    				}
    				if (temp[42] != Double.MAX_VALUE) {
    					dataWareHouse.setData043(canValue.get(42));
    				}
    				if (temp[43] != Double.MAX_VALUE) {
    					dataWareHouse.setData044(canValue.get(43));
    				}
    				if (temp[44] != Double.MAX_VALUE) {
    					dataWareHouse.setData045(canValue.get(44));
    				}
    				if (temp[45] != Double.MAX_VALUE) {
    					dataWareHouse.setData046(canValue.get(45));
    				}
    				if (temp[46] != Double.MAX_VALUE) {
    					dataWareHouse.setData047(canValue.get(46));
    				}
    				if (temp[47] != Double.MAX_VALUE) {
    					dataWareHouse.setData048(canValue.get(47));
    				}
    				if (temp[48] != Double.MAX_VALUE) {
    					dataWareHouse.setData049(canValue.get(48));
    				}
    				if (temp[49] != Double.MAX_VALUE) {
    					dataWareHouse.setData050(canValue.get(49));
    				}
    				if (temp[50] != Double.MAX_VALUE) {
    					dataWareHouse.setData051(canValue.get(50));
    				}
    				if (temp[51] != Double.MAX_VALUE) {
    					dataWareHouse.setData052(canValue.get(51));
    				}
    				if (temp[52] != Double.MAX_VALUE) {
    					dataWareHouse.setData053(canValue.get(52));
    				}
    				if (temp[53] != Double.MAX_VALUE) {
    					dataWareHouse.setData054(canValue.get(53));
    				}
    				if (temp[54] != Double.MAX_VALUE) {
    					dataWareHouse.setData055(canValue.get(54));
    				}
    				if (temp[55] != Double.MAX_VALUE) {
    					dataWareHouse.setData056(canValue.get(55));
    				}
    				if (temp[56] != Double.MAX_VALUE) {
    					dataWareHouse.setData057(canValue.get(56));
    				}
    				if (temp[57] != Double.MAX_VALUE) {
    					dataWareHouse.setData058(canValue.get(57));
    				}
    				if (temp[58] != Double.MAX_VALUE) {
    					dataWareHouse.setData059(canValue.get(58));
    				}
    				if (temp[59] != Double.MAX_VALUE) {
    					dataWareHouse.setData060(canValue.get(59));
    				}
    				if (temp[60] != Double.MAX_VALUE) {
    					dataWareHouse.setData061(canValue.get(60));
    				}
    				if (temp[61] != Double.MAX_VALUE) {
    					dataWareHouse.setData062(canValue.get(61));
    				}
    				if (temp[62] != Double.MAX_VALUE) {
    					dataWareHouse.setData063(canValue.get(62));
    				}
    				if (temp[63] != Double.MAX_VALUE) {
    					dataWareHouse.setData064(canValue.get(63));
    				}
    				if (temp[64] != Double.MAX_VALUE) {
    					dataWareHouse.setData065(canValue.get(64));
    				}
    				if (temp[65] != Double.MAX_VALUE) {
    					dataWareHouse.setData066(canValue.get(65));
    				}
    				if (temp[66] != Double.MAX_VALUE) {
    					dataWareHouse.setData067(canValue.get(66));
    				}
    				if (temp[67] != Double.MAX_VALUE) {
    					dataWareHouse.setData068(canValue.get(67));
    				}
    				if (temp[68] != Double.MAX_VALUE) {
    					dataWareHouse.setData069(canValue.get(68));
    				}
    				if (temp[69] != Double.MAX_VALUE) {
    					dataWareHouse.setData070(canValue.get(69));
    				}
    				if (temp[70] != Double.MAX_VALUE) {
    					dataWareHouse.setData071(canValue.get(70));
    				}
    				if (temp[71] != Double.MAX_VALUE) {
    					dataWareHouse.setData072(canValue.get(71));
    				}
    				if (temp[72] != Double.MAX_VALUE) {
    					dataWareHouse.setData073(canValue.get(72));
    				}
    				if (temp[73] != Double.MAX_VALUE) {
    					dataWareHouse.setData074(canValue.get(73));
    				}
    				if (temp[74] != Double.MAX_VALUE) {
    					dataWareHouse.setData075(canValue.get(74));
    				}
    				if (temp[75] != Double.MAX_VALUE) {
    					dataWareHouse.setData076(canValue.get(75));
    				}
    				if (temp[76] != Double.MAX_VALUE) {
    					dataWareHouse.setData077(canValue.get(76));
    				}
    				if (temp[77] != Double.MAX_VALUE) {
    					dataWareHouse.setData078(canValue.get(77));
    				}
    				if (temp[78] != Double.MAX_VALUE) {
    					dataWareHouse.setData079(canValue.get(78));
    				}
    				if (temp[79] != Double.MAX_VALUE) {
    					dataWareHouse.setData080(canValue.get(79));
    				}
    				if (temp[80] != Double.MAX_VALUE) {
    					dataWareHouse.setData081(canValue.get(80));
    				}
    				if (temp[81] != Double.MAX_VALUE) {
    					dataWareHouse.setData082(canValue.get(81));
    				}
    				if (temp[82] != Double.MAX_VALUE) {
    					dataWareHouse.setData083(canValue.get(82));
    				}
    				if (temp[83] != Double.MAX_VALUE) {
    					dataWareHouse.setData084(canValue.get(83));
    				}
    				if (temp[84] != Double.MAX_VALUE) {
    					dataWareHouse.setData085(canValue.get(84));
    				}
    				if (temp[85] != Double.MAX_VALUE) {
    					dataWareHouse.setData086(canValue.get(85));
    				}
    				if (temp[86] != Double.MAX_VALUE) {
    					dataWareHouse.setData087(canValue.get(86));
    				}
    				if (temp[87] != Double.MAX_VALUE) {
    					dataWareHouse.setData088(canValue.get(87));
    				}
    				if (temp[88] != Double.MAX_VALUE) {
    					dataWareHouse.setData089(canValue.get(88));
    				}
    				if (temp[89] != Double.MAX_VALUE) {
    					dataWareHouse.setData090(canValue.get(89));
    				}
    				if (temp[90] != Double.MAX_VALUE) {
    					dataWareHouse.setData091(canValue.get(90));
    				}
    				if (temp[91] != Double.MAX_VALUE) {
    					dataWareHouse.setData092(canValue.get(91));
    				}
    				if (temp[92] != Double.MAX_VALUE) {
    					dataWareHouse.setData093(canValue.get(92));
    				}
    				if (temp[93] != Double.MAX_VALUE) {
    					dataWareHouse.setData094(canValue.get(93));
    				}
    				if (temp[94] != Double.MAX_VALUE) {
    					dataWareHouse.setData095(canValue.get(94));
    				}
    				if (temp[95] != Double.MAX_VALUE) {
    					dataWareHouse.setData096(canValue.get(95));
    				}
    				if (temp[96] != Double.MAX_VALUE) {
    					dataWareHouse.setData097(canValue.get(96));
    				}
    				if (temp[97] != Double.MAX_VALUE) {
    					dataWareHouse.setData098(canValue.get(97));
    				}
    				if (temp[98] != Double.MAX_VALUE) {
    					dataWareHouse.setData099(canValue.get(98));
    				}
    				if (temp[99] != Double.MAX_VALUE) {
    					dataWareHouse.setData100(canValue.get(99));
    				}
    				if (temp[100] != Double.MAX_VALUE) {
    					dataWareHouse.setData101(canValue.get(100));
    				}
    				if (temp[101] != Double.MAX_VALUE) {
    					dataWareHouse.setData102(canValue.get(101));
    				}
    				if (temp[102] != Double.MAX_VALUE) {
    					dataWareHouse.setData103(canValue.get(102));
    				}
    				if (temp[103] != Double.MAX_VALUE) {
    					dataWareHouse.setData104(canValue.get(103));
    				}
    				if (temp[104] != Double.MAX_VALUE) {
    					dataWareHouse.setData105(canValue.get(104));
    				}
    				if (temp[105] != Double.MAX_VALUE) {
    					dataWareHouse.setData106(canValue.get(105));
    				}
    				if (temp[106] != Double.MAX_VALUE) {
    					dataWareHouse.setData107(canValue.get(106));
    				}
    				if (temp[107] != Double.MAX_VALUE) {
    					dataWareHouse.setData108(canValue.get(107));
    				}
    				if (temp[108] != Double.MAX_VALUE) {
    					dataWareHouse.setData109(canValue.get(108));
    				}
    				if (temp[109] != Double.MAX_VALUE) {
    					dataWareHouse.setData110(canValue.get(109));
    				}
    				if (temp[110] != Double.MAX_VALUE) {
    					dataWareHouse.setData111(canValue.get(110));
    				}
    				if (temp[111] != Double.MAX_VALUE) {
    					dataWareHouse.setData112(canValue.get(111));
    				}
    				if (temp[112] != Double.MAX_VALUE) {
    					dataWareHouse.setData113(canValue.get(112));
    				}
    				if (temp[113] != Double.MAX_VALUE) {
    					dataWareHouse.setData114(canValue.get(113));
    				}
    				if (temp[114] != Double.MAX_VALUE) {
    					dataWareHouse.setData115(canValue.get(114));
    				}
    				if (temp[115] != Double.MAX_VALUE) {
    					dataWareHouse.setData116(canValue.get(115));
    				}
    				if (temp[116] != Double.MAX_VALUE) {
    					dataWareHouse.setData117(canValue.get(116));
    				}
    				if (temp[117] != Double.MAX_VALUE) {
    					dataWareHouse.setData118(canValue.get(117));
    				}
    				if (temp[118] != Double.MAX_VALUE) {
    					dataWareHouse.setData119(canValue.get(118));
    				}
    				if (temp[119] != Double.MAX_VALUE) {
    					dataWareHouse.setData120(canValue.get(119));
    				}
    				if (temp[120] != Double.MAX_VALUE) {
    					dataWareHouse.setData121(canValue.get(120));
    				}
    				if (temp[121] != Double.MAX_VALUE) {
    					dataWareHouse.setData122(canValue.get(121));
    				}
    				if (temp[122] != Double.MAX_VALUE) {
    					dataWareHouse.setData123(canValue.get(122));
    				}
    				if (temp[123] != Double.MAX_VALUE) {
    					dataWareHouse.setData124(canValue.get(123));
    				}
    				if (temp[124] != Double.MAX_VALUE) {
    					dataWareHouse.setData125(canValue.get(124));
    				}
    				if (temp[125] != Double.MAX_VALUE) {
    					dataWareHouse.setData126(canValue.get(125));
    				}
    				if (temp[126] != Double.MAX_VALUE) {
    					dataWareHouse.setData127(canValue.get(126));
    				}
    				if (temp[127] != Double.MAX_VALUE) {
    					dataWareHouse.setData128(canValue.get(127));
    				}
    				if (temp[128] != Double.MAX_VALUE) {
    					dataWareHouse.setData129(canValue.get(128));
    				}
    				if (temp[129] != Double.MAX_VALUE) {
    					dataWareHouse.setData130(canValue.get(129));
    				}
    				if (temp[130] != Double.MAX_VALUE) {
    					dataWareHouse.setData131(canValue.get(130));
    				}
    				if (temp[131] != Double.MAX_VALUE) {
    					dataWareHouse.setData132(canValue.get(131));
    				}
    				if (temp[132] != Double.MAX_VALUE) {
    					dataWareHouse.setData133(canValue.get(132));
    				}
    				if (temp[133] != Double.MAX_VALUE) {
    					dataWareHouse.setData134(canValue.get(133));
    				}
    				if (temp[134] != Double.MAX_VALUE) {
    					dataWareHouse.setData135(canValue.get(134));
    				}
    				if (temp[135] != Double.MAX_VALUE) {
    					dataWareHouse.setData136(canValue.get(135));
    				}
    				if (temp[136] != Double.MAX_VALUE) {
    					dataWareHouse.setData137(canValue.get(136));
    				}
    				if (temp[137] != Double.MAX_VALUE) {
    					dataWareHouse.setData138(canValue.get(137));
    				}
    				if (temp[138] != Double.MAX_VALUE) {
    					dataWareHouse.setData139(canValue.get(138));
    				}
    				if (temp[139] != Double.MAX_VALUE) {
    					dataWareHouse.setData140(canValue.get(139));
    				}
    				if (temp[140] != Double.MAX_VALUE) {
    					dataWareHouse.setData141(canValue.get(140));
    				}
    				if (temp[141] != Double.MAX_VALUE) {
    					dataWareHouse.setData142(canValue.get(141));
    				}
    				if (temp[142] != Double.MAX_VALUE) {
    					dataWareHouse.setData143(canValue.get(142));
    				}
    				if (temp[143] != Double.MAX_VALUE) {
    					dataWareHouse.setData144(canValue.get(143));
    				}
    				if (temp[144] != Double.MAX_VALUE) {
    					dataWareHouse.setData145(canValue.get(144));
    				}
    				if (temp[145] != Double.MAX_VALUE) {
    					dataWareHouse.setData146(canValue.get(145));
    				}
    				if (temp[146] != Double.MAX_VALUE) {
    					dataWareHouse.setData147(canValue.get(146));
    				}
    				if (temp[147] != Double.MAX_VALUE) {
    					dataWareHouse.setData148(canValue.get(147));
    				}
    				if (temp[148] != Double.MAX_VALUE) {
    					dataWareHouse.setData149(canValue.get(148));
    				}
    				if (temp[149] != Double.MAX_VALUE) {
    					dataWareHouse.setData150(canValue.get(149));
    				}
    				if (temp[150] != Double.MAX_VALUE) {
    					dataWareHouse.setData151(canValue.get(150));
    				}
    				if (temp[151] != Double.MAX_VALUE) {
    					dataWareHouse.setData152(canValue.get(151));
    				}
    				if (temp[152] != Double.MAX_VALUE) {
    					dataWareHouse.setData153(canValue.get(152));
    				}
    				if (temp[153] != Double.MAX_VALUE) {
    					dataWareHouse.setData154(canValue.get(153));
    				}
    				if (temp[154] != Double.MAX_VALUE) {
    					dataWareHouse.setData155(canValue.get(154));
    				}
    				if (temp[155] != Double.MAX_VALUE) {
    					dataWareHouse.setData156(canValue.get(155));
    				}
    				if (temp[156] != Double.MAX_VALUE) {
    					dataWareHouse.setData157(canValue.get(156));
    				}
    				if (temp[157] != Double.MAX_VALUE) {
    					dataWareHouse.setData158(canValue.get(157));
    				}
    				if (temp[158] != Double.MAX_VALUE) {
    					dataWareHouse.setData159(canValue.get(158));
    				}
    				if (temp[159] != Double.MAX_VALUE) {
    					dataWareHouse.setData160(canValue.get(159));
    				}
    				if (temp[160] != Double.MAX_VALUE) {
    					dataWareHouse.setData161(canValue.get(160));
    				}
    				if (temp[161] != Double.MAX_VALUE) {
    					dataWareHouse.setData162(canValue.get(161));
    				}
    				if (temp[162] != Double.MAX_VALUE) {
    					dataWareHouse.setData163(canValue.get(162));
    				}
    				if (temp[163] != Double.MAX_VALUE) {
    					dataWareHouse.setData164(canValue.get(163));
    				}
    				if (temp[164] != Double.MAX_VALUE) {
    					dataWareHouse.setData165(canValue.get(164));
    				}
    				if (temp[165] != Double.MAX_VALUE) {
    					dataWareHouse.setData166(canValue.get(165));
    				}
    				if (temp[166] != Double.MAX_VALUE) {
    					dataWareHouse.setData167(canValue.get(166));
    				}
    				if (temp[167] != Double.MAX_VALUE) {
    					dataWareHouse.setData168(canValue.get(167));
    				}
    				if (temp[168] != Double.MAX_VALUE) {
    					dataWareHouse.setData169(canValue.get(168));
    				}
    				if (temp[169] != Double.MAX_VALUE) {
    					dataWareHouse.setData170(canValue.get(169));
    				}
    				if (temp[170] != Double.MAX_VALUE) {
    					dataWareHouse.setData171(canValue.get(170));
    				}
    				if (temp[171] != Double.MAX_VALUE) {
    					dataWareHouse.setData172(canValue.get(171));
    				}
    				if (temp[172] != Double.MAX_VALUE) {
    					dataWareHouse.setData173(canValue.get(172));
    				}
    				if (temp[173] != Double.MAX_VALUE) {
    					dataWareHouse.setData174(canValue.get(173));
    				}
    				if (temp[174] != Double.MAX_VALUE) {
    					dataWareHouse.setData175(canValue.get(174));
    				}
    				if (temp[175] != Double.MAX_VALUE) {
    					dataWareHouse.setData176(canValue.get(175));
    				}
    				if (temp[176] != Double.MAX_VALUE) {
    					dataWareHouse.setData177(canValue.get(176));
    				}
    				if (temp[177] != Double.MAX_VALUE) {
    					dataWareHouse.setData178(canValue.get(177));
    				}
    				if (temp[178] != Double.MAX_VALUE) {
    					dataWareHouse.setData179(canValue.get(178));
    				}
    				if (temp[179] != Double.MAX_VALUE) {
    					dataWareHouse.setData180(canValue.get(179));
    				}
    				if (temp[180] != Double.MAX_VALUE) {
    					dataWareHouse.setData181(canValue.get(180));
    				}
    				if (temp[181] != Double.MAX_VALUE) {
    					dataWareHouse.setData182(canValue.get(181));
    				}
    				if (temp[182] != Double.MAX_VALUE) {
    					dataWareHouse.setData183(canValue.get(182));
    				}
    				if (temp[183] != Double.MAX_VALUE) {
    					dataWareHouse.setData184(canValue.get(183));
    				}
    				if (temp[184] != Double.MAX_VALUE) {
    					dataWareHouse.setData185(canValue.get(184));
    				}
    				if (temp[185] != Double.MAX_VALUE) {
    					dataWareHouse.setData186(canValue.get(185));
    				}
    				if (temp[186] != Double.MAX_VALUE) {
    					dataWareHouse.setData187(canValue.get(186));
    				}
    				if (temp[187] != Double.MAX_VALUE) {
    					dataWareHouse.setData188(canValue.get(187));
    				}
    				if (temp[188] != Double.MAX_VALUE) {
    					dataWareHouse.setData189(canValue.get(188));
    				}
    				if (temp[189] != Double.MAX_VALUE) {
    					dataWareHouse.setData190(canValue.get(189));
    				}
    				if (temp[190] != Double.MAX_VALUE) {
    					dataWareHouse.setData191(canValue.get(190));
    				}
    				if (temp[191] != Double.MAX_VALUE) {
    					dataWareHouse.setData192(canValue.get(191));
    				}
    				if (temp[192] != Double.MAX_VALUE) {
    					dataWareHouse.setData193(canValue.get(192));
    				}
    				if (temp[193] != Double.MAX_VALUE) {
    					dataWareHouse.setData194(canValue.get(193));
    				}
    				if (temp[194] != Double.MAX_VALUE) {
    					dataWareHouse.setData195(canValue.get(194));
    				}
    				if (temp[195] != Double.MAX_VALUE) {
    					dataWareHouse.setData196(canValue.get(195));
    				}
    				if (temp[196] != Double.MAX_VALUE) {
    					dataWareHouse.setData197(canValue.get(196));
    				}
    				if (temp[197] != Double.MAX_VALUE) {
    					dataWareHouse.setData198(canValue.get(197));
    				}
    				if (temp[198] != Double.MAX_VALUE) {
    					dataWareHouse.setData199(canValue.get(198));
    				}
    				if (temp[199] != Double.MAX_VALUE) {
    					dataWareHouse.setData200(canValue.get(199));
    				}
    				if (temp[200] != Double.MAX_VALUE) {
    					dataWareHouse.setData201(canValue.get(200));
    				}
    				if (temp[201] != Double.MAX_VALUE) {
    					dataWareHouse.setData202(canValue.get(201));
    				}
    				if (temp[202] != Double.MAX_VALUE) {
    					dataWareHouse.setData203(canValue.get(202));
    				}
    				if (temp[203] != Double.MAX_VALUE) {
    					dataWareHouse.setData204(canValue.get(203));
    				}
    				if (temp[204] != Double.MAX_VALUE) {
    					dataWareHouse.setData205(canValue.get(204));
    				}
    				if (temp[205] != Double.MAX_VALUE) {
    					dataWareHouse.setData206(canValue.get(205));
    				}
    				if (temp[206] != Double.MAX_VALUE) {
    					dataWareHouse.setData207(canValue.get(206));
    				}
    				if (temp[207] != Double.MAX_VALUE) {
    					dataWareHouse.setData208(canValue.get(207));
    				}
    				if (temp[208] != Double.MAX_VALUE) {
    					dataWareHouse.setData209(canValue.get(208));
    				}
    				if (temp[209] != Double.MAX_VALUE) {
    					dataWareHouse.setData210(canValue.get(209));
    				}
    				if (temp[210] != Double.MAX_VALUE) {
    					dataWareHouse.setData211(canValue.get(210));
    				}
    				if (temp[211] != Double.MAX_VALUE) {
    					dataWareHouse.setData212(canValue.get(211));
    				}
    				if (temp[212] != Double.MAX_VALUE) {
    					dataWareHouse.setData213(canValue.get(212));
    				}
    				if (temp[213] != Double.MAX_VALUE) {
    					dataWareHouse.setData214(canValue.get(213));
    				}
    				if (temp[214] != Double.MAX_VALUE) {
    					dataWareHouse.setData215(canValue.get(214));
    				}
    				if (temp[215] != Double.MAX_VALUE) {
    					dataWareHouse.setData216(canValue.get(215));
    				}
    				if (temp[216] != Double.MAX_VALUE) {
    					dataWareHouse.setData217(canValue.get(216));
    				}
    				if (temp[217] != Double.MAX_VALUE) {
    					dataWareHouse.setData218(canValue.get(217));
    				}
    				if (temp[218] != Double.MAX_VALUE) {
    					dataWareHouse.setData219(canValue.get(218));
    				}
    				if (temp[219] != Double.MAX_VALUE) {
    					dataWareHouse.setData220(canValue.get(219));
    				}
    				if (temp[220] != Double.MAX_VALUE) {
    					dataWareHouse.setData221(canValue.get(220));
    				}
    				if (temp[221] != Double.MAX_VALUE) {
    					dataWareHouse.setData222(canValue.get(221));
    				}
    				if (temp[222] != Double.MAX_VALUE) {
    					dataWareHouse.setData223(canValue.get(222));
    				}
    				if (temp[223] != Double.MAX_VALUE) {
    					dataWareHouse.setData224(canValue.get(223));
    				}
    				if (temp[224] != Double.MAX_VALUE) {
    					dataWareHouse.setData225(canValue.get(224));
    				}
    				if (temp[225] != Double.MAX_VALUE) {
    					dataWareHouse.setData226(canValue.get(225));
    				}
    				if (temp[226] != Double.MAX_VALUE) {
    					dataWareHouse.setData227(canValue.get(226));
    				}
    				if (temp[227] != Double.MAX_VALUE) {
    					dataWareHouse.setData228(canValue.get(227));
    				}
    				if (temp[228] != Double.MAX_VALUE) {
    					dataWareHouse.setData229(canValue.get(228));
    				}
    				if (temp[229] != Double.MAX_VALUE) {
    					dataWareHouse.setData230(canValue.get(229));
    				}
    				if (temp[230] != Double.MAX_VALUE) {
    					dataWareHouse.setData231(canValue.get(230));
    				}
    				if (temp[231] != Double.MAX_VALUE) {
    					dataWareHouse.setData232(canValue.get(231));
    				}
    				if (temp[232] != Double.MAX_VALUE) {
    					dataWareHouse.setData233(canValue.get(232));
    				}
    				if (temp[233] != Double.MAX_VALUE) {
    					dataWareHouse.setData234(canValue.get(233));
    				}
    				if (temp[234] != Double.MAX_VALUE) {
    					dataWareHouse.setData235(canValue.get(234));
    				}
    				if (temp[235] != Double.MAX_VALUE) {
    					dataWareHouse.setData236(canValue.get(235));
    				}
    				if (temp[236] != Double.MAX_VALUE) {
    					dataWareHouse.setData237(canValue.get(236));
    				}
    				if (temp[237] != Double.MAX_VALUE) {
    					dataWareHouse.setData238(canValue.get(237));
    				}
    				if (temp[238] != Double.MAX_VALUE) {
    					dataWareHouse.setData239(canValue.get(238));
    				}
    				if (temp[239] != Double.MAX_VALUE) {
    					dataWareHouse.setData240(canValue.get(239));
    				}
    				if (temp[240] != Double.MAX_VALUE) {
    					dataWareHouse.setData241(canValue.get(240));
    				}
    				if (temp[241] != Double.MAX_VALUE) {
    					dataWareHouse.setData242(canValue.get(241));
    				}
    				if (temp[242] != Double.MAX_VALUE) {
    					dataWareHouse.setData243(canValue.get(242));
    				}
    				if (temp[243] != Double.MAX_VALUE) {
    					dataWareHouse.setData244(canValue.get(243));
    				}
    				if (temp[244] != Double.MAX_VALUE) {
    					dataWareHouse.setData245(canValue.get(244));
    				}
    				if (temp[245] != Double.MAX_VALUE) {
    					dataWareHouse.setData246(canValue.get(245));
    				}
    				if (temp[246] != Double.MAX_VALUE) {
    					dataWareHouse.setData247(canValue.get(246));
    				}
    				if (temp[247] != Double.MAX_VALUE) {
    					dataWareHouse.setData248(canValue.get(247));
    				}
    				if (temp[248] != Double.MAX_VALUE) {
    					dataWareHouse.setData249(canValue.get(248));
    				}
    				if (temp[249] != Double.MAX_VALUE) {
    					dataWareHouse.setData250(canValue.get(249));
    				}
    				if (temp[250] != Double.MAX_VALUE) {
    					dataWareHouse.setData251(canValue.get(250));
    				}
    				if (temp[251] != Double.MAX_VALUE) {
    					dataWareHouse.setData252(canValue.get(251));
    				}
    				if (temp[252] != Double.MAX_VALUE) {
    					dataWareHouse.setData253(canValue.get(252));
    				}
    				if (temp[253] != Double.MAX_VALUE) {
    					dataWareHouse.setData254(canValue.get(253));
    				}
    				if (temp[254] != Double.MAX_VALUE) {
    					dataWareHouse.setData255(canValue.get(254));
    				}
    				if (temp[255] != Double.MAX_VALUE) {
    					dataWareHouse.setData256(canValue.get(255));
    				}
    				if (temp[256] != Double.MAX_VALUE) {
    					dataWareHouse.setData257(canValue.get(256));
    				}
    				if (temp[257] != Double.MAX_VALUE) {
    					dataWareHouse.setData258(canValue.get(257));
    				}
    				if (temp[258] != Double.MAX_VALUE) {
    					dataWareHouse.setData259(canValue.get(258));
    				}
    				if (temp[259] != Double.MAX_VALUE) {
    					dataWareHouse.setData260(canValue.get(259));
    				}
    				if (temp[260] != Double.MAX_VALUE) {
    					dataWareHouse.setData261(canValue.get(260));
    				}
    				if (temp[261] != Double.MAX_VALUE) {
    					dataWareHouse.setData262(canValue.get(261));
    				}
    				if (temp[262] != Double.MAX_VALUE) {
    					dataWareHouse.setData263(canValue.get(262));
    				}
    				if (temp[263] != Double.MAX_VALUE) {
    					dataWareHouse.setData264(canValue.get(263));
    				}
    				if (temp[264] != Double.MAX_VALUE) {
    					dataWareHouse.setData265(canValue.get(264));
    				}
    				if (temp[265] != Double.MAX_VALUE) {
    					dataWareHouse.setData266(canValue.get(265));
    				}
    				if (temp[266] != Double.MAX_VALUE) {
    					dataWareHouse.setData267(canValue.get(266));
    				}
    				if (temp[267] != Double.MAX_VALUE) {
    					dataWareHouse.setData268(canValue.get(267));
    				}
    				if (temp[268] != Double.MAX_VALUE) {
    					dataWareHouse.setData269(canValue.get(268));
    				}
    				if (temp[269] != Double.MAX_VALUE) {
    					dataWareHouse.setData270(canValue.get(269));
    				}
    				if (temp[270] != Double.MAX_VALUE) {
    					dataWareHouse.setData271(canValue.get(270));
    				}
    				if (temp[271] != Double.MAX_VALUE) {
    					dataWareHouse.setData272(canValue.get(271));
    				}
    				if (temp[272] != Double.MAX_VALUE) {
    					dataWareHouse.setData273(canValue.get(272));
    				}
    				if (temp[273] != Double.MAX_VALUE) {
    					dataWareHouse.setData274(canValue.get(273));
    				}
    				if (temp[274] != Double.MAX_VALUE) {
    					dataWareHouse.setData275(canValue.get(274));
    				}
    				if (temp[275] != Double.MAX_VALUE) {
    					dataWareHouse.setData276(canValue.get(275));
    				}
    				if (temp[276] != Double.MAX_VALUE) {
    					dataWareHouse.setData277(canValue.get(276));
    				}
    				if (temp[277] != Double.MAX_VALUE) {
    					dataWareHouse.setData278(canValue.get(277));
    				}
    				if (temp[278] != Double.MAX_VALUE) {
    					dataWareHouse.setData279(canValue.get(278));
    				}
    				if (temp[279] != Double.MAX_VALUE) {
    					dataWareHouse.setData280(canValue.get(279));
    				}
    				if (temp[280] != Double.MAX_VALUE) {
    					dataWareHouse.setData281(canValue.get(280));
    				}
    				if (temp[281] != Double.MAX_VALUE) {
    					dataWareHouse.setData282(canValue.get(281));
    				}
    				if (temp[282] != Double.MAX_VALUE) {
    					dataWareHouse.setData283(canValue.get(282));
    				}
    				if (temp[283] != Double.MAX_VALUE) {
    					dataWareHouse.setData284(canValue.get(283));
    				}
    				if (temp[284] != Double.MAX_VALUE) {
    					dataWareHouse.setData285(canValue.get(284));
    				}
    				if (temp[285] != Double.MAX_VALUE) {
    					dataWareHouse.setData286(canValue.get(285));
    				}
    				if (temp[286] != Double.MAX_VALUE) {
    					dataWareHouse.setData287(canValue.get(286));
    				}
    				if (temp[287] != Double.MAX_VALUE) {
    					dataWareHouse.setData288(canValue.get(287));
    				}
    				if (temp[288] != Double.MAX_VALUE) {
    					dataWareHouse.setData289(canValue.get(288));
    				}
    				if (temp[289] != Double.MAX_VALUE) {
    					dataWareHouse.setData290(canValue.get(289));
    				}
    				if (temp[290] != Double.MAX_VALUE) {
    					dataWareHouse.setData291(canValue.get(290));
    				}
    				if (temp[291] != Double.MAX_VALUE) {
    					dataWareHouse.setData292(canValue.get(291));
    				}
    				if (temp[292] != Double.MAX_VALUE) {
    					dataWareHouse.setData293(canValue.get(292));
    				}
    				if (temp[293] != Double.MAX_VALUE) {
    					dataWareHouse.setData294(canValue.get(293));
    				}
    				if (temp[294] != Double.MAX_VALUE) {
    					dataWareHouse.setData295(canValue.get(294));
    				}
    				if (temp[295] != Double.MAX_VALUE) {
    					dataWareHouse.setData296(canValue.get(295));
    				}
    				if (temp[296] != Double.MAX_VALUE) {
    					dataWareHouse.setData297(canValue.get(296));
    				}
    				if (temp[297] != Double.MAX_VALUE) {
    					dataWareHouse.setData298(canValue.get(297));
    				}
    				if (temp[298] != Double.MAX_VALUE) {
    					dataWareHouse.setData299(canValue.get(298));
    				}
    				if (temp[299] != Double.MAX_VALUE) {
    					dataWareHouse.setData300(canValue.get(299));
    				}
    				if (temp[300] != Double.MAX_VALUE) {
    					dataWareHouse.setData301(canValue.get(300));
    				}
    				if (temp[301] != Double.MAX_VALUE) {
    					dataWareHouse.setData302(canValue.get(301));
    				}
    				if (temp[302] != Double.MAX_VALUE) {
    					dataWareHouse.setData303(canValue.get(302));
    				}
    				if (temp[303] != Double.MAX_VALUE) {
    					dataWareHouse.setData304(canValue.get(303));
    				}
    				if (temp[304] != Double.MAX_VALUE) {
    					dataWareHouse.setData305(canValue.get(304));
    				}
    				if (temp[305] != Double.MAX_VALUE) {
    					dataWareHouse.setData306(canValue.get(305));
    				}
    				if (temp[306] != Double.MAX_VALUE) {
    					dataWareHouse.setData307(canValue.get(306));
    				}
    				if (temp[307] != Double.MAX_VALUE) {
    					dataWareHouse.setData308(canValue.get(307));
    				}
    				if (temp[308] != Double.MAX_VALUE) {
    					dataWareHouse.setData309(canValue.get(308));
    				}
    				if (temp[309] != Double.MAX_VALUE) {
    					dataWareHouse.setData310(canValue.get(309));
    				}
    				if (temp[310] != Double.MAX_VALUE) {
    					dataWareHouse.setData311(canValue.get(310));
    				}
    				if (temp[311] != Double.MAX_VALUE) {
    					dataWareHouse.setData312(canValue.get(311));
    				}
    				if (temp[312] != Double.MAX_VALUE) {
    					dataWareHouse.setData313(canValue.get(312));
    				}
    				if (temp[313] != Double.MAX_VALUE) {
    					dataWareHouse.setData314(canValue.get(313));
    				}
    				if (temp[314] != Double.MAX_VALUE) {
    					dataWareHouse.setData315(canValue.get(314));
    				}
    				if (temp[315] != Double.MAX_VALUE) {
    					dataWareHouse.setData316(canValue.get(315));
    				}
    				if (temp[316] != Double.MAX_VALUE) {
    					dataWareHouse.setData317(canValue.get(316));
    				}
    				if (temp[317] != Double.MAX_VALUE) {
    					dataWareHouse.setData318(canValue.get(317));
    				}
    				if (temp[318] != Double.MAX_VALUE) {
    					dataWareHouse.setData319(canValue.get(318));
    				}
    				if (temp[319] != Double.MAX_VALUE) {
    					dataWareHouse.setData320(canValue.get(319));
    				}
    				if (temp[320] != Double.MAX_VALUE) {
    					dataWareHouse.setData321(canValue.get(320));
    				}
    				if (temp[321] != Double.MAX_VALUE) {
    					dataWareHouse.setData322(canValue.get(321));
    				}
    				if (temp[322] != Double.MAX_VALUE) {
    					dataWareHouse.setData323(canValue.get(322));
    				}
    				if (temp[323] != Double.MAX_VALUE) {
    					dataWareHouse.setData324(canValue.get(323));
    				}
    				if (temp[324] != Double.MAX_VALUE) {
    					dataWareHouse.setData325(canValue.get(324));
    				}
    				if (temp[325] != Double.MAX_VALUE) {
    					dataWareHouse.setData326(canValue.get(325));
    				}
    				if (temp[326] != Double.MAX_VALUE) {
    					dataWareHouse.setData327(canValue.get(326));
    				}
    				if (temp[327] != Double.MAX_VALUE) {
    					dataWareHouse.setData328(canValue.get(327));
    				}
    				if (temp[328] != Double.MAX_VALUE) {
    					dataWareHouse.setData329(canValue.get(328));
    				}
    				if (temp[329] != Double.MAX_VALUE) {
    					dataWareHouse.setData330(canValue.get(329));
    				}
    				if (temp[330] != Double.MAX_VALUE) {
    					dataWareHouse.setData331(canValue.get(330));
    				}
    				if (temp[331] != Double.MAX_VALUE) {
    					dataWareHouse.setData332(canValue.get(331));
    				}
    				if (temp[332] != Double.MAX_VALUE) {
    					dataWareHouse.setData333(canValue.get(332));
    				}
    				if (temp[333] != Double.MAX_VALUE) {
    					dataWareHouse.setData334(canValue.get(333));
    				}
    				if (temp[334] != Double.MAX_VALUE) {
    					dataWareHouse.setData335(canValue.get(334));
    				}
    				if (temp[335] != Double.MAX_VALUE) {
    					dataWareHouse.setData336(canValue.get(335));
    				}
    				if (temp[336] != Double.MAX_VALUE) {
    					dataWareHouse.setData337(canValue.get(336));
    				}
    				if (temp[337] != Double.MAX_VALUE) {
    					dataWareHouse.setData338(canValue.get(337));
    				}
    				if (temp[338] != Double.MAX_VALUE) {
    					dataWareHouse.setData339(canValue.get(338));
    				}
    				if (temp[339] != Double.MAX_VALUE) {
    					dataWareHouse.setData340(canValue.get(339));
    				}
    				if (temp[340] != Double.MAX_VALUE) {
    					dataWareHouse.setData341(canValue.get(340));
    				}
    				if (temp[341] != Double.MAX_VALUE) {
    					dataWareHouse.setData342(canValue.get(341));
    				}
    				if (temp[342] != Double.MAX_VALUE) {
    					dataWareHouse.setData343(canValue.get(342));
    				}
    				if (temp[343] != Double.MAX_VALUE) {
    					dataWareHouse.setData344(canValue.get(343));
    				}
    				if (temp[344] != Double.MAX_VALUE) {
    					dataWareHouse.setData345(canValue.get(344));
    				}
    				if (temp[345] != Double.MAX_VALUE) {
    					dataWareHouse.setData346(canValue.get(345));
    				}
    				if (temp[346] != Double.MAX_VALUE) {
    					dataWareHouse.setData347(canValue.get(346));
    				}
    				if (temp[347] != Double.MAX_VALUE) {
    					dataWareHouse.setData348(canValue.get(347));
    				}
    				if (temp[348] != Double.MAX_VALUE) {
    					dataWareHouse.setData349(canValue.get(348));
    				}
    				if (temp[349] != Double.MAX_VALUE) {
    					dataWareHouse.setData350(canValue.get(349));
    				}
    				if (temp[350] != Double.MAX_VALUE) {
    					dataWareHouse.setData351(canValue.get(350));
    				}
    				if (temp[351] != Double.MAX_VALUE) {
    					dataWareHouse.setData352(canValue.get(351));
    				}
    				if (temp[352] != Double.MAX_VALUE) {
    					dataWareHouse.setData353(canValue.get(352));
    				}
    				if (temp[353] != Double.MAX_VALUE) {
    					dataWareHouse.setData354(canValue.get(353));
    				}
    				if (temp[354] != Double.MAX_VALUE) {
    					dataWareHouse.setData355(canValue.get(354));
    				}
    				if (temp[355] != Double.MAX_VALUE) {
    					dataWareHouse.setData356(canValue.get(355));
    				}
    				if (temp[356] != Double.MAX_VALUE) {
    					dataWareHouse.setData357(canValue.get(356));
    				}
    				if (temp[357] != Double.MAX_VALUE) {
    					dataWareHouse.setData358(canValue.get(357));
    				}
    				if (temp[358] != Double.MAX_VALUE) {
    					dataWareHouse.setData359(canValue.get(358));
    				}
    				if (temp[359] != Double.MAX_VALUE) {
    					dataWareHouse.setData360(canValue.get(359));
    				}
    				if (temp[360] != Double.MAX_VALUE) {
    					dataWareHouse.setData361(canValue.get(360));
    				}
    				if (temp[361] != Double.MAX_VALUE) {
    					dataWareHouse.setData362(canValue.get(361));
    				}
    				if (temp[362] != Double.MAX_VALUE) {
    					dataWareHouse.setData363(canValue.get(362));
    				}
    				if (temp[363] != Double.MAX_VALUE) {
    					dataWareHouse.setData364(canValue.get(363));
    				}
    				if (temp[364] != Double.MAX_VALUE) {
    					dataWareHouse.setData365(canValue.get(364));
    				}
    				if (temp[365] != Double.MAX_VALUE) {
    					dataWareHouse.setData366(canValue.get(365));
    				}
    				if (temp[366] != Double.MAX_VALUE) {
    					dataWareHouse.setData367(canValue.get(366));
    				}
    				if (temp[367] != Double.MAX_VALUE) {
    					dataWareHouse.setData368(canValue.get(367));
    				}
    				if (temp[368] != Double.MAX_VALUE) {
    					dataWareHouse.setData369(canValue.get(368));
    				}
    				if (temp[369] != Double.MAX_VALUE) {
    					dataWareHouse.setData370(canValue.get(369));
    				}
    				if (temp[370] != Double.MAX_VALUE) {
    					dataWareHouse.setData371(canValue.get(370));
    				}
    				if (temp[371] != Double.MAX_VALUE) {
    					dataWareHouse.setData372(canValue.get(371));
    				}
    				if (temp[372] != Double.MAX_VALUE) {
    					dataWareHouse.setData373(canValue.get(372));
    				}
    				if (temp[373] != Double.MAX_VALUE) {
    					dataWareHouse.setData374(canValue.get(373));
    				}
    				if (temp[374] != Double.MAX_VALUE) {
    					dataWareHouse.setData375(canValue.get(374));
    				}
    				if (temp[375] != Double.MAX_VALUE) {
    					dataWareHouse.setData376(canValue.get(375));
    				}
    				if (temp[376] != Double.MAX_VALUE) {
    					dataWareHouse.setData377(canValue.get(376));
    				}
    				if (temp[377] != Double.MAX_VALUE) {
    					dataWareHouse.setData378(canValue.get(377));
    				}
    				if (temp[378] != Double.MAX_VALUE) {
    					dataWareHouse.setData379(canValue.get(378));
    				}
    				if (temp[379] != Double.MAX_VALUE) {
    					dataWareHouse.setData380(canValue.get(379));
    				}
    				if (temp[380] != Double.MAX_VALUE) {
    					dataWareHouse.setData381(canValue.get(380));
    				}
    				if (temp[381] != Double.MAX_VALUE) {
    					dataWareHouse.setData382(canValue.get(381));
    				}
    				if (temp[382] != Double.MAX_VALUE) {
    					dataWareHouse.setData383(canValue.get(382));
    				}
    				if (temp[383] != Double.MAX_VALUE) {
    					dataWareHouse.setData384(canValue.get(383));
    				}
    				if (temp[384] != Double.MAX_VALUE) {
    					dataWareHouse.setData385(canValue.get(384));
    				}
    				if (temp[385] != Double.MAX_VALUE) {
    					dataWareHouse.setData386(canValue.get(385));
    				}
    				if (temp[386] != Double.MAX_VALUE) {
    					dataWareHouse.setData387(canValue.get(386));
    				}
    				if (temp[387] != Double.MAX_VALUE) {
    					dataWareHouse.setData388(canValue.get(387));
    				}
    				if (temp[388] != Double.MAX_VALUE) {
    					dataWareHouse.setData389(canValue.get(388));
    				}
    				if (temp[389] != Double.MAX_VALUE) {
    					dataWareHouse.setData390(canValue.get(389));
    				}
    				if (temp[390] != Double.MAX_VALUE) {
    					dataWareHouse.setData391(canValue.get(390));
    				}
    				if (temp[391] != Double.MAX_VALUE) {
    					dataWareHouse.setData392(canValue.get(391));
    				}
    				if (temp[392] != Double.MAX_VALUE) {
    					dataWareHouse.setData393(canValue.get(392));
    				}
    				if (temp[393] != Double.MAX_VALUE) {
    					dataWareHouse.setData394(canValue.get(393));
    				}
    				if (temp[394] != Double.MAX_VALUE) {
    					dataWareHouse.setData395(canValue.get(394));
    				}
    				if (temp[395] != Double.MAX_VALUE) {
    					dataWareHouse.setData396(canValue.get(395));
    				}
    				if (temp[396] != Double.MAX_VALUE) {
    					dataWareHouse.setData397(canValue.get(396));
    				}
    				if (temp[397] != Double.MAX_VALUE) {
    					dataWareHouse.setData398(canValue.get(397));
    				}
    				if (temp[398] != Double.MAX_VALUE) {
    					dataWareHouse.setData399(canValue.get(398));
    				}
    				if (temp[399] != Double.MAX_VALUE) {
    					dataWareHouse.setData400(canValue.get(399));
    				}
    				if (temp[400] != Double.MAX_VALUE) {
    					dataWareHouse.setData401(canValue.get(400));
    				}
    				if (temp[401] != Double.MAX_VALUE) {
    					dataWareHouse.setData402(canValue.get(401));
    				}
    				if (temp[402] != Double.MAX_VALUE) {
    					dataWareHouse.setData403(canValue.get(402));
    				}
    				if (temp[403] != Double.MAX_VALUE) {
    					dataWareHouse.setData404(canValue.get(403));
    				}
    				if (temp[404] != Double.MAX_VALUE) {
    					dataWareHouse.setData405(canValue.get(404));
    				}
    				if (temp[405] != Double.MAX_VALUE) {
    					dataWareHouse.setData406(canValue.get(405));
    				}
    				if (temp[406] != Double.MAX_VALUE) {
    					dataWareHouse.setData407(canValue.get(406));
    				}
    				if (temp[407] != Double.MAX_VALUE) {
    					dataWareHouse.setData408(canValue.get(407));
    				}
    				if (temp[408] != Double.MAX_VALUE) {
    					dataWareHouse.setData409(canValue.get(408));
    				}
    				if (temp[409] != Double.MAX_VALUE) {
    					dataWareHouse.setData410(canValue.get(409));
    				}
    				if (temp[410] != Double.MAX_VALUE) {
    					dataWareHouse.setData411(canValue.get(410));
    				}
    				if (temp[411] != Double.MAX_VALUE) {
    					dataWareHouse.setData412(canValue.get(411));
    				}
    				if (temp[412] != Double.MAX_VALUE) {
    					dataWareHouse.setData413(canValue.get(412));
    				}
    				if (temp[413] != Double.MAX_VALUE) {
    					dataWareHouse.setData414(canValue.get(413));
    				}
    				if (temp[414] != Double.MAX_VALUE) {
    					dataWareHouse.setData415(canValue.get(414));
    				}
    				if (temp[415] != Double.MAX_VALUE) {
    					dataWareHouse.setData416(canValue.get(415));
    				}
    				if (temp[416] != Double.MAX_VALUE) {
    					dataWareHouse.setData417(canValue.get(416));
    				}
    				if (temp[417] != Double.MAX_VALUE) {
    					dataWareHouse.setData418(canValue.get(417));
    				}
    				if (temp[418] != Double.MAX_VALUE) {
    					dataWareHouse.setData419(canValue.get(418));
    				}
    				if (temp[419] != Double.MAX_VALUE) {
    					dataWareHouse.setData420(canValue.get(419));
    				}
    				if (temp[420] != Double.MAX_VALUE) {
    					dataWareHouse.setData421(canValue.get(420));
    				}
    				if (temp[421] != Double.MAX_VALUE) {
    					dataWareHouse.setData422(canValue.get(421));
    				}
    				if (temp[422] != Double.MAX_VALUE) {
    					dataWareHouse.setData423(canValue.get(422));
    				}
    				if (temp[423] != Double.MAX_VALUE) {
    					dataWareHouse.setData424(canValue.get(423));
    				}
    				if (temp[424] != Double.MAX_VALUE) {
    					dataWareHouse.setData425(canValue.get(424));
    				}
    				if (temp[425] != Double.MAX_VALUE) {
    					dataWareHouse.setData426(canValue.get(425));
    				}
    				if (temp[426] != Double.MAX_VALUE) {
    					dataWareHouse.setData427(canValue.get(426));
    				}
    				if (temp[427] != Double.MAX_VALUE) {
    					dataWareHouse.setData428(canValue.get(427));
    				}
    				if (temp[428] != Double.MAX_VALUE) {
    					dataWareHouse.setData429(canValue.get(428));
    				}
    				if (temp[429] != Double.MAX_VALUE) {
    					dataWareHouse.setData430(canValue.get(429));
    				}
    				if (temp[430] != Double.MAX_VALUE) {
    					dataWareHouse.setData431(canValue.get(430));
    				}
    				if (temp[431] != Double.MAX_VALUE) {
    					dataWareHouse.setData432(canValue.get(431));
    				}
    				if (temp[432] != Double.MAX_VALUE) {
    					dataWareHouse.setData433(canValue.get(432));
    				}
    				if (temp[433] != Double.MAX_VALUE) {
    					dataWareHouse.setData434(canValue.get(433));
    				}
    				if (temp[434] != Double.MAX_VALUE) {
    					dataWareHouse.setData435(canValue.get(434));
    				}
    				if (temp[435] != Double.MAX_VALUE) {
    					dataWareHouse.setData436(canValue.get(435));
    				}
    				if (temp[436] != Double.MAX_VALUE) {
    					dataWareHouse.setData437(canValue.get(436));
    				}
    				if (temp[437] != Double.MAX_VALUE) {
    					dataWareHouse.setData438(canValue.get(437));
    				}
    				if (temp[438] != Double.MAX_VALUE) {
    					dataWareHouse.setData439(canValue.get(438));
    				}
    				if (temp[439] != Double.MAX_VALUE) {
    					dataWareHouse.setData440(canValue.get(439));
    				}
    				if (temp[440] != Double.MAX_VALUE) {
    					dataWareHouse.setData441(canValue.get(440));
    				}
    				if (temp[441] != Double.MAX_VALUE) {
    					dataWareHouse.setData442(canValue.get(441));
    				}
    				if (temp[442] != Double.MAX_VALUE) {
    					dataWareHouse.setData443(canValue.get(442));
    				}
    				if (temp[443] != Double.MAX_VALUE) {
    					dataWareHouse.setData444(canValue.get(443));
    				}
    				if (temp[444] != Double.MAX_VALUE) {
    					dataWareHouse.setData445(canValue.get(444));
    				}
    				if (temp[445] != Double.MAX_VALUE) {
    					dataWareHouse.setData446(canValue.get(445));
    				}
    				if (temp[446] != Double.MAX_VALUE) {
    					dataWareHouse.setData447(canValue.get(446));
    				}
    				if (temp[447] != Double.MAX_VALUE) {
    					dataWareHouse.setData448(canValue.get(447));
    				}
    				if (temp[448] != Double.MAX_VALUE) {
    					dataWareHouse.setData449(canValue.get(448));
    				}
    				if (temp[449] != Double.MAX_VALUE) {
    					dataWareHouse.setData450(canValue.get(449));
    				}
    				if (temp[450] != Double.MAX_VALUE) {
    					dataWareHouse.setData451(canValue.get(450));
    				}
    				if (temp[451] != Double.MAX_VALUE) {
    					dataWareHouse.setData452(canValue.get(451));
    				}
    				if (temp[452] != Double.MAX_VALUE) {
    					dataWareHouse.setData453(canValue.get(452));
    				}
    				if (temp[453] != Double.MAX_VALUE) {
    					dataWareHouse.setData454(canValue.get(453));
    				}
    				if (temp[454] != Double.MAX_VALUE) {
    					dataWareHouse.setData455(canValue.get(454));
    				}
    				if (temp[455] != Double.MAX_VALUE) {
    					dataWareHouse.setData456(canValue.get(455));
    				}
    				if (temp[456] != Double.MAX_VALUE) {
    					dataWareHouse.setData457(canValue.get(456));
    				}
    				if (temp[457] != Double.MAX_VALUE) {
    					dataWareHouse.setData458(canValue.get(457));
    				}
    				if (temp[458] != Double.MAX_VALUE) {
    					dataWareHouse.setData459(canValue.get(458));
    				}
    				if (temp[459] != Double.MAX_VALUE) {
    					dataWareHouse.setData460(canValue.get(459));
    				}
    				if (temp[460] != Double.MAX_VALUE) {
    					dataWareHouse.setData461(canValue.get(460));
    				}
    				if (temp[461] != Double.MAX_VALUE) {
    					dataWareHouse.setData462(canValue.get(461));
    				}
    				if (temp[462] != Double.MAX_VALUE) {
    					dataWareHouse.setData463(canValue.get(462));
    				}
    				if (temp[463] != Double.MAX_VALUE) {
    					dataWareHouse.setData464(canValue.get(463));
    				}
    				if (temp[464] != Double.MAX_VALUE) {
    					dataWareHouse.setData465(canValue.get(464));
    				}
    				if (temp[465] != Double.MAX_VALUE) {
    					dataWareHouse.setData466(canValue.get(465));
    				}
    				if (temp[466] != Double.MAX_VALUE) {
    					dataWareHouse.setData467(canValue.get(466));
    				}
    				if (temp[467] != Double.MAX_VALUE) {
    					dataWareHouse.setData468(canValue.get(467));
    				}
    				if (temp[468] != Double.MAX_VALUE) {
    					dataWareHouse.setData469(canValue.get(468));
    				}
    				if (temp[469] != Double.MAX_VALUE) {
    					dataWareHouse.setData470(canValue.get(469));
    				}
    				if (temp[470] != Double.MAX_VALUE) {
    					dataWareHouse.setData471(canValue.get(470));
    				}
    				if (temp[471] != Double.MAX_VALUE) {
    					dataWareHouse.setData472(canValue.get(471));
    				}
    				if (temp[472] != Double.MAX_VALUE) {
    					dataWareHouse.setData473(canValue.get(472));
    				}
    				if (temp[473] != Double.MAX_VALUE) {
    					dataWareHouse.setData474(canValue.get(473));
    				}
    				if (temp[474] != Double.MAX_VALUE) {
    					dataWareHouse.setData475(canValue.get(474));
    				}
    				if (temp[475] != Double.MAX_VALUE) {
    					dataWareHouse.setData476(canValue.get(475));
    				}
    				if (temp[476] != Double.MAX_VALUE) {
    					dataWareHouse.setData477(canValue.get(476));
    				}
    				if (temp[477] != Double.MAX_VALUE) {
    					dataWareHouse.setData478(canValue.get(477));
    				}
    				if (temp[478] != Double.MAX_VALUE) {
    					dataWareHouse.setData479(canValue.get(478));
    				}
    				if (temp[479] != Double.MAX_VALUE) {
    					dataWareHouse.setData480(canValue.get(479));
    				}
    				if (temp[480] != Double.MAX_VALUE) {
    					dataWareHouse.setData481(canValue.get(480));
    				}
    				if (temp[481] != Double.MAX_VALUE) {
    					dataWareHouse.setData482(canValue.get(481));
    				}
    				if (temp[482] != Double.MAX_VALUE) {
    					dataWareHouse.setData483(canValue.get(482));
    				}
    				if (temp[483] != Double.MAX_VALUE) {
    					dataWareHouse.setData484(canValue.get(483));
    				}
    				if (temp[484] != Double.MAX_VALUE) {
    					dataWareHouse.setData485(canValue.get(484));
    				}
    				if (temp[485] != Double.MAX_VALUE) {
    					dataWareHouse.setData486(canValue.get(485));
    				}
    				if (temp[486] != Double.MAX_VALUE) {
    					dataWareHouse.setData487(canValue.get(486));
    				}
    				if (temp[487] != Double.MAX_VALUE) {
    					dataWareHouse.setData488(canValue.get(487));
    				}
    				if (temp[488] != Double.MAX_VALUE) {
    					dataWareHouse.setData489(canValue.get(488));
    				}
    				if (temp[489] != Double.MAX_VALUE) {
    					dataWareHouse.setData490(canValue.get(489));
    				}
    				if (temp[490] != Double.MAX_VALUE) {
    					dataWareHouse.setData491(canValue.get(490));
    				}
    				if (temp[491] != Double.MAX_VALUE) {
    					dataWareHouse.setData492(canValue.get(491));
    				}
    				if (temp[492] != Double.MAX_VALUE) {
    					dataWareHouse.setData493(canValue.get(492));
    				}
    				if (temp[493] != Double.MAX_VALUE) {
    					dataWareHouse.setData494(canValue.get(493));
    				}
    				if (temp[494] != Double.MAX_VALUE) {
    					dataWareHouse.setData495(canValue.get(494));
    				}
    				if (temp[495] != Double.MAX_VALUE) {
    					dataWareHouse.setData496(canValue.get(495));
    				}
    				if (temp[496] != Double.MAX_VALUE) {
    					dataWareHouse.setData497(canValue.get(496));
    				}
    				if (temp[497] != Double.MAX_VALUE) {
    					dataWareHouse.setData498(canValue.get(497));
    				}
    				if (temp[498] != Double.MAX_VALUE) {
    					dataWareHouse.setData499(canValue.get(498));
    				}
    				if (temp[499] != Double.MAX_VALUE) {
    					dataWareHouse.setData500(canValue.get(499));
    				}
    				if (temp[500] != Double.MAX_VALUE) {
    					dataWareHouse.setData501(canValue.get(500));
    				}
    				if (temp[501] != Double.MAX_VALUE) {
    					dataWareHouse.setData502(canValue.get(501));
    				}
    				if (temp[502] != Double.MAX_VALUE) {
    					dataWareHouse.setData503(canValue.get(502));
    				}
    				if (temp[503] != Double.MAX_VALUE) {
    					dataWareHouse.setData504(canValue.get(503));
    				}
    				if (temp[504] != Double.MAX_VALUE) {
    					dataWareHouse.setData505(canValue.get(504));
    				}
    				if (temp[505] != Double.MAX_VALUE) {
    					dataWareHouse.setData506(canValue.get(505));
    				}
    				if (temp[506] != Double.MAX_VALUE) {
    					dataWareHouse.setData507(canValue.get(506));
    				}
    				if (temp[507] != Double.MAX_VALUE) {
    					dataWareHouse.setData508(canValue.get(507));
    				}
    				if (temp[508] != Double.MAX_VALUE) {
    					dataWareHouse.setData509(canValue.get(508));
    				}
    				if (temp[509] != Double.MAX_VALUE) {
    					dataWareHouse.setData510(canValue.get(509));
    				}
    				if (temp[510] != Double.MAX_VALUE) {
    					dataWareHouse.setData511(canValue.get(510));
    				}
    				if (temp[511] != Double.MAX_VALUE) {
    					dataWareHouse.setData512(canValue.get(511));
    				}
    				if (temp[512] != Double.MAX_VALUE) {
    					dataWareHouse.setData513(canValue.get(512));
    				}
    				if (temp[513] != Double.MAX_VALUE) {
    					dataWareHouse.setData514(canValue.get(513));
    				}
    				if (temp[514] != Double.MAX_VALUE) {
    					dataWareHouse.setData515(canValue.get(514));
    				}
    				if (temp[515] != Double.MAX_VALUE) {
    					dataWareHouse.setData516(canValue.get(515));
    				}
    				if (temp[516] != Double.MAX_VALUE) {
    					dataWareHouse.setData517(canValue.get(516));
    				}
    				if (temp[517] != Double.MAX_VALUE) {
    					dataWareHouse.setData518(canValue.get(517));
    				}
    				if (temp[518] != Double.MAX_VALUE) {
    					dataWareHouse.setData519(canValue.get(518));
    				}
    				if (temp[519] != Double.MAX_VALUE) {
    					dataWareHouse.setData520(canValue.get(519));
    				}
    				if (temp[520] != Double.MAX_VALUE) {
    					dataWareHouse.setData521(canValue.get(520));
    				}
    				if (temp[521] != Double.MAX_VALUE) {
    					dataWareHouse.setData522(canValue.get(521));
    				}
    				if (temp[522] != Double.MAX_VALUE) {
    					dataWareHouse.setData523(canValue.get(522));
    				}
    				if (temp[523] != Double.MAX_VALUE) {
    					dataWareHouse.setData524(canValue.get(523));
    				}
    				if (temp[524] != Double.MAX_VALUE) {
    					dataWareHouse.setData525(canValue.get(524));
    				}
    				if (temp[525] != Double.MAX_VALUE) {
    					dataWareHouse.setData526(canValue.get(525));
    				}
    				if (temp[526] != Double.MAX_VALUE) {
    					dataWareHouse.setData527(canValue.get(526));
    				}
    				if (temp[527] != Double.MAX_VALUE) {
    					dataWareHouse.setData528(canValue.get(527));
    				}
    				if (temp[528] != Double.MAX_VALUE) {
    					dataWareHouse.setData529(canValue.get(528));
    				}
    				if (temp[529] != Double.MAX_VALUE) {
    					dataWareHouse.setData530(canValue.get(529));
    				}
    				if (temp[530] != Double.MAX_VALUE) {
    					dataWareHouse.setData531(canValue.get(530));
    				}
    				if (temp[531] != Double.MAX_VALUE) {
    					dataWareHouse.setData532(canValue.get(531));
    				}
    				if (temp[532] != Double.MAX_VALUE) {
    					dataWareHouse.setData533(canValue.get(532));
    				}
    				if (temp[533] != Double.MAX_VALUE) {
    					dataWareHouse.setData534(canValue.get(533));
    				}
    				if (temp[534] != Double.MAX_VALUE) {
    					dataWareHouse.setData535(canValue.get(534));
    				}
    				if (temp[535] != Double.MAX_VALUE) {
    					dataWareHouse.setData536(canValue.get(535));
    				}
    				if (temp[536] != Double.MAX_VALUE) {
    					dataWareHouse.setData537(canValue.get(536));
    				}
    				if (temp[537] != Double.MAX_VALUE) {
    					dataWareHouse.setData538(canValue.get(537));
    				}
    				if (temp[538] != Double.MAX_VALUE) {
    					dataWareHouse.setData539(canValue.get(538));
    				}
    				if (temp[539] != Double.MAX_VALUE) {
    					dataWareHouse.setData540(canValue.get(539));
    				}
    				if (temp[540] != Double.MAX_VALUE) {
    					dataWareHouse.setData541(canValue.get(540));
    				}
    				if (temp[541] != Double.MAX_VALUE) {
    					dataWareHouse.setData542(canValue.get(541));
    				}
    				if (temp[542] != Double.MAX_VALUE) {
    					dataWareHouse.setData543(canValue.get(542));
    				}
    				if (temp[543] != Double.MAX_VALUE) {
    					dataWareHouse.setData544(canValue.get(543));
    				}
    				if (temp[544] != Double.MAX_VALUE) {
    					dataWareHouse.setData545(canValue.get(544));
    				}
    				if (temp[545] != Double.MAX_VALUE) {
    					dataWareHouse.setData546(canValue.get(545));
    				}
    				if (temp[546] != Double.MAX_VALUE) {
    					dataWareHouse.setData547(canValue.get(546));
    				}
    				if (temp[547] != Double.MAX_VALUE) {
    					dataWareHouse.setData548(canValue.get(547));
    				}
    				if (temp[548] != Double.MAX_VALUE) {
    					dataWareHouse.setData549(canValue.get(548));
    				}
    				if (temp[549] != Double.MAX_VALUE) {
    					dataWareHouse.setData550(canValue.get(549));
    				}
    				if (temp[550] != Double.MAX_VALUE) {
    					dataWareHouse.setData551(canValue.get(550));
    				}
    				if (temp[551] != Double.MAX_VALUE) {
    					dataWareHouse.setData552(canValue.get(551));
    				}
    				if (temp[552] != Double.MAX_VALUE) {
    					dataWareHouse.setData553(canValue.get(552));
    				}
    				if (temp[553] != Double.MAX_VALUE) {
    					dataWareHouse.setData554(canValue.get(553));
    				}
    				if (temp[554] != Double.MAX_VALUE) {
    					dataWareHouse.setData555(canValue.get(554));
    				}
    				if (temp[555] != Double.MAX_VALUE) {
    					dataWareHouse.setData556(canValue.get(555));
    				}
    				if (temp[556] != Double.MAX_VALUE) {
    					dataWareHouse.setData557(canValue.get(556));
    				}
    				if (temp[557] != Double.MAX_VALUE) {
    					dataWareHouse.setData558(canValue.get(557));
    				}
    				if (temp[558] != Double.MAX_VALUE) {
    					dataWareHouse.setData559(canValue.get(558));
    				}
    				if (temp[559] != Double.MAX_VALUE) {
    					dataWareHouse.setData560(canValue.get(559));
    				}
    				if (temp[560] != Double.MAX_VALUE) {
    					dataWareHouse.setData561(canValue.get(560));
    				}
    				if (temp[561] != Double.MAX_VALUE) {
    					dataWareHouse.setData562(canValue.get(561));
    				}
    				if (temp[562] != Double.MAX_VALUE) {
    					dataWareHouse.setData563(canValue.get(562));
    				}
    				if (temp[563] != Double.MAX_VALUE) {
    					dataWareHouse.setData564(canValue.get(563));
    				}
    				if (temp[564] != Double.MAX_VALUE) {
    					dataWareHouse.setData565(canValue.get(564));
    				}
    				if (temp[565] != Double.MAX_VALUE) {
    					dataWareHouse.setData566(canValue.get(565));
    				}
    				if (temp[566] != Double.MAX_VALUE) {
    					dataWareHouse.setData567(canValue.get(566));
    				}
    				if (temp[567] != Double.MAX_VALUE) {
    					dataWareHouse.setData568(canValue.get(567));
    				}
    				if (temp[568] != Double.MAX_VALUE) {
    					dataWareHouse.setData569(canValue.get(568));
    				}
    				if (temp[569] != Double.MAX_VALUE) {
    					dataWareHouse.setData570(canValue.get(569));
    				}
    				if (temp[570] != Double.MAX_VALUE) {
    					dataWareHouse.setData571(canValue.get(570));
    				}
    				if (temp[571] != Double.MAX_VALUE) {
    					dataWareHouse.setData572(canValue.get(571));
    				}
    				if (temp[572] != Double.MAX_VALUE) {
    					dataWareHouse.setData573(canValue.get(572));
    				}
    				if (temp[573] != Double.MAX_VALUE) {
    					dataWareHouse.setData574(canValue.get(573));
    				}
    				if (temp[574] != Double.MAX_VALUE) {
    					dataWareHouse.setData575(canValue.get(574));
    				}
    				if (temp[575] != Double.MAX_VALUE) {
    					dataWareHouse.setData576(canValue.get(575));
    				}
    				if (temp[576] != Double.MAX_VALUE) {
    					dataWareHouse.setData577(canValue.get(576));
    				}
    				if (temp[577] != Double.MAX_VALUE) {
    					dataWareHouse.setData578(canValue.get(577));
    				}
    				if (temp[578] != Double.MAX_VALUE) {
    					dataWareHouse.setData579(canValue.get(578));
    				}
    				if (temp[579] != Double.MAX_VALUE) {
    					dataWareHouse.setData580(canValue.get(579));
    				}
    				if (temp[580] != Double.MAX_VALUE) {
    					dataWareHouse.setData581(canValue.get(580));
    				}
    				if (temp[581] != Double.MAX_VALUE) {
    					dataWareHouse.setData582(canValue.get(581));
    				}
    				if (temp[582] != Double.MAX_VALUE) {
    					dataWareHouse.setData583(canValue.get(582));
    				}
    				if (temp[583] != Double.MAX_VALUE) {
    					dataWareHouse.setData584(canValue.get(583));
    				}
    				if (temp[584] != Double.MAX_VALUE) {
    					dataWareHouse.setData585(canValue.get(584));
    				}
    				if (temp[585] != Double.MAX_VALUE) {
    					dataWareHouse.setData586(canValue.get(585));
    				}
    				if (temp[586] != Double.MAX_VALUE) {
    					dataWareHouse.setData587(canValue.get(586));
    				}
    				if (temp[587] != Double.MAX_VALUE) {
    					dataWareHouse.setData588(canValue.get(587));
    				}
    				if (temp[588] != Double.MAX_VALUE) {
    					dataWareHouse.setData589(canValue.get(588));
    				}
    				if (temp[589] != Double.MAX_VALUE) {
    					dataWareHouse.setData590(canValue.get(589));
    				}
    				if (temp[590] != Double.MAX_VALUE) {
    					dataWareHouse.setData591(canValue.get(590));
    				}
    				if (temp[591] != Double.MAX_VALUE) {
    					dataWareHouse.setData592(canValue.get(591));
    				}
    				if (temp[592] != Double.MAX_VALUE) {
    					dataWareHouse.setData593(canValue.get(592));
    				}
    				if (temp[593] != Double.MAX_VALUE) {
    					dataWareHouse.setData594(canValue.get(593));
    				}
    				if (temp[594] != Double.MAX_VALUE) {
    					dataWareHouse.setData595(canValue.get(594));
    				}
    				if (temp[595] != Double.MAX_VALUE) {
    					dataWareHouse.setData596(canValue.get(595));
    				}
    				if (temp[596] != Double.MAX_VALUE) {
    					dataWareHouse.setData597(canValue.get(596));
    				}
    				if (temp[597] != Double.MAX_VALUE) {
    					dataWareHouse.setData598(canValue.get(597));
    				}
    				if (temp[598] != Double.MAX_VALUE) {
    					dataWareHouse.setData599(canValue.get(598));
    				}
    				if (temp[599] != Double.MAX_VALUE) {
    					dataWareHouse.setData600(canValue.get(599));
    				}
    				if (temp[600] != Double.MAX_VALUE) {
    					dataWareHouse.setData601(canValue.get(600));
    				}
    				if (temp[601] != Double.MAX_VALUE) {
    					dataWareHouse.setData602(canValue.get(601));
    				}
    				if (temp[602] != Double.MAX_VALUE) {
    					dataWareHouse.setData603(canValue.get(602));
    				}
    				if (temp[603] != Double.MAX_VALUE) {
    					dataWareHouse.setData604(canValue.get(603));
    				}
    				if (temp[604] != Double.MAX_VALUE) {
    					dataWareHouse.setData605(canValue.get(604));
    				}
    				if (temp[605] != Double.MAX_VALUE) {
    					dataWareHouse.setData606(canValue.get(605));
    				}
    				if (temp[606] != Double.MAX_VALUE) {
    					dataWareHouse.setData607(canValue.get(606));
    				}
    				if (temp[607] != Double.MAX_VALUE) {
    					dataWareHouse.setData608(canValue.get(607));
    				}
    				if (temp[608] != Double.MAX_VALUE) {
    					dataWareHouse.setData609(canValue.get(608));
    				}
    				if (temp[609] != Double.MAX_VALUE) {
    					dataWareHouse.setData610(canValue.get(609));
    				}
    				if (temp[610] != Double.MAX_VALUE) {
    					dataWareHouse.setData611(canValue.get(610));
    				}
    				if (temp[611] != Double.MAX_VALUE) {
    					dataWareHouse.setData612(canValue.get(611));
    				}
    				if (temp[612] != Double.MAX_VALUE) {
    					dataWareHouse.setData613(canValue.get(612));
    				}
    				if (temp[613] != Double.MAX_VALUE) {
    					dataWareHouse.setData614(canValue.get(613));
    				}
    				if (temp[614] != Double.MAX_VALUE) {
    					dataWareHouse.setData615(canValue.get(614));
    				}
    				if (temp[615] != Double.MAX_VALUE) {
    					dataWareHouse.setData616(canValue.get(615));
    				}
    				if (temp[616] != Double.MAX_VALUE) {
    					dataWareHouse.setData617(canValue.get(616));
    				}
    				if (temp[617] != Double.MAX_VALUE) {
    					dataWareHouse.setData618(canValue.get(617));
    				}
    				if (temp[618] != Double.MAX_VALUE) {
    					dataWareHouse.setData619(canValue.get(618));
    				}
    				if (temp[619] != Double.MAX_VALUE) {
    					dataWareHouse.setData620(canValue.get(619));
    				}
    				if (temp[620] != Double.MAX_VALUE) {
    					dataWareHouse.setData621(canValue.get(620));
    				}
    				if (temp[621] != Double.MAX_VALUE) {
    					dataWareHouse.setData622(canValue.get(621));
    				}
    				if (temp[622] != Double.MAX_VALUE) {
    					dataWareHouse.setData623(canValue.get(622));
    				}
    				if (temp[623] != Double.MAX_VALUE) {
    					dataWareHouse.setData624(canValue.get(623));
    				}
    				if (temp[624] != Double.MAX_VALUE) {
    					dataWareHouse.setData625(canValue.get(624));
    				}
    				if (temp[625] != Double.MAX_VALUE) {
    					dataWareHouse.setData626(canValue.get(625));
    				}
    				if (temp[626] != Double.MAX_VALUE) {
    					dataWareHouse.setData627(canValue.get(626));
    				}
    				if (temp[627] != Double.MAX_VALUE) {
    					dataWareHouse.setData628(canValue.get(627));
    				}
    				if (temp[628] != Double.MAX_VALUE) {
    					dataWareHouse.setData629(canValue.get(628));
    				}
    				if (temp[629] != Double.MAX_VALUE) {
    					dataWareHouse.setData630(canValue.get(629));
    				}
    				if (temp[630] != Double.MAX_VALUE) {
    					dataWareHouse.setData631(canValue.get(630));
    				}
    				if (temp[631] != Double.MAX_VALUE) {
    					dataWareHouse.setData632(canValue.get(631));
    				}
    				if (temp[632] != Double.MAX_VALUE) {
    					dataWareHouse.setData633(canValue.get(632));
    				}
    				if (temp[633] != Double.MAX_VALUE) {
    					dataWareHouse.setData634(canValue.get(633));
    				}
    				if (temp[634] != Double.MAX_VALUE) {
    					dataWareHouse.setData635(canValue.get(634));
    				}
    				if (temp[635] != Double.MAX_VALUE) {
    					dataWareHouse.setData636(canValue.get(635));
    				}
    				if (temp[636] != Double.MAX_VALUE) {
    					dataWareHouse.setData637(canValue.get(636));
    				}
    				if (temp[637] != Double.MAX_VALUE) {
    					dataWareHouse.setData638(canValue.get(637));
    				}
    				if (temp[638] != Double.MAX_VALUE) {
    					dataWareHouse.setData639(canValue.get(638));
    				}
    				if (temp[639] != Double.MAX_VALUE) {
    					dataWareHouse.setData640(canValue.get(639));
    				}
    				if (temp[640] != Double.MAX_VALUE) {
    					dataWareHouse.setData641(canValue.get(640));
    				}
    				if (temp[641] != Double.MAX_VALUE) {
    					dataWareHouse.setData642(canValue.get(641));
    				}
    				if (temp[642] != Double.MAX_VALUE) {
    					dataWareHouse.setData643(canValue.get(642));
    				}
    				if (temp[643] != Double.MAX_VALUE) {
    					dataWareHouse.setData644(canValue.get(643));
    				}
    				if (temp[644] != Double.MAX_VALUE) {
    					dataWareHouse.setData645(canValue.get(644));
    				}
    				if (temp[645] != Double.MAX_VALUE) {
    					dataWareHouse.setData646(canValue.get(645));
    				}
    				if (temp[646] != Double.MAX_VALUE) {
    					dataWareHouse.setData647(canValue.get(646));
    				}
    				if (temp[647] != Double.MAX_VALUE) {
    					dataWareHouse.setData648(canValue.get(647));
    				}
    				if (temp[648] != Double.MAX_VALUE) {
    					dataWareHouse.setData649(canValue.get(648));
    				}
    				if (temp[649] != Double.MAX_VALUE) {
    					dataWareHouse.setData650(canValue.get(649));
    				}
    				if (temp[650] != Double.MAX_VALUE) {
    					dataWareHouse.setData651(canValue.get(650));
    				}
    				if (temp[651] != Double.MAX_VALUE) {
    					dataWareHouse.setData652(canValue.get(651));
    				}
    				if (temp[652] != Double.MAX_VALUE) {
    					dataWareHouse.setData653(canValue.get(652));
    				}
    				if (temp[653] != Double.MAX_VALUE) {
    					dataWareHouse.setData654(canValue.get(653));
    				}
    				if (temp[654] != Double.MAX_VALUE) {
    					dataWareHouse.setData655(canValue.get(654));
    				}
    				if (temp[655] != Double.MAX_VALUE) {
    					dataWareHouse.setData656(canValue.get(655));
    				}
    				if (temp[656] != Double.MAX_VALUE) {
    					dataWareHouse.setData657(canValue.get(656));
    				}
    				if (temp[657] != Double.MAX_VALUE) {
    					dataWareHouse.setData658(canValue.get(657));
    				}
    				if (temp[658] != Double.MAX_VALUE) {
    					dataWareHouse.setData659(canValue.get(658));
    				}
    				if (temp[659] != Double.MAX_VALUE) {
    					dataWareHouse.setData660(canValue.get(659));
    				}
    				if (temp[660] != Double.MAX_VALUE) {
    					dataWareHouse.setData661(canValue.get(660));
    				}
    				if (temp[661] != Double.MAX_VALUE) {
    					dataWareHouse.setData662(canValue.get(661));
    				}
    				if (temp[662] != Double.MAX_VALUE) {
    					dataWareHouse.setData663(canValue.get(662));
    				}
    				if (temp[663] != Double.MAX_VALUE) {
    					dataWareHouse.setData664(canValue.get(663));
    				}
    				if (temp[664] != Double.MAX_VALUE) {
    					dataWareHouse.setData665(canValue.get(664));
    				}
    				if (temp[665] != Double.MAX_VALUE) {
    					dataWareHouse.setData666(canValue.get(665));
    				}
    				if (temp[666] != Double.MAX_VALUE) {
    					dataWareHouse.setData667(canValue.get(666));
    				}
    				if (temp[667] != Double.MAX_VALUE) {
    					dataWareHouse.setData668(canValue.get(667));
    				}
    				if (temp[668] != Double.MAX_VALUE) {
    					dataWareHouse.setData669(canValue.get(668));
    				}
    				if (temp[669] != Double.MAX_VALUE) {
    					dataWareHouse.setData670(canValue.get(669));
    				}
    				if (temp[670] != Double.MAX_VALUE) {
    					dataWareHouse.setData671(canValue.get(670));
    				}
    				if (temp[671] != Double.MAX_VALUE) {
    					dataWareHouse.setData672(canValue.get(671));
    				}
    				if (temp[672] != Double.MAX_VALUE) {
    					dataWareHouse.setData673(canValue.get(672));
    				}
    				if (temp[673] != Double.MAX_VALUE) {
    					dataWareHouse.setData674(canValue.get(673));
    				}
    				if (temp[674] != Double.MAX_VALUE) {
    					dataWareHouse.setData675(canValue.get(674));
    				}
    				if (temp[675] != Double.MAX_VALUE) {
    					dataWareHouse.setData676(canValue.get(675));
    				}
    				if (temp[676] != Double.MAX_VALUE) {
    					dataWareHouse.setData677(canValue.get(676));
    				}
    				if (temp[677] != Double.MAX_VALUE) {
    					dataWareHouse.setData678(canValue.get(677));
    				}
    				if (temp[678] != Double.MAX_VALUE) {
    					dataWareHouse.setData679(canValue.get(678));
    				}
    				if (temp[679] != Double.MAX_VALUE) {
    					dataWareHouse.setData680(canValue.get(679));
    				}
    				if (temp[680] != Double.MAX_VALUE) {
    					dataWareHouse.setData681(canValue.get(680));
    				}
    				if (temp[681] != Double.MAX_VALUE) {
    					dataWareHouse.setData682(canValue.get(681));
    				}
    				if (temp[682] != Double.MAX_VALUE) {
    					dataWareHouse.setData683(canValue.get(682));
    				}
    				if (temp[683] != Double.MAX_VALUE) {
    					dataWareHouse.setData684(canValue.get(683));
    				}
    				if (temp[684] != Double.MAX_VALUE) {
    					dataWareHouse.setData685(canValue.get(684));
    				}
    				if (temp[685] != Double.MAX_VALUE) {
    					dataWareHouse.setData686(canValue.get(685));
    				}
    				if (temp[686] != Double.MAX_VALUE) {
    					dataWareHouse.setData687(canValue.get(686));
    				}
    				if (temp[687] != Double.MAX_VALUE) {
    					dataWareHouse.setData688(canValue.get(687));
    				}
    				if (temp[688] != Double.MAX_VALUE) {
    					dataWareHouse.setData689(canValue.get(688));
    				}
    				if (temp[689] != Double.MAX_VALUE) {
    					dataWareHouse.setData690(canValue.get(689));
    				}
    				if (temp[690] != Double.MAX_VALUE) {
    					dataWareHouse.setData691(canValue.get(690));
    				}
    				if (temp[691] != Double.MAX_VALUE) {
    					dataWareHouse.setData692(canValue.get(691));
    				}
    				if (temp[692] != Double.MAX_VALUE) {
    					dataWareHouse.setData693(canValue.get(692));
    				}
    				if (temp[693] != Double.MAX_VALUE) {
    					dataWareHouse.setData694(canValue.get(693));
    				}
    				if (temp[694] != Double.MAX_VALUE) {
    					dataWareHouse.setData695(canValue.get(694));
    				}
    				if (temp[695] != Double.MAX_VALUE) {
    					dataWareHouse.setData696(canValue.get(695));
    				}
    				if (temp[696] != Double.MAX_VALUE) {
    					dataWareHouse.setData697(canValue.get(696));
    				}
    				if (temp[697] != Double.MAX_VALUE) {
    					dataWareHouse.setData698(canValue.get(697));
    				}
    				if (temp[698] != Double.MAX_VALUE) {
    					dataWareHouse.setData699(canValue.get(698));
    				}
    				if (temp[699] != Double.MAX_VALUE) {
    					dataWareHouse.setData700(canValue.get(699));
    				}

    				dataWareHouses.add(dataWareHouse);
    				if (dataWareHouses.size() > 9) {
    					long start = System.currentTimeMillis();
    					new DataWareHouseImpl().saveOrUpdate(dataWareHouses);
    					dataWareHouses.clear();
    					long end = System.currentTimeMillis();
    					System.out.println("更新历史库用时:" + (end - start) / 1000);
    				}
    			}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			if (dataWareHouses.size() > 0 ) {
				long start = System.currentTimeMillis();
				new DataWareHouseImpl().saveOrUpdate(Deduplication(dataWareHouses));
				dataWareHouses.clear();
				long end = System.currentTimeMillis();
				System.out.println("更新历史库用时:" + (end - start) / 1000);
			}
			if (incomRealTimeEntitys.size() > 0) {
				long start = System.currentTimeMillis();
				new IncomRealTimeEntityImpl().saveWithJDBC(incomRealTimeEntitys);
				long end = System.currentTimeMillis();
				System.out.println("更新实时表用时:" + (end - start) / 1000);
				incomRealTimeEntitys.clear();
			}
		}
	}
	
	public List<DataWareHouse> Deduplication(List<DataWareHouse> list) {
		List<DataWareHouse> newList = new  ArrayList<DataWareHouse>(); 
        for (DataWareHouse cd:list) {
           if(!newList.contains(cd)){
               newList.add(cd);
           }
       }
		return newList;
	}
	
	public List<DataWareHouseV1> DeduplicationV1(List<DataWareHouseV1> list) {
		List<DataWareHouseV1> newList = new  ArrayList<DataWareHouseV1>(); 
        for (DataWareHouseV1 cd:list) {
           if(!newList.contains(cd)){
               newList.add(cd);
           }
       }
		return newList;
	}
	
	public void getMessageFromRmq2OracleJDBC(String message) {
		if(!message.equals("null")) {
			try {
				UserData userData = DataParse.decode(message);
    			IncomRealTimeEntity incomRealTimeEntity = new IncomRealTimeEntity();
    			incomRealTimeEntity.setTc(201704221);
    			incomRealTimeEntity.setTe(new Date());
    			incomRealTimeEntity.setMd5("1F15AB7B9BCBEA5D54883863A2FFAD8C");
    			incomRealTimeEntity
    					.setLongitude(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.longitude);
    			incomRealTimeEntity
    					.setLatitude(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.latitude);
    			incomRealTimeEntity
    					.setAltitude(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.height);
    			incomRealTimeEntity.setSpeed(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.speed);
    			incomRealTimeEntity
    					.setMileage(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.distance);
    			incomRealTimeEntity
    					.setDirection(userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.direction);
    			incomRealTimeEntity.setIsPosition(1);
    			incomRealTimeEntity.setIsDriver(1);
    			incomRealTimeEntitys.add(incomRealTimeEntity);
    			//先排序 在去重
    			Collections.sort(incomRealTimeEntitys);
    			// 批量入库
    			if (incomRealTimeEntitys.size() > 30000) {
    				new IncomRealTimeEntityImpl().saveWithJDBC(DeduplicationRealTime(incomRealTimeEntitys));
    				incomRealTimeEntitys.clear();
    			}
    			// 更新历史库
    			if (userData.chioceDate.dataReport.vehiData.vehiReport != null) {
    				List<Double> canValue = userData.chioceDate.dataReport.vehiData.vehiReport;
    				
    				DataWareHouseV1 dataWareHouseV1 = new DataWareHouseV1();
    				dataWareHouseV1.setTc(201704221);
    				dataWareHouseV1.setSeqId(
    						BuildSeqId.getSeqId(new Date(userData.basicInfo.timeSec * 1000 + userData.basicInfo.timeMsec)));
    				dataWareHouseV1.setMd5("1F15AB7B9BCBEA5D54883863A2FFAD8C");
    				dataWareHouseV1.setTe(new Date(userData.basicInfo.timeMsec + userData.basicInfo.timeSec * 1000));
    				dataWareHouseV1.setLongitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.longitude);
    				dataWareHouseV1.setLatitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.latitude);
    				dataWareHouseV1.setAltitude(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.height);
    				dataWareHouseV1.setSpeed(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.speed);
    				dataWareHouseV1.setMileage(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.distance);
    				dataWareHouseV1.setDirection(
    						userData.chioceDate.dataReport.terminalStat.terminalReport.gpsLocation.direction);
    				dataWareHouseV1.setVeliData(canValue);
    				dataWareHouseV1s.add(dataWareHouseV1);
    				if (dataWareHouseV1s.size() > 30000) {
    					new DataWareHouseImpl().saveWithJDBC(DeduplicationV1(dataWareHouseV1s));
    					dataWareHouseV1s.clear();
    				}
    			}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			if (dataWareHouseV1s.size() > 0 ) {
				long start = System.currentTimeMillis();
				new DataWareHouseImpl().saveWithJDBC(DeduplicationV1(dataWareHouseV1s));
				dataWareHouseV1s.clear();
				long end = System.currentTimeMillis();
				System.out.println("更新历史库用时:" + (end - start) / 1000);
			}
			if (incomRealTimeEntitys.size() > 0) {
				long start = System.currentTimeMillis();
				new IncomRealTimeEntityImpl().saveWithJDBC(DeduplicationRealTime(incomRealTimeEntitys));
				long end = System.currentTimeMillis();
				System.out.println("更新实时表用时:" + (end - start) / 1000);
				incomRealTimeEntitys.clear();
			}
		}
	}
	
	public static List<IncomRealTimeEntity> DeduplicationRealTime(List<IncomRealTimeEntity> list) {
		List<IncomRealTimeEntity> newList = new  ArrayList<IncomRealTimeEntity>(); 
        for (IncomRealTimeEntity cd:list) {
           if(!newList.contains(cd)){
               newList.add(cd);
           }
       }
		return newList;
	}
}
