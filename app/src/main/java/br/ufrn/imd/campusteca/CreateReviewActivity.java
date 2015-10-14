package br.ufrn.imd.campusteca;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateReviewActivity extends AppCompatActivity {

    private EditText reviewEditText;
    private Button sendReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reviewEditText = (EditText) findViewById(R.id.reviewEditText);

        sendReviewButton = (Button) findViewById(R.id.sendReviewButton);
        sendReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = reviewEditText.getText().toString();
                Intent intent = new Intent(CreateReviewActivity.this, ArchiveActivity.class);
                intent.putExtra("EXTRA_REVIEW", review);
                startActivity(intent);
            }
        });
    }

}
