package com.example.lobleradam.touristapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";

    public static String name;
    public static String country;
    public static String city;
    public static String description;
    public static String category;

    private TextView nameText;
    private TextView descriptionText;
    private TextView countryText;
    private TextView cityText;
    private ImageView categoryImage;

    private Spinner categorySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /*
        name = getIntent().getStringExtra(name);
        country = getIntent().getStringExtra(country);
        city = getIntent().getStringExtra(city);
        description = getIntent().getStringExtra(description);
        category = getIntent().getStringExtra(category);
        */

        nameText = findViewById(R.id.ItemNameText);
        descriptionText =findViewById(R.id.ItemDescriptionText);
        cityText =findViewById(R.id.ItemCityText);
        countryText = findViewById(R.id.ItemCountryText);
        categoryImage = findViewById(R.id.MonumentItemCategoryImageView);


        Intent intent= getIntent();

        Bundle extras = intent.getExtras();

        nameText.setText(extras.getString("EXTRA_NAME"));
        descriptionText.setText(extras.getString("EXTRA_DESCRIPTION"));
        cityText.setText(extras.getString("EXTRA_CITY"));
        countryText.setText(extras.getString("EXTRA_COUNTRY"));
        categoryImage.setImageResource(getImageResource(extras.getString("EXTRA_CATEGORY")));

        //findViewById(R.id.MonumentItemCategoryImageView);


        getSupportActionBar().setTitle(extras.getString("EXTRA_NAME"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Slidr.attach(this);
    }

    /*
    private MonumentItem getItem() {
        MonumentItem Item = new MonumentItem();
        Item.name = nameEditText.getText().toString();
        Item.description = descriptionEditText.getText().toString();
        Item.country = countryEditText.getText().toString();
        Item.city = cityEditText.getText().toString();
        Item.category = MonumentItem.Category.getByOrdinal(categorySpinner.getSelectedItemPosition());
        return Item;
    }
    */

    private @DrawableRes
    int getImageResource(String category) {
        @DrawableRes int ret;
        switch (category) {
            case "STATUE":
                ret = R.drawable.ic_statue;
                break;
            case "BUILDING":
                ret = R.drawable.ic_building;
                break;
            case "BRIDGE":
                ret = R.drawable.ic_bridge;
                break;
            case "LANDSCAPE":
                ret = R.drawable.ic_landscape;
                break;
            default:
                ret = 0;
        }
        return ret;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
