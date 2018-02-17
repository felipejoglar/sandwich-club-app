package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject name = jsonSandwich.getJSONObject("name");

            sandwich.setMainName(name.getString("mainName"));
            sandwich.setAlsoKnownAs(getListFromJsonArray(name.getJSONArray("alsoKnownAs")));
            sandwich.setPlaceOfOrigin(jsonSandwich.getString("placeOfOrigin"));
            sandwich.setDescription(jsonSandwich.getString("description"));
            sandwich.setImage(jsonSandwich.getString("image"));
            sandwich.setIngredients(getListFromJsonArray(jsonSandwich.getJSONArray("ingredients")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> getListFromJsonArray(JSONArray jsonArray) throws JSONException {

        List<String> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.getString(i));
        }

        return result;
    }

}
