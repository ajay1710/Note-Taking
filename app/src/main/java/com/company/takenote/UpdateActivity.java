package com.company.takenote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText title,description;
    Button save, cancel;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setTitle("Edit Note");


        title=findViewById(R.id.editTextTitleUpdate);
        description=findViewById(R.id.editTextDescriptionUpdate);
        save=findViewById(R.id.buttonSaveUpdate);
        cancel=findViewById(R.id.buttonCancelUpdate);

        getData();


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nothing Update",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
            }
        });
    }
    private void updateNote() {
        String titleLast=title.getText().toString();
        String descriptionLast=description.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("titleLast",titleLast);
        intent.putExtra("descriptionLast",descriptionLast);
        if(noteId!=-1){
            intent.putExtra("noteId",noteId);
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public void getData(){
        Intent i=getIntent();
         noteId=i.getIntExtra("id",-1);
       String noteTitle= i.getStringExtra("title");
       String noteDescription=i.getStringExtra("description");

       title.setText(noteTitle);
       description.setText(noteDescription);


    }
}