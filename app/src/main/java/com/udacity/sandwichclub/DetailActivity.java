package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mAlsoKnownAsTv, mPlaceOfOriginTv, mDescriptionTv, mIngredientsTv, labelOrigin,labelAlsoKnowas;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        mAlsoKnownAsTv = findViewById(R.id.also_known_tv);
        mPlaceOfOriginTv = findViewById(R.id.origin_tv);
        mIngredientsTv = findViewById(R.id.ingredients_tv);
        mDescriptionTv = findViewById(R.id.description_tv);
        labelOrigin = findViewById(R.id.label_origin);
        labelAlsoKnowas = findViewById(R.id.label_also_known_as);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            closeOnError();
            return;
        }
        if (position == 0){
            labelOrigin.setVisibility(View.INVISIBLE);
            labelAlsoKnowas.setVisibility(View.INVISIBLE);
        }
        if (position == 6){
            labelAlsoKnowas.setVisibility(View.INVISIBLE);
        }
        if (position == 2){
            labelAlsoKnowas.setVisibility(View.INVISIBLE);
        }
        if (position == 8){
            labelAlsoKnowas.setVisibility(View.INVISIBLE);
        }
        populateUI();
        Picasso.with(this).load(sandwich.getImage()).into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        String alsoKnownAs = sandwich.getAlsoKnownAs().toString();
        mAlsoKnownAsTv.setText(alsoKnownAs.substring(1, alsoKnownAs.length() - 1));

        mPlaceOfOriginTv.setText(sandwich.getPlaceOfOrigin());

        String ingredients = sandwich.getIngredients().toString();
        mIngredientsTv.setText(ingredients.substring(1, ingredients.length() - 1));
        mDescriptionTv.setText(sandwich.getDescription());
    }
}


