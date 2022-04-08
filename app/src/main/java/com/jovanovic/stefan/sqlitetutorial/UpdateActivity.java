package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText cues_input, description_input, latitude_input,longitude_input;
    Button update_button, delete_button;

    String id, cues, description, latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        cues_input = findViewById(R.id.cues_input2);
        description_input = findViewById(R.id.description_input2);
        latitude_input = findViewById(R.id.latitude_input2);
        longitude_input = findViewById(R.id.longitude_input2);
        update_button = findViewById(R.id.update_button);

        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();



        //Set actionbar title after getAndSetIntentData method
  /*      ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(cues);
        }*/

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                cues = cues_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                latitude = latitude_input.getText().toString().trim();
                longitude = longitude_input.getText().toString().trim();

                myDB.updateData(id, cues,description,latitude,longitude);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });



    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("cues") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("latitude") && getIntent().hasExtra("longitude")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            cues = getIntent().getStringExtra("cues");
            description = getIntent().getStringExtra("description");
            latitude = getIntent().getStringExtra("latitude");
            longitude = getIntent().getStringExtra("longitude");

            //Setting Intent Data
            cues_input.setText(cues);
            description_input.setText(description);
            latitude_input.setText(latitude);
            longitude_input.setText(longitude);
           // Log.d("stev", cues+" "+description+" "+latitude+" "+longitude);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + cues + " ?");
        builder.setMessage("Are you sure you want to delete " + cues + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
