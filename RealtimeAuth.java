package org.secken.api;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class RealtimeAuth {

	private int status;
	private String description;
	private String event_id;

	RealtimeAuth(String url, String powerID, String powerKey, String username) throws Exception {

		if (url == null || powerID == null || powerKey == null || username == null) {
			throw new Exception("null Pointer");
		}

		String signature = "power_id=" + powerID + "username=" + username + powerKey;
		signature = EncoderHandler.encode("SHA1", signature);

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("power_id", powerID);
		dataMap.put("username", username);
		dataMap.put("signature", signature);

		JSONObject json = JSONObject.fromObject(new HttpRequestor().doPost(url, dataMap));

		this.description = json.getString("description");
		this.status = Integer.parseInt(json.getString("status"));
		if (this.status == 200) {
			this.event_id = json.getString("event_id");
		}
	}

	public int getStatus() {
		return this.status;
	}

	public String getDescription() {
		return this.description;
	}

	public String getEventID() {
		return this.event_id;
	}

}
