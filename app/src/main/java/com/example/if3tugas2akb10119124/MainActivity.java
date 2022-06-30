 package com.example.if3tugas2akb10119124;

 import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import if3tugas2akb10119124.R;
import if3tugas2akb10119124.databinding.ActivityMainBinding;

 //10119124, Primarazaq Noorshalih Putra Hilmana, IF-3
 public class MainActivity extends AppCompatActivity {
     ActivityMainBinding binding;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());


         replaceFragment(new HomeFragment());

         binding.bottomNavigationView.setOnItemSelectedListener(item ->{

             switch (item.getItemId()){
                 case R.id.home:
                     replaceFragment(new HomeFragment());
                     break;

                 case R.id.profile:
                     replaceFragment(new ProfileFragment());
                     break;

                 case R.id.info:
                     replaceFragment(new InfoFragment());
                     break;

             }

             return true;
         });
     }

     private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

}