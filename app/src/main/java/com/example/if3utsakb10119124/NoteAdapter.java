package com.example.if3utsakb10119124;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//10119124, Primarazaq Noorshalih Putra Hilmana, IF-3
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    NoteAdapter(Context context, List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_list_view, viewGroup, false);
        return new NoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewholder, int i) {
        String title = notes.get(i).getTitle();
        String date = notes.get(i).getDate();
        String time = notes.get(i).getTime();
        long id = notes.get(i).getID();
        Log.d("date on ", "Date on: "+date);

        viewholder.nTitle.setText(title);
        viewholder.nDate.setText(date);
        viewholder.nTime.setText(time);
        viewholder.nID.setText(String.valueOf(notes.get(i).getID()));
    }

    @Override
    public int getItemCount() {

        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nID,nTitle,nDate,nTime;

        public ViewHolder (@NonNull final View itemView){
            super(itemView);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);
            nID = itemView.findViewById(R.id.nID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Details.class);
                    i.putExtra("ID", notes.get(getAdapterPosition()).getID());
                    v.getContext().startActivity(i);
                }
            });

        }
    }
}

