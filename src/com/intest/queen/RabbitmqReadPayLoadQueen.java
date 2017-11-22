package com.intest.queen;

import java.util.Vector;

import com.intest.asn.DataParse;
import com.intest.asn.FaultData;
import com.intest.asn.FaultStruct;
import com.intest.asn.FileTrans;
import com.intest.asn.ParaSetQuery;
import com.intest.asn.UserData;
import com.intest.bean.IncomTerminal;
import com.intest.impl.IncomTerminalImpl;
import com.intest.mqtt.Server;
import com.intest.rabbitmq.RabbitMqUtil;
import com.intest.util.ByteUtil;
import com.intest.util.ValitionUtil;
import com.intest.zlib.ZlibUtils;

/*
 * 处理ASN.1的数据线程
 */
public class RabbitmqReadPayLoadQueen implements Runnable {
	
	@SuppressWarnings("static-access")
	public void run() {
		RabbitMqUtil rabbitMqUtil = new RabbitMqUtil();
		while(true) {
			try {
				byte[] payload = PayLoadQueen.take();
				//获取zlib
		        byte[] zlib = ByteUtil.getZlib(payload);
		        //获取数据块长度
		        byte[] dataLength = ByteUtil.getDataLength(payload);
		        //获取校验值
		        byte[] valitionValue = ByteUtil.getValitionValue(payload);
		        int length = payload.length;
				if(ValitionUtil.valitionLength(dataLength, length)) {
		        	//System.out.println("数据长度校验成功...");
		        	//固定头部是59字节
		        	int restlength = length - 59;
		            byte[] bs1 = new byte[restlength];
		            System.arraycopy(payload, 59, bs1, 0, restlength);
		            if(ValitionUtil.valitionValue(bs1, valitionValue)) {
		            	//System.out.println("校验值验证通过...");
		            	//根据zlib判断是否压缩
		                byte[] bs2 = null;
		                if (Integer.parseInt(UserData.TYPE.toHexString(zlib)) > 0) {
		                	bs2 = ZlibUtils.decompress(bs1);
		                } else {
		                	bs2 = bs1;
		                }
		                UserData userData = DataParse.decode(bs2);            
		                if(userData.chioceDate.logInOut != null) {
		                	//发送登录回复
		                	byte[] str = DataParse.loginOut();
		                	Server server = new Server("tcp://101.132.39.198:1883","/123456/asn1/1/3", "server1", "admin", "admin");
		                	server.publish(str);
		                	//PublishQueen.put(str);		            
		                	//登陆平台
		                	IncomTerminal incomTerminal = new IncomTerminal();
		                	incomTerminal.setTc(201704221);
		                	incomTerminal.setIsOnline(1);
		            		incomTerminal.setGlobalFlag(0);
		            		new IncomTerminalImpl().saveOrUpdate(incomTerminal);
		                    System.out.println("平台登录成功...");
						} else {
							if (userData.chioceDate.paraSetQueryChosen == userData.chioceDate.choiceID) {
								if (userData.chioceDate.paraSetQuery.choiceID == ParaSetQuery.paraSetRspChosen) {
									System.out.println(
											"终端通信数据间隔参数设置:" + userData.chioceDate.paraSetQuery.paraSetRsp.intSet);
									System.out.println(
											"主站IP地址和端口设置:" + userData.chioceDate.paraSetQuery.paraSetRsp.link1Set);
									System.out.println(
											"第二链路IP地址和端口设置:" + userData.chioceDate.paraSetQuery.paraSetRsp.link2Set);
									System.out.println("设置第二个链接的开关(国标直连):"
											+ userData.chioceDate.paraSetQuery.paraSetRsp.link2EnSet);
									System.out.println(
											"DBC文件名设置:" + userData.chioceDate.paraSetQuery.paraSetRsp.dbcNameSet);
									System.out
											.println("系统休眠模式设置：" + userData.chioceDate.paraSetQuery.paraSetRsp.sysSet);
									System.out.println(
											"SD卡数据存储频率设置:" + userData.chioceDate.paraSetQuery.paraSetRsp.sdHzSet);
									System.out.println("参数设置回复已收到...");
								}
								
								if (userData.chioceDate.paraSetQuery.choiceID == ParaSetQuery.paraQueryRspChosen) {
									System.out.println("参数查询结果展示:");
									System.out.println("*******************************************************");
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.intRsp != null) {
										System.out.println("终端通信数据间隔参数设置: "
												+ userData.chioceDate.paraSetQuery.paraQueryRsp.intRsp.dataInterval);
									} else {
										System.out.println("终端通信数据间隔参数设置: 未查询到结果");
									}
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.link1Rsp != null) {
										System.out.println("主站IP地址或域名和端口: " + new String(
												userData.chioceDate.paraSetQuery.paraQueryRsp.link1Rsp.commUrl, "utf-8"));
									} else {
										System.out.println("主站IP地址或域名和端口: 未查询到结果");
									}
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.link2Rsp != null) {
										System.out.println("第二链路IP地址或域名和端口: "
												+ new String(userData.chioceDate.paraSetQuery.paraQueryRsp.link2Rsp.commUrl,
														"utf-8")
												+ ":" + userData.chioceDate.paraSetQuery.paraQueryRsp.link2Rsp.commPort);
									} else {
										System.out.println("第二链路IP地址或域名和端口: 未查询到结果");
									}
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.link2EnRsp != null) {
										System.out.println("查询第二个链接的开关(国标直连): "
												+ userData.chioceDate.paraSetQuery.paraQueryRsp.link2EnRsp);
									} else {
										System.out.println("查询第二个链接的开关(国标直连): 未查询到结果");
									}
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.dbcNameRsp != null) {
										System.out.println("DBC文件名查询: " + new String(
												userData.chioceDate.paraSetQuery.paraQueryRsp.dbcNameRsp, "utf-8"));
									} else {
										System.out.println("DBC文件名查询: 未查询到结果");
									}
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.sysRsp != null) {
										System.out.println(
												"系统休眠模式查询: " + userData.chioceDate.paraSetQuery.paraQueryRsp.sysRsp);
									} else {
										System.out.println("系统休眠模式查询: 未查询到结果");
									}
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.sdHzRsp != null) {
										System.out.println(
												"SD卡数据存储频率查询: " + userData.chioceDate.paraSetQuery.paraQueryRsp.sdHzRsp);
									} else {
										System.out.println("SD卡数据存储频率查询: 未查询到结果");
									}
									if (userData.chioceDate.paraSetQuery.paraQueryRsp.sdCapRsp != null) {
										System.out.println(
												"SD卡容量查询: " + userData.chioceDate.paraSetQuery.paraQueryRsp.sdCapRsp);
									} else {
										System.out.println("SD卡容量查询: 未查询到结果");
									}

									System.out.println("*******************************************************");
									System.out.println("参数查询请求响应已收到....");
								}
							}
							
							if (userData.chioceDate.choiceID == userData.chioceDate.rmtControlChosen) {
								/// *userData.chioceDate.rmtControlChosen*/
								//userData.chioceDate.rmtControl.choiceID == RmtControl.rmtControlRspChosen
								System.out.println("*******************************************************");
								System.out.println("累计变量清零，1：终端运行时长清零，2：终端流量统计清零: "
										+ userData.chioceDate.rmtControl.rmtControlRsp.accumVarRsp);
								System.out.println("终端校时，UTC时间，单位s: "
										+ userData.chioceDate.rmtControl.rmtControlRsp.calibrateRsp);
								System.out.println("删除SD卡上的文件/目录，文件或目录名称: "
										+ userData.chioceDate.rmtControl.rmtControlRsp.delFileRsp);
								System.out.println(
										"终端重启使能，false：终端重启失能 --: " + userData.chioceDate.rmtControl.rmtControlRsp.rebootRsp);
								System.out
										.println("true：立即上报使能，false：立即上报失能: " + userData.chioceDate.rmtControl.rmtControlRsp.reportRsp);
								System.out.println("重置GPS里程使能，false：重置GPS里程失能: "
										+ userData.chioceDate.rmtControl.rmtControlRsp.resetDistRsp);
								System.out.println("设置虚拟CAN卡: "
										+ userData.chioceDate.rmtControl.rmtControlRsp.vCanCardRsp);
								System.out.println("远程控制回复 已收到...");
								System.out.println("*******************************************************");
							}
							
							if (userData.chioceDate.choiceID == userData.chioceDate.eventReportChosen){
								System.out.println("事件上报结果结果展示...");
							}
							if (userData.chioceDate.choiceID == userData.chioceDate.eventReportChosen) {
								System.out.println("*******************************************************");
								if (userData.chioceDate.eventReport.faultReport.choiceID == FaultStruct.vf1939Chosen) {
									Vector<FaultData> vf1939s = userData.chioceDate.eventReport.faultReport.vf1939;
									for (FaultData faultData : vf1939s) {
										System.out.println("J1939故障...");
										System.out.println("故障状态标志，1：发生，2：结束，3：DBC结束:" + faultData.vfFlag);
										System.out.println("故障起始UTC时间，精度s :" + faultData.vfStartSec);
										System.out.println("故障结束UTC时间，精度10ms:" + faultData.vfEndSec);
										System.out.println("故障码：N字节:" + new String(faultData.vfCode,"utf-8"));
									}
								}
								if (userData.chioceDate.eventReport.faultReport.choiceID == FaultStruct.vfCustmChosen) {
									Vector<FaultData> vfCustms = userData.chioceDate.eventReport.faultReport.vfCustm;
									for (FaultData faultData : vfCustms) {
										System.out.println("自定义故障...");
										System.out.println("故障状态标志，1：发生，2：结束，3：DBC结束:" + faultData.vfFlag);
										System.out.println("故障起始UTC时间，精度s :" + faultData.vfStartSec);
										System.out.println("故障结束UTC时间，精度10ms:" + faultData.vfEndSec);
										System.out.println("故障码：N字节:" + new String(faultData.vfCode,"utf-8"));
									}
								}
								if (userData.chioceDate.eventReport.faultReport.choiceID == FaultStruct.vfStateChosen) {
									Integer state = userData.chioceDate.eventReport.faultReport.vfState;
									System.out.println("错误码：" + state);
								}
								System.out.println("事件上报回复已收到...");
								System.out.println("*******************************************************");
							}
							if (userData.chioceDate.fileTransChosen == userData.chioceDate.choiceID) {
								System.out.println("*******************************************************");
								if (userData.chioceDate.fileTrans.choiceID == FileTrans.dbcDownloadChosen) {
									System.out.println("0：成功，1：段重复，2：正在更新，3：段非连续，4：文件溢出，5：传输错误: "
											+ userData.chioceDate.fileTrans.dbcDownload.dbcDownRsp);
								}
								if (userData.chioceDate.fileTrans.choiceID == FileTrans.imgDownloadChosen) {
									System.out.println("响应,0：成功，1：段重复，2：正在更新，3：段非连续，4：文件溢出，5：传输错误: "
											+ userData.chioceDate.fileTrans.imgDownload.imgDownRsp);
								}
								System.out.println("文件传输回复已收到...");
								System.out.println("*******************************************************");
							}

						} 
		                if (userData.chioceDate.choiceID == userData.chioceDate.dataReportChosen) {
		                		//把数据写入到rabbitmq中 
		                		rabbitMqUtil.send(UserData.TYPE.toHexString(bs2).getBytes());
			            		System.out.println("车辆状态可查询...");
		                	}
		                
		            } else {
		            	System.out.println("校验值校验失败...");
		            }
		        } else {
		        	System.out.println("数据长度校验失败...");
		        }
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
}
