package com.example.councilusecase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button logout;
    private Button maps;
    private Button transporthub;
    private Button council;
    private Button jobs;
    private Button bin;
    private Button social;
    private Button report;
    private Button feedback;
    private Button news;


    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.LogOut);

        //assigning the buttons to a variables
        maps = (Button)findViewById(R.id.btnMaps);
        transporthub = (Button)findViewById(R.id.btnTransport);
        council = (Button)findViewById(R.id.btnCouncil);
        jobs = (Button)findViewById(R.id.btnJobs);
        bin = (Button)findViewById(R.id.btnBin);
        social = (Button)findViewById(R.id.btnSocial);
        report = (Button)findViewById(R.id.btnReport);
        feedback = (Button)findViewById(R.id.btnFeedback);
        news = (Button)findViewById(R.id.btnNews);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
            }
        });

        //once the user clicks on these they will be directed to another page
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, MapsActivity.class));
            }
        });

        transporthub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Transport_hub.class
                ));
            }
        });

        council.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Council_tax.class
                ));
            }
        });

        jobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Jobs_page.class
                ));
            }
        });

        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Bin_page.class
                ));
            }
        });

        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Social_Page.class
                ));
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Report_Page.class));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, Feedback_Page.class));
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, News_Page.class));
            }
        });


    }

    private void logout(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout){
                logout();
                return true;
            }
        return super.onOptionsItemSelected(item);
    }

}
