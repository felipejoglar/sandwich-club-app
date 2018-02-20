package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_MAIN_NAME_KEY = "mainName";
    private static final String JSON_ALSO_KNOWN_KEY = "alsoKnownAs";
    private static final String JSON_PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_KEY = "description";
    private static final String JSON_IMAGE_KEY = "image";
    private static final String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject name = jsonSandwich.getJSONObject(JSON_NAME_KEY);

            sandwich.setMainName(name.optString(JSON_MAIN_NAME_KEY));
            sandwich.setAlsoKnownAs(getListFromJsonArray(name.getJSONArray(JSON_ALSO_KNOWN_KEY)));
            sandwich.setPlaceOfOrigin(jsonSandwich.optString(JSON_PLACE_OF_ORIGIN_KEY));
            sandwich.setDescription(jsonSandwich.optString(JSON_DESCRIPTION_KEY));
            sandwich.setImage(jsonSandwich.optString(JSON_IMAGE_KEY));
            sandwich.setIngredients(getListFromJsonArray(jsonSandwich.getJSONArray(JSON_INGREDIENTS_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> getListFromJsonArray(JSONArray jsonArray) throws JSONException {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.optString(i));
        }

        return result;
    }

}
