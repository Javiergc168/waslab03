package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;

public class Tasca_6 {

	public static void main(String[] args) {
		String URI = "https://mastodont.cat/api/v1/trends/tags"; // limit 10
		String TOKEN = Token.get();
		
		try {
			String output = Request.get(URI)
					.addHeader("Authorization","Bearer "+TOKEN)
					.execute()
					.returnContent()
					.asString();
			System.out.format("Els 10 tags més populars a Mastodon [" + ""/*data i hora actual*/ + "]");
			JSONArray result = new JSONArray(output);
			for (int i=0; i<10; i++) {
				String tag = result.getJSONObject(i).getString("name");
				System.out.format("*************************************************");
				System.out.format("* Tag: " + tag);
				System.out.format("*************************************************");

				String URI2 = "https://mastodont.cat/api/v1/timelines/tag/" + tag;	//limit 5			
				String output2 = Request.get(URI2)
						.addHeader("Authorization","Bearer "+TOKEN)
						.execute()
						.returnContent()
						.asString();
				JSONArray result2 = new JSONArray(output2);
				for (int j=0; j<5; j++) {
					String content = result2.getJSONObject(i).getString("content");
					System.out.format("- " + content);	
					System.out.format("-------------------------------------------------");
				}
				System.out.format("\n");	
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
