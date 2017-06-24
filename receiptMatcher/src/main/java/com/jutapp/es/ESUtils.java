package com.jutapp.es;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ESUtils {
	public static final Gson newGson() {
		return new GsonBuilder()
				//.registerTypeAdapter(ESShards.class, new ESShardsDeserializer())
				.create();
	}

	public static String escapeForJson(String text) {
		Gson gson = new Gson();
		String t = gson.toJson(text);
		return t.substring(1, t.length() - 1);
	}

}
