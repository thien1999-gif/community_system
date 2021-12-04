package edu.fa.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
// configuration json for map
public class JsonReader {
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		String API = "https://api.mapbox.com/geocoding/v5/mapbox.places/" +URLEncoder.encode(url, "UTF-8")+".json?access_token=pk.eyJ1IjoiaHVuZ3Bwc2UxMzA0MjIiLCJhIjoiY2tjcGlxYXQ4MDZ2azJzcGc2bXR5d3gzcSJ9.-4f_JS5bwn7X5xC1-hBlgQ";

	    InputStream is = new URL(API).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
}
