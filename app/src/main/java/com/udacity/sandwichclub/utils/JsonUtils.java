package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {




    public static Sandwich parseSandwichJson(String json){

        //Sandwich values
        String mainName;
        List<String> alsoKnownAs;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients;

        Sandwich sandwich = new Sandwich();

        if(json !=null){
            try{
                //Initialize JSON for individual item
                JSONObject sandwichJson = new JSONObject(json);

                //Create String references for JSON
                final String SANDWICH_NAME = "mainName";
                final String SANDWICH_ALTERNATIVE_NAMES = "alsoKnownAs";
                final String SANDWICH_ORIGIN="placeOfOrigin";
                final String SANDWICH_DESCRIPTION="description";
                final String SANDWICH_IMAGE="image";
                final String SANDWICH_INGREDIENTS="ingredients";

                JSONObject name = sandwichJson.getJSONObject("name");
                // 1. Parse out mainName
                mainName = name.getString(SANDWICH_NAME);

                // 2.Parse out alsoKnownAs
                JSONArray alternativeNames = name.getJSONArray(SANDWICH_ALTERNATIVE_NAMES);
                //Array List of also Known as
                alsoKnownAs = new ArrayList<>();

                int alternativeNameCount;
                for(alternativeNameCount = 0; alternativeNameCount < alternativeNames.length(); alternativeNameCount++){
                    alsoKnownAs.add(alternativeNames.getString(alternativeNameCount));
                }

                // 3. Parse out place of origin
                placeOfOrigin = sandwichJson.getString(SANDWICH_ORIGIN);

                // 4. Parse out description
                description = sandwichJson.getString(SANDWICH_DESCRIPTION);

                // 5. Parse out Image
                image = sandwichJson.getString(SANDWICH_IMAGE);

                // 6. Parse out Ingredients
                JSONArray ingredientsJsonArr = sandwichJson.getJSONArray(SANDWICH_INGREDIENTS);
                ingredients = new ArrayList<>();

                int ingredientCount;

                for(ingredientCount = 0; ingredientCount < ingredientsJsonArr.length(); ingredientCount++){
                    ingredients.add(ingredientsJsonArr.getString(ingredientCount));
                }

                // 7. Create Sandwich Object with parsed values
                sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

            }
            catch (JSONException jsonException){
                jsonException.printStackTrace();

            }

        }

        return sandwich;
    }
}
