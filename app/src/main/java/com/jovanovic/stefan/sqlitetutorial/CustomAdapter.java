package com.jovanovic.stefan.sqlitetutorial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList _id, cues1, description1, latitude1,longitude1;
   //int position;

    CustomAdapter(Activity activity, Context context, ArrayList _id, ArrayList cues1,ArrayList description1, ArrayList latitude1,ArrayList longitude1
                  ){
        this.activity = activity;
        this.context = context;
        this._id = _id;
        this.cues1 = cues1;
        this.description1 = description1;
        this.latitude1 = latitude1;
        this.longitude1 = longitude1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       // this.position=position;

        holder.id_txt.setText(String.valueOf(_id.get(position)));
        holder.cues_text.setText(String.valueOf(cues1.get(position)));
        holder.description_txt.setText(String.valueOf(description1.get(position)));
        holder.latitude_txt.setText(String.valueOf(latitude1.get(position)));
        holder.longitude_txt.setText(String.valueOf(longitude1.get(position)));

        //Recyclerview onClickListener

                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, UpdateActivity.class);
                        intent.putExtra("id", String.valueOf(_id.get(position)));
                        intent.putExtra("cues", String.valueOf(cues1.get(position)));
                        intent.putExtra("description", String.valueOf(description1.get(position)));
                        intent.putExtra("latitude", String.valueOf(latitude1.get(position)));
                        intent.putExtra("longitude", String.valueOf(longitude1.get(position)));
                        activity.startActivityForResult(intent,1);


                    }
                });

    }

    @Override
    public int getItemCount() {
        return _id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, cues_text,description_txt, latitude_txt, longitude_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            cues_text = itemView.findViewById(R.id.cues_txt);
            description_txt = itemView.findViewById(R.id.description_txt);
            latitude_txt = itemView.findViewById(R.id.latitude_txt);
           longitude_txt = itemView.findViewById(R.id.longitude_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
