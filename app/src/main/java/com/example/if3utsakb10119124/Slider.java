package com.example.if3utsakb10119124;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
//10119124, Primarazaq Noorshalih Putra Hilmana, IF-3
public class Slider extends AppCompatActivity {
    ViewPager viewPager;
    Button btnNext;
    int[] layouts;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        viewPager = findViewById(R.id.pager);
        btnNext = findViewById(R.id.nextBtn);
        layouts = new int[] {
                R.layout.slider1,
                R.layout.slider2,
                R.layout.slider3,
                R.layout.slider4
        };

        adapter = new Adapter(this, layouts);
        viewPager.setAdapter(adapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            Handler h = new Handler();
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() + 1 < layouts.length){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                } else {
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(Slider.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }, 2000);
                }
            }
        });

        viewPager.addOnPageChangeListener(viewPagerChangeListener);
    }

    ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int il) {

        }

        @Override
        public void onPageSelected(int i) {
            if(i == layouts.length - 1){
                btnNext.setText("Continue");
            } else {
                btnNext.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}