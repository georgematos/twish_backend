package com.octodev.twish.controllers;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialFacebookController {

  private String app_id = "id";
  private String app_key = "key";

  private FacebookConnectionFactory factory = new FacebookConnectionFactory(app_id, app_key);

  @GetMapping(value = "/useApplication")
  public ResponseEntity<String> producer() {

    OAuth2Operations operations = factory.getOAuthOperations();
    OAuth2Parameters params = new OAuth2Parameters();

    params.setRedirectUri("http://localhost:4200/main");
    params.setScope("email,public_profile");

    String url = operations.buildAuthenticateUrl(params);
    System.out.println("The URL is" + url);
    JSONObject object = new JSONObject();
    try {
      object.put("url", url);
    } catch (JSONException e) {
      e.printStackTrace();
    }
		return ResponseEntity.ok().body(object.toString());

	}

	@GetMapping(value = "/forwardLogin")
	public ResponseEntity<User> prodducer(@RequestParam("code") String authorizationCode) {
		OAuth2Operations operations = factory.getOAuthOperations();
		AccessGrant accessToken = operations.exchangeForAccess(authorizationCode, "http://localhost:4200/main",
				null);

		Connection<Facebook> connection = factory.createConnection(accessToken);
		Facebook facebook = connection.getApi();
		String[] fields = { "id", "email", "first_name", "last_name" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		return ResponseEntity.ok().body(userProfile);

	}

}
