package org.secken.api;

public class test {

	public static void main(String[] args) {

		String url = "http:// your server ip /api/access/";
		String id = "your power id";
		String key = "your power key";
		String username = " your user name";

		try {

			System.out.println("now try to auth by push~~~");
			RealtimeAuth auth = new RealtimeAuth(url + "realtime_authorization", id, key, username);
			System.out.println("waiting for push auth reuslt");
			while (true) {
				Thread.sleep(3000);
				GetEventResult result = new GetEventResult(url + "event_result", id, key, auth.getEventID());
				if (200 == result.getStatus()) {
					System.out.println("push auth success");
					break;
				} else if (602 != result.getStatus()) {
					System.out.println("push auth failed, status:" + result.getStatus());
					break;
				}
			}

			System.out.println("\nnow try to auth by qrcode~~~");
			QrcodeForAuth qr = new QrcodeForAuth(url + "qrcode_for_auth", id, key);
			System.out.println("url = " + qr.getQrcodeUrl());
			System.out.println("please input the " + qr.getQrcodeUrl() + " in your webbroswer, and scan it throw your yangcong app");
			while (true) {
				Thread.sleep(3000);
				GetEventResult result = new GetEventResult(url + "event_result", id, key, qr.getEventID());
				if (200 == result.getStatus()) {
					System.out.println("qrcode auth success");
					break;
				} else if (602 != result.getStatus()) {
					System.out.println("qrcode auth failed, status:" + result.getStatus());
					break;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
