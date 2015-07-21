package com.gdgssu.android_deviewsched.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdgssu.android_deviewsched.R;

public class MainActivity extends AppCompatActivity implements DeviewFragment.OnFragmentInteractionListener {

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
                        showAllSche(getResources().getText(R.string.all_schedule));
                        break;
                    case R.id.nav_my_schedule:
                        showMySche(getResources().getText(R.string.my_schedule));

                        break;
                    case R.id.nav_find_friends:
                        showFindFriends(getResources().getText(R.string.find_friends));

                        break;
                    case R.id.nav_deview_story:
                        showDeviewStory(getResources().getText(R.string.deview_story));

                        break;
                    case R.id.nav_setting:
                        //showSetting(getResources().getText(R.string.setting));

                        break;
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void showHome(){
        Fragment homeFragment = HomeFragment.newInstance(getText(R.string.app_name));
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStackImmediate();
        fragmentTransaction.replace(R.id.content_container, homeFragment).commit();

        mDrawerLayout.closeDrawers();
    }

    public void showAllSche(CharSequence title){
        Fragment allScheFragment = AllScheFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_container, allScheFragment);
        fragmentTransaction.addToBackStack(null).commit();

        mDrawerLayout.closeDrawers();
    }

    public void showMySche(CharSequence title){
        Fragment myScheFragment = AllScheFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_container, myScheFragment);
        fragmentTransaction.addToBackStack(null).commit();

        mDrawerLayout.closeDrawers();
    }

    public void showFindFriends(CharSequence title){
        Fragment findFriendsFragment = AllScheFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_container, findFriendsFragment);
        fragmentTransaction.addToBackStack(null).commit();

        mDrawerLayout.closeDrawers();
    }

    public void showDeviewStory(CharSequence title){
        Fragment deviewStoryFragment = AllScheFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_container, deviewStoryFragment);
        fragmentTransaction.addToBackStack(null).commit();

        mDrawerLayout.closeDrawers();
    }

    public void showSetting(){

        mDrawerLayout.closeDrawers();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        showHome();
    }
}
