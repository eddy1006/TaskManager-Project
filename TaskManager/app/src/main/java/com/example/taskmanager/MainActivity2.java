package com.example.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
    TextView name;
    Button sign_out;
    RecyclerView recyclerView;
    FloatingActionButton add;
    public static final String Extra = "com.example.everythingin10.extra.pic";
    public static final String Extra2 = "com.example.everythingin10.extra.pic2";
    public static final String Extra3 = "com.example.everythingin10.extra.pic3";
    public static final String Extra4 = "com.example.everythingin10.extra.pic4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        name = findViewById(R.id.textView2);
        sign_out = findViewById(R.id.button);
        add = findViewById(R.id.add);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            String txt = getString(R.string.wlcm);
            name.setText(txt +" "+personName);
        }
        sign_out.setOnClickListener((view)->{signOut();});
        myDbHandler db = new myDbHandler(MainActivity2.this,"Tasks",null,1);
        List<mytasks> tasklist  =  db.getAllTasks();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter customadapter = new CustomAdapter(tasklist,this);
        recyclerView.setAdapter(customadapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity2.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity2.this, MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
