package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;

public class Tasca_6 {

	public static void main(String[] args) {
		String URI = "https://mastodont.cat/api/v1/trends/tags";
		String TOKEN = Token.get();
		
		try {
			String output = Request.get(URI)
					.addHeader("Authorization","Bearer "+TOKEN)
					.execute()
					.returnContent()
					.asString();
			//System.out.format(output);
			JSONArray result = new JSONArray(output);
			for (int i=0; i<10; i++) {
				String tag = result.getJSONObject(i).getString("name");
				String URI2 = "https://mastodont.cat/api/v1/trends/tags";
			}

			

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
