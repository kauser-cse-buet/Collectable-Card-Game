package com.example.homework4_mahmmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.NavigableMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Player player;
    private List<Card> cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
//        getLayoutInflater().inflate(R.layout.fragment_cards, contentFrameLayout);



//        Fragment fragment = new InboxFragment();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.content_frame, fragment);
//        ft.commit();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawyer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this,
//                drawer,
//                toolbar,
//                R.string.open,
//                R.string.close
//        );
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        addNavigationDrawer(this, this);




        try {
            CustomDatabaseHelper helper = new CustomDatabaseHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();
            player = helper.getPlayerByName(db, Player.players[0].getName());
            cardsList = helper.getCards(db);

            FragmentManager fragmentManager = getSupportFragmentManager();
            CardsFragment cardsFragment = new CardsFragment(cardsList);

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.content_frame, cardsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        catch (SQLException e){
            Toast.makeText(this, "Database not available", Toast.LENGTH_SHORT).show();
            Log.d("DB N/A", e.getStackTrace().toString());
        }
    }

    public void addNavigationDrawer(NavigationView.OnNavigationItemSelectedListener listener, Activity activity){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawyer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity,
                drawer,
                toolbar,
                R.string.open,
                R.string.close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(listener);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id){
            case R.id.nav_play:
                fragment = new PlayFragment(player);
                break;


            case R.id.nav_player_stats:
                fragment = new PlayerStatsFragment(player);
                break;

            case R.id.nav_purchase_packs:
                fragment = new PurchasePacksFragment(player, cardsList);
                break;

            case R.id.nav_open_packs:
                fragment = new OpenPacksFragment(player);
                break;
            case R.id.nav_browse_collection:
                fragment = new BrowseCollectionFragment(player);
                break;
        }



//        startActivity(fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        DrawerLayout drawer = findViewById(R.id.drawyer_layout);

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }


        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawyer_layout);

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }


    }
}
