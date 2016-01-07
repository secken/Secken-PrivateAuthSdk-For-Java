package org.secken.api;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class QrcodeForAuth {

	private int status;
	private String description;
	private String qrcode_url;
	private String qrcode_data;
	private String event_id;

	QrcodeForAuth(String url, String powerID, String powerKey) throws Exception {
		if (url == null || powerID == null || powerKey == null) {
			throw new Exception("null Pointer");
		}

		String signature = "power_id=" + powerID + powerKey;
		signature = EncoderHandler.encode("SHA1", signature);

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("power_id", powerID);
		dataMap.put("signature", signature);

		JSONObject json = JSONObject.fromObject(new HttpRequestor().doPost(url, dataMap));
		
		this.status = Integer.parseInt(json.getString("status"));
		if (this.status == 200) {
			this.qrcode_url = json.getString("qrcode_url");
			this.qrcode_data = json.getString("qrcode_data");
			this.event_id = json.getString("event_id");
		}
		this.description = json.getString("description");
	}

	public String getDescription() {
		return this.description;
	}

	public String getQrcodeUrl() {
		return this.qrcode_url;
	}

	public String getQrcodeData() {
		return this.qrcode_data;
	}

	public String getEventID() {
		return this.event_id;
	}

}
