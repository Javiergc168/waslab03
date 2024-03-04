package fib.asw.waslab03;

import org.apache.hc.client5.http.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;


public class Tasca_6 {

	public static void main(String[] args) {
		String URI = "https://mastodont.cat/api/v1/trends/tags"; // limit 10
		String TOKEN = Token.get();
		
		try {
			String output = Request.get(URI)
					.addHeader("Authorization","Bearer "+TOKEN).addHeader("KEY=VALUE", "limit=10")
					.execute()
					.returnContent()
					.asString();
			System.out.format("Els 10 tags més populars a Mastodon [" + ""/*data i hora actual*/ + "]\n\n");
			JSONArray result = new JSONArray(output);
			for (int i=0; i<10; i++) {
				String tag = result.getJSONObject(i).getString("name");
				System.out.format("*************************************************\n");
				System.out.format("* Tag: " + tag + "\n");
				System.out.format("*************************************************\n");

				String URI2 = "https://mastodont.cat/api/v1/timelines/tag/" + tag;	//limit 5			
				String output2 = Request.get(URI2)
						.addHeader("Authorization","Bearer "+TOKEN).addHeader("KEY=VALUE", "limit=5")
						.execute()
						.returnContent()
						.asString();
				JSONArray result2 = new JSONArray(output2);
				for (int j=0; j<5; j++) {
					JSONObject jobject = result2.getJSONObject(j);
					String name = jobject.getJSONObject("account").getString("display_name");
					String username = jobject.getJSONObject("account").getString("acct");
					String content = jobject.getString("content").replaceAll("\\<.*?\\>", "");
					System.out.format("- " + name +  " (" + username + "):" + content + "\n");	
					System.out.format("-------------------------------------------------\n");
				}
				System.out.format("\n");	
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
