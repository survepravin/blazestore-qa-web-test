package stepDefinitions;

import org.json.JSONObject;

public class TestData {

	public static String loginPayload(String username, String password) {
		JSONObject userObject = new JSONObject();
		userObject.put("email", username);
		userObject.put("password", password);
		JSONObject user = new JSONObject();
		user.put("user", userObject);
		
		return user.toString();
	}
	
	public static JSONObject CreateArticlePayload(String title, String description, String body, String tags) {
		JSONObject articleObject = new JSONObject();
		if (title.length() != 0)
			articleObject.put("title", title);
		articleObject.put("description", description);
		articleObject.put("body", body);
		articleObject.put("tagList", tags.split(","));
		JSONObject article = new JSONObject();
		article.put("article", articleObject);
		
		return article;
	}
}
