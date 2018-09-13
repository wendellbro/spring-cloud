package com.business.gateway.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
{
    "source": "pc", 
    "version": "2.0.0.0", 
    "client_ip": "101.81.128.56", 
    "device_id": "ios", 
    "data": {
        "phone": "15236568566", 
        "password": "111111q", 
        "registerType": "1"
    }, 
    "msg_id": "03ece4f7937787f6aabf2e9538858471", 
    "session_id": "03ece4f7937787f6aabf2e9538858471", 
    "token": "e1195fa4e34911153eea412ecf4df5e0"
}
 * @describe 客户端请求参数
 * @author wupeng
 * @createtime 2017年9月15日
 */
public class CommonRequest {

	private String source;
	private String version;
	@JSONField(name="client_ip")
	private String clientIp;
	@JSONField(name="device_id")
    private String deviceId;
    private JSONObject data;
    @JSONField(name="msg_id")
    private String msgId;
    @JSONField(name="session_id")
    private String sessionId;
    private String token;

    public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

    public void setSource(String source) {
         this.source = source;
     }
     public String getSource() {
         return source;
     }

    public void setVersion(String version) {
         this.version = version;
     }
     public String getVersion() {
         return version;
     }
     
 	public void setData(JSONObject data) {
        this.data = data;
    }
    public JSONObject getData() {
        return data;
    }

    public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public void setSessionId(String sessionId) {
         this.sessionId = sessionId;
     }
     public String getSessionId() {
         return sessionId;
     }

    public void setToken(String token) {
         this.token = token;
     }
     public String getToken() {
         return token;
     }
     
}
