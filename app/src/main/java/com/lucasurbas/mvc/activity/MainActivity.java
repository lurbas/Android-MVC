package com.lucasurbas.mvc.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.lucasurbas.mvc.R;
import com.lucasurbas.mvc.adapter.MainPagerAdapter;
import com.lucasurbas.mvc.fragment.FavoritesFragment;
import com.lucasurbas.mvc.fragment.HomeFragment;


public class MainActivity extends ActionBarActivity {

    private ViewPager viewPager;
    private PagerSlidingTabStrip tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View v = findViewById(R.id.viewPager);
        if(v != null){
            viewPager = (ViewPager) v;
            tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
            viewPager.setAdapter(new MainPagerAdapter(this, getSupportFragmentManager()));
            tabs.setViewPager(viewPager);
        }

        if (savedInstanceState == null && viewPager == null) {
            HomeFragment homeFragment = HomeFragment.newInstance();
            FavoritesFragment favoriteFragment = FavoritesFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.left_container, homeFragment).add(R.id.right_container, favoriteFragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
