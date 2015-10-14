package br.ufrn.imd.campusteca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ArchiveActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArchiveActivity.this, CreateReviewActivity.class);
                startActivity(intent);
            }
        });

        String review = null;
        if(getIntent().hasExtra("EXTRA_REVIEW")){
            review = getIntent().getStringExtra("EXTRA_REVIEW");
        }

        textView = (TextView) findViewById(R.id.textView2);
        textView.setText(review);
    }

}
