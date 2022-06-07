 package com.example.if3utsakb10119124;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.if3utsakb10119124.databinding.ActivityMainBinding;

import java.util.List;
 //10119124, Primarazaq Noorshalih Putra Hilmana, IF-3
 public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Toolbar toolbar;
    RecyclerView recyclerView;
    NoteAdapter adapter;
    List<Note> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NoteDatabase db = new NoteDatabase(this);
        notes = db.getNotes();
        recyclerView = findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter(this,notes);
        recyclerView.setAdapter(adapter);


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

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.add_menu, menu);
        return true;
     }

     @Override
     public boolean onOptionsItemSelected(
             @NonNull MenuItem item) {
        if(item.getItemId() == R.id.add){
            Toast.makeText(this, "Add New Note", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,AddNote.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
     }

     private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

}