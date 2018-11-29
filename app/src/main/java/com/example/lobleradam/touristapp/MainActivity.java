package com.example.lobleradam.touristapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lobleradam.touristapp.adapter.MonumentAdapter;
import com.example.lobleradam.touristapp.data.MonumentItem;
import com.example.lobleradam.touristapp.data.MonumentItemDatabase;
import com.example.lobleradam.touristapp.fragments.NewItemDialogFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NewItemDialogFragment.NewItemDialogListener, MonumentAdapter.MonumentItemClickListener {

    private RecyclerView recyclerView;
    private MonumentAdapter adapter;

    private MonumentItemDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewItemDialogFragment().show(getSupportFragmentManager(), NewItemDialogFragment.TAG);
            }
        });

        database = Room.databaseBuilder(
                getApplicationContext(),
                MonumentItemDatabase.class,
                "monument-list"
        ).build();

        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.MainRecyclerView);
        adapter = new MonumentAdapter(this);
        loadItemsInBackground();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<MonumentItem>>() {

            @Override
            protected List<MonumentItem> doInBackground(Void... voids) {
                return database.MonumentItemDataAccess().getAll();
            }

            @Override
            protected void onPostExecute(List<MonumentItem> shoppingItems) {
                adapter.update(shoppingItems);
            }
        }.execute();
    }


    @Override
    public void onItemCreated(final MonumentItem newItem) {
        new AsyncTask<Void, Void, MonumentItem>() {

            @Override
            protected MonumentItem doInBackground(Void... voids) {
                newItem.id = database.MonumentItemDataAccess().insert(newItem);
                return newItem;
            }

            @Override
            protected void onPostExecute(MonumentItem Item) {
                adapter.addItem(Item);
            }
        }.execute();
    }

    public void removeItem(final MonumentItem item) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... voids) {
                database.MonumentItemDataAccess().deleteItem(item);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccessful) {
                Log.d("MainActivity", "ShoppingItem delete was successful");
            }
        }.execute();
    }

    public void onItemSelected(MonumentItem item) {
        Intent showDetailsIntent = new Intent();
        showDetailsIntent.setClass(MainActivity.this, DetailsActivity.class);

        Bundle extras = new Bundle();

        extras.putString("EXTRA_NAME", item.name);
        extras.putString("EXTRA_COUNTRY", item.country);
        extras.putString("EXTRA_CITY", item.city);
        //extras.putString(DetailsActivity.category, item.category.toString());
        extras.putString("EXTRA_DESCRIPTION", item.description);
        extras.putString("EXTRA_CATEGORY", item.category.name());
        showDetailsIntent.putExtras(extras);
        startActivity(showDetailsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
