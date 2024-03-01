package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

public class Tasca_5 {

	public static void main(String[] args) {
		String URI = "https://mastodont.cat/api/v1/accounts/109862447110628983/statuses";
		
		String TOKEN = Token.get();
		
		try {
			String output = Request.get(URI)
					.addHeader("Authorization","Bearer "+TOKEN)
					.execute()
					.returnContent()
					.asString();
			//System.out.format(output);

			JSONArray result = new JSONArray(output);
			String tut = result.getJSONObject(0).getString("content");
			String id = result.getJSONObject(0).getString("id");
			System.out.format(tut);
			//System.out.format(result.toString());
			String URI2 = "https://mastodont.cat/api/v1/statuses/" + id + "/reblog";
			Request.post(URI2)
					.addHeader("Authorization","Bearer "+TOKEN)
					.execute();

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
