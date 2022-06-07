package com.example.if3utsakb10119124;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//10119124, Primarazaq Noorshalih Putra Hilmana, IF-3
public class Details extends AppCompatActivity {
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        id = i.getLongExtra("ID",0);
        NoteDatabase db = new NoteDatabase(this);
        Note note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        TextView details = findViewById(R.id.noteDesc);
        details.setText(note.getContent());
        details.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoteDatabase db = new NoteDatabase(getApplicationContext());
                db.deleteNote(id);
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                goToMain();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.editNote){
            Intent i = new Intent(this,Edit.class);
            i.putExtra("ID",id);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void goToMain() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }


}