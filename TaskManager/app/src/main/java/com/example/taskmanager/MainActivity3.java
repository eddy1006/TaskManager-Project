package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity3 extends AppCompatActivity {
    EditText title;
    EditText desc;
    FloatingActionButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        title = findViewById(R.id.edit1);
        desc = findViewById(R.id.edit2);
        btn = findViewById(R.id.floatingActionButton);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            String tt = bundle.getString(MainActivity2.Extra);
            String des = bundle.getString(MainActivity2.Extra2);
            title.setText(tt);
            desc.setText(des);
        }
        myDbHandler db = new myDbHandler(MainActivity3.this,"Tasks",null,1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mytasks task = new mytasks();
                if(desc.getText().toString().equals("") && title.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),
                            "Add something",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                task.setDescription(desc.getText().toString());
                task.setTitle(title.getText().toString());
                if(bundle!=null){
                    task.setId(bundle.getInt(MainActivity2.Extra3));
                    int affected = db.updateTask(task);
                }
                else{
                db.addTask(task);}
                Toast.makeText(getApplicationContext(),
                        "Successfully added",
                        Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(intent);}
            }
        });
    }
}