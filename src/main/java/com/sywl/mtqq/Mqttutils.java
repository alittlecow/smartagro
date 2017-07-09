package com.sywl.mtqq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sywl.web.domain.MqttReq;
import com.sywl.web.service.DeviceService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sywl.utils.DateUtils;

@Component
public class Mqttutils {

	
	public static MqttClient client;
	private MqttConnectOptions options;
	private static final Logger logger=LoggerFactory.getLogger(Mqttutils.class);

	@Autowired
	DeviceService deviceService;
	
	public void connection(){
		try {
			// host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
			client = new MqttClient("tcp://cloud.nj-shiyin.com:1883", "cloudlocal", new MemoryPersistence());
			// MQTT的连接设置
			options = new MqttConnectOptions();
			// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
			options.setCleanSession(true);
			// 设置连接的用户名
			options.setUserName("sywl");
			// 设置连接的密码
			 options.setPassword("public".toCharArray());
			// 设置超时时间 单位为秒
			options.setConnectionTimeout(10);
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);
			// 设置回调
			client.setCallback(new PushCallback());
			client.connect(options);
			List<String> deviceCode = deviceService.getBindDevice();
			for(int i = 0;i<deviceCode.size();i++){
				String topic = deviceCode.get(i)+"/subsd";
				client.subscribe(topic, 2);
			}
			
		} catch (Exception e) {
			logger.error("mqtt connection failed,error message is:"
					+ e.getMessage());
			while(!client.isConnected()){
				connection();
			}
		}
	}
	
	public void subscribe(int qos,String topic){
		try {
			client.subscribe(topic, qos);
		} catch (MqttException e) {
			logger.error(e.getMessage());
			connection();
		}
	}
	
	public void unsubscribe(String topic){
		try {
			client.unsubscribe(topic);
		} catch (MqttException e) {
			logger.error(e.getMessage());
			connection();
		}	
	}
	
	public void publish(String topic, Map message)
		{
		MqttTopic sendTopic = client.getTopic(topic);
		MqttMessage sendMessage = new MqttMessage();
		sendMessage.setQos(2);
		sendMessage.setRetained(true);
		sendMessage.setPayload(JSONObject.toJSONString(message).getBytes());
		MqttDeliveryToken token;
		try {
			token = sendTopic.publish(sendMessage);
			token.waitForCompletion();
		} catch (MqttPersistenceException e) {
			connection();
			logger.error(e.getMessage());
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			connection();
			logger.error(e.getMessage());
		}
		
	}
	
	public class PushCallback implements MqttCallback {

		public void connectionLost(Throwable cause) {
			try {
				System.out.println(client.isConnected());
				while(!client.isConnected()){
					client.reconnect();
					System.out.println("连接已断开.正在尝试重连");
					logger.info("连接已断开.正在尝试重连");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				connection();
				logger.error(e.getMessage());
			}
		}

		public void deliveryComplete(IMqttDeliveryToken token) {
			System.out.println("deliveryComplete---------" + token.isComplete());
		}

		public void messageArrived(String topic, MqttMessage message)
				{
			 String deviceCode =  topic.replace("/subsd", "");
			 System.out.println("接收消息主题 : " + topic);  
			 System.out.println("接收消息Qos : " + message.getQos());  
			 System.out.println("接收消息 : " + message.toString());  
			 MqttReq mqttReq = JSON.parseObject(message.toString(), MqttReq.class);
			 if(mqttReq.getAction().equals("reqtiming")){
				 Map map = new HashMap();
				 Map timing = new HashMap();
				 map.put("action", "reqtiming");
				 timing.put("datetime", DateUtils.todayStr());
				 map.put("timing", timing);
				 publish(deviceCode+"/pubsd",map);
			 }
			 
			 /*if(mqttReq.getAction().equals("reqOTA")){
				 DeviceVersion deviceVersion = deviceVersionService.selectByDeviceCode(deviceCode);
				 Map map = new HashMap();
				 map.put("action", "reqOTA");
				 map.put("OTA", deviceVersion);
				 publish(deviceCode+"/pubsd",map);
			 }
			 
			 if(mqttReq.getAction().equals("reportsysinfo")){
				 if (deviceCode.contains("relay")) {
					 mqttReq.getSysinfo().setDeviceCode(deviceCode);
					 deviceRelayService.updateByDeviceCode(mqttReq.getSysinfo());
				 }
				 if (deviceCode.contains("th")) {
					 mqttReq.getSysinfo().setDeviceCode(deviceCode);
					 deviceTHService.updateByDeviceCode(mqttReq.getSysinfo());
				 }
			 }
			 
			 if(mqttReq.getAction().equals("reporthistory")){
				 List<HistoryBean> historyBeans = mqttReq.getHistory();
				 List<TempHumiDomain> tempHumiDomains = new ArrayList<TempHumiDomain>();
				 for(int i = 0;i<historyBeans.size();i++){
					 String his[] = historyBeans.get(i).getRecord().split("&");
					 TempHumiDomain tempHumiDomain = new TempHumiDomain();
					 tempHumiDomain.setcTime(DateUtils.strToDate(his[0]));
					 tempHumiDomain.setHumi(his[2]);
					 tempHumiDomain.setTemp(his[1]);
					 tempHumiDomain.setDeviceCode(deviceCode);
					 tempHumiDomain.setId(UUIDUtil.getUUId());
					 tempHumiDomains.add(tempHumiDomain);
				 }
				 tempHumiService.insertBatch(tempHumiDomains);
			 }
			 
			 if(mqttReq.getAction().equals("reportwarn")){
				 DeviceTHDomain DeviceTHDomain = deviceTHService.queryDeviceByDeviceCode(deviceCode);
				 List<THRelayDomain> thRelayDomains = thRelayService.selectDeviceByIdAndType(DeviceTHDomain.getThId(), mqttReq.getWarn().getType());
				 //List<TempHumiDomain> tempHumiDomains = new ArrayList<TempHumiDomain>();
				 for(int i = 0;i<thRelayDomains.size();i++){
					 Map map = new HashMap();
				     Map state = new HashMap();
				     state.put("state", thRelayDomains.get(i).getAction());
					 map.put("action", "setrelay");
					 map.put("relay", state);
					 publish(thRelayDomains.get(i).getDeviceCode()+"/pubsd",map);
				 }
			 }*/
			 
		}
	}

}
