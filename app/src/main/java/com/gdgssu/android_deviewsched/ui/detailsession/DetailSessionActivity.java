package com.gdgssu.android_deviewsched.ui.detailsession;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.gdgssu.android_deviewsched.R;

import java.util.ArrayList;

public class DetailSessionActivity extends AppCompatActivity {

    private ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_session);

        /**
         * Todo 이전 세션리스트에서 Intent를 이용해 데이터를 가져와야한다.
         */

        //Intent intent = getIntent();
        //Session sessionInfo = (Session)intent.getSerializableExtra("KEY")

        // CoolapsingView를 테스트하기위한 리스트뷰 더미데이터
        arrayList.add("Item1");
        arrayList.add("Item2");
        arrayList.add("Item3");
        arrayList.add("Item4");
        arrayList.add("Item1");
        arrayList.add("Item2");
        arrayList.add("Item3");
        arrayList.add("Item4");
        arrayList.add("Item1");
        arrayList.add("Item2");
        arrayList.add("Item3");
        arrayList.add("Item4");
        arrayList.add("Item1");
        arrayList.add("Item2");
        arrayList.add("Item3");
        arrayList.add("Item4");

        initView();
    }

    private void initView() {

        initToolbar();
        loadBackdropImage();
        initListView();

    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Session1");
    }

    private void loadBackdropImage() {
        //임시 더미이미지
        ImageView backdropImage = (ImageView) findViewById(R.id.backdrop);
        Glide.with(getApplicationContext()).load("http://insanehong.kr/post/deview2013/@img/keynote.jpg")
                .centerCrop().into(backdropImage);
    }

    private void initListView() {
        ListView listview = (ListView) findViewById(R.id.activity_detail_session_list);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            listview.setNestedScrollingEnabled(true);
        }
        listview.setAdapter(new DetailSessionAdapter(getApplicationContext(), arrayList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_session, menu);
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
