package vn.edu.usth.weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {

    ViewPager viewPager;
    TextView pagination;
    int total = 4, count = 1;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        viewPager = findViewById(R.id.view_pager);
        pagination = findViewById(R.id.pagination);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new SliderAdapter(getSupportFragmentManager()));

        pagination.setText(count + "/" + total);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onPageSelected(int position) {
                count = 0;
                count = position + 1;
                pagination.setText(count + "/" + total);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(getClass().getName(), "Weather Activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getClass().getName(), "Weather Activity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(getClass().getName(), "Weather Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(getClass().getName(), "Weather Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getName(), "Weather Activity onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                Response.Listener<Bitmap> responseListener = response -> {
                    ImageView logo = findViewById(R.id.logo);
                    logo.setImageBitmap(response);
                };

                ImageRequest imageRequest = new ImageRequest(
                        "https://usth.edu.vn/uploads/logo_moi-eng.png", responseListener,
                        0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, null);

                requestQueue.add(imageRequest);
                return true;
            case R.id.settings:
                startActivity(new Intent(WeatherActivity.this, PrefActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}