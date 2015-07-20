package com.gdgssu.android_deviewsched;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;

    private ImageView avatarImage;
    private TextView nameText;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        initNavigationView();
        showHome();
    }

    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null)
            setupDrawerContent(navigationView);

        avatarImage = (ImageView) findViewById(R.id.profile_image);
        nameText = (TextView) findViewById(R.id.profile_name_text);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        showHome();

                        break;
                    case R.id.nav_all_schedule:
                        break;
                    case R.id.nav_my_schedule:
                        break;
                    case R.id.nav_find_friends:
                        break;
                    case R.id.nav_deview_story:
                        break;
                    case R.id.nav_setting:
                        break;
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void showHome(){
        openOptionsMenu();
        Fragment homeFragment = HomeFragment.newInstance(getText(R.string.app_name));
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStackImmediate();
        fragmentTransaction.replace(R.id.content_container, homeFragment).commit();

        mDrawerLayout.closeDrawers();

    }

    public void showAllSche(){

    }

    public void showMySche(){

    }

    public void showFindFriends(){

    }

    public void showDeviewStory(){

    }

    public void showSetting(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
