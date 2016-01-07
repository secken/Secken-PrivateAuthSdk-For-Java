package org.secken.api;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class GetEventResult {

	private int status;
	private String description;
	private String event_id;
	private String uid;

	GetEventResult(String url, String powerID, String powerKey, String eventID) throws Exception {
		if (url == null || powerID == null || powerKey == null || eventID == null) {
			throw new Exception("null Pointer");
		}

		String signature = "event_id=" + eventID + "power_id=" + powerID + powerKey;
		signature = EncoderHandler.encode("SHA1", signature);

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("power_id", powerID);
		dataMap.put("event_id", eventID);
		dataMap.put("signature", signature);

		JSONObject json = JSONObject.fromObject(new HttpRequestor().doPost(url, dataMap));

		this.status = Integer.parseInt(json.getString("status"));
		if (this.status == 200) {
			this.event_id = json.getString("event_id");
			this.uid = json.getString("uid");
		}
		this.description = json.getString("description");
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

	public String getUid() {
		return this.uid;
	}

}
